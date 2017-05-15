package com.cloud.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cloud.model.CreateClassActivity;
import com.cloud.model.ExaminationPaper;
import com.cloud.model.QuestionBank;
import com.cloud.model.StudentUser;
import com.cloud.model.User;
import com.cloud.model.UserEvenLog;

@Component("IOnlineTestDao")
public interface IOnlineTestDao {
	//创建试卷
	ExaminationPaper  addExaminationPaper(Integer questionsId);
	
	//通过活动ID查找试卷questionsId
	ExaminationPaper  selectExaminationPaper(Integer activityId);
	
	//找到试卷对应的题目
	List<QuestionBank> findQuestions(List<Integer> questionList);
	
	//查询系统题库组成试卷
	List<QuestionBank>  selectSystemExaminationPaper(String	classType);
	
	//将用户系统答题结果插入用户日志
	int insertAnswerResult(UserEvenLog userEvenLog);
	
	//更新题目正确率 
	int updateRightAnswer(List<Integer> rightId);
	
	//更新题目错误率 
	int updateErrorAnswer(List<Integer> rightId);
	
	//更新用户日志 提交教学评价
	int updateUserEvaluate(UserEvenLog userEvenLog);
}
