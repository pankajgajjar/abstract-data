package com.cs.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tree")
public class TreeController {

	public TreeController() {

	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView getIndexPage() {
		return new ModelAndView("index");
	}
}
