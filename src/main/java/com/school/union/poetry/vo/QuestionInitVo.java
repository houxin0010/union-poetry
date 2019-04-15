package com.school.union.poetry.vo;

import java.util.List;

import lombok.Data;

@Data
public class QuestionInitVo {

	String firstQuestionType;
	List<QuestionVo> questions;
}
