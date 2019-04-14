package com.school.union.poetry.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.union.poetry.entity.BankedCloze;
import com.school.union.poetry.mapper.BankedClozeMapper;
import com.school.union.poetry.service.BankedClozeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankedClozeServiceImpl extends ServiceImpl<BankedClozeMapper, BankedCloze> implements BankedClozeService {

    @Autowired
    private BankedClozeMapper bankedClozeMapper;

    @Override
    public Long randomBankedCloze() {
        return bankedClozeMapper.selectRandomId();
    }

	@Override
	public List<Long> selectIds() {
		 
		return bankedClozeMapper.selectIds();
	}
}
