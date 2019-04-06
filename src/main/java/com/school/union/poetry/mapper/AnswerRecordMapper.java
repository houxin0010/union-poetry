package com.school.union.poetry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.union.poetry.entity.AnswerRecord;
import org.springframework.stereotype.Repository;

/**
 * 答题记录(AnswerRecord)表数据库访问层
 *
 * @author makejava
 * @since 2019-04-05 19:13:22
 */
@Repository
public interface AnswerRecordMapper extends BaseMapper<AnswerRecord> {

}