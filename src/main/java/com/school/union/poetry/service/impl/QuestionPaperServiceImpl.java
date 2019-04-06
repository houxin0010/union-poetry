package com.school.union.poetry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.school.union.poetry.constant.QuestionType;
import com.school.union.poetry.entity.QuestionPaper;
import com.school.union.poetry.mapper.QuestionPaperMapper;
import com.school.union.poetry.service.QuestionPaperService;
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
            while (questionTotal > 0) {
                QuestionType questionType = EnumUtil.random(QuestionType.class);
                log.info("questionType = {}", questionType);
                switch (questionType) {
                    case COMPLETION:

                        break;
                    case SINGLE_SEL:
                        break;
                    case BANKED_CLOZE:
                        break;
                    default:
                }
                questionTotal--;
            }
            return questionPaper.getId();
        } else {
            return questionPaperCheck.getId();
        }
    }
}
