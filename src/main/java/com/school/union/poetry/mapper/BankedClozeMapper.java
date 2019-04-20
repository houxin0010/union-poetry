package com.school.union.poetry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.union.poetry.entity.BankedCloze;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 选词填空（九宫格）(BankedCloze)表数据库访问层
 *
 * @author makejava
 * @since 2019-04-05 11:43:06
 */
@Repository
public interface BankedClozeMapper extends BaseMapper<BankedCloze> {

    @Select(value = "SELECT id FROM banked_cloze WHERE id >= ((SELECT MAX(id) FROM banked_cloze) - (SELECT MIN(id) FROM banked_cloze)) * RAND() + (SELECT MIN(id) FROM banked_cloze) LIMIT 1")
    Long selectRandomId();
    
    @Select(value = "SELECT id FROM banked_cloze where grade=#{grade}")
    List<Long> selectIds(int grade);
}