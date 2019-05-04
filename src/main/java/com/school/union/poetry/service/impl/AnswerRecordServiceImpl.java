package com.school.union.poetry.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.union.poetry.constant.QuestionType;
import com.school.union.poetry.entity.AnswerRecord;
import com.school.union.poetry.entity.BankedCloze;
import com.school.union.poetry.entity.Completion;
import com.school.union.poetry.entity.SingleSel;
import com.school.union.poetry.mapper.AnswerRecordMapper;
import com.school.union.poetry.mapper.BankedClozeMapper;
import com.school.union.poetry.mapper.CompletionMapper;
import com.school.union.poetry.mapper.SingleSelMapper;
import com.school.union.poetry.service.AnswerRecordService;
import com.school.union.poetry.service.BankedClozeService;
import com.school.union.poetry.service.CompletionService;
import com.school.union.poetry.service.SingleSelService;
import com.school.union.poetry.vo.GetAnswerRecordVo;
import com.school.union.poetry.vo.param.GetAnswerRecordParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@Transactional
public class AnswerRecordServiceImpl extends ServiceImpl<AnswerRecordMapper, AnswerRecord> implements AnswerRecordService {

    @Autowired
    private AnswerRecordMapper answerRecordMapper;
    @Autowired
    private CompletionMapper completionMapper;
    @Autowired
    private SingleSelMapper singleSelMapper;
    @Autowired
    private BankedClozeMapper bankedClozeMapper;

    @Autowired
    private SingleSelService singleSelService;
    @Autowired
    private BankedClozeService bankedClozeService;
    @Autowired
    private CompletionService completionService;

    private static List<Long> completionIds3 = null;
    private static List<Long> singleSelIds3 = null;
    private static List<Long> bankedClozeIds3 = null;

    private static List<Long> completionIds4 = null;
    private static List<Long> singleSelIds4 = null;
    private static List<Long> bankedClozeIds4 = null;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createAnswerRecord(AnswerRecord answerRecord) {
        answerRecordMapper.insert(answerRecord);
    }

    @Override
    public Long randomQuestionId(List<String> curQuestion, QuestionType questionType, int grade) {
        Long questionId = null;
        questionId = getRandomQuestionId(questionType, questionId, grade);
        log.info("question id = {}", questionId);
        if (!curQuestion.contains(questionType + "-" + questionId.toString())) {
            curQuestion.add(questionType + "-" + questionId.toString());
            return questionId;
        }
        return randomQuestionId(curQuestion, questionType, grade);
    }

    @Override
    public AnswerRecord getNewestAnswerRecord(Long questionPaperId) {
        List<AnswerRecord> answerRecords = answerRecordMapper
                .selectList(new LambdaQueryWrapper<AnswerRecord>().eq(AnswerRecord::getQuestionPaperId, questionPaperId)
                        .eq(AnswerRecord::getIsAccomplish, false).orderByAsc(AnswerRecord::getQuestionNo));
        if (answerRecords != null && !answerRecords.isEmpty()) {
            return answerRecords.stream().findFirst().get();
        }
        return null;
    }

    @Override
    public AnswerRecord getAnswerRecord(Long questionPaperId, Integer questionNumber) {
        AnswerRecord answerRecord = new AnswerRecord();
        answerRecord.setQuestionPaperId(Optional.ofNullable(questionPaperId).orElse(0L));
        answerRecord.setQuestionNo(Optional.ofNullable(questionNumber).orElse(0));
        return answerRecordMapper.selectOne(new QueryWrapper<>(answerRecord));
    }

    @Override
    public IPage<GetAnswerRecordVo> queryAnswerRecord(Page page, GetAnswerRecordParam getAnswerRecordParam) {
        log.info("page = {}", JSON.toJSONString(page));
        AnswerRecord answerRecord = new AnswerRecord();
        BeanUtils.copyProperties(getAnswerRecordParam, answerRecord);
        IPage<GetAnswerRecordVo> listIPage = answerRecordMapper.selectByParams(page, answerRecord, getAnswerRecordParam.getStartTime(), getAnswerRecordParam.getEndTime());
        List<GetAnswerRecordVo> answerRecordList = listIPage.getRecords();
        answerRecordList.forEach(record -> {
            if (QuestionType.COMPLETION.name().equals(record.getQuestionType())) {
                Completion completion = completionMapper.selectById(record.getQuestionId());
                record.setQuestion(completion.getQuestion());
                record.setAnswer(completion.getAnswer());
            } else if (QuestionType.SINGLE_SEL.name().equals(record.getQuestionType())) {
                SingleSel singleSel = singleSelMapper.selectById(record.getQuestionId());
                record.setQuestion(singleSel.getQuestion());
                record.setAnswer(singleSel.getAnswer());
                List<String> options = new ArrayList<>();
                options.add(singleSel.getChoiceA());
                options.add(singleSel.getChoiceB());
                options.add(singleSel.getChoiceC());
                record.setOptions(options);
            } else if (QuestionType.BANKED_CLOZE.name().equals(record.getQuestionType())) {
                BankedCloze bankedCloze = bankedClozeMapper.selectById(record.getQuestionId());
                record.setQuestion(bankedCloze.getQuestion());
                record.setAnswer(bankedCloze.getAnswer());
                List<String> options = Arrays.asList(bankedCloze.getOptions().split(","));
                record.setOptions(options);
            }
        });
        log.info("answerRecordList = {}", JSON.toJSONString(listIPage));
        return listIPage;
    }

    private Long getRandomQuestionId(QuestionType questionType, Long questionId, int grade) {
        switch (questionType) {
            case COMPLETION:
                if (grade == 3) {
                    if (completionIds3 == null) {
                        completionIds3 = completionService.selectIds(grade);
                    }
                    questionId = completionIds3.get(new Random().nextInt(completionIds3.size()));
                }
                if (grade == 4) {
                    if (completionIds4 == null) {
                        completionIds4 = completionService.selectIds(grade);
                    }
                    questionId = completionIds4.get(new Random().nextInt(completionIds4.size()));
                }

                break;
            case SINGLE_SEL:
                if (grade == 3) {
                    if (singleSelIds3 == null) {
                        singleSelIds3 = singleSelService.selectIds(grade);
                    }
                    questionId = singleSelIds3.get(new Random().nextInt(singleSelIds3.size()));
                }

                if (grade == 4) {
                    if (singleSelIds4 == null) {
                        singleSelIds4 = singleSelService.selectIds(grade);
                    }
                    questionId = singleSelIds4.get(new Random().nextInt(singleSelIds4.size()));
                }

                break;
            case BANKED_CLOZE:
                if (grade == 3) {
                    if (bankedClozeIds3 == null) {
                        bankedClozeIds3 = bankedClozeService.selectIds(grade);
                    }
                    questionId = bankedClozeIds3.get(new Random().nextInt(bankedClozeIds3.size()));
                }

                if (grade == 4) {
                    if (bankedClozeIds4 == null) {
                        bankedClozeIds4 = bankedClozeService.selectIds(grade);
                    }
                    questionId = bankedClozeIds4.get(new Random().nextInt(bankedClozeIds4.size()));
                }

                break;
            default:
        }
        return questionId;
    }

}
