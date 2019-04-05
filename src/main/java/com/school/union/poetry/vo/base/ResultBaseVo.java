package com.school.union.poetry.vo.base;

import lombok.Data;

/**
 * ResultBaseVo
 *
 * @author houxin
 * @date 2019/4/5
 */
@Data
public class ResultBaseVo<T> {

    private String code;
    private String desc;
    private T content;
}
