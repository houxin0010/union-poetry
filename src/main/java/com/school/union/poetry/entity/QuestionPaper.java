package com.school.union.poetry.entity;

import com.school.union.poetry.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 试卷(QuestionPaper)表实体类
 *
 * @author makejava
 * @since 2019-04-05 19:23:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionPaper extends BaseEntity {
    private static final long serialVersionUID = 995466897329282308L;

    /**
     * 玩家
     */
    private String player;
    /**
     * 玩家得分
     */
    private Integer score;
    /**
     * 试卷总分
     */
    private Integer totalPoints;
    /**
     * 已完成数量
     */
    private Integer completedNo;
    /**
     * 题目数量
     */
    private Integer questionTotal;
    /**
     * 完成状态，0：未完成，1：完成
     */
    private Integer status;
    /**
     * 微信唯一标识
     */
    private String openId;
    private Date createTime;
}