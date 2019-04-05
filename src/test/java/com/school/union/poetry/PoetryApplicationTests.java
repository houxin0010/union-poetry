package com.school.union.poetry;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.school.union.poetry.entity.AnswerRecord;
import com.school.union.poetry.mapper.AnswerRecordMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PoetryApplicationTests {

    private AnswerRecordMapper answerRecordMapper;

    @Test
    public void contextLoads() {

        answerRecordMapper.selectList(new QueryWrapper<>(new AnswerRecord()));
    }

}
