package com.cloud.testmybatis;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cloud.dao.IUserSettingsDao;
import com.cloud.model.StudentUser;
import com.cloud.model.TeacherUser;
import com.cloud.service.IUserSettingsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml","classpath:spring/spring-web.xml" })
public class MyTest2 {
	private static Logger logger = Logger.getLogger(MyTest2.class);
	
	// * @Resource private IOnlineTestService service;
	 
	@Autowired
	@Qualifier("IUserSettingsDao")
	private IUserSettingsDao dao;
	
	@Resource
	private IUserSettingsService service;

	@Test
	public void test1(){
		/*StudentUser studentUser = new StudentUser(1,"小诺诺","南工","信息工程学院","2014102202","计科","1090336400@qq.com");
		System.out.println(this.dao.updateStudentUserInfo(studentUser));
		*/
		/*StudentUser studentUser2 = new StudentUser(1,"654321");
		System.out.println(this.dao.updateStudentUserPassword(studentUser2));*/
		/*TeacherUser teacherUser = new TeacherUser(1,"小诺诺","南工","信息工程学院","2014102202","计科","软件工程","1090336400@qq.com");
		System.out.println(this.dao.updateTeacherUserInfo(teacherUser));*/
		TeacherUser teacherUser = new TeacherUser(1,"789456123");
		System.out.println(this.dao.updateTeacherUserPassword(teacherUser));
	}
	
	public void test2(){
		TeacherUser teacherUser = new TeacherUser(1,"56");
		System.out.println(this.service.updateTeacherUserPassword(teacherUser));
	}
}


