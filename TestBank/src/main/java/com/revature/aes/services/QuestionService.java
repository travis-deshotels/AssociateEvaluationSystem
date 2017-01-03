package com.revature.aes.services;

import java.util.List;
import java.util.Set;

import com.revature.aes.beans.Category;
import com.revature.aes.beans.Format;
import com.revature.aes.beans.Question;
import com.revature.aes.beans.QuestionOptionsJSONHandler;

public interface QuestionService {
	public Question addQuestion(Question question);

	public Question getQuestionById(Integer id);

	public List<Question> getAllQuestions();

	public List<Question> getAllQuestionsByFormat(Format format);

	public Question updateQuestion(Question question);

	public Set<Question> getAllQuestionsByCategory(Category category);
	
	public void deleteQuestionById(Integer id);

	public Question updateQuestion(Question question);

	public Question addFullQuestion(QuestionOptionsJSONHandler question);

}
