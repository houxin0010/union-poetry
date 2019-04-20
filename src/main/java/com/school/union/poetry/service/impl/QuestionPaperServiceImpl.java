package com.school.union.poetry.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.union.poetry.constant.QuestionType;
import com.school.union.poetry.entity.*;
import com.school.union.poetry.mapper.QuestionPaperMapper;
import com.school.union.poetry.service.*;
import com.school.union.poetry.util.EnumUtil;
import com.school.union.poetry.vo.AnswerResultVo;
import com.school.union.poetry.vo.QuestionInitVo;
import com.school.union.poetry.vo.QuestionVo;
import com.school.union.poetry.vo.QuestionPaperInitVo;
import com.school.union.poetry.vo.QuestionResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * QuestionPaperServiceImpl
 *
 * @author houxin
 * @date 2019/4/5
 */
@Slf4j
@Service
@Transactional
public class QuestionPaperServiceImpl extends ServiceImpl<QuestionPaperMapper, QuestionPaper>
		implements QuestionPaperService {

	@Autowired
	private QuestionPaperMapper questionPaperMapper;
	@Autowired
	private AnswerRecordService answerRecordService;
	@Autowired
	private CompletionService completionService;
	@Autowired
	private SingleSelService singleSelService;
	@Autowired
	private BankedClozeService bankedClozeService;

 

	@Override
	public QuestionInitVo createQuestionPaperNew(int grade) {
		QuestionInitVo result = new QuestionInitVo();
		List<QuestionVo> questions = new ArrayList<QuestionVo>();
		List<String> curQuestion = new ArrayList<String>();
		int questionTotal = 20;
		QuestionVo questionVo;
		for (int i = 1; i <= questionTotal; i++) {

			questionVo = new QuestionVo();
			QuestionType questionType = EnumUtil.random(QuestionType.class);
			if (i == 1) {
				result.setFirstQuestionType(questionType.name());
			}
			log.info("questionType = {}", questionType);
			questionVo.setQuestionId(answerRecordService.randomQuestionId(curQuestion, questionType,grade));
			questionVo.setQuestionNo(i);
			questionVo.setQuestionType(questionType.name());
			questions.add(questionVo);
		}
		  result.setQuestions(questions);
		  return result;		

	}

	@Override
	public QuestionResultVo getQuestionContent(Long questionPaperId) {
		QuestionPaper questionPaper = questionPaperMapper.selectById(questionPaperId);
		AnswerRecord answerRecord = answerRecordService.getNewestAnswerRecord(questionPaperId);
		if (questionPaper != null) {
			QuestionResultVo questionResultVo = new QuestionResultVo();
			questionResultVo.setCurrentScore(questionPaper.getScore());
			if (answerRecord != null) {
				questionResultVo.setQuestionType(answerRecord.getQuestionType());
				questionResultVo.setQuestionTotal(10);
				questionResultVo.setQuestionNo(answerRecord.getQuestionNo());
				if (QuestionType.COMPLETION.name().equals(answerRecord.getQuestionType())) {
					Completion completion = completionService.getById(answerRecord.getQuestionId());
					questionResultVo.setQuestion(completion.getQuestion());
					questionResultVo.setAnswer(completion.getAnswer());
				} else if (QuestionType.SINGLE_SEL.name().equals(answerRecord.getQuestionType())) {
					SingleSel singleSel = singleSelService.getById(answerRecord.getQuestionId());
					questionResultVo.setQuestion(singleSel.getQuestion());
					questionResultVo.setAnswer(singleSel.getAnswer());
					List<String> options = new ArrayList<>();
					Optional.ofNullable(singleSel.getChoiceA()).ifPresent(options::add);
					Optional.ofNullable(singleSel.getChoiceB()).ifPresent(options::add);
					Optional.ofNullable(singleSel.getChoiceC()).ifPresent(options::add);
					Optional.ofNullable(singleSel.getChoiceD()).ifPresent(options::add);
					questionResultVo.setOptions(options);
				} else if (QuestionType.BANKED_CLOZE.name().equals(answerRecord.getQuestionType())) {
					BankedCloze bankedCloze = bankedClozeService.getById(answerRecord.getQuestionId());
					questionResultVo.setQuestion(bankedCloze.getQuestion());
					questionResultVo.setAnswer(bankedCloze.getAnswer());
					String optionsStr = Optional.ofNullable(bankedCloze.getOptions()).orElse("");
					List<String> options = Arrays.asList(optionsStr.split(","));
					questionResultVo.setOptions(options);
				}
			}
			return questionResultVo;
		}
		return null;
	}
	
	@Override
	public QuestionResultVo getQuestionContent(Long questionId,String questionType) {
 
			QuestionResultVo questionResultVo = new QuestionResultVo();
			questionResultVo.setCurrentScore(0);
		 
				questionResultVo.setQuestionType(questionType);
				questionResultVo.setQuestionTotal(5);
				//questionResultVo.setQuestionNo(answerRecord.getQuestionNo());
				if (QuestionType.COMPLETION.name().equals(questionType)) {
					Completion completion = completionService.getById(questionId);
					questionResultVo.setQuestion(completion.getQuestion());
					questionResultVo.setAnswer(completion.getAnswer());
					questionResultVo.setType(completion.getType());
				} else if (QuestionType.SINGLE_SEL.name().equals(questionType)) {
					SingleSel singleSel = singleSelService.getById(questionId);
					questionResultVo.setQuestion(singleSel.getQuestion());
					questionResultVo.setAnswer(singleSel.getAnswer());
					List<String> options = new ArrayList<>();
					Optional.ofNullable(singleSel.getChoiceA()).ifPresent(options::add);
					Optional.ofNullable(singleSel.getChoiceB()).ifPresent(options::add);
					Optional.ofNullable(singleSel.getChoiceC()).ifPresent(options::add);
					Optional.ofNullable(singleSel.getChoiceD()).ifPresent(options::add);
					questionResultVo.setOptions(options);
				} else if (QuestionType.BANKED_CLOZE.name().equals(questionType)) {
					BankedCloze bankedCloze = bankedClozeService.getById(questionId);
					questionResultVo.setQuestion(bankedCloze.getQuestion());
					questionResultVo.setAnswer(bankedCloze.getAnswer());
					String optionsStr = Optional.ofNullable(bankedCloze.getOptions()).orElse("");
					List<String> options = Arrays.asList(optionsStr.split(","));
					questionResultVo.setOptions(options);
				}
		 
			return questionResultVo;
		 
	 
	}

	@Override
	@Transactional
	public String completeQuestion(Long questionPaperId, Boolean isCorrect) {
		QuestionPaper questionPaper = questionPaperMapper.selectById(questionPaperId);
		AnswerRecord answerRecord = answerRecordService.getNewestAnswerRecord(questionPaperId);
		if (isCorrect) {
			questionPaper.setScore(questionPaper.getScore() + 10);
			answerRecord.setIsCorrect(isCorrect);
		}
		questionPaper.setCompletedNo(questionPaper.getCompletedNo() + 1);
		questionPaperMapper.updateById(questionPaper);
		answerRecord.setIsAccomplish(true);
		answerRecordService.updateById(answerRecord);

		AnswerRecord nextAnswerRecord = answerRecordService.getNewestAnswerRecord(questionPaperId);
		if (nextAnswerRecord != null) {
			return nextAnswerRecord.getQuestionType();
		}
		questionPaper.setStatus(1);
		questionPaperMapper.updateById(questionPaper);
		return null;
	}

	@Override
	public AnswerResultVo getScore(Long questionPaperId, String openId) {
		AnswerResultVo answerResultVo = new AnswerResultVo();
		QuestionPaper questionPaper = questionPaperMapper.selectById(questionPaperId);
		answerResultVo.setCurrentScore(questionPaper.getScore());
		List<QuestionPaper> questionPapers = questionPaperMapper.selectList(new LambdaUpdateWrapper<QuestionPaper>()
				.eq(QuestionPaper::getOpenId, openId).orderByDesc(QuestionPaper::getCreateTime));
		List<Map<String, Object>> hisScore = new ArrayList<>();
		questionPapers.stream().limit(3).forEach(paper -> {
			Map<String, Object> scoreMap = new HashMap<>(16);
			scoreMap.put("hisScore", paper.getScore());
			scoreMap.put("date", new SimpleDateFormat("yyyy-MM-dd").format(paper.getCreateTime()));
			hisScore.add(scoreMap);
		});

		answerResultVo.setHisScore(hisScore);
		return answerResultVo;
	}

}
