package io.github.com.omateusamaral.todo_spring_boot.controllers;

import io.github.com.omateusamaral.todo_spring_boot.entities.Todo;
import io.github.com.omateusamaral.todo_spring_boot.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping
    public Todo save(@RequestBody Todo todo){
        return this.todoService.save(todo);
    }
}
