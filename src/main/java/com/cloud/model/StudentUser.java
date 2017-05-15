package com.cloud.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author 胖大星
 *学生详情
 */
public class StudentUser {
	private Integer studentId;  //学生编号  主键
	private String name;		//用户账号
	private String password;	//密码
	private String type;		//  用户类型
	private String studentRealName; 	//用户真实名
	private String school;	//所在学校
	private String college;  //院系
	private String studentNumber; 	//学号
	private String major;	//专业
	private String mail;	//邮箱
	private int status;//用户激活状态
	private Date registerTime;
	public StudentUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public StudentUser(Integer studentId, String password) {
		super();
		this.studentId = studentId;
		this.password = password;
	}


	public StudentUser(Integer studentId, String studentRealName, String school, String college, String studentNumber,
			String major, String mail) {
		super();
		this.studentId = studentId;
		this.studentRealName = studentRealName;
		this.school = school;
		this.college = college;
		this.studentNumber = studentNumber;
		this.major = major;
		this.mail = mail;
	}


	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
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
	public String getStudentRealName() {
		return studentRealName;
	}
	public void setStudentRealName(String studentRealName) {
		this.studentRealName = studentRealName;
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
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
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
		return "StudentUser [studentId=" + studentId + ", name=" + name + ", password=" + password + ", type=" + type
				+ ", studentRealName=" + studentRealName + ", school=" + school + ", college=" + college
				+ ", studentNumber=" + studentNumber + ", major=" + major + ", registerTime=" + registerTime + "]";
	}
	
}
