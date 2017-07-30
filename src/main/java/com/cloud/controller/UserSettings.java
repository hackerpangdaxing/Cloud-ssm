package com.cloud.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.model.StudentUser;
import com.cloud.model.TeacherUser;
import com.cloud.service.IUserSettingsService;

@RestController
@RequestMapping("userSettins")
public class UserSettings {
	@Resource
	private IUserSettingsService service;
	
	@RequestMapping(value="updateStudentInfo",method=RequestMethod.POST)
	public Boolean updateStudentUserInfo(@RequestBody StudentUser studentUser){
	
			int updateStudentUserInfo = this.service.updateStudentUserInfo(studentUser);
			return updateStudentUserInfo == 1 ? true : false;
		

	}
	
	@RequestMapping(value="updateStudentPassword",method=RequestMethod.POST)
	public Boolean updateStudentPassword(@RequestBody StudentUser studentUser,String type){
			int updateStudentUserPassword = this.service.updateStudentUserPassword(studentUser);
			return updateStudentUserPassword == 1 ? true : false;
	}
	
	@CrossOrigin
	@RequestMapping(value="updateTeacherInfo",method=RequestMethod.POST,consumes = "application/json")
	public @ResponseBody Boolean updateTeacherUserInfo(@RequestBody TeacherUser teacherUser){
		
		int updateTeacherUserInfo = this.service.updateTeacherUserInfo(teacherUser);
		return updateTeacherUserInfo == 1 ? true : false;
		
		
	}
	
	@RequestMapping(value="updateTeacherPassword",method=RequestMethod.POST)
	public Boolean updateTeacherPassword(@RequestBody TeacherUser teacherUser,String type){
		int updateTeacherUserPassword = this.service.updateTeacherUserPassword(teacherUser);
		return updateTeacherUserPassword == 1 ? true : false;
	}
	
	
}
