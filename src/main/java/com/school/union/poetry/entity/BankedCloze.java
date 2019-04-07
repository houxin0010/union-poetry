package com.school.union.poetry.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.school.union.poetry.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 选词填空（九宫格）(BankedCloze)表实体类
 *
 * @author makejava
 * @since 2019-04-05 11:37:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BankedCloze extends BaseEntity {
    private static final long serialVersionUID = 754206692491121504L;
    /**
     * 题目
     */
    private String question;
    /**
     * 可选择的字，以英文逗号分隔
     */
    private String options;
    /**
     * 答案
     */
    private String answer;
    /**
     * 分值
     */
    private Integer score;
    /**
     * 难度系数, 1-5
     */
    private Integer itemDifficulty;
    private Date createTime;
}