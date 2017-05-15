package com.cloud.model;

public class CourseIntroduction {
	private Integer id;
	private String className;
	private String press;
	private String iconUrl;
	private int start;
	private String Introduction;
	public CourseIntroduction(String className, String press, String iconUrl, int start, String introduction) {
		super();
		this.className = className;
		this.press = press;
		this.iconUrl = iconUrl;
		this.start = start;
		Introduction = introduction;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public CourseIntroduction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public String getIntroduction() {
		return Introduction;
	}
	public void setIntroduction(String introduction) {
		Introduction = introduction;
	}
	@Override
	public String toString() {
		return "CourseIntroduction [className=" + className + ", press=" + press + ", iconUrl=" + iconUrl + ", start="
				+ start + ", Introduction=" + Introduction + "]";
	}
	

}
