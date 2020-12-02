package com.polarising.spring.web2.webapplication.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.polarising.spring.web2.webapplication.Interfaces.ValidateCredentials;
import com.polarising.spring.web2.webapplication.Services.LoginService;

// session scope allow us to store values across multiple requests.
// the scope of the request parameters is just that particular request. (they dont persist across multiple requests)
// the scope of the model values is just that particular request. (they dont persist across multiple requests) 




@Controller
@SessionAttributes("sessionName") // persist the value across multiple requests
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
	
	// submit login form
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String showWelcomePage(@RequestParam String fName, @RequestParam String fPassword, ModelMap model) {
		
		boolean isValidUser = validateCredentials.validateUser(fName, fPassword);
		
		//if not valid, return to login page
		if (!isValidUser) {
			model.put("mErrorMessage", "Invalid credentials");
			return "login";
		}
		
		// Model is used to pass data from Controller to View (JSP)
		model.put("sessionName", fName);
		return "welcome";
	}
	
	@RequestMapping(value="/greetings", method = RequestMethod.GET)
	public String greetingsMessage(@RequestParam String name, ModelMap model) {
		
		// Model is used to pass data from Controller to View (JSP)
		model.put("mName", name);
		
		return "greetings";
	}
}
