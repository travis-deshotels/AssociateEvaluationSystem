package com.revature.aes.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;

@Controller
public class TrainerController{
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getIndex(){
		System.out.println("made it here:");
;		return "forward:resources/pages/trainerHome.html";
	}
}

