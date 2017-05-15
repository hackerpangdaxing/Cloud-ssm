package com.cloud.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cloud.dao.IClassActivityDao;
import com.cloud.model.CourseIntroduction;
import com.cloud.model.CreateClassActivity;
import com.cloud.model.UserEvenLog;
import com.cloud.service.IClassActivityService;

@Service("ClassActivityService")
public class ClassActivityServiceImpl implements IClassActivityService{
	 private static Logger logger = Logger.getLogger(ClassActivityServiceImpl.class);  
	   @Autowired
	   @Qualifier("IClassActivity")
	    IClassActivityDao dao;
		@Override
		public int insertClassActivity(CreateClassActivity classActivity) {

			return this.dao.insertClassActivity(classActivity);
		}
		@Override
		public int selectClassActivity(Integer classActivityId) {
			// TODO Auto-generated method stub
			if(this.dao.selectClassActivity(classActivityId)==null){
				return 0;
			}
			return 1 ;
		}
		@Override
		public  List<CourseIntroduction>  selectClassList() {
			
			return this.dao.selectClassList();
		}
		@Override
		public UserEvenLog selectUserEvenLog(Integer userId) {
			// TODO Auto-generated method stub
			return this.dao.selectUserEvenLog(userId);
		}
		@Override
		public List<UserEvenLog> getEduEvaluate(Integer activityId) {
			// TODO Auto-generated method stub
			return this.dao.getEduEvaluate(activityId);
		}
		@Override
		public List<CreateClassActivity> getClassActivity(Integer teacherId) {
			// TODO Auto-generated method stub
			return this.dao.getClassActivity(teacherId);
		}
		@Override
		public Integer selectQuestionOneId(Integer questionsId) {
			// TODO Auto-generated method stub
			return this.dao.selectQuestionOneId(questionsId);
		}
}
