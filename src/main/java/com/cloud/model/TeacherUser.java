package com.cloud.model;

import java.util.Date;


/**
 * @author 胖大星
 *教师详情
 */
public class TeacherUser {
	private Integer teacherId;  //教师编号  主键
	private String name;		//用户账号
	private String password;	//密码
	private String type;		//  用户类型
	private String teacherRealName; 	//用户真实名
	private String school;	//所在学校
	private String college;  //院系
	private String teacherNumber; 	//教师职工号
	private String mainMajor;		//主教专业
	private String electiveMajor;		//辅修专业
	private String mail;   //邮箱
	private int status;//用户激活状态
	private Date registerTime;
	public TeacherUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public TeacherUser(Integer teacherId, String password) {
		super();
		this.teacherId = teacherId;
		this.password = password;
	}


	public TeacherUser(Integer teacherId, String teacherRealName, String school, String college, String teacherNumber,
			String mainMajor, String electiveMajor, String mail) {
		super();
		this.teacherId = teacherId;
		this.teacherRealName = teacherRealName;
		this.school = school;
		this.college = college;
		this.teacherNumber = teacherNumber;
		this.mainMajor = mainMajor;
		this.electiveMajor = electiveMajor;
		this.mail = mail;
	}




	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTeacherRealName() {
		return teacherRealName;
	}
	public void setTeacherRealName(String teacherRealName) {
		this.teacherRealName = teacherRealName;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getTeacherNumber() {
		return teacherNumber;
	}
	public void setTeacherNumber(String teacherNumber) {
		this.teacherNumber = teacherNumber;
	}
	public String getMainMajor() {
		return mainMajor;
	}
	public void setMainMajor(String mainMajor) {
		this.mainMajor = mainMajor;
	}

	public String getElectiveMajor() {
		return electiveMajor;
	}
	public void setElectiveMajor(String electiveMajor) {
		this.electiveMajor = electiveMajor;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	
	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TeacherUser [teacherId=" + teacherId + ", name=" + name + ", password=" + password + ", type=" + type
				+ ", teacherRealName=" + teacherRealName + ", school=" + school + ", college=" + college
				+ ", teacherNumber=" + teacherNumber + ", mainMajor=" + mainMajor + ", electiveMajor=" + electiveMajor
				+ ", mail=" + mail + ", status=" + status + ", registerTime=" + registerTime + "]";
	}
	
}
