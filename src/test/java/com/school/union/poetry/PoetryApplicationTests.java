package com.school.union.poetry;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.union.poetry.entity.AnswerRecord;
import com.school.union.poetry.entity.QuestionPaper;
import com.school.union.poetry.mapper.QuestionPaperMapper;
import com.school.union.poetry.service.AnswerRecordService;
import com.school.union.poetry.service.QuestionPaperService;
import com.school.union.poetry.vo.GetAnswerRecordVo;
import com.school.union.poetry.vo.param.GetAnswerRecordParam;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PoetryApplicationTests {

    @Autowired
    private QuestionPaperService questionPaperService;

    @Autowired
    private QuestionPaperMapper questionPaperMapper;

    @Autowired
    private AnswerRecordService answerRecordService;

    @Test
    public void contextLoads() {

        GetAnswerRecordParam getAnswerRecordParam=new GetAnswerRecordParam();
        getAnswerRecordParam.setQuestionType("SINGLE_SEL");
        Page<AnswerRecord> page = new Page<>(1, 10);
        IPage<AnswerRecord> getAnswerRecordVoIPage = answerRecordService.queryAnswerRecord(page, getAnswerRecordParam);
        log.info("getAnswerRecordVoIPage = {}", JSON.toJSONString(getAnswerRecordVoIPage));
    }

}
