package com.cloud.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cloud.dao.InsertQuestionDao;
import com.cloud.model.CreateClassActivity;
import com.cloud.model.ExaminationPaper;
import com.cloud.model.QuestionBank;
import com.cloud.service.IQuestionInsertService;
@Service("QuestionInsertServiceImpl")
public class QuestionInsertServiceImpl implements IQuestionInsertService{
	@Autowired
	@Qualifier("InsertQuestionDao")
	private InsertQuestionDao questionDao;
	
	public int findNumber(int id) {
		return questionDao.findNumber(id);
	}

	public int insertQuestion(List<QuestionBank> list,int teacherId) {
		int id = questionDao.insertQuestion(list);
		if(id > 0){
			ArrayList<Integer> arrayList = new ArrayList<Integer>();
			for (QuestionBank questionBank : list) {
				arrayList.add(questionBank.getPdId());
			}
			ExaminationPaper paper = getPaper(arrayList,teacherId);
			int qid = questionDao.creatExam(paper);
			if(qid > 0){
				return paper.getQuestionsId();
			}else{
				return 0;
			}
		}
		return 0;
	}

	public List<QuestionBank> findQuestion(int id) {
		return  questionDao.findQuestion(id);
	}
	
	public List<CreateClassActivity> findAllActivity(int id) {
		return questionDao.findAllActivity(id);
	}
	
	public List<ExaminationPaper> findAllExam(int id) {
		
		return questionDao.findAllExam(id);
	}

	
	/**
	 * 用于生成试卷
	 * @param array
	 * @return
	 */
	private ExaminationPaper getPaper(ArrayList<Integer> array,int teacherId){
		ExaminationPaper paper = new ExaminationPaper();
		int i = 0;
		int size = array.size();
		if(i >= size){
			return paper;
		}
		
		paper.setRegistTime(new Date());
		paper.setTeacherId(teacherId);
		paper.setQuestionId1(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId2(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId3(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId4(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId5(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId6(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId7(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId8(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId9(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId10(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId11(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId12(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId13(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId14(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId15(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId16(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId17(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId18(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId19(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId20(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId21(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId22(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId23(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId24(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId25(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId26(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId27(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId28(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId29(array.get(i));
		i++;
		if(i >= size){
			return paper;
		}
		paper.setQuestionId30(array.get(i));

		return paper;
	}


	
}
