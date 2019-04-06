package com.school.union.poetry.service.impl;

import com.school.union.poetry.mapper.SingleSelMapper;
import com.school.union.poetry.service.SingleSelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SingleSelServiceImpl implements SingleSelService {

    @Autowired
    private SingleSelMapper singleSelMapper;

    @Override
    public Long randomSingleSel(){
        return singleSelMapper.selectRandomId();
    }
}
