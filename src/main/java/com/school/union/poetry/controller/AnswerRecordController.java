package com.school.union.poetry.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.union.poetry.entity.AnswerRecord;
import com.school.union.poetry.service.AnswerRecordService;
import com.school.union.poetry.vo.QuestionResultVo;
import com.school.union.poetry.vo.param.GetAnswerRecordParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * AnswerRecordController
 *
 * @author houxin
 * @date 2019/4/30
 */
@Slf4j
@Controller
@RequestMapping("/answerRecord")
public class AnswerRecordController {

    @Autowired
    private AnswerRecordService answerRecordService;

    @RequestMapping("/index")
    public String index(GetAnswerRecordParam getAnswerRecordParam, Model model) {
        log.info("getAnswerRecordParam = {}", JSON.toJSONString(getAnswerRecordParam));
        Page page = new Page(getAnswerRecordParam.getPage(), getAnswerRecordParam.getSize());
        IPage<AnswerRecord> answerRecordIPage = answerRecordService.queryAnswerRecord(page, getAnswerRecordParam);
        model.addAttribute("answerRecordResult", answerRecordIPage);
        return "answerRecord";
    }

    @PostMapping("/getAnswerRecord")
    public String getAnswerRecord(GetAnswerRecordParam getAnswerRecordParam, Model model) {
        Page page = new Page(getAnswerRecordParam.getPage(), getAnswerRecordParam.getSize());
        IPage<AnswerRecord> answerRecordIPage = answerRecordService.queryAnswerRecord(page, getAnswerRecordParam);
        model.addAttribute("answerRecordResult", answerRecordIPage);
        return "answerRecord::answerRecordList";
    }

    @GetMapping("/getQuestionDetail")
    public String getQuestionDetail(Long id, Model model) {
        QuestionResultVo questionResultVo = answerRecordService.queryQuestionDetail(id);
        model.addAttribute("questionDetailResult", questionResultVo);
        return "answerRecord::questionDetail";
    }

}
