package net.javaguides.todo.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.entity.Todo;
import net.javaguides.todo.exception.ResourseNotFoundException;
import net.javaguides.todo.repository.TodoRepository;
import net.javaguides.todo.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {
	@Autowired
	private TodoRepository todoRepository;
    @Autowired
	private ModelMapper modelMapper;
	@Override
	public TodoDto addTodo(TodoDto todoDto) {
		// Convert TodoDto to Todo
		Todo todo = new Todo();
		todo.setTitle(todoDto.getTitle());
		todo.setDescription(todoDto.getDescription());
		todo.setCompleted(todoDto.isCompleted());

		Todo save = todoRepository.save(todo);

		TodoDto todoDto2 = new TodoDto();
		todoDto2.setId(save.getId());
		todoDto2.setTitle(save.getTitle());
		todoDto2.setDescription(save.getDescription());
		todoDto2.setCompleted(save.isCompleted());
		return todoDto2;
	}
	@Override
	public TodoDto getTodo(long id) {
		Todo todo = todoRepository.findById(id).orElseThrow(()->new ResourseNotFoundException("Todo not found by id: "+id));
		TodoDto map = modelMapper.map(todo, TodoDto.class);
		return map;
	}
	
	@Override
	public List<TodoDto> getAllTodos() {
		// TODO Auto-generated method stub
		List<Todo> allTodo = this.todoRepository.findAll();
		
		return allTodo.stream().map(todo->modelMapper.map(todo, TodoDto.class)).toList();
	}
  
	@Override
	public TodoDto updateTodo(TodoDto todoDto, long id) {
		// TODO Auto-generated method stub
		Todo todo = this.todoRepository.findById(id).orElseThrow(()->new ResourseNotFoundException("Todo Not found with id: "+id));
	    todo.setTitle(todoDto.getTitle());
	    todo.setDescription(todoDto.getDescription());
	    todo.setCompleted(todoDto.isCompleted());
	    Todo save = this.todoRepository.save(todo);
		return modelMapper.map(save, TodoDto.class);
	}
	
	@Override
	public String deleteTodo(long id) {
		// TODO Auto-generated method stub
		this.todoRepository.deleteById(id);
		return "Deleted Successfully";
	}
	
	@Override
	public TodoDto completeTodo(long id) {
		// TODO Auto-generated method stub
		Todo todo = this.todoRepository.findById(id).orElseThrow(()->new ResourseNotFoundException("Todo not found with id: "+id));
		todo.setCompleted(true);
		Todo save = this.todoRepository.save(todo);
		return this.modelMapper.map(save, TodoDto.class);
	}
	
	@Override
	public TodoDto incompleteToDo(long id) {
		// TODO Auto-generated method stub
		Todo todo = this.todoRepository.findById(id).orElseThrow(()->new ResourseNotFoundException("Todo not found with id: "+id));
		todo.setCompleted(false);
		Todo save = this.todoRepository.save(todo);
		return this.modelMapper.map(save, TodoDto.class);
	}
}
