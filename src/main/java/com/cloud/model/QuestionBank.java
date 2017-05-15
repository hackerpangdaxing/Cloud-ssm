package com.cloud.model;

/**
 * @author 胖大星
 *系统总题库
 */
public class QuestionBank {
	private Integer pid;  // 主键
	private Integer pdId; 	//题目编号 XX XX XX XXXX （编号规则 ：课程类型  单元  小节  编号） 
	private int answerType;	// 0 : 单选  1：多选
	private String testType;	// 测试类型：JAVA 、C++...
	private String questionTitle; //题目
	//以下为选项
	private String A;
	private String B;
	private String C;
	private String D;
	private String E;
	private String F;
	private String G;
	private String answer;	//答案
	private String analysis;  //题目解析
	private Integer teacherId; 	//题目上传者
	private int rightTimes;		//用户答对次数
	private int errorTimes;		//用户打错次数
	private String 	accuracy;	//正确率
	public QuestionBank() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionBank(Integer pid) {
		super();
		this.pid = pid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getPdId() {
		return pdId;
	}
	public void setPdId(Integer pdId) {
		this.pdId = pdId;
	}
	public int getAnswerType() {
		return answerType;
	}
	public void setAnswerType(int answerType) {
		this.answerType = answerType;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getA() {
		return A;
	}
	public void setA(String a) {
		A = a;
	}
	public String getB() {
		return B;
	}
	public void setB(String b) {
		B = b;
	}
	public String getC() {
		return C;
	}
	public void setC(String c) {
		C = c;
	}
	public String getD() {
		return D;
	}
	public void setD(String d) {
		D = d;
	}
	public String getE() {
		return E;
	}
	public void setE(String e) {
		E = e;
	}
	public String getF() {
		return F;
	}
	public void setF(String f) {
		F = f;
	}
	public String getG() {
		return G;
	}
	public void setG(String g) {
		G = g;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	
	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
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

	@Override
	public String toString() {
		return "QuestionBank [pid=" + pid + ", pdId=" + pdId + ", answerType=" + answerType + ", testType=" + testType
				+ ", questionTitle=" + questionTitle + ", A=" + A + ", B=" + B + ", C=" + C + ", D=" + D + ", E=" + E
				+ ", F=" + F + ", G=" + G + ", answer=" + answer + ", analysis=" + analysis + ", teacherId=" + teacherId
				+ ", rightTimes=" + rightTimes + ", errorTimes=" + errorTimes + ", accuracy=" + accuracy + "]";
	}


}
