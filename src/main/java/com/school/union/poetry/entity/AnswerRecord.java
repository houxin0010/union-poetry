package com.school.union.poetry.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 答题记录(AnswerRecord)表实体类
 *
 * @author makejava
 * @since 2019-04-05 19:12:05
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AnswerRecord extends Model<AnswerRecord> {
    private static final long serialVersionUID = 856296376653905624L;
    private Long id;
    private Integer optimistic;
    /**
     * 答题记录ID
     */
    private Long answerRecordId;
    /**
     * 题目id
     */
    private Long questionId;
    /**
     * 题目类型，选择题：SINGLE_SEL，填空题：COMPLETION，选择题：TRUE_OR_FALSE，选字填空：BANKED_CLOZE
     */
    private String questionType;
    /**
     * 是否正确
     */
    private Boolean isCorrect;
}