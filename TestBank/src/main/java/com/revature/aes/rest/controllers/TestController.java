package com.revature.aes.rest.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.aes.beans.Category;
import com.revature.aes.beans.Question;
import com.revature.aes.services.CategoryService;
import com.revature.aes.services.QuestionService;

@RestController
public class TestController {
	
	@Autowired
	QuestionService qServ;
	@Autowired
	CategoryService cServ;

	@RequestMapping(value = "user/link", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public String createLink(@RequestBody int assessmentId) {
		return "blahblahblah.gov";
	}
	
/*	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public Category testCat(HttpSession session, ModelMap map){
		return cServ.getCategoryById(1);
	}*/
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public List<Question> testDao(HttpSession session, ModelMap map){
		return qServ.getAllQuestions();
	}
}