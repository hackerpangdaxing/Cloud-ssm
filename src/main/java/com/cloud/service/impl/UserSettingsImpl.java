package com.cloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cloud.dao.IUserSettingsDao;
import com.cloud.model.StudentUser;
import com.cloud.model.TeacherUser;
import com.cloud.service.IUserSettingsService;

@Service("UserSettingsService")
public class UserSettingsImpl implements IUserSettingsService {
	
	   @Autowired
	   @Qualifier("IUserSettingsDao")
	 private IUserSettingsDao dao;
	   
	@Override
	public int updateStudentUserInfo(StudentUser studentUser) {
		// TODO Auto-generated method stub
		return this.dao.updateStudentUserInfo(studentUser);
	}

	@Override
	public int updateStudentUserPassword(StudentUser studentUser) {
		// TODO Auto-generated method stub
		return this.dao.updateStudentUserPassword(studentUser);
	}

	@Override
	public int updateTeacherUserInfo(TeacherUser teacherUser) {
		// TODO Auto-generated method stub
		return this.dao.updateTeacherUserInfo(teacherUser);
	}

	@Override
	public int updateTeacherUserPassword(TeacherUser teacherUser) {
		// TODO Auto-generated method stub
		return this.dao.updateTeacherUserPassword(teacherUser);
	}

}
