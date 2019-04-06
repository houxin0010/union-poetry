package com.school.union.poetry.controller;

import com.school.union.poetry.service.QuestionPaperService;
import com.school.union.poetry.vo.base.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questionPaper")
public class QuestionPaperController {

    @Autowired
    private QuestionPaperService questionPaperService;

    @RequestMapping("/init")
    public ResultVo<Long> questionPaperInit() {
        String openId = "houxin";
        return ResultVo.success(questionPaperService.createQuestionPaper(openId));
    }
}