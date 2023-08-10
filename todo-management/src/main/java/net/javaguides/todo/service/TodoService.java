package net.javaguides.todo.service;

import java.util.List;

import net.javaguides.todo.dto.TodoDto;

public interface TodoService {
 TodoDto addTodo(TodoDto todoDto);
 
 TodoDto getTodo(long id);
 
 List<TodoDto> getAllTodos();
 
 TodoDto updateTodo(TodoDto todoDto, long id);
 
 String deleteTodo(long id);
 
 TodoDto completeTodo(long id);
 
 TodoDto incompleteToDo(long id);
}
