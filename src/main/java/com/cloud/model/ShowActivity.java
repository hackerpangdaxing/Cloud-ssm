package com.cloud.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ShowActivity {
	private int questionsId;//试卷编号
	private String type;//课程类型
	private int chapter;//章节
	private int unit;//单元
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date date;
	public int getQuestionsId() {
		return questionsId;
	}
	public void setQuestionsId(int questionsId) {
		this.questionsId = questionsId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getChapter() {
		return chapter;
	}
	public void setChapter(int chapter) {
		this.chapter = chapter;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
