package com.school.union.poetry.vo.param;

import com.school.union.poetry.vo.base.BaseParam;
import lombok.Data;

import java.util.Date;

@Data
public class GetAnswerRecordParam extends BaseParam {

    private static final long serialVersionUID = -4063078378446957085L;
    /**
     * 题目类型，选择题：SINGLE_SEL，填空题：COMPLETION，选择题：TRUE_OR_FALSE，选字填空：BANKED_CLOZE
     */
    private String questionType;
    /**
     * 是否正确
     */
    private Boolean isCorrect;
    /**
     * 答题时间
     */
    private Date createTime;
}
