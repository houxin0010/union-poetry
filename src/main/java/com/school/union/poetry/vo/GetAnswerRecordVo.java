package com.school.union.poetry.vo;

import com.school.union.poetry.entity.AnswerRecord;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GetAnswerRecordVo extends AnswerRecord {
    private static final long serialVersionUID = -3509820022371257529L;

//    /**
//     * 题目类型，选择题：SINGLE_SEL，填空题：COMPLETION，选择题：TRUE_OR_FALSE，选字填空：BANKED_CLOZE
//     */
//    private String questionType;
//    /**
//     * 是否正确
//     */
//    private Boolean isCorrect;
    /**
     * 题目
     */
    private String question;
    /**
     * 选项
     */
    private List<String> options;
    /**
     * 参考答案
     */
    private String answer;
//    /**
//     * 用户答案
//     */
//    private String userAnswer;
//    /**
//     * 创建时间
//     */
//    private Date createTime;
}
