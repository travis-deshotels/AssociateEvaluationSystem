package com.revature.aes.controllers;

import javax.servlet.http.HttpSession;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.aes.beans.Assessment;
import com.revature.aes.beans.Format;
import com.revature.aes.dao.AssessmentService;

@RestController
@RequestMapping("/rest")
public class GetAssessmentController {
	@Autowired
	private AssessmentService service;

	// @Autowired
	private HttpSession httpSession;

	@RequestMapping(value = "/link", method = RequestMethod.POST)
	public String getAssessmentID(@RequestBody int assessmentId) {
		httpSession.setAttribute("assessmentId", assessmentId);
		return "thisIsALink";
	}

	@RequestMapping(value = "{id}")
	public String getAssessment(@PathVariable("id") int AssessmentId) throws JsonProcessingException {

		System.out.println("I'm here!" + AssessmentId);
		String JSONString;
		ObjectMapper mapper = new ObjectMapper();
		// httpSession.getAttribute("assessmentId");
		Assessment assessment = new Assessment();
		try {
			assessment = service.getAssessmentById(AssessmentId);
		} catch (NullPointerException e) {
			System.out.println("error");
			e.printStackTrace();
		}
		// Assessment assessment = service.findOne(AssessmentId);
		System.out.println(assessment);
		JSONString = mapper.writeValueAsString(assessment);
		return JSONString;
	}
}