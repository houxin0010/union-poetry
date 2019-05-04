package com.school.union.poetry.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.union.poetry.entity.AnswerRecord;
import com.school.union.poetry.service.AnswerRecordService;
import com.school.union.poetry.vo.GetAnswerRecordVo;
import com.school.union.poetry.vo.param.GetAnswerRecordParam;
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
@Controller
@RequestMapping("/answerRecord")
public class AnswerRecordController {

    @Autowired
    private AnswerRecordService answerRecordService;

    @RequestMapping("/index")
    public String index(GetAnswerRecordParam getAnswerRecordParam, Model model) {
        Page<AnswerRecord> page = new Page<>(getAnswerRecordParam.getPage(), getAnswerRecordParam.getSize());
        IPage<GetAnswerRecordVo> getAnswerRecordVoIPage = answerRecordService.queryAnswerRecord(page, getAnswerRecordParam);
        model.addAttribute("answerRecordResult", getAnswerRecordVoIPage);
        return "answerRecord";
    }

    @PostMapping("/getAnswerRecord")
    public String getAnswerRecord(GetAnswerRecordParam getAnswerRecordParam, Model model) {
        Page<AnswerRecord> page = new Page<>(getAnswerRecordParam.getPage(), getAnswerRecordParam.getSize());
        IPage<GetAnswerRecordVo> getAnswerRecordVoIPage = answerRecordService.queryAnswerRecord(page, getAnswerRecordParam);
        model.addAttribute("answerRecordResult", getAnswerRecordVoIPage);
        return "answerRecord::answerRecordList";
    }

}
