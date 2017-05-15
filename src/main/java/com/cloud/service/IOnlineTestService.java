package com.cloud.service;

import java.util.List;
import java.util.Map;

import com.cloud.model.CreateClassActivity;
import com.cloud.model.ExaminationPaper;
import com.cloud.model.QuestionBank;
import com.cloud.model.StudentUser;
import com.cloud.model.User;
import com.cloud.model.UserEvenLog;

//主业务接口
public interface IOnlineTestService {
	// 根据试卷id获取试卷的一幕id集合
	public ExaminationPaper getExcamById(Integer activityId) throws Exception;
	// 根获取试卷完整的题目详情

	public List<QuestionBank> findQuestionsById(List<Integer> questionList);

	public List<QuestionBank> selectSystemExaminationPaper(String classType);

	public int insertAnswerResult(UserEvenLog userEvenLog);

	int updateRightAnswer(List<Integer> rightId);

	int updateErrorAnswer(List<Integer> rightId);
	
	int updateUserEvaluate(UserEvenLog userEvenLog);
}
