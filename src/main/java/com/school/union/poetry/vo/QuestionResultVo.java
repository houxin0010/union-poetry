package com.school.union.poetry.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * QuestionResultVo
 *
 * @author houxin
 * @date 2019/4/5
 */
@Data
public class QuestionResultVo implements Serializable {
    private static final long serialVersionUID = 6036469335670880036L;

    /**
     * 题号
     */
    private Integer questionNo;
    /**
     * 题目
     */
    private String question;
    /**
     * 答案
     */
    private String answer;
    /**
     * 选项
     */
    private List<String> options;
    /**
     * 题目类型，选择题：SINGLE_SEL，填空题：COMPLETION，选择题：TRUE_OR_FALSE，选字填空：BANKED_CLOZE
     */
    private String questionType;
    /**
     * 题目数量
     */
    private Integer questionTotal;
}
