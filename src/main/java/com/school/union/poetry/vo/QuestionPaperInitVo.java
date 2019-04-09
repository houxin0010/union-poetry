package com.school.union.poetry.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionPaperInitVo implements Serializable {

    private Long questionPaperId;
    private Integer questionNo;
    private String firstQuestionType;
}
