package com.school.union.poetry.controller;

import com.school.union.poetry.service.QuestionPaperService;
import com.school.union.poetry.vo.QuestionResultVo;
import com.school.union.poetry.vo.base.ResultVo;
import com.school.union.poetry.vo.QuestionPaperInitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questionPaper")
public class QuestionPaperController {

    @Autowired
    private QuestionPaperService questionPaperService;

    @RequestMapping("/init")
    public ResultVo<QuestionPaperInitVo> questionPaperInit() {
        String openId = "houxin";
        return ResultVo.success(questionPaperService.createQuestionPaper(openId));
    }

    @RequestMapping("/getQuestion")
    public ResultVo<QuestionResultVo> getQuestion(Long questionPaperId) {
        return ResultVo.success(questionPaperService.getQuestionContent(questionPaperId));
    }

    @RequestMapping("/completeQuestion")
    public ResultVo<String> completeQuestion(Long questionPaperId, Boolean isCorrect) {
        return ResultVo.success(questionPaperService.completeQuestion(questionPaperId, isCorrect));
    }

    @RequestMapping("/getAnswerResult")
    public ResultVo<?> getAnswerResult(Long questionPaperId) {

        return ResultVo.success(null);
    }
}