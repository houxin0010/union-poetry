package com.school.union.poetry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.school.union.poetry.constant.QuestionType;
import com.school.union.poetry.entity.AnswerRecord;

public interface AnswerRecordService extends IService<AnswerRecord> {

    void createAnswerRecord(AnswerRecord answerRecord);

    Long randomQuestionId(Long questionPaperId, QuestionType questionType);

    AnswerRecord getNewestAnswerRecord(Long questionPaperId);

    AnswerRecord getAnswerRecord(Long questionPaperId, Integer questionNumber);

}
