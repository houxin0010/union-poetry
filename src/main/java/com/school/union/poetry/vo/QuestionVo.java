package com.school.union.poetry.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class QuestionVo implements Serializable  {
	   
	private static final long serialVersionUID = 1L;
		private Long questionId;
	    private Integer questionNo;
	    private String questionType;

}
