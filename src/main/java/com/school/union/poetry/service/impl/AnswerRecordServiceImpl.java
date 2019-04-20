package com.school.union.poetry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.union.poetry.constant.QuestionType;
import com.school.union.poetry.entity.AnswerRecord;
import com.school.union.poetry.mapper.AnswerRecordMapper;
import com.school.union.poetry.service.AnswerRecordService;
import com.school.union.poetry.service.BankedClozeService;
import com.school.union.poetry.service.CompletionService;
import com.school.union.poetry.service.SingleSelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class AnswerRecordServiceImpl extends ServiceImpl<AnswerRecordMapper, AnswerRecord>
		implements AnswerRecordService {

	@Autowired
	private AnswerRecordMapper answerRecordMapper;

	@Autowired
	private SingleSelService singleSelService;
	@Autowired
	private BankedClozeService bankedClozeService;
	@Autowired
	private CompletionService completionService;

	private static List<Long> completionIds3 = null;
	private static List<Long> singleSelIds3 = null;
	private static List<Long> bankedClozeIds3 = null;

	private static List<Long> completionIds4 = null;
	private static List<Long> singleSelIds4 = null;
	private static List<Long> bankedClozeIds4 = null;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void createAnswerRecord(AnswerRecord answerRecord) {
		answerRecordMapper.insert(answerRecord);
	}

	@Override
    public Long randomQuestionId(List<String> curQuestion, QuestionType questionType,int grade) {
        Long questionId = null;
        questionId = getRandomQuestionId(questionType, questionId, grade);

        if (!curQuestion.contains(questionType + "-" + questionId.toString())) {
            curQuestion.add(questionType + "-" + questionId.toString());
            return questionId;
        }
        return randomQuestionId(curQuestion, questionType,grade);
    }

	@Override
	public AnswerRecord getNewestAnswerRecord(Long questionPaperId) {
		List<AnswerRecord> answerRecords = answerRecordMapper
				.selectList(new LambdaQueryWrapper<AnswerRecord>().eq(AnswerRecord::getQuestionPaperId, questionPaperId)
						.eq(AnswerRecord::getIsAccomplish, false).orderByAsc(AnswerRecord::getQuestionNo));
		if (answerRecords != null && !answerRecords.isEmpty()) {
			return answerRecords.stream().findFirst().get();
		}
		return null;
	}

	@Override
	public AnswerRecord getAnswerRecord(Long questionPaperId, Integer questionNumber) {
		AnswerRecord answerRecord = new AnswerRecord();
		answerRecord.setQuestionPaperId(Optional.ofNullable(questionPaperId).orElse(0L));
		answerRecord.setQuestionNo(Optional.ofNullable(questionNumber).orElse(0));
		return answerRecordMapper.selectOne(new QueryWrapper<>(answerRecord));
	}

	private Long getRandomQuestionId(QuestionType questionType, Long questionId, int grade) {
		switch (questionType) {
		case COMPLETION:
			if (grade == 3) {
				if (completionIds3 == null) {
					completionIds3 = completionService.selectIds(grade);
				}
				questionId = completionIds3.get(new Random().nextInt(completionIds3.size()));
			}
			if (grade == 4) {
				if (completionIds4 == null) {
					completionIds4 = completionService.selectIds(grade);
				}
				questionId = completionIds4.get(new Random().nextInt(completionIds4.size()));
			}

			break;
		case SINGLE_SEL:
			if (grade == 3) {
				if (singleSelIds3 == null) {
					singleSelIds3 = singleSelService.selectIds(grade);
				}
				questionId = singleSelIds3.get(new Random().nextInt(singleSelIds3.size()));
			}
			
			if (grade == 4) {
				if (singleSelIds4 == null) {
					singleSelIds4 = singleSelService.selectIds(grade);
				}
				questionId = singleSelIds4.get(new Random().nextInt(singleSelIds4.size()));
			}
			
			break;
		case BANKED_CLOZE:
			if (grade == 3) {
			if (bankedClozeIds3 == null) {
				bankedClozeIds3 = bankedClozeService.selectIds(grade);
			}
			questionId = bankedClozeIds3.get(new Random().nextInt(bankedClozeIds3.size()));
			}
			
			if (grade == 4) {
				if (bankedClozeIds4 == null) {
					bankedClozeIds4 = bankedClozeService.selectIds(grade);
				}
				questionId = bankedClozeIds4.get(new Random().nextInt(bankedClozeIds4.size()));
			}
			
			break;
		default:
		}
		return questionId;
	}

}
