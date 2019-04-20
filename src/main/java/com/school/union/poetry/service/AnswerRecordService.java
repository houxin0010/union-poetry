package com.school.union.poetry.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.school.union.poetry.constant.QuestionType;
import com.school.union.poetry.entity.AnswerRecord;

public interface AnswerRecordService extends IService<AnswerRecord> {

    void createAnswerRecord(AnswerRecord answerRecord);

     Long randomQuestionId(List<String> curQuestion, QuestionType questionType,int grade);

    AnswerRecord getNewestAnswerRecord(Long questionPaperId);

    AnswerRecord getAnswerRecord(Long questionPaperId, Integer questionNumber);

}
