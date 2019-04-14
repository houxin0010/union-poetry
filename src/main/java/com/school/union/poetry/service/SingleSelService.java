package com.school.union.poetry.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.school.union.poetry.entity.SingleSel;

public interface SingleSelService extends IService<SingleSel> {

    Long randomSingleSel();
    List<Long> selectIds();

}
