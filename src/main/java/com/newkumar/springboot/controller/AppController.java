package com.newkumar.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	@RequestMapping("/test")
	String home(ModelMap modal) {
		modal.addAttribute("title","CRUD Example");
		return "index";
	}

	@RequestMapping("/partials/{page}")
	String partialHandler(@PathVariable("page") final String page) {
		return page;
	}
	
	@RequestMapping("/skv")
	String home1(ModelMap modal) {
		modal.addAttribute("title","CRUD Example");
		return "skv";
	}

	@RequestMapping("/skv/{page}")
	String partialHandler1(@PathVariable("page") final String page) {
		return page;
	}

}
