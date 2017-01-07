package com.revature.aes.rest.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.revature.aes.beans.Assessment;
import com.revature.aes.beans.AssessmentRequest;
import com.revature.aes.beans.Template;
import com.revature.aes.beans.TemplateQuestion;
import com.revature.aes.beans.User;
import com.revature.aes.core.SystemTemplate;
import com.revature.aes.services.AssessmentService;
import com.revature.aes.services.UserService;
/** Handles the REST requests for making assessments.
 * 
 * @author Matthew
 *
 */
@RestController
public class AssessmentRestController {

	@Autowired
	private SystemTemplate systemp;
	@Autowired
	private UserService userService;
	@Autowired
	private AssessmentService assServ;
	private final static String url = "http://192.168.60.64:8090/asmt";
//	private final static String url = "Bank";
	private RestTemplate restTemplate = new RestTemplate();

	/**
	 * Takes in a JSON that requests an assessment.
	 * 
	 * @param category:
	 *            The category that a question falls into.
	 * @return a list of questions that are of the requested categories.
	 * @throws URISyntaxException 
	 */
	@RequestMapping(value = "user/RandomAssessment", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public AssessmentRequest createAssessment(@RequestBody AssessmentRequest assReq) throws URISyntaxException {
		Template tmpl = new Template();

		Set<TemplateQuestion> finalQuestion = systemp.getRandomSelectionFromCategory(assReq);

		for (TemplateQuestion tq : finalQuestion) {
			tq.setTemplate(tmpl);
		}

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		tmpl.setTemplateQuestion(finalQuestion);
		tmpl.setCreateTimeStamp(timestamp);
		// The user id needs to be set to whatever system user is in the AES_USERS table.
		tmpl.setCreator(userService.getUserById(1));

		User user = userService.getUserByEmail(assReq.getUserEmail());

		Assessment assessment = new Assessment(user, -1, 30, null, null, tmpl);
		assessment = assServ.addNewAssessment(assessment);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		RequestEntity<Integer> request = null;
		
		request = RequestEntity.post(new URI(url + "/rest/link")).accept(MediaType.APPLICATION_JSON).body(assessment.getAssessmentId());
		ResponseEntity<String> result = restTemplate.exchange(request, String.class);

		System.out.println("-----------------------------------------------------------------");
		System.out.println("result: " + result);
		
		String link = result.getBody();
		
		assReq.setLink(link);

		return assReq;
	}

}
