package com.cloud.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cloud.model.CreateClassActivity;
import com.cloud.model.ExaminationPaper;
import com.cloud.model.QuestionBank;

// TODO: Auto-generated Javadoc
/**
 * The Interface InsertQuestionDao.
 */
@Component("InsertQuestionDao")
public interface InsertQuestionDao {
	
	/**
	 * 插入题目.
	 *
	 * @param question the question
	 * @return the int
	 */
	int insertQuestion(List<QuestionBank> question);
	
	/**
	 * Find number.
	 *
	 * @param id the id
	 * @return the int
	 */
	int findNumber(int id);
	
	/**
	 * Find question.
	 *
	 * @param id the id
	 * @return the list
	 */
	List<QuestionBank> findQuestion(int id);
	
	/**
	 * Creat exam.
	 *
	 * @param question the question
	 * @return the int
	 */
	int creatExam(ExaminationPaper question);
	
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
