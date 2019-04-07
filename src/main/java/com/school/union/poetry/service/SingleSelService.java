package com.school.union.poetry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.school.union.poetry.entity.SingleSel;

public interface SingleSelService extends IService<SingleSel> {

    Long randomSingleSel();

}
