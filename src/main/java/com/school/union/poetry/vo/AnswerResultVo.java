package com.school.union.poetry.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * AnswerResultVo
 *
 * @author houxin
 * @date 2019/4/13
 */
@Data
public class AnswerResultVo implements Serializable {
    private static final long serialVersionUID = 4735318793579797214L;

    private Integer currentScore;
    private List<Map<String, Object>> hisScore;
}
