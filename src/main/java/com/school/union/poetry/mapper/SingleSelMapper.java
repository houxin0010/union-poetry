package com.school.union.poetry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.union.poetry.entity.SingleSel;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 选择题(SingleSel)表数据库访问层
 *
 * @author makejava
 * @since 2019-04-05 11:43:06
 */
@Repository
public interface SingleSelMapper extends BaseMapper<SingleSel> {

    @Select( value = "SELECT id FROM single_sel WHERE id >= ((SELECT MAX(id) FROM single_sel) - (SELECT MIN(id) FROM single_sel)) * RAND() + (SELECT MIN(id) FROM single_sel) LIMIT 1")
    Long selectRandomId();
    
    @Select( value = "SELECT id FROM single_sel where grade=#{grade}")
    List<Long> selectIds(int grade);
}