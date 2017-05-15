package com.cloud.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author 胖大星
 *用户行为日志
 */
public class UserEvenLog {
	private String eventId;  //事件编号		主键
	private Integer activityId;		//教学活动编号   
	private Integer userId;		// 用户编号
	private String answerResults;		//答题结果集 数组（题目id+答题结果 ）
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date startTime;		// 测试开始时间
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;		//测试结束时间
	private int rightTimes;		//用户答对次数
	private int errorTimes;		//用户打错次数
	private String 	accuracy;	//正确率
	private String eduEvaluate;
	public UserEvenLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserEvenLog(String eventId, Integer activityId, Integer userId, String answerResults) {
		super();
		this.eventId = eventId;
		this.activityId = activityId;
		this.userId = userId;
		this.answerResults = answerResults;
	}
	
	public UserEvenLog( Integer activityId, Integer userId, String answerResults, Date startTime,
			Date endTime, int rightTimes, int errorTimes, String accuracy, String eduEvaluate) {
		super();
		this.activityId = activityId;
		this.userId = userId;
		this.answerResults = answerResults;
		this.startTime = startTime;
		this.endTime = endTime;
		this.rightTimes = rightTimes;
		this.errorTimes = errorTimes;
		this.accuracy = accuracy;
		this.eduEvaluate = eduEvaluate;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getAnswerResults() {
		return answerResults;
	}
	public void setAnswerResults(String answerResults) {
		this.answerResults = answerResults;
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
	public int getRightTimes() {
		return rightTimes;
	}
	public void setRightTimes(int rightTimes) {
		this.rightTimes = rightTimes;
	}
	public int getErrorTimes() {
		return errorTimes;
	}
	public void setErrorTimes(int errorTimes) {
		this.errorTimes = errorTimes;
	}
	public String getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}
	public String getEduEvaluate() {
		return eduEvaluate;
	}
	public void setEduEvaluate(String eduEvaluate) {
		this.eduEvaluate = eduEvaluate;
	}
	@Override
	public String toString() {
		return "UserEvenLog [eventId=" + eventId + ", activityId=" + activityId + ", userId=" + userId
				+ ", answerResults=" + answerResults + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", rightTimes=" + rightTimes + ", errorTimes=" + errorTimes + ", accuracy=" + accuracy
				+ ", eduEvaluate=" + eduEvaluate + "]";
	}
	
	
}
