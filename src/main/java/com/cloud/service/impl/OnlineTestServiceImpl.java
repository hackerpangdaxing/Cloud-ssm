package com.cloud.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cloud.dao.IOnlineTestDao;
import com.cloud.model.CreateClassActivity;
import com.cloud.model.ExaminationPaper;
import com.cloud.model.QuestionBank;
import com.cloud.model.StudentUser;
import com.cloud.model.User;
import com.cloud.model.UserEvenLog;
import com.cloud.service.IOnlineTestService;

@Service("onlineTestService")
public class OnlineTestServiceImpl implements IOnlineTestService{
    private static Logger logger = Logger.getLogger(OnlineTestServiceImpl.class);  
    @Autowired
    @Qualifier("IOnlineTestDao")
	private IOnlineTestDao questionDao;
	//根据试卷ID获取题库id
    public ExaminationPaper getExcamById(Integer questionsId)  throws Exception {
    	   ExaminationPaper examPaper ;
			
			examPaper = (ExaminationPaper) this.questionDao.selectExaminationPaper(questionsId);
			 if(examPaper==null){
				 logger.info("当前访问的试卷不存在，请重新验证！");
			 }
		return examPaper;
	}
	@Override
	public List<QuestionBank> findQuestionsById(  List<Integer> questionList) {
		List<QuestionBank> findQuestions = this.questionDao.findQuestions(questionList);
		
		return findQuestions;
	}
	@Override
	public List<QuestionBank> selectSystemExaminationPaper(String classType) {
		
		return this.questionDao.selectSystemExaminationPaper(classType);
	}
	@Override
	public int insertAnswerResult(UserEvenLog userEventLog) {
		
		return this.questionDao.insertAnswerResult(userEventLog);
	}
	@Override
	public int updateRightAnswer(List<Integer> rightId) {
		// TODO Auto-generated method stub
		return this.questionDao.updateRightAnswer(rightId);
	}
	@Override
	public int updateErrorAnswer(List<Integer> rightId) {
		// TODO Auto-generated method stub
		return this.questionDao.updateErrorAnswer(rightId);
	}
	@Override
	public int updateUserEvaluate(UserEvenLog userEvenLog) {
		// TODO Auto-generated method stub
		return this.questionDao.updateUserEvaluate(userEvenLog);
	}




}
