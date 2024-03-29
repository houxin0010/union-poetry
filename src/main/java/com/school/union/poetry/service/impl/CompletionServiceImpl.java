package com.school.union.poetry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.union.poetry.entity.Completion;
import com.school.union.poetry.mapper.CompletionMapper;
import com.school.union.poetry.service.CompletionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompletionServiceImpl extends ServiceImpl<CompletionMapper, Completion> implements CompletionService {

	 
    @Autowired
    private CompletionMapper completionMapper;

    @Override
    public Long randomCompletion() {
        return completionMapper.selectRandomId();
    }

	@Override
	public List<Long> selectIds(int grade) {
		//completionMapper.selectList(new QueryWrapper<Completion>().eq("grade", grade));
		return completionMapper.selectIds(grade);
	}
    
    
}
