package com.polarising.spring.web2.webapplication.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	
	
	
	@RequestMapping("/login")
	@ResponseBody
	public String loginMessage() {
		return "Hello World Modified";
	}
	
	
	@RequestMapping("/greetings")
	public String greetingsMessage(@RequestParam String name, ModelMap model) {
		
		// Model is used to pass data from Controller to View (JSP)
		model.put("mName", name);
		
		return "greetings";
	}
}
