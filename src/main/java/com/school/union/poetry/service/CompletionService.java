package com.school.union.poetry.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.school.union.poetry.entity.Completion;

public interface CompletionService extends IService<Completion> {
    Long randomCompletion();
    
    List<Long> selectIds();
}
