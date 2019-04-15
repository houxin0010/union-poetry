package com.school.union.poetry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.union.poetry.constant.QuestionType;
import com.school.union.poetry.entity.AnswerRecord;
import com.school.union.poetry.mapper.AnswerRecordMapper;
import com.school.union.poetry.service.AnswerRecordService;
import com.school.union.poetry.service.BankedClozeService;
import com.school.union.poetry.service.CompletionService;
import com.school.union.poetry.service.SingleSelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class AnswerRecordServiceImpl extends ServiceImpl<AnswerRecordMapper, AnswerRecord>
        implements AnswerRecordService {

    @Autowired
    private AnswerRecordMapper answerRecordMapper;

    @Autowired
    private SingleSelService singleSelService;
    @Autowired
    private BankedClozeService bankedClozeService;
    @Autowired
    private CompletionService completionService;

    private static List<Long> completionIds = null;
    private static List<Long> singleSelIds = null;
    private static List<Long> bankedClozeIds = null;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createAnswerRecord(AnswerRecord answerRecord) {
        answerRecordMapper.insert(answerRecord);
    }

    @Override
    public Long randomQuestionId(Long questionPaperId, QuestionType questionType) {
        Long questionId = null;
        questionId = getRandomQuestionId(questionType, questionId);
        AnswerRecord answerRecord = answerRecordMapper
                .selectOne(new LambdaQueryWrapper<AnswerRecord>().eq(AnswerRecord::getQuestionPaperId, questionPaperId)
                        .eq(AnswerRecord::getQuestionType, questionType).eq(AnswerRecord::getQuestionId, questionId));
        if (answerRecord == null) {
            return questionId;
        }
        return randomQuestionId(questionPaperId, questionType);
    }

    @Override
    public Long randomQuestionId(List<String> curQuestion, QuestionType questionType) {
        Long questionId = null;
        questionId = getRandomQuestionId(questionType, questionId);

        if (!curQuestion.contains(questionType + "-" + questionId.toString())) {
            curQuestion.add(questionType + "-" + questionId.toString());
            return questionId;
        }
        return randomQuestionId(curQuestion, questionType);
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

    private Long getRandomQuestionId(QuestionType questionType, Long questionId) {
        switch (questionType) {
            case COMPLETION:
                if (completionIds == null) {
                    completionIds = completionService.selectIds();
                }
                questionId = completionIds.get(new Random().nextInt(completionIds.size()));
                break;
            case SINGLE_SEL:
                if (singleSelIds == null) {
                    singleSelIds = singleSelService.selectIds();
                }
                questionId = singleSelIds.get(new Random().nextInt(singleSelIds.size()));
                break;
            case BANKED_CLOZE:
                if (bankedClozeIds == null) {
                    bankedClozeIds = bankedClozeService.selectIds();
                }
                questionId = bankedClozeIds.get(new Random().nextInt(bankedClozeIds.size()));
                break;
            default:
        }
        return questionId;
    }

}
