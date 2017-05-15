package com.cloud.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.model.CourseIntroduction;
import com.cloud.model.QuestionBank;
import com.cloud.model.UserEvenLog;
import com.cloud.service.IClassActivityService;

/**
 * @author 胖大星
 * 显示首页详情
 */
@Controller  
@RequestMapping("/show")  
public class ShowDetailControl {
	
	@Resource
	private IClassActivityService service;
	/**
	 * @return  返回首页课程内容详情
	 * @throws Exception
	 */
	@RequestMapping(value="/showList",method=RequestMethod.GET)  
	public @ResponseBody  List<CourseIntroduction>  getShowDetail() throws Exception {
		 List<CourseIntroduction>  selectClassList = this.service.selectClassList();
		return selectClassList;  
	}
	/**
	 * @param userId   用户ID
	 * @return  返回用户 答题正确题数 错误题数 和 正确率
	 * @throws Exception
	 */
	@RequestMapping(value="/getUserEvenLog",method=RequestMethod.GET)  
	public @ResponseBody  UserEvenLog  getUserEvenLog(Integer userId) throws Exception {
		 UserEvenLog selectUserEvenLog = this.service.selectUserEvenLog(userId);
		
		return selectUserEvenLog;  
	}
	
	/**
	 * 获取学生对教学活动的评价
	 * @param activityId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getEduEvaluate",method=RequestMethod.GET)  
	public @ResponseBody  List<UserEvenLog>  getEduEvaluate(Integer activityId) throws Exception {
		
		return this.service.getEduEvaluate(activityId); 
	}

	
}
