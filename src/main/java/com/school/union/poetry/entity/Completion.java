package com.school.union.poetry.entity;

import com.school.union.poetry.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 填空题(Completion)表实体类
 *
 * @author makejava
 * @since 2019-04-05 11:37:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Completion extends BaseEntity {
    private static final long serialVersionUID = 695085603219906852L;
    /**
     * 题目
     */
    private String question;
    /**
     * 答案
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
    private String grade;//年级
    private String type;//类型 UP:上句,DOWN：下句 
    
}