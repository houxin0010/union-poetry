package com.school.union.poetry.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.school.union.poetry.entity.BankedCloze;

public interface BankedClozeService extends IService<BankedCloze> {

    Long randomBankedCloze();
    
    List<Long> selectIds(int grade);
}
