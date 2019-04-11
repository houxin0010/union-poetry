package com.school.union.poetry.vo;

import lombok.Data;

/**
 * GetAnswerResultVo
 *
 * @author houxin
 * @date 2019/4/11
 */
@Data
public class GetAnswerResultVo {

    /**
     * 试卷ID
     */
    private Long questionPaperId;
    /**
     * 玩家得分
     */
    private Integer score;
    /**
     * 记录最高分
     */
    private Integer topScore;
}
