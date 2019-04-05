package com.school.union.poetry.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 试卷(QuestionPaper)表实体类
 *
 * @author makejava
 * @since 2019-04-05 17:01:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionPaper extends Model<QuestionPaper> {
    private static final long serialVersionUID = -43847770521834115L;
    private Long id;
    private Integer optimistic;
    /**
     * 玩家
     */
    private String player;
    /**
     * 总分
     */
    private Integer totalPoints;
    /**
     * 完成状态，0：未完成，1：完成
     */
    private String status;
    private String openId;
    private Date createTime;
}