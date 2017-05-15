package com.cloud.service;

import java.util.List;

import com.cloud.model.CourseIntroduction;
import com.cloud.model.CreateClassActivity;
import com.cloud.model.ExaminationPaper;
import com.cloud.model.UserEvenLog;

public interface IClassActivityService {

	public int insertClassActivity(CreateClassActivity classActivity);
	
	public Integer selectQuestionOneId(Integer questionsId);
	
	public List<CreateClassActivity>  getClassActivity(Integer teacherId);
	
	public int selectClassActivity(Integer classActivityId);
	
	public List<CourseIntroduction>  selectClassList();
	
	public UserEvenLog selectUserEvenLog(Integer userId);
	
	public List<UserEvenLog> getEduEvaluate(Integer activityId);
}
