package com.cloud.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 胖大星 创建教师活动房间
 */
public class ActivityRoom {
	private Integer activityid; // 活动ID
	private String chatRoom; //聊天室
	private String signIn; // 签到
	private String eduEvaluate;  //教学评价
	ArrayList<QuestionBank> questionBank;  // 完整的试卷详情
	Map<Integer,RoomUser>  roomUser; // 加入房间的用户集合  聊天室
	public ActivityRoom(Integer activityid, String chatRoom, String signIn, String eduEvaluate,
			ArrayList<QuestionBank> questionBank) {
		super();
		this.activityid = activityid;
		this.chatRoom = chatRoom;
		this.signIn = signIn;
		this.eduEvaluate = eduEvaluate;
		this.questionBank = questionBank;
		roomUser = new HashMap();
	}

	public ActivityRoom() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getActivityid() {
		return activityid;
	}
	public void setActivityid(Integer activityid) {
		this.activityid = activityid;
	}
	
	public String getChatRoom() {
		return chatRoom;
	}
	public void setChatRoom(String chatRoom) {
		this.chatRoom = chatRoom;
	}
	public String getSignIn() {
		return signIn;
	}
	public void setSignIn(String signIn) {
		this.signIn = signIn;
	}
	public String getEduEvaluate() {
		return eduEvaluate;
	}
	public void setEduEvaluate(String eduEvaluate) {
		this.eduEvaluate = eduEvaluate;
	}
	public ArrayList<QuestionBank> getQuestionBank() {
		return questionBank;
	}
	public void setQuestionBank(ArrayList<QuestionBank> questionBank) {
		this.questionBank = questionBank;
	}
	public Map<Integer, RoomUser> getRoomUser() {
		return roomUser;
	}
	public void setRoomUser(Map<Integer, RoomUser> roomUser) {
		this.roomUser = roomUser;
	}
	
	public Boolean addRoomUser(RoomUser roomuser) {
		if ( roomuser.getStudentuser() != null ) {
			this.roomUser.put(roomuser.getStudentuser().getStudentId(), roomuser);
			return true;
		}
		else if ( roomuser.getTeacheruser() != null ) {
			this.roomUser.put(roomuser.getTeacheruser().getTeacherId(), roomuser);
			return true;
		}
		
		return false;
	}
	@Override
	public String toString() {
		return "ActivityRoom [activityid=" + activityid + ", chatRoom=" + chatRoom + ", signIn=" + signIn
				+ ", eduEvaluate=" + eduEvaluate + ", questionBank=" + questionBank + "]";
	}
	

	

}
