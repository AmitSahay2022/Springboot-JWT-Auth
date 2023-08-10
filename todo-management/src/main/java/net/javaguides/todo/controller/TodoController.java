package net.javaguides.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.service.TodoService;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
	@Autowired
	private TodoService todoService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<TodoDto> saveTodo(@RequestBody TodoDto todoDto){
		TodoDto savedToDo = todoService.addTodo(todoDto);
		return new ResponseEntity<>(savedToDo,HttpStatus.CREATED);
	}
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("{id}")
	public ResponseEntity<TodoDto> getTodo(@PathVariable long id){
		TodoDto todoDto = this.todoService.getTodo(id);
		return new ResponseEntity<TodoDto>(todoDto,HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping
	public ResponseEntity<List<TodoDto>> getAllTodo(){
		List<TodoDto> allTodos = this.todoService.getAllTodos();
		return new ResponseEntity<List<TodoDto>>(allTodos,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("{id}")
	public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto,@PathVariable long id){
		TodoDto updateTodo = this.todoService.updateTodo(todoDto, id);
		return new ResponseEntity<TodoDto>(updateTodo,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable long id){
		String deleteTodo = this.todoService.deleteTodo(id);
		return new ResponseEntity<String>(deleteTodo,HttpStatus.ACCEPTED);
	}
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PatchMapping("{id}/completed")
	public ResponseEntity<TodoDto> completeTodo(@PathVariable long id){
		TodoDto completeTodo = this.todoService.completeTodo(id);
		return new ResponseEntity<TodoDto>(completeTodo,HttpStatus.ACCEPTED);
	}
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PatchMapping("{id}/incompleted")
	public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable long id){
		TodoDto completeTodo = this.todoService.incompleteToDo(id);
		return new ResponseEntity<TodoDto>(completeTodo,HttpStatus.ACCEPTED);
	}
}
