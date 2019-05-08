package com.school.union.poetry.mapper.provider;

import com.school.union.poetry.entity.AnswerRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

public class AnswerRecordProvider {

    public String selectByParamsSql(@Param("answerRecord") AnswerRecord answerRecord,
                                    @Param("startTime") String startTime, @Param("endTime") String endTime){
        return new SQL(){{
            SELECT("*");
            FROM("answer_record");
            if (!StringUtils.isEmpty(answerRecord.getQuestionType())){
                WHERE("question_type = #{answerRecord.questionType}");
            }
            if (!StringUtils.isEmpty(answerRecord.getIsCorrect())){
                WHERE("is_correct = #{answerRecord.isCorrect}");
            }
            if (!StringUtils.isEmpty(startTime)) {
                WHERE("DATE_FORMAT(create_time,'%Y-%m-%d') >= #{startTime}");
            }
            if (!StringUtils.isEmpty(endTime)) {
                WHERE("DATE_FORMAT(create_time,'%Y-%m-%d') < #{endTime}");
            }
            ORDER_BY("create_time DESC");
        }}.toString();
    }
}
