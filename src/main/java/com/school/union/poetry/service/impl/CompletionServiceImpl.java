package com.school.union.poetry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.union.poetry.entity.Completion;
import com.school.union.poetry.mapper.CompletionMapper;
import com.school.union.poetry.service.CompletionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompletionServiceImpl extends ServiceImpl<CompletionMapper, Completion> implements CompletionService {

    @Autowired
    private CompletionMapper completionMapper;

    @Override
    public Long randomCompletion() {
        return completionMapper.selectRandomId();
    }
}
