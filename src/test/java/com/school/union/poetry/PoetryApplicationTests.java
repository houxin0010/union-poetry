package com.school.union.poetry;

import com.school.union.poetry.entity.QuestionPaper;
import com.school.union.poetry.mapper.QuestionPaperMapper;
import com.school.union.poetry.service.QuestionPaperService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PoetryApplicationTests {

    @Autowired
    private QuestionPaperService questionPaperService;

    @Autowired
    private QuestionPaperMapper questionPaperMapper;

    @Test
    public void contextLoads() {
//        questionPaperService.createQuestionPaper("houxin");
        QuestionPaper questionPaper = questionPaperMapper.selectById(4);
        questionPaper.setScore(20);
        questionPaperMapper.updateById(questionPaper);
    }

}
