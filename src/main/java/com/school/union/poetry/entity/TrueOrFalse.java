package com.school.union.poetry.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 选择题(TrueOrFalse)表实体类
 *
 * @author makejava
 * @since 2019-04-05 11:37:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TrueOrFalse extends Model<TrueOrFalse> {
    private static final long serialVersionUID = -92983804610115039L;
    private Long id;
    private Integer optimistic;
    private String question;
    /**
     * 答案，0，1
     */
    private Boolean answer;
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