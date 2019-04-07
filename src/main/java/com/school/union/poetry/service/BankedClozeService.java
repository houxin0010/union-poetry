package com.school.union.poetry.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.school.union.poetry.entity.BankedCloze;

public interface BankedClozeService extends IService<BankedCloze> {

    Long randomBankedCloze();
}
