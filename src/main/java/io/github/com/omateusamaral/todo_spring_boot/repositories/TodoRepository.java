package io.github.com.omateusamaral.todo_spring_boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.com.omateusamaral.todo_spring_boot.entities.Todo;
public interface TodoRepository extends JpaRepository<Todo,String> {
}
