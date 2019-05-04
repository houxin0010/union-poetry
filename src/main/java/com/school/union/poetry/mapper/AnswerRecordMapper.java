package com.school.union.poetry.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.school.union.poetry.entity.AnswerRecord;
import com.school.union.poetry.mapper.provider.AnswerRecordProvider;
import com.school.union.poetry.vo.GetAnswerRecordVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 答题记录(AnswerRecord)表数据库访问层
 *
 * @author makejava
 * @since 2019-04-05 19:13:22
 */
@Repository
public interface AnswerRecordMapper extends BaseMapper<AnswerRecord> {

    @SelectProvider(type = AnswerRecordProvider.class, method = "selectByParamsSql")
    IPage<GetAnswerRecordVo> selectByParams(Page page, @Param("answerRecord") AnswerRecord answerRecord,
                                            @Param("startTime") String startTime, @Param("endTime") String endTime);
}