package com.school.union.poetry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.union.poetry.entity.QuestionPaper;
import org.springframework.stereotype.Repository;

/**
 * 试卷(QuestionPaper)表数据库访问层
 *
 * @author makejava
 * @since 2019-04-05 19:23:43
 */
@Repository
public interface QuestionPaperMapper extends BaseMapper<QuestionPaper> {

}