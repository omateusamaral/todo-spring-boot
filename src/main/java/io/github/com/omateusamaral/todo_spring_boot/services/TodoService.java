package io.github.com.omateusamaral.todo_spring_boot.services;

import io.github.com.omateusamaral.todo_spring_boot.entities.Todo;
import io.github.com.omateusamaral.todo_spring_boot.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Todo save(Todo todo){
       return this.todoRepository.save(todo);
    }
}
