package com.school.union.poetry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.school.union.poetry.entity.AnswerRecord;
import com.school.union.poetry.entity.QuestionPaper;
import com.school.union.poetry.vo.AnswerResultVo;
import com.school.union.poetry.vo.QuestionInitVo;
import com.school.union.poetry.vo.QuestionResultVo;

/**
 * QuestionPaperService
 *
 * @author houxin
 * @date 2019/4/5
 */
public interface QuestionPaperService extends IService<QuestionPaper> {

 
    QuestionResultVo getQuestionContent(Long questionPaperId);
    QuestionResultVo getQuestionContent(Long questionId,String questionType);
 
    String completeQuestion(Long questionPaperId, Boolean isCorrect);

    AnswerResultVo getScore(Long questionPaperId, String openId);
    
 
    QuestionInitVo createQuestionPaperNew(int grade);
}
