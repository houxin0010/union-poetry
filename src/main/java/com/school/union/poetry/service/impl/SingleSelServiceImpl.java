package com.school.union.poetry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.union.poetry.entity.SingleSel;
import com.school.union.poetry.mapper.SingleSelMapper;
import com.school.union.poetry.service.SingleSelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SingleSelServiceImpl extends ServiceImpl<SingleSelMapper, SingleSel> implements SingleSelService {

    @Autowired
    private SingleSelMapper singleSelMapper;

    @Override
    public Long randomSingleSel() {
        return singleSelMapper.selectRandomId();
    }

}
