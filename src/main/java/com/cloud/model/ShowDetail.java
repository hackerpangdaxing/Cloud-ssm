package com.cloud.model;

import java.util.List;
import java.util.Map;

/**
 * @author 胖大星
 *显示首页内容
 */
public class ShowDetail {
	private String joinedClass;
	private List<Map<String,CourseIntroduction>> showList;
	public ShowDetail(String joinedClass, List<Map<String, CourseIntroduction>> showList) {
		super();
		this.joinedClass = joinedClass;
		this.showList = showList;
	}
	public ShowDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getJoinedClass() {
		return joinedClass;
	}
	public void setJoinedClass(String joinedClass) {
		this.joinedClass = joinedClass;
	}
	public List<Map<String, CourseIntroduction>> getShowList() {
		return showList;
	}
	public void setShowList(List<Map<String, CourseIntroduction>> showList) {
		this.showList = showList;
	}
	@Override
	public String toString() {
		return "ShowDetail [joinedClass=" + joinedClass + ", showList=" + showList + "]";
	}
	
}
