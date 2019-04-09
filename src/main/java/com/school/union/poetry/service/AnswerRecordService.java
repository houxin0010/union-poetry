package com.school.union.poetry.service;

import com.school.union.poetry.constant.QuestionType;
import com.school.union.poetry.entity.AnswerRecord;

public interface AnswerRecordService {

    void createAnswerRecord(AnswerRecord answerRecord);

    Long randomQuestionId(Long questionPaperId, QuestionType questionType);

    AnswerRecord getNewestAnswerRecord(Long questionPaperId);

    AnswerRecord getAnswerRecord(Long questionPaperId, Integer questionNumber);

}
