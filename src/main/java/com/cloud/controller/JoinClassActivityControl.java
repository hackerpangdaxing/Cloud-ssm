package com.cloud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.dao.IOnlineTestDao;
import com.cloud.model.ActivityRoom;
import com.cloud.model.CreateClassActivity;
import com.cloud.model.ExaminationPaper;
import com.cloud.model.QuestionBank;
import com.cloud.model.RoomUser;
import com.cloud.model.StudentUser;
import com.cloud.model.User;
import com.cloud.model.UserEvenLog;
import com.cloud.service.IClassActivityService;
import com.cloud.service.IOnlineTestService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/ClassActivity")
public class JoinClassActivityControl {
	@Autowired
	ServletContext servletContext;

	@Resource
	private IOnlineTestService onlineTestService;

	private static Logger logger = Logger.getLogger(JoinClassActivityControl.class);

	// @Resource
	// private IClassActivityService activityService;
	// http://localhost:8080/Cloud/ClassActivity/join?classActivityId=?&studentId=?
	/**
	 * @param id
	 *            活动id
	 * @param sid
	 *            学生id
	 * @return 判断用户是否已经加入房间 成功则返回所有房间相关的信息
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/joinTest", method = RequestMethod.GET)
	public @ResponseBody ActivityRoom getExaminationPaper(@RequestParam("classActivityId") String id,
			@RequestParam("studentId") String sid) throws Exception {
		int classActivityId = Integer.parseInt(id);
		int studentId = Integer.parseInt(sid);
		Map<Integer, ActivityRoom> ActivityRooms = (Map<Integer, ActivityRoom>) servletContext
				.getAttribute("ActivityRooms");
		ArrayList<Integer> activityid = (ArrayList<Integer>) servletContext.getAttribute("activityIds");
		ArrayList<User> user = (ArrayList<User>) servletContext.getAttribute("User");
		Map<Integer, RoomUser> roomUser = (Map<Integer, RoomUser>) servletContext.getAttribute("RoomUser");
		// 添加学生用户
		/*
		 * roomUser = new RoomUser(); user.add(new User(studentId, "学生用户加入",
		 * "password")); roomUser.setUser(user);
		 * ActivityRooms.get(classActivityId).setRoomUser(roomUser);
		 */
		// 如果邀请码正确的话 加入房间
		if(activityid == null){
			return new ActivityRoom();
		}else if (activityid.contains(classActivityId)) {
			/*
			 * if (user == null) { user = new ArrayList<User>();
			 * servletContext.setAttribute("User", user); } user.add(new
			 * User(studentId, "student用户加入房間", "password")); RoomUser room =
			 * new RoomUser(user); if (roomUser == null) { roomUser = new
			 * HashMap<>(); servletContext.setAttribute("roomUser", roomUser); }
			 * roomUser.put(classActivityId, room);
			 * ActivityRooms.get(classActivityId).setRoomUser(roomUser);
			 */
			
			ActivityRoom acctivityRoome = new ActivityRoom(ActivityRooms.get(classActivityId).getActivityid(), 
					ActivityRooms.get(classActivityId).getChatRoom(), ActivityRooms.get(classActivityId).getSignIn(),
					ActivityRooms.get(classActivityId).getEduEvaluate(), ActivityRooms.get(classActivityId).getQuestionBank());
			
			return  acctivityRoome;
			
		}
		return new ActivityRoom();
	}

	/**
	 * @param type
	 *            答题类型
	 * @return 系统自动生成的试卷详情
	 * @throws Exception
	 */
	@RequestMapping(value = "/joinSystemTest", method = RequestMethod.GET)
	public @ResponseBody Object getSystemExaminationPaper(@RequestParam("classType") String type) throws Exception {
		List<QuestionBank> selectSystemExaminationPaper = onlineTestService.selectSystemExaminationPaper(type);
		ArrayList titleList = new ArrayList();
		for (int i = 0; i < 10; i++) {
			int index = (int) (Math.random() * selectSystemExaminationPaper.size());
			titleList.add(selectSystemExaminationPaper.get(index));
			selectSystemExaminationPaper.remove(index);
		}
		return titleList;
	}

	/**
	 * @param userEvenLog
	 *               用户答题记录
	 * @return 是否将用户答案记录到数据库
	 * @throws Exception
	 */
	@RequestMapping(value = "/submitAnswer", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public Boolean getExaminationPaperAnswer(@RequestBody UserEvenLog userEvenLog) throws Exception {
		String uuid = UUID.randomUUID().toString();
		userEvenLog.setEventId(uuid);
		if (userEvenLog.getActivityId() == null) {
			userEvenLog.setActivityId(0);
		}
		int insertAnswerResult = this.onlineTestService.insertAnswerResult(userEvenLog);
		if (insertAnswerResult == 1) {

			List<Integer> rightId = new ArrayList<>();
			List<Integer> errorId = new ArrayList<>();
			String answerResults = userEvenLog.getAnswerResults();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(answerResults);
			for (int i = 0; i < node.size(); i++) {
				int qbId = Integer.parseInt(node.get(i).get("qbId").toString());
				int isRight = Integer.parseInt(node.get(i).get("isRight").toString());
				if (0 == isRight) {
					rightId.add(qbId);
				} else if (1 == isRight) {
					errorId.add(qbId);
				}
			}
			if(rightId == null)
			{
				if(errorId == null){
					return false;
				}else{
					int updateErrorAnswer = this.onlineTestService.updateErrorAnswer(errorId);
					if (updateErrorAnswer != 0) {
						return true;
					}
				}
			}else{
				int updateRightAnswer = this.onlineTestService.updateRightAnswer(rightId);
				if (updateRightAnswer != 0) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 上传用户的教学评价
	 * @param userEvenLog
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadEvaluate", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public Boolean upLoadUserEvaluate( @RequestBody UserEvenLog userEvenLog) throws Exception {
		int updateUserEvaluate = this.onlineTestService.updateUserEvaluate(userEvenLog);
		if (updateUserEvaluate == 1) {
			return true;
		}
		return false;
		
	}

}
