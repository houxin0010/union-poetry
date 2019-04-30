package com.school.union.poetry.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.HttpKit;
import com.school.union.poetry.entity.AnswerRecord;
import com.school.union.poetry.service.AnswerRecordService;
import com.school.union.poetry.service.QuestionPaperService;
import com.school.union.poetry.vo.AnswerResultVo;
import com.school.union.poetry.vo.QuestionInitVo;
import com.school.union.poetry.vo.QuestionResultVo;
import com.school.union.poetry.vo.base.ResultVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/questionPaper")
public class QuestionPaperController {

    @Autowired
    private QuestionPaperService questionPaperService;
    
    @Autowired
    private AnswerRecordService answerRecordService;

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
    
    @RequestMapping("/record")
    public ResultVo<Boolean> record(Long qid,String qtype,String userAnswer,String answer) {
    	
    	AnswerRecord  answerResult=new AnswerRecord();
    	answerResult.setCreateTime(new Date());
    	answerResult.setIsCorrect(false);
    	answerResult.setQuestionId(qid);
    	answerResult.setQuestionType(qtype);
    	answerResult.setUserAnswer(userAnswer);
    	answerResult.setAnswer(answer);
        answerRecordService.createAnswerRecord(answerResult);
         return  ResultVo.success(true);
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
