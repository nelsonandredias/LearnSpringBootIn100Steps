package com.polarising.spring.web2.webapplication.Controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
public class TodoController {

	@Autowired
	private TodoService todoService;

	// create an unique date format everytime we use the Date.class
	@InitBinder
	public void initBinder(WebDataBinder binder) {

		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));

	}

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showListTodosPage(ModelMap model) {

		// access session model attributes
		String sessionName = getLoggedInUserName(model);

		// use the session model name to retrieve todo list
		model.put("mTodosList", todoService.retrieveTodos(sessionName));
		return "list-todos";
	}

	// get logged in username from spring security
	private String getLoggedInUserName(ModelMap model) {

		// get logged in user bean via principal details from spring security
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}

	// Add part - Start

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		// default todo present in the form and linked to the modelAttribute "todo" bean
		model.addAttribute("todo", new Todo(0, getLoggedInUserName(model), "Default desc", new Date(), false));
		return "add-update-todo";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(@Valid Todo todo, BindingResult validationResult, ModelMap model) {

		if (validationResult.hasErrors()) {
			return "add-update-todo";
		}

		// access session model attributes
		String sessionName = getLoggedInUserName(model);

		todoService.addTodo(sessionName, todo.getDesc(), todo.getTargetDate(), false);

		return "redirect:/list-todos";
	}

	// Add part - End

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id, ModelMap model) {

		if(id == 1) {
			throw new RuntimeException("Something went wrong");
		}
		
		todoService.deleteTodo(id);

		return "redirect:/list-todos";
	}

	// Update part - Start

	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {

		Todo todo = todoService.retrieveTodoById(id);

		// pass retrieved todo to the respective page to be updated
		model.put("todo", todo);
		return "add-update-todo";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(@Valid Todo todo, BindingResult validationResult, ModelMap model) {

		todo.setUser(getLoggedInUserName(model));

		if (validationResult.hasErrors()) {
			return "add-update-todo";
		}
		todoService.updateTodo(todo);

		return "redirect:/list-todos";
	}

	// Update part - End

}
