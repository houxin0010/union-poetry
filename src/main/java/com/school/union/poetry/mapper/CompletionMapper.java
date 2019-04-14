package com.school.union.poetry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.union.poetry.entity.Completion;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 填空题(Completion)表数据库访问层
 *
 * @author makejava
 * @since 2019-04-05 11:43:06
 */
@Repository
public interface CompletionMapper extends BaseMapper<Completion> {

    @Select(value = "SELECT id FROM completion WHERE id >= ((SELECT MAX(id) FROM completion) - (SELECT MIN(id) FROM completion)) * RAND() + (SELECT MIN(id) FROM completion) LIMIT 1")
    Long selectRandomId();
    
    @Select(value = "SELECT id FROM completion")
    List<Long> selectIds();
}