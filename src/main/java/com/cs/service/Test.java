package com.cs.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test {
	
	@RequestMapping("/")
	public @ResponseBody String welcomeHome(){
		
		return "Test Succeded";
	}

}
