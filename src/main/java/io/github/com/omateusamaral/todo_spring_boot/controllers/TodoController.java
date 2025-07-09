package io.github.com.omateusamaral.todo_spring_boot.controllers;

import io.github.com.omateusamaral.todo_spring_boot.dtos.ListTodoDTO;
import io.github.com.omateusamaral.todo_spring_boot.dtos.TodoDTO;
import io.github.com.omateusamaral.todo_spring_boot.dtos.UpdateTodoDTO;
import io.github.com.omateusamaral.todo_spring_boot.entities.Todo;
import io.github.com.omateusamaral.todo_spring_boot.services.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoDTO> createTodo(@Valid @RequestBody TodoDTO todoDTO) {
        Todo todo = todoDTO.toEntity();
        Todo saved = todoService.save(todo);
        return ResponseEntity.ok(TodoDTO.fromEntity(saved));
    }

    @GetMapping
    public ResponseEntity<Page<Todo>> listTodos(@ModelAttribute @Valid ListTodoDTO queryParams, Pageable pageable, @Param("completed") Boolean  completed){
        return ResponseEntity.ok(todoService.listTodos(queryParams, pageable, completed));
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getTodo(@PathVariable("todoId") String todoId){
        return ResponseEntity.ok(todoService.getTodo(todoId));
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("todoId") String todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodo(
            @PathVariable String todoId,
            @RequestBody @Valid UpdateTodoDTO todoToUpdate
    ) {
        Todo updated = todoService.updateTodo(todoId, todoToUpdate);
        return ResponseEntity.ok(updated);
    }
}
