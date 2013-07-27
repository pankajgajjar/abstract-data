package com.cs.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Test {
	
	@RequestMapping("/test")
	public  String welcomeHome(){
		
		return "redirect:/pages/index.html";
	}

}
