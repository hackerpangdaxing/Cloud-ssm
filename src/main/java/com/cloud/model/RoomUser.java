package com.cloud.model;

import java.util.ArrayList;

import org.springframework.web.socket.WebSocketSession;

public class RoomUser {
    private WebSocketSession webSocketSession;
    private StudentUser studentuser;
    private TeacherUser teacheruser;
    private Integer activityId;
	public RoomUser(WebSocketSession webSocketSession, TeacherUser teacheruser, Integer activityId) {
		super();
		this.webSocketSession = webSocketSession;
		this.teacheruser = teacheruser;
		this.activityId = activityId;
	}
	public RoomUser(WebSocketSession webSocketSession, StudentUser studentuser, Integer activityId) {
		super();
		this.webSocketSession = webSocketSession;
		this.studentuser = studentuser;
		this.activityId = activityId;
	}
	public RoomUser(Integer activityId, TeacherUser teacheruser,  StudentUser studentuser ) {
		super();
		this.studentuser = studentuser;
		this.teacheruser = teacheruser;
		this.activityId = activityId;
	}
	public WebSocketSession getWebSocketSession() {
		return webSocketSession;
	}
	public void setWebSocketSession(WebSocketSession webSocketSession) {
		this.webSocketSession = webSocketSession;
	}
	public StudentUser getStudentuser() {
		return studentuser;
	}
	public void setStudentuser(StudentUser studentuser) {
		this.studentuser = studentuser;
	}
	public TeacherUser getTeacheruser() {
		return teacheruser;
	}
	public void setTeacheruser(TeacherUser teacheruser) {
		this.teacheruser = teacheruser;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	@Override
	public String toString() {
		return "{"  + "studentuser:" + studentuser + ", teacheruser:"
				+ teacheruser + ", activityId:" + activityId + "}";
	}
    
	
}

