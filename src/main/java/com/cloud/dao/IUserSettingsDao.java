package com.cloud.dao;

import org.springframework.stereotype.Component;

import com.cloud.model.StudentUser;
import com.cloud.model.TeacherUser;

@Component("IUserSettingsDao")
public interface IUserSettingsDao {
	//更新教师个人信息
	public int updateStudentUserInfo(StudentUser studentUser);
	
	//更新教师个人密码
	public int updateStudentUserPassword(StudentUser studentUser);
	
	//更新学生个人信息
	public int updateTeacherUserInfo(TeacherUser teacherUser);

	//更新学生个人密码
	public int updateTeacherUserPassword(TeacherUser teacherUser);
}