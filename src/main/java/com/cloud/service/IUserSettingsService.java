package com.cloud.service;

import com.cloud.model.StudentUser;
import com.cloud.model.TeacherUser;

public interface IUserSettingsService {
	
		public int updateStudentUserInfo(StudentUser studentUser);
		
		public int updateStudentUserPassword(StudentUser studentUser);
		
		public int updateTeacherUserInfo(TeacherUser teacherUser);

		public int updateTeacherUserPassword(TeacherUser teacherUser);
}
