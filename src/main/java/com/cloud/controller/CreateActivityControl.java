package com.cloud.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloud.model.ActivityMessage;
import com.cloud.model.ActivityRoom;
import com.cloud.model.CreateClassActivity;
import com.cloud.model.ExaminationPaper;
import com.cloud.model.QuestionBank;
import com.cloud.model.RoomUser;
import com.cloud.model.ShowActivity;
import com.cloud.model.TeacherUser;
import com.cloud.model.User;
import com.cloud.service.IClassActivityService;
import com.cloud.service.IOnlineTestService;
import com.cloud.service.impl.ClassActivityServiceImpl;
import com.cloud.testmybatis.JacksonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

@Controller
public class CreateActivityControl {
	@Autowired
	private ServletContext servletContext;

	@Resource
	private IOnlineTestService testService;
	@Resource
	private IClassActivityService activityService;
	// @Resource
	// private ICheckUserService userService;
	private static Logger logger = Logger.getLogger(CreateActivityControl.class);

	/**
	 * ActivityRooms是所有的房间，ActivityRoom里面有ActivityId，公告，以及试卷对象，
	 * 里面要监测各种数据的存在，比如ActivityId已经存在于当前的servletContext或者数据库。
	 * 第一次启动的时候，ActivityRooms和classActivityId为空，我们要进行判断并且重新给servletContext赋值
	 * 
	 * @param classActivity
	 * @param signIn
	 * @param printWriter
	 * @URL:/createAcitvity?activityId=?&teacherId=?&questionsId=?
	 * @return
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/createAcitvity")
	public @ResponseBody ActivityRoom CreateActivityRoom(@RequestBody CreateClassActivity classActivity) throws Exception {
		String format = new SimpleDateFormat("HHmmss").format(new Date());
		Map<Integer, ActivityRoom> ActivityRooms = (Map<Integer, ActivityRoom>) servletContext.getAttribute("ActivityRooms");
		ArrayList<Integer> classActivityId = (ArrayList<Integer>) servletContext.getAttribute("activityIds");
		
		ArrayList<QuestionBank> questionBank = (ArrayList<QuestionBank>) servletContext.getAttribute("QuestionBank");// questionBank
		ArrayList<User> user = (ArrayList<User>) servletContext.getAttribute("User");
		// 完整的试卷详情
		Integer teacherId = classActivity.getTeacherId();
		Random random = new Random();
		classActivity.setActivityId(teacherId+Integer.valueOf(format)+random.nextInt(6556126));
		Integer activityId = classActivity.getActivityId();
		String eduEvaluate = classActivity.getEduEvaluate();
		// userService.findUser(name, password)
		if (classActivityId != null && classActivityId.contains(activityId)) {
			//printWriter.write("Already exist");

			return new ActivityRoom();
		} else if (0 != this.activityService.selectClassActivity(activityId) && classActivityId.contains(activityId)) {
			logger.info("已经存在在数据库 并且也存在在servletContext中");
		}

		// 如果在线测试模块不为空则查询相应的试卷详情
		if (classActivity.getOnlineTest() != null || classActivity.getOnlineTest() != "") {
			// TODO 获取试卷所有题目id集合
			ExaminationPaper examinationPaper = testService.getExcamById(classActivity.getQuestionsId());
			String examPaperToJson = JacksonUtil.toJSon(examinationPaper);
			// 将创建的试卷所有题目id存在此处
			List<Integer> questionList = new ArrayList();
			JSONObject jsonObject = JSONObject.parseObject(examPaperToJson);
			for (java.util.Map.Entry<String, Object> entry : jsonObject.entrySet()) {
				if (null != entry.getValue() && entry.getValue() != "") {
					questionList.add((Integer) entry.getValue());
				}
			}
			// 获取试卷详情
			questionBank = (ArrayList<QuestionBank>) testService.findQuestionsById(questionList);

		}
		// 创建教学活动
		int testType = classActivity.getQuestionsId();
		Integer selectQuestionOneId = this.activityService.selectQuestionOneId(testType);
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
		classActivity.setUnit(activity.getUnit());
		classActivity.setSection(activity.getChapter());
		classActivity.setTestType(activity.getType());
		classActivity.setStartTime(new Date());
		if (1 == activityService.insertClassActivity(classActivity)) {
			logger.info("创建教学活动成功！" + "=========================");
		} else {
			return new ActivityRoom();
		}

		/**
		 * 创建活动房间 模板组件可以为空
		 * 
		 * @param activityId
		 *            房间号 / 邀请码
		 * @param signIn
		 *            签到
		 * @param eduEvaluate
		 *            教学评价
		 * @param questionBank
		 *            小测 / 完整的试卷详情
		 * @param roomUser
		 *            聊天室
		 * @return
		 * @throws Exception
		 */
		// 添加用户到房间

		/*
		 * if (user == null) { user = new ArrayList<User>();
		 * servletContext.setAttribute("User", user); } user.add(new
		 * User(classActivity.getTeacherId(), "老师用户加入房間", "password")); RoomUser
		 * room = new RoomUser(user); if (roomUser == null) { roomUser = new
		 * HashMap<>(); servletContext.setAttribute("roomUser", roomUser); }
		 * roomUser.put(activityId, room);
		 * 
		 * System.out.println(roomUser.toString()+"===============");
		 */
		String signIn = classActivity.getSignIn();
		String chatRoom = classActivity.getChatRoom();
		ActivityRoom activityRoom = new ActivityRoom(activityId, chatRoom,signIn, eduEvaluate, questionBank);
		if (ActivityRooms == null) {
			ActivityRooms = new HashMap<>();
			servletContext.setAttribute("ActivityRooms", ActivityRooms);
		}
		if (classActivityId == null) {
			classActivityId = new ArrayList<>();
			servletContext.setAttribute("activityIds", classActivityId);
		}
		ActivityRooms.put(activityId, activityRoom);
		classActivityId.add(activityId);
		//printWriter.write("创建成功，房间ID是" + activityId);
		System.out.println( ActivityRooms.get(activityId)+"============");
		return ActivityRooms.get(activityId);
	}

	/**
	 * 系统通知： 返回教室所创建的课堂活动记录
	 * @param teacherId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getActivityInfo")
	public @ResponseBody List<CreateClassActivity> getActivityInfo( Integer teacherId ) throws Exception {
		List<CreateClassActivity> classActivity = this.activityService.getClassActivity(teacherId);
	System.out.println(classActivity+"++++++++++++++++++");
		return classActivity;
	
	}
	
	@RequestMapping("/getId")
	public void test1(PrintWriter printWriter) {

		printWriter.write("getId");
	}

	@RequestMapping("/close")
	public void CloseRoom(Integer activityId, PrintWriter printWriter) throws Exception {
		// System.out.println("id:=============" + id);
		Map<Integer, ActivityRoom> ActivityRooms = (Map<Integer, ActivityRoom>) servletContext
				.getAttribute("ActivityRooms");
		// System.out.println("---------------------:" +
		// classActivityId.toString());
//		if (classActivityId == null || ActivityRooms == null || users == null) {
//			printWriter.write("ERROR");
//		} else if (classActivityId.size() == 0 || ActivityRooms.size() == 0 || users.size() == 0) {
//			printWriter.write("ERROR");
//		} else {
//			classActivityId.remove(id);
//			/*
//			 * for (ActivityRoom activityRoom : ActivityRooms) { if
//			 * (activityRoom.getActivityid() == id) { //
//			 * ActivityRooms.remove(activityRoom);
//			 * ActivityRoomsRemove.add(activityRoom); } }
//			 */
//			for (RoomUser roomUser : users) {
//				if (roomUser.getActicityId() == id) {
//					// users.remove(roomUser);
//					usersRemove.add(roomUser);
//					try {
//						// 发送的消息为ActivityMessage的json
//						roomUser.getWebSocketSession().sendMessage(new TextMessage("本活动已经被结束"));
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//			}
			ObjectMapper objectMapper = new ObjectMapper();
			ActivityRoom activityRoom = ActivityRooms.get( activityId );
			for ( Entry<Integer,RoomUser> roomUser : activityRoom.getRoomUser().entrySet()) {
				roomUser.getValue().getWebSocketSession().sendMessage(
						new TextMessage(
								objectMapper.writeValueAsString(
										new ActivityMessage("close", activityId, "本房间已经被关闭")
										)
								)
						);
			}
			ActivityRooms.remove( activityId);
			printWriter.write("success");
		
	}

	@RequestMapping("/errorOperation")
	public void errorOperation(PrintWriter printWriter) {
		printWriter.write("error");
	}
	
	
	/*@RequestMapping(value = "/getActivityRoom")
	public @ResponseBody ActivityRoom getActivityRoom( Integer activityId) throws Exception {
		
		@SuppressWarnings("unchecked")
		Map<Integer, ActivityRoom> ActivityRooms = (Map<Integer, ActivityRoom>) servletContext.getAttribute("ActivityRooms");
        System.out.println(ActivityRooms.get(activityId)+"==============");
		return ActivityRooms.get(activityId);
	}
*/
	
}
