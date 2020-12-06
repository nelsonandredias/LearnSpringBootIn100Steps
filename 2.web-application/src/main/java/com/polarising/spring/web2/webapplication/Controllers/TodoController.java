package com.polarising.spring.web2.webapplication.Controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.polarising.spring.web2.webapplication.Interfaces.ValidateCredentials;
import com.polarising.spring.web2.webapplication.Services.LoginService;
import com.polarising.spring.web2.webapplication.Services.TodoService;
import com.polarising.spring.web2.webapplication.models.Todo;

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
		//default todo present in the form and linked to the modelAttribute "todo" bean
		model.addAttribute("todo", new Todo(0, (String) model.get("sessionName"), "Default desc", new Date(), false));
		return "add-update-todo";
	}

	@RequestMapping(value="/add-todo", method = RequestMethod.POST)
	public String addTodo(@Valid Todo todo, BindingResult validationResult, ModelMap model) {
		
		System.out.println("bindingResult ----------------------->" + validationResult);
		
		if(validationResult.hasErrors()) {
			return "add-update-todo";
		}
		
		//access session model attributes
		String sessionName = (String) model.get("sessionName");
				
		todoService.addTodo(sessionName,todo.getDesc(), new Date(), false);
		
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value="/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id, ModelMap model) {
		
		todoService.deleteTodo(id);
		
		return "redirect:/list-todos";
	}
}
