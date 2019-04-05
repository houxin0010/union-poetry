package com.school.union.poetry.service.impl;

import com.school.union.poetry.entity.QuestionPaper;
import com.school.union.poetry.mapper.QuestionPaperMapper;
import com.school.union.poetry.service.QuestionPaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    @Override
    public String createQuestionPaper(String openId) {

        QuestionPaper questionPaper = new QuestionPaper();
        questionPaper.setStatus(0);
        questionPaper.setScore(0);
        questionPaper.setCompletedNo(0);
        questionPaper.setQuestionTotal(10);
        questionPaper.setTotalPoints(100);
        questionPaper.setOpenId(openId);
        questionPaper.setCreateTime(new Date());
        questionPaperMapper.insert(questionPaper);

        return "success";
    }
}
