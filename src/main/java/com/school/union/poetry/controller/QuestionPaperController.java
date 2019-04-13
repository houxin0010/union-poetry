package com.school.union.poetry.controller;

import com.school.union.poetry.vo.AnswerResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.HttpKit;
import com.school.union.poetry.service.QuestionPaperService;
import com.school.union.poetry.vo.QuestionPaperInitVo;
import com.school.union.poetry.vo.QuestionResultVo;
import com.school.union.poetry.vo.base.ResultVo;

@Slf4j
@RestController
@RequestMapping("/questionPaper")
public class QuestionPaperController {

    @Autowired
    private QuestionPaperService questionPaperService;

    @RequestMapping("/init")
    public ResultVo<QuestionPaperInitVo> questionPaperInit(String openId) {
        return ResultVo.success(questionPaperService.createQuestionPaper(openId));
    }

    @RequestMapping("/getcode")
    public ResultVo<String> getcode(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx9d98970b60072e66&secret=57ae72018a581f2a15be8ce971036dc0&js_code=" + code + "&grant_type=authorization_code";                                                               
        String json = HttpKit.get(url);
        log.info("getcode json = {}", json);
        JSONObject a = JSONObject.parseObject(json);
        String openid = a.getString("openid");
        return ResultVo.success(openid);
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
    public ResultVo<AnswerResultVo> getAnswerResult(Long questionPaperId, String openId) {
        return ResultVo.success(questionPaperService.getScore(questionPaperId, openId));
    }
}
