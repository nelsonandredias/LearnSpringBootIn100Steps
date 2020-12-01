package com.polarising.spring.web2.webapplication.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.polarising.spring.web2.webapplication.Interfaces.ValidateCredentials;
import com.polarising.spring.web2.webapplication.Services.LoginService;

@Controller
public class LoginController {

	@Autowired
	private ValidateCredentials validateCredentials;
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	@ResponseBody
	public String testMessage() {
		return "this is a test message";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String showLoginPage() {
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String showWelcomePage(@RequestParam String fName, @RequestParam String fPassword, ModelMap model) {
		
		boolean isValidUser = validateCredentials.validateUser(fName, fPassword);
		
		//if not valid, return to login page
		if (!isValidUser) {
			model.put("mErrorMessage", "Invalid credentials");
			return "login";
		}
		
		// Model is used to pass data from Controller to View (JSP)
		model.put("mName", fName);
		return "welcome";
	}
	
	@RequestMapping(value="/greetings", method = RequestMethod.GET)
	public String greetingsMessage(@RequestParam String name, ModelMap model) {
		
		// Model is used to pass data from Controller to View (JSP)
		model.put("mName", name);
		
		return "greetings";
	}
}
