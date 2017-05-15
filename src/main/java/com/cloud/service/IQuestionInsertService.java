package com.cloud.service;

import java.util.List;

import com.cloud.model.CreateClassActivity;
import com.cloud.model.ExaminationPaper;
import com.cloud.model.QuestionBank;

// TODO: Auto-generated Javadoc
/**
 * The Interface IQuestionInsertService.
 */
public interface IQuestionInsertService {
	
	/**
	 * 查找数量.
	 *
	 * @param id the id
	 * @return the int
	 */
	public int findNumber(int id);
	
	/**
	 * Insert question.
	 *
	 * @param list the list
	 * @return the int
	 */
	public int insertQuestion(List<QuestionBank> list,int teacherId);
	
	/**
	 * Find question.
	 *
	 * @param id the id
	 * @return the list
	 */
	List<QuestionBank> findQuestion(int id);
	
	/**
	 * Find all activity.
	 *
	 * @param id the id
	 * @return the list
	 */
	List<CreateClassActivity> findAllActivity(int id);
	
	/**
	 * Find all exam.
	 *
	 * @param id the id
	 * @return the list
	 */
	List<ExaminationPaper> findAllExam(int id);
}
