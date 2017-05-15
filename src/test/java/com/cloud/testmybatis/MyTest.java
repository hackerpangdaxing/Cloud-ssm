/*package com.cloud.testmybatis;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cloud.dao.IClassActivityDao;
import com.cloud.dao.IOnlineTestDao;
import com.cloud.model.CreateClassActivity;
import com.cloud.model.ExaminationPaper;
import com.cloud.model.QuestionBank;
import com.cloud.model.ShowActivity;
import com.cloud.model.UserEvenLog;
import com.cloud.service.IOnlineTestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/spring-dao.xml" })
public class MyTest {
	private static Logger logger = Logger.getLogger(MyTest.class);
	
	 * @Resource private IOnlineTestService service;
	 
	@Autowired
	@Qualifier("IClassActivity")
	private IClassActivityDao dao;
	
	  @Autowired
	  
	  @Qualifier("IOnlineTestDao") private IOnlineTestDao Qdao;
	 
	
	 * @Test public void test1(){ System.out.println(dao.selectClassList()); }
	 * 
	 * @Test public void test2() { ExaminationPaper examPaper; try { examPaper =
	 * service.getExcamById(1); } catch (Exception e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } }
	 

	
	 * @SuppressWarnings("null")
	 * 
	 * @Test public void test3() throws IOException { List<QuestionBank>
	 * selectSystemExaminationPaper =
	 * this.Qdao.selectSystemExaminationPaper("JAVA"); ObjectMapper mapper = new
	 * ObjectMapper(); String questions =
	 * mapper.writeValueAsString(selectSystemExaminationPaper); JsonNode node =
	 * mapper.readTree(questions); System.out.println(
	 * node.get(0).get("answer")); List<QuestionBank> titleList = new
	 * ArrayList<QuestionBank>(); for(int i = 0 ; i < 10 ; i++){
	 * 
	 * @SuppressWarnings("unused") int index = (int)
	 * (Math.random()*selectSystemExaminationPaper.size());
	 * titleList.add(selectSystemExaminationPaper.get(index));
	 * selectSystemExaminationPaper.remove(index); }
	 * System.out.println(titleList.toString()+"======");
	 * 
	 * }
	 * 
	 * @Test public void test4(){ SimpleDateFormat df = new
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式 Date date = new Date();
	 * String format = df.format(date); //
	 * System.out.println(format+"==========");// new Date()为获取当前系统时间 //
	 * System.out.println(date+"=========="); UserEvenLog userEvenLog = new
	 * UserEvenLog("255", 22, 55, "成功啦", date, date, 1, 1, "50%", "success");
	 * int insertAnswerResult = this.Qdao.insertAnswerResult(userEvenLog);
	 * System.out.println(insertAnswerResult+"=============="); }
	 * 
	 * @Test public void test5(){ List<Integer> list = new ArrayList<>();
	 * list.add(1100000001); list.add(1100000002); list.add(1100000003);
	 * list.add(1100000004); list.add(1100000005); int updateRightAnswer =
	 * this.Qdao.updateRightAnswer(list);
	 * System.out.println(updateRightAnswer+"=============="); }
	 * 
	 * 
	 * @Test public boolean test6() throws JsonProcessingException, IOException{
	 * 
	 * UserEvenLog userEvenLog = new UserEvenLog(); String uuid =
	 * UUID.randomUUID().toString(); userEvenLog.setEventId(uuid);
	 * if(userEvenLog.getActivityId() == null ){ userEvenLog.setActivityId(0); }
	 * int insertAnswerResult = this.Qdao.insertAnswerResult(userEvenLog);
	 * if(insertAnswerResult == 1){ return true; } List<Integer> rightId = new
	 * ArrayList<>(); List<Integer> errorId = new ArrayList<>(); String
	 * answerResults = userEvenLog.getAnswerResults(); ObjectMapper mapper = new
	 * ObjectMapper(); JsonNode node = mapper.readTree(answerResults); for (int
	 * i = 0 ; i < node.size() ; i ++) { int qbId =
	 * Integer.parseInt(node.get(i).get("qbId").toString());
	 * System.out.println(qbId+"=================="+i); int isRight =
	 * Integer.parseInt(node.get(i).get("isRight").toString());
	 * System.out.println(isRight+"=================="+i); QuestionBank
	 * questionBank = new QuestionBank( qbId ); if(0 == isRight){
	 * rightId.add(qbId); }else if(1 == isRight){ errorId.add(qbId); } } int
	 * updateRightAnswer = this.Qdao.updateRightAnswer(rightId);
	 * 
	 * int updateErrorAnswer = this.Qdao.updateErrorAnswer(errorId);
	 * 
	 * if(updateRightAnswer != 0 ){ return true; } else if(updateErrorAnswer !=
	 * 0 ){ return true; } return false; }
	 

	@Test
	public void test7() {
		System.out.println(this.dao.selectUserEvenLog(1) + "===========");
	}
	@Test
	public void test8() {
		System.out.println(this.dao.getEduEvaluate(0) + "===========");
		
	}
	@Test
	public void test9() {
		Date date = new Date();
		UserEvenLog userEvenLog = new UserEvenLog("2555", 22, 55, "成功啦", date, date, 1, 1, "50%", "3453453453");
		System.out.println(this.Qdao.updateUserEvaluate(userEvenLog) + "===========");
		
	}
	@Test
	public void test10() {
		List<CreateClassActivity> createclassActivity = this.dao.getClassActivity(1);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YY-MM-DD HH:mm:ss");
		List<String> message = new ArrayList<>();
	for (CreateClassActivity  classActivity: createclassActivity) {
		 String info = "尊敬的"+classActivity.getTeacherId()+"用户，您好！您在"+simpleDateFormat.format(classActivity.getStartTime())+"创建了课堂活动，编号为："+classActivity.getActivityId()+"的"+classActivity.getTestType()+"课程活动，试卷编号为："+classActivity.getQuestionsId();
		 message.add(info);
		}
	 System.out.println(message+"================");
		
	}
	@Test
	public void test11() throws JsonProcessingException{
		Integer selectQuestionOneId = this.dao.selectQuestionOneId(6267);
		ObjectMapper map = new ObjectMapper();
		ShowActivity activity = new ShowActivity();
		String s =String.valueOf(selectQuestionOneId);
		for (int i = 1; i < 10; i++) {
			String s1 = "1"+i;
			if(s1.equals(s.substring(0, 2))){
				switch (i) {
				case 1: activity.setType("C");break;
				case 2: activity.setType("Java");break;
				case 3: activity.setType("H5");break;
				case 4: activity.setType("数据结构");break;
				case 5: activity.setType("C#");break;
				case 6: activity.setType("Android");break;
				case 7: activity.setType("软件开发");break;
				case 8: activity.setType("数学算法");break;
				default:activity.setType("其他");break;
				}
			}
		}
		activity.setUnit(Integer.parseInt(s.substring(2,4)));
		activity.setChapter(Integer.parseInt(s.substring(4,6)));
		 System.out.println(map.writeValueAsString(activity)+"=====");
	}
	
	}


*/