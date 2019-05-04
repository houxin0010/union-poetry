package com.school.union.poetry.vo.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseParam implements Serializable {

    private static final long serialVersionUID = -4226129790104874345L;
    private Integer page = 1;
    private Integer size = 10;
    private String startTime;
    private String endTime;
}
