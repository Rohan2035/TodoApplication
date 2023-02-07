package com.course.FirstProject.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@SessionAttributes("name")
@Controller
public class TodoControllerJpa {
	
	@Autowired
	private TodoRepository todoRepository;
	
	public TodoControllerJpa(TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}
	
	public TodoControllerJpa() {
		super();
	}
	
	
	@RequestMapping("/")
	public String listTodos(ModelMap map) {		

		String name = this.getLoggedinUsername();
		
		List<Todo> todos = todoRepository.findByUsername(name);
		map.addAttribute("todos", todos);
		
		map.put("name", name);
		
		return "todos";
		
	}
	
	// Add todo
	@GetMapping("/add-todo")
	public String addTodo(ModelMap map) {
		
		Todo todo = new Todo(0, (String) map.get("name"), null, LocalDate.now().plusYears(1), false);
		map.put("todo", todo);
		return "todo";
		
	}
	
	// Add todo
	@PostMapping("/add-todo")
	public String saveTodo(ModelMap map, @Valid Todo todo, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			
			return "todo";
			
		}
		
		String username = (String) map.get("name");
		
		todo.setUsername(username);		
		todoRepository.save(todo);
		
		return "redirect:/";
		
	}
	
	
	// Delete todo
	@RequestMapping("/delete-todo")
	public String deleteTodo(@RequestParam int id) {
		
		todoRepository.deleteById(id);
		
		return "redirect:/";
		
	}
	
	
	// Update todo
	@GetMapping("/update-todo")
	public String updateTodo(@RequestParam int id, ModelMap map) {
		
		Todo todo = todoRepository.findById(id).get(); 
		map.addAttribute("todo", todo);
		return "todo";
		
	}
	
	// Update todo
	@PostMapping("/update-todo")
	public String saveUpdatedTodo(@Valid Todo todo, BindingResult bindingResult, ModelMap map) {
		
		if(bindingResult.hasErrors()) {
			
			return "todo";
			
		}
		
		String username = (String) map.get("name");
		
		todo.setUsername(username);
		todoRepository.save(todo);
		
		return "redirect:/";
		
		
	}
	
	
	// Helper Function
	private String getLoggedinUsername() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String principal = auth.getPrincipal().toString();
		
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		char[] p = principal.toCharArray();
		
		for(char i : p) {
			
			if(i == ',') break;
			if(flag) sb.append(i);
			if(i == '=') flag = true;
			
			
		}

		return sb.toString();
		
	}
	
	
}
