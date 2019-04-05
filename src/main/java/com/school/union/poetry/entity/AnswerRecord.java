package com.school.union.poetry.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 答题记录(AnswerRecord)表实体类
 *
 * @author makejava
 * @since 2019-04-05 19:13:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AnswerRecord extends Model<AnswerRecord> {
    private static final long serialVersionUID = -98665542703938810L;
    private Long id;
    private Integer optimistic;
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
     * 创建时间
     */
    private Date createTime;
}