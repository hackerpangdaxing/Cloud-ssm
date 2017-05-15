package com.cloud.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cloud.model.QuestionBank;
import com.cloud.model.TeacherUser;
import com.cloud.service.IQuestionInsertService;
import com.cloud.utils.constant.Constant;
import com.cloud.utils.question.InsertQuestionUtil;






/**
 * The Class FileUpLoad.
 */
@Controller
public class FileUpLoad {
		
	@Autowired
	@Qualifier("QuestionInsertServiceImpl")
	private IQuestionInsertService service;
	
	/**
	 * 获取文件
	 *
	 * @param file the file
	 * @param request the request
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@RequestMapping(value="file",method=RequestMethod.POST)
	public ModelAndView addFile(MultipartFile file,HttpServletRequest request) throws IOException{
		String name = file.getOriginalFilename();
		if(!file.isEmpty()||name.endsWith(".xls") || name.endsWith(".xlsx")){
			int  teacherId = ((TeacherUser)request.getSession().getAttribute(Constant.TEACHER_SESSION)).getTeacherId();
			String selectName = request.getParameter("selectName");
			String chapternum = request.getParameter("chapternum");
			String sectionnum = request.getParameter("sectionnum");
			InputStream in = file.getInputStream();
			InsertQuestionUtil excel = new InsertQuestionUtil();
			List<List<String>> list = excel.read(name, in);
			List<QuestionBank> question = setQuestion(list,selectName,chapternum,sectionnum,teacherId);
			int id = service.insertQuestion(question,teacherId);
			ModelMap map = new ModelMap();
			System.out.println(id);
			map.addAttribute("paper",id);
			return new ModelAndView("success",map);
		}else{
			return new ModelAndView("error");
		}
		
		
	}
	
	/**
	 * 用于生成题目id 生成数据库题目集合
	 * 
	 * @param list
	 * @param selectName
	 * @param chapternum
	 * @param sectionnum
	 * @return List<QuestionBank>
	 */
	
	@SuppressWarnings("unused")
	private List<QuestionBank> setQuestion(List<List<String>> list,String selectName,
			String chapternum,String sectionnum,int teacherId){
		
		String cid = null;
		if("C".equals(selectName)){
			 cid = "11";
		}else if("JAVA".equals(selectName)){
			 cid = "12";
		}else if("H5".equals(selectName)){
			 cid = "13";
		}else if("数据结构".equals(selectName)){
			selectName = "dataStructure";
			 cid = "14";
		}else if("C#".equals(selectName)){
			 cid = "15";
		}else if("Android".equals(selectName)){
			 cid = "16";
		}else if("软件开发".equals(selectName)){
			selectName = "SoftwareDevelopment";
			 cid = "17";
		}else if("数学算法".equals(selectName)){
			selectName = "Mathematical";
			 cid = "18";
		}else{
			selectName = "other";
			 cid = "19";
		}
		ArrayList<QuestionBank> dataList = new ArrayList<QuestionBank>();
		//用于生成PdId
		int tId = service.findNumber(teacherId);
		String tid = String.valueOf(tId);
		String id = cid + chapternum + sectionnum + teacherId + tid;
		int pdId = Integer.parseInt(id);
		for (List<String> list2 : list) {
			QuestionBank bank = new QuestionBank();
			pdId +=1;
			
			bank.setPdId(pdId);
			bank.setAnswerType(Integer.parseInt(list2.get(0)));
			bank.setTestType(selectName);
			bank.setQuestionTitle(list2.get(1));
			bank.setA(list2.get(2));
			bank.setB(list2.get(3));
			bank.setC(list2.get(4));
			bank.setD(list2.get(5));
			bank.setE(list2.get(6));
			bank.setF(list2.get(7));
			bank.setG(list2.get(8));
			bank.setAnswer(list2.get(9));
			bank.setAnalysis(list2.get(10));
			bank.setTeacherId(teacherId);
			dataList.add(bank);
		}
		return dataList;
	}
	
	
	@RequestMapping("editFile")
	public void editFile(){
	}
}
