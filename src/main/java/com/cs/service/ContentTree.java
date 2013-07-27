package com.cs.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContentTree {

	public ContentTree() {

	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String getIndexPage() {
		return "redirect:/pages/index.html";
	}
	
	
	
}
