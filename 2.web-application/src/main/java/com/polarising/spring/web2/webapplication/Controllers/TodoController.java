package com.polarising.spring.web2.webapplication.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.polarising.spring.web2.webapplication.Interfaces.ValidateCredentials;
import com.polarising.spring.web2.webapplication.Services.LoginService;
import com.polarising.spring.web2.webapplication.Services.TodoService;

@Controller
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	
	@RequestMapping(value="/list-todos", method = RequestMethod.GET)
	public String showListTodosPage(ModelMap model) {
		
		model.put("mTodosList", todoService.retrieveTodos("Nelson"));
		return "list-todos";
	}

}
