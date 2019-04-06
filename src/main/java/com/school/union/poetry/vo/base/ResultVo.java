package com.school.union.poetry.vo.base;

import lombok.Data;

/**
 * ResultVo
 *
 * @author houxin
 * @date 2019/4/5
 */
@Data
public class ResultVo<T> {

    private String code;
    private String desc;
    private T content;

    public static <T> ResultVo<T> success(T t) {
        ResultVo<T> tResultVo = new ResultVo<>();
        tResultVo.code = "00";
        tResultVo.desc = "ok";
        tResultVo.content = t;
        return tResultVo;
    }
}
