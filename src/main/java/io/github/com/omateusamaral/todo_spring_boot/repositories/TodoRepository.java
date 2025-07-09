package io.github.com.omateusamaral.todo_spring_boot.repositories;

import io.github.com.omateusamaral.todo_spring_boot.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TodoRepository
    extends JpaRepository<Todo, String>, JpaSpecificationExecutor<Todo> {}
