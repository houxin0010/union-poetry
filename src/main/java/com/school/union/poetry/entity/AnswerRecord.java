package com.school.union.poetry.entity;

import com.school.union.poetry.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 答题记录(AnswerRecord)表实体类
 *
 * @author makejava
 * @since 2019-04-05 19:13:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AnswerRecord extends BaseEntity {
    private static final long serialVersionUID = -98665542703938810L;
    /**
     * 试卷ID
     */
    private Long questionPaperId;
    /**
     * 题目id
     */
    private Long questionId;
    /**
     * 题号
     */
    private Integer questionNo;
    /**
     * 题目类型，选择题：SINGLE_SEL，填空题：COMPLETION，选择题：TRUE_OR_FALSE，选字填空：BANKED_CLOZE
     */
    private String questionType;
    /**
     * 是否完成
     */
    private Boolean isAccomplish;
    /**
     * 是否正确
     */
    private Boolean isCorrect;
    /**
     * 用户答案
     */
    private String userAnswer;
    /**
     * 创建时间
     */
    private Date createTime;
    
     private String answer;
}