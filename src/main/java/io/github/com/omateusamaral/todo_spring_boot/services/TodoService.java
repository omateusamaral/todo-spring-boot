package io.github.com.omateusamaral.todo_spring_boot.services;

import io.github.com.omateusamaral.todo_spring_boot.dtos.ListTodoDTO;
import io.github.com.omateusamaral.todo_spring_boot.entities.Todo;
import io.github.com.omateusamaral.todo_spring_boot.repositories.TodoRepository;
import io.github.com.omateusamaral.todo_spring_boot.specifications.TodoSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Todo save(Todo todo){
       return this.todoRepository.save(todo);
    }

    public Page<Todo> listTodos( ListTodoDTO queryParms, Pageable pageable, Boolean completed){
        Specification<Todo> todoSpecification = null;
        String search = queryParms.getSearch();
        if (completed != null) {
            todoSpecification = TodoSpecifications.hasCompleted(completed);
        }

        if (search != null && !search.isBlank()) {
            Specification<Todo> searchSpec = TodoSpecifications.titleOrDescriptionContains(search);
            todoSpecification = (todoSpecification == null) ? searchSpec : todoSpecification.and(searchSpec);
        }

       return  (todoSpecification == null)
                ? todoRepository.findAll(pageable)
                : todoRepository.findAll(todoSpecification, pageable);

    }
}
