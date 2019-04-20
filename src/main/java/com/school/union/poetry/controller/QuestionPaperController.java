package com.school.union.poetry.controller;

import com.school.union.poetry.vo.AnswerResultVo;
import com.school.union.poetry.vo.QuestionInitVo;
import com.school.union.poetry.vo.QuestionVo;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

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
    public ResultVo<QuestionInitVo> questionPaperInit(int grade) {
        return ResultVo.success(questionPaperService.createQuestionPaperNew(grade));
    }

    @RequestMapping("/getcode")
    public ResultVo<String> getcode(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx4440a5b57f80fb27&secret=ac9e5b022bfafe10b3feec9c44bc3703&js_code=" + code + "&grant_type=authorization_code";                                                               
        String json = HttpKit.get(url);
        log.info("getcode json = {}", json);
        JSONObject a = JSONObject.parseObject(json);
        String openid = a.getString("openid");
        return ResultVo.success(openid);
    }

   /* @RequestMapping("/getQuestion")
    public ResultVo<QuestionResultVo> getQuestion(Long questionPaperId) {
        return ResultVo.success(questionPaperService.getQuestionContent(questionPaperId));
    }*/
    
    @RequestMapping("/getQuestion")
    public ResultVo<QuestionResultVo> getQuestion(Long questionId,String questionType) {
        return ResultVo.success(questionPaperService.getQuestionContent(questionId,questionType));
    }

    @RequestMapping("/completeQuestion")
    public ResultVo<String> completeQuestion(Long questionPaperId, Boolean isCorrect) {
        return ResultVo.success(questionPaperService.completeQuestion(questionPaperId, isCorrect));
    }

    @RequestMapping("/getAnswerResult")
    public ResultVo<AnswerResultVo> getAnswerResult(Long questionPaperId, String openId) {
    	  log.info("获取答题结果参数{0},{1}", questionPaperId,openId);
    	  ResultVo<AnswerResultVo>  ret= ResultVo.success(questionPaperService.getScore(questionPaperId, openId));
    	  log.info("获取答题结果", JSONObject.toJSONString(ret));
    	  return ret;
    }
}
