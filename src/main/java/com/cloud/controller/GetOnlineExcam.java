package com.cloud.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.dao.IOnlineTestDao;
import com.cloud.model.CreateClassActivity;
import com.cloud.model.ExaminationPaper;
import com.cloud.model.StudentUser;
import com.cloud.model.User;
import com.cloud.service.IOnlineTestService;
import com.cloud.service.impl.OnlineTestServiceImpl;

@Controller  
@RequestMapping("/exam")  
public class GetOnlineExcam{

	@Resource
	private IOnlineTestService onlineTestService;
	
	/**
	 * @param id 获取试卷ID
	 * @return  返回试卷详情
	 * @throws Exception
	 */
	@RequestMapping(value="/getExam",method=RequestMethod.GET)  
	public @ResponseBody ExaminationPaper CreateExaminationPaper(@RequestParam("questionId") String id) throws Exception {
		 int questionsId = Integer.parseInt(id);  
		
		 ExaminationPaper examinationPaper = this.onlineTestService.getExcamById(questionsId);
		 
		return examinationPaper;  //将获取的试卷直接以json形式返回前台
	}
}
