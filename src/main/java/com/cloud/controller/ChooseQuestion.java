package com.cloud.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.JsonMappingException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cloud.model.CreateClassActivity;
import com.cloud.model.ExaminationPaper;
import com.cloud.model.QuestionBank;
import com.cloud.model.ShowActivity;
import com.cloud.model.TeacherUser;
import com.cloud.service.IQuestionInsertService;
import com.cloud.utils.constant.Constant;

// TODO: Auto-generated Javadoc
/**
 * The Class ChooseQuestion.
 */
@Controller
public class ChooseQuestion {
	
	/** The service. */
	@Autowired
	@Qualifier("QuestionInsertServiceImpl")
	private IQuestionInsertService service;
	
	/**
	 * 查询所有的题目正确率.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the model and view
	 */
	@RequestMapping("chooseMsg")
	@ResponseBody
	public Object chooseMsg(HttpServletRequest request,HttpServletResponse response){
		int teacherId =((TeacherUser)request.getSession().getAttribute(Constant.TEACHER_SESSION)).getTeacherId();
		List<QuestionBank> question = service.findQuestion(teacherId);
		ModelMap model=new ModelMap();
		if(question == null){
			model.addAttribute("error", "你还没有上传过题目");
			return new ModelAndView("question",model);
		}
		String pageNum = request.getParameter("pageNum");
		int num = -1;
		if(pageNum == null || "".equals(pageNum.trim())){
			num = 1;
		}else{
			num = Integer.parseInt(pageNum);
		}
		int total = 0;
		for (int i = 0; i < question.size(); i++) {
			total+=1;
		}
		
		List<String> list = getList(question, num);
		model.addAttribute("question", list);
		total = total/7 +1;
		if(num >= total){
			num = total;
		}
		model.addAttribute("pageNum", num);
		model.addAttribute("total", total);
		return new ModelAndView("question", model);
	}
	
	/**
	 * Gets the list.
	 *
	 * @param list the list
	 * @param num the num
	 * @return the list
	 * @throws JsonProcessingException 
	 */
	private List<String> getList(List<QuestionBank> list,int num) {
		ArrayList<String> array =  new ArrayList<String>();
		int n = (num-1) * 7;
		ObjectMapper map = new ObjectMapper();
		for(int i=0;i<list.size();i++){
			if(i>=n && i<(n+7)){
				String s;
				try {
					s = map.writeValueAsString(list.get(i));
					array.add(s);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return array;
	}
	
	/**
	 * 评价的活动ID
	 *
	 * @param request the request
	 * @return the model and view
	 */
	@RequestMapping("evaluat")
	public ModelAndView evaluate(HttpServletRequest request){
		int teacherId = ((TeacherUser)request.getSession().getAttribute(Constant.TEACHER_SESSION)).getTeacherId();
		List<CreateClassActivity> id = service.findAllActivity(teacherId);
		ModelMap model = new ModelMap();
		if(id.isEmpty() || id==null){
			return new ModelAndView("evaluate", model);
		}else{
			ArrayList array = new ArrayList();
			for (CreateClassActivity createClassActivity : id) {
				List list = new ArrayList();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				list.add(createClassActivity.getActivityId());
				list.add(format.format(createClassActivity.getStartTime()));
				array.add(list);
			}
			model.addAttribute("activityId", array);
			return new ModelAndView("evaluate", model);
		}
	}
	
	@RequestMapping("teacherMsg")
	@ResponseBody
	public Object teacherMsg(HttpServletRequest request) throws JsonProcessingException{
		int teacherId = ((TeacherUser)request.getSession().getAttribute(Constant.TEACHER_SESSION)).getTeacherId();
		List<ExaminationPaper> exams = service.findAllExam(teacherId);
		ArrayList<String> s = new ArrayList<String>();
	
		for (ExaminationPaper examinationPaper : exams) {
			String e = getActivity(examinationPaper);
			s.add(e);
		}
		return s;
	}
	
	private String getActivity(ExaminationPaper exam) throws JsonProcessingException{
		ObjectMapper map = new ObjectMapper();
		Integer questionId1 = exam.getQuestionId1();
		CreateClassActivity activity = new CreateClassActivity();
		activity.setQuestionsId(exam.getQuestionsId());
		activity.setStartTime(exam.getRegistTime());
		String s =String.valueOf(questionId1);
		for (int i = 1; i < 10; i++) {
			String s1 = "1"+i;
			if(s1.equals(s.substring(0, 2))){
				switch (i) {
				case 1: activity.setTestType("C");break;
				case 2: activity.setTestType("Java");break;
				case 3: activity.setTestType("H5");break;
				case 4: activity.setTestType("数据结构");break;
				case 5: activity.setTestType("C#");break;
				case 6: activity.setTestType("Android");break;
				case 7: activity.setTestType("软件开发");break;
				case 8: activity.setTestType("数学算法");break;
				default:activity.setTestType("其他");break;
				}
			}
		}
		activity.setUnit(Integer.parseInt(s.substring(2,4)));
		activity.setSection(Integer.parseInt(s.substring(4,6)));
		return map.writeValueAsString(activity);
	}
}
