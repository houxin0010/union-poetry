package com.school.union.poetry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.school.union.poetry.entity.Completion;

public interface CompletionService extends IService<Completion> {
    Long randomCompletion();
}
