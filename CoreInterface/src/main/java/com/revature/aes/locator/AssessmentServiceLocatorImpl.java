package com.revature.aes.locator;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.revature.aes.beans.AssessmentRequest;

/**
 * 
 * This service locator class provides a single location to send Rest calls
 * to the Bank team's code. 
 * 
 * @author Michelle Slay
 *
 */

@Service
public class AssessmentServiceLocatorImpl implements AssessmentServiceLocator {
	private static final String URL = "http://192.168.60.64:8080/TestBank";
	private RestTemplate restTemplate = new RestTemplate();
	Logger log = Logger.getRootLogger();
	
	/**
	 * 
	 * This method sends a rest call to the bank team's code.
	 * There the link to the generated assessment is returned
	 * in the response. 
	 * 
	 * @param AssessmentRequest
	 * @return AssessmentRequest
	 */
	@Override
	public AssessmentRequest getLink(AssessmentRequest request) {
		//Change the URL to whatever Matthew's thingy will be
		String lines = "=============================================";
		log.debug(lines);
		log.debug(request);
		log.debug(lines);
		
		System.out.println("CALLING MATTHEWS SERVICE!");
		System.out.println(request);
		ResponseEntity<AssessmentRequest> responseEntity = restTemplate.postForEntity("http://192.168.60.64:8080/TestBank/user/RandomAssessment", request, AssessmentRequest.class);	
		AssessmentRequest response = responseEntity.getBody();
		log.debug(lines);
		log.debug(response);
		log.debug(lines);
		HttpStatus httpStatus = responseEntity.getStatusCode();
		
		if(httpStatus != HttpStatus.OK)
			return null;
		
		return response;
	}
}
