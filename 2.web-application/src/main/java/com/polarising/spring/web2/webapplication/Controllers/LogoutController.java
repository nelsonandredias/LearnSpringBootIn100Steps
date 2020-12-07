package com.polarising.spring.web2.webapplication.Controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
public class LogoutController {

	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		// get user authentication details from spring security
		Authentication authentication = SecurityContextHolder
											.getContext()
												.getAuthentication();
		
		// if user is authenticated, let's logout
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		
		return "redirect: /";
	}
	
	
	// get logged in username from spring security
	private String getLoggedInUserName() {
		
		// get logged in user bean via principal details from spring security
		Object principal = SecurityContextHolder
							.getContext()
								.getAuthentication()
									.getPrincipal();
		
		if (principal instanceof UserDetails) {
			return ((UserDetails)principal).getUsername();
		}
		
		return principal.toString();
	}
	
}
