package com.cloud.model;

public class ActivityMessage {
	private String state;   // 已加入 退出 被关闭（活动结束） 广播消息
	private Integer activityId;
	private String message; // 
	private Integer userId;
	private String sendTo;
	private String username;
	private String data;
	
	public ActivityMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public ActivityMessage(String state, Integer activityId, String message, Integer userId, String sendTo,
			String username, String data) {
		super();
		this.state = state;
		this.activityId = activityId;
		this.message = message;
		this.userId = userId;
		this.sendTo = sendTo;
		this.username = username;
		this.data = data;
	}

	public ActivityMessage(String state, Integer activityId, String data) {
		super();
		this.state = state;
		this.activityId = activityId;
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getMessage() {
		return message;
	}

	

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
