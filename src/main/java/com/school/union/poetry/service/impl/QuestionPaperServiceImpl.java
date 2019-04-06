package com.school.union.poetry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.school.union.poetry.constant.QuestionType;
import com.school.union.poetry.entity.AnswerRecord;
import com.school.union.poetry.entity.QuestionPaper;
import com.school.union.poetry.mapper.QuestionPaperMapper;
import com.school.union.poetry.service.*;
import com.school.union.poetry.util.EnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * QuestionPaperServiceImpl
 *
 * @author houxin
 * @date 2019/4/5
 */
@Slf4j
@Service
public class QuestionPaperServiceImpl implements QuestionPaperService {

    @Autowired
    private QuestionPaperMapper questionPaperMapper;

    @Autowired
    private SingleSelService singleSelService;
    @Autowired
    private BankedClozeService bankedClozeService;
    @Autowired
    private CompletionService completionService;
    @Autowired
    private AnswerRecordService answerRecordService;

    @Override
    public Long createQuestionPaper(String openId) {

        QuestionPaper questionPaper = new QuestionPaper();
        questionPaper.setStatus(0);
        questionPaper.setOpenId(openId);
        QuestionPaper questionPaperCheck = questionPaperMapper.selectOne(new QueryWrapper<>(questionPaper));
        if (questionPaperCheck == null) {
            int questionTotal = 10;
            questionPaper.setScore(0);
            questionPaper.setCompletedNo(0);
            questionPaper.setQuestionTotal(questionTotal);
            questionPaper.setTotalPoints(100);
            questionPaper.setCreateTime(new Date());
            questionPaperMapper.insert(questionPaper);

            for (int i = 1; i <= questionTotal; i++) {
                QuestionType questionType = EnumUtil.random(QuestionType.class);
                log.info("questionType = {}", questionType);
                AnswerRecord answerRecord = new AnswerRecord();
                answerRecord.setQuestionPaperId(questionPaper.getId());
                answerRecord.setQuestionNo(i);
                answerRecord.setQuestionType(questionType.toString());
                answerRecord.setIsAccomplish(false);
                answerRecord.setCreateTime(new Date());
                answerRecord.setQuestionId(answerRecordService.randomQuestionId(questionPaper.getId(), questionType));
                answerRecordService.createAnswerRecord(answerRecord);
            }
            return questionPaper.getId();
        } else {
            return questionPaperCheck.getId();
        }
    }
}
