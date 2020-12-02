package com.polarising.spring.web2.webapplication.Controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.polarising.spring.web2.webapplication.Interfaces.ValidateCredentials;
import com.polarising.spring.web2.webapplication.Services.LoginService;
import com.polarising.spring.web2.webapplication.Services.TodoService;

@Controller
@SessionAttributes("sessionName") // persist the value across multiple requests
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	
	@RequestMapping(value="/list-todos", method = RequestMethod.GET)
	public String showListTodosPage(ModelMap model) {
		
		//access session model attributes
		String sessionName = (String) model.get("sessionName");
		
		//use the session model name to retrieve todo list
		model.put("mTodosList", todoService.retrieveTodos(sessionName));
		return "list-todos";
	}
	
	@RequestMapping(value="/add-todo", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		
		return "add-update-todo";
	}

	@RequestMapping(value="/add-todo", method = RequestMethod.POST)
	public String addTodo(@RequestParam String fDescription, ModelMap model) {
		
		//access session model attributes
		String sessionName = (String) model.get("sessionName");
				
		todoService.addTodo(sessionName,fDescription, new Date(), false);
		
		return "redirect:/list-todos";
	}
}
