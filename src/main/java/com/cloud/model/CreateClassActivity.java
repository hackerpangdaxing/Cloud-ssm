package com.cloud.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CreateClassActivity {
    private Integer activityId ;		//教学活动编号		主键
    private Integer teacherId;		//创建者的编号		外键
    private Integer questionsId;	//题目数组编号组成的试卷编号  外键
//    private TeacherUser teacherUser;
//    private ExaminationPaper examinationPaper;
    private String testType;	//测试类型
    private int unit;  //测试单元
    private int section;	//测试小节
    //活动模块
    private String signIn;   //签到
    private String chatRoom;   //课堂聊天室
    private String onlineTest;   //在线测试
    private String eduEvaluate;   //教学评价
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;		//活动创建开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;		//活动结束时间
    private String address;  //活动发生地点
	public CreateClassActivity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public Integer getQuestionsId() {
		return questionsId;
	}
	public void setQuestionsId(Integer questionsId) {
		this.questionsId = questionsId;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}
	public String getSignIn() {
		return signIn;
	}
	public void setSignIn(String signIn) {
		this.signIn = signIn;
	}
	public String getChatRoom() {
		return chatRoom;
	}
	public void setChatRoom(String chatRoom) {
		this.chatRoom = chatRoom;
	}
	public String getOnlineTest() {
		return onlineTest;
	}
	public void setOnlineTest(String onlineTest) {
		this.onlineTest = onlineTest;
	}
	public String getEduEvaluate() {
		return eduEvaluate;
	}
	public void setEduEvaluate(String eduEvaluate) {
		this.eduEvaluate = eduEvaluate;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "CreateClassActivity [activityId=" + activityId + ", teacherId=" + teacherId + ", questionsId="
				+ questionsId + ", testType=" + testType + ", unit=" + unit + ", section=" + section + ", signIn="
				+ signIn + ", chatRoom=" + chatRoom + ", onlineTest=" + onlineTest + ", eduEvaluate=" + eduEvaluate
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", address=" + address + "]";
	}
  
}
