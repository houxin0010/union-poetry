package com.school.union.poetry.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 选择题(SingleSel)表实体类
 *
 * @author makejava
 * @since 2019-04-05 11:40:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SingleSel extends Model<SingleSel> {
    private static final long serialVersionUID = 667286701793070500L;
    private Long id;
    private Integer optimistic;
    /**
     * 题目
     */
    private String question;
    private String choiceA;
    private String choiceB;
    private String choiceC;
    private String choiceD;
    /**
     * 答案，A，B，C，D
     */
    private String answer;
    /**
     * 分值
     */
    private Integer score;
    /**
     * 难度系数，1-5，值越大越难
     */
    private Integer itemDifficulty;
    private Date createTime;
}