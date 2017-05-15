package com.cloud.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cloud.model.CourseIntroduction;
import com.cloud.model.CreateClassActivity;
import com.cloud.model.ExaminationPaper;
import com.cloud.model.UserEvenLog;

@Component("IClassActivity")
public interface IClassActivityDao {
	//教师创建课堂活动
	public int insertClassActivity(CreateClassActivity classActivity);
	
	//查询试卷题目id
	public Integer selectQuestionOneId(Integer questionsId);
	
	//系统通知： 返回教室所创建的课堂活动记录
	public List<CreateClassActivity>  getClassActivity(Integer teacherId);
	
	//通过邀请码   加入课堂活动
	public String selectClassActivity(Integer classActivityId);
	
	//查询首页面信息
	public List<CourseIntroduction>  selectClassList();
	
	//显示用户首页的答题正确题数，错误题数，正确率
	public UserEvenLog selectUserEvenLog(Integer userId);

	//返回教学评价列表
	public List<UserEvenLog> getEduEvaluate(Integer activityId);
	
}
