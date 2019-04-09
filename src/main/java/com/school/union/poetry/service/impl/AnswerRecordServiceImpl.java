package com.school.union.poetry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

@Service
public class AnswerRecordServiceImpl implements AnswerRecordService {

    @Autowired
    private AnswerRecordMapper answerRecordMapper;

    @Autowired
    private SingleSelService singleSelService;
    @Autowired
    private BankedClozeService bankedClozeService;
    @Autowired
    private CompletionService completionService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createAnswerRecord(AnswerRecord answerRecord) {
        answerRecordMapper.insert(answerRecord);
    }

    @Override
    public Long randomQuestionId(Long questionPaperId, QuestionType questionType) {
        Long questionId = null;
        switch (questionType) {
            case COMPLETION:
                questionId = completionService.randomCompletion();
                break;
            case SINGLE_SEL:
                questionId = singleSelService.randomSingleSel();
                break;
            case BANKED_CLOZE:
                questionId = bankedClozeService.randomBankedCloze();
                break;
            default:
        }
        AnswerRecord answerRecord = answerRecordMapper.selectOne(new LambdaQueryWrapper<AnswerRecord>()
                .eq(AnswerRecord::getQuestionPaperId, questionPaperId)
                .eq(AnswerRecord::getQuestionType, questionType)
                .eq(AnswerRecord::getQuestionId, questionId));
        if (answerRecord == null) {
            return questionId;
        }
        return randomQuestionId(questionPaperId, questionType);
    }

    @Override
    public AnswerRecord getNewestAnswerRecord(Long questionPaperId) {
        List<AnswerRecord> answerRecords = answerRecordMapper.selectList(new LambdaQueryWrapper<AnswerRecord>()
                .eq(AnswerRecord::getQuestionPaperId, questionPaperId)
                .orderByAsc(AnswerRecord::getQuestionNo));
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

}
