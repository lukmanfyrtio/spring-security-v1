package io.java.app.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {
	
	@GetMapping("login")
	public String getLoginPage() {
		return "login";
	}
	
	@GetMapping("courses")
	public String getCourse() {
		return "courses";
	}

}
