package io.github.com.omateusamaral.todo_spring_boot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import io.github.com.omateusamaral.todo_spring_boot.dtos.ListTodoDTO;
import io.github.com.omateusamaral.todo_spring_boot.entities.Todo;
import io.github.com.omateusamaral.todo_spring_boot.repositories.TodoRepository;
import io.github.com.omateusamaral.todo_spring_boot.services.TodoService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

  @Mock private TodoRepository todoRepository;

  @InjectMocks private TodoService todoService;

  @Test
  void shouldSaveTodoSuccessfully() {
    // Arrange
    Todo todo = new Todo();
    todo.setTitle("Estudar Spring Boot");
    todo.setDescription("Estudar testes com Mockito");
    todo.setCompleted(false);

    Todo saved = new Todo();
    saved.setTodoId("123e4567-e89b-12d3-a456-426614174000");
    saved.setTitle(todo.getTitle());
    saved.setDescription(todo.getDescription());
    saved.setCompleted(todo.getCompleted());

    when(todoRepository.save(todo)).thenReturn(saved);

    // Act
    Todo result = todoService.save(todo);

    // Assert
    assertThat(result.getTodoId()).isEqualTo("123e4567-e89b-12d3-a456-426614174000");
    assertThat(result.getTitle()).isEqualTo("Estudar Spring Boot");
    assertThat(result.getCompleted()).isEqualTo(false);
  }

  @Test
  void shouldGetTodoSuccessfully() {
    // Assert
    Todo todo = new Todo();
    todo.setTitle("Estudar Spring Boot");
    todo.setDescription("Estudar testes com Mockito");
    todo.setCompleted(false);

    Todo saved = new Todo();
    saved.setTodoId("123e4567-e89b-12d3-a456-426614174000");
    saved.setTitle(todo.getTitle());
    saved.setDescription(todo.getDescription());
    saved.setCompleted(todo.getCompleted());

    when(todoRepository.findById(todo.getTodoId())).thenReturn(Optional.of(saved));

    // Act
    Todo result = todoService.getTodo(todo.getTodoId());

    // Assert
    assertThat(result.getTodoId()).isEqualTo("123e4567-e89b-12d3-a456-426614174000");
    assertThat(result.getTitle()).isEqualTo("Estudar Spring Boot");
    assertThat(result.getCompleted()).isEqualTo(false);
  }

  @Test
  void getTodoShouldThrowNotFoundExceptionWhenTodoDoesNotExist() {
    // Assert
    String todoId = "123";

    when(todoRepository.findById(todoId)).thenReturn(Optional.empty());

    // Act && Assert
    assertThatThrownBy(() -> todoService.getTodo(todoId))
        .isInstanceOf(ResponseStatusException.class)
        .hasMessageContaining("Todo not found with id: " + todoId);
  }

  @Test
  void shouldDeleteTodo() {
    // Arrange
    String todoId = "123";

    Todo todo = new Todo();
    todo.setTodoId(todoId);

    when(todoRepository.findById(todoId)).thenReturn(Optional.of(todo));

    // Act
    todoService.deleteTodo(todoId);

    // Assert
    Mockito.verify(todoRepository, Mockito.times(1)).deleteById(todoId);
  }

  @Test
  void listTodos_shouldReturnPageFilteredByCompletedAndSearch() {
    // Arrange
    ListTodoDTO queryParms = new ListTodoDTO();
    queryParms.setSearch("Spring");

    Pageable pageable = PageRequest.of(0, 10);

    Boolean completed = false;

    Todo todo1 = new Todo();
    todo1.setTitle("Learn Spring Boot");
    todo1.setDescription("Test Spring Data JPA");

    Todo todo2 = new Todo();
    todo2.setTitle("Spring Testing");
    todo2.setDescription("Mockito and JUnit");

    List<Todo> todos = List.of(todo1, todo2);
    Page<Todo> page = new PageImpl<>(todos, pageable, todos.size());

    Mockito.when(todoRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(page);

    // Act
    Page<Todo> result = todoService.listTodos(queryParms, pageable, completed);

    // Assert
    assertThat(result.getTotalElements()).isEqualTo(2);
    assertThat(result.getContent()).containsExactly(todo1, todo2);

    Mockito.verify(todoRepository, Mockito.times(1))
        .findAll(ArgumentMatchers.<Specification<Todo>>any(), Mockito.eq(pageable));
  }

  @Test
  void listTodos_shouldReturnAllTodosWhenNoFilters() {
    // Arrange
    ListTodoDTO queryParms = new ListTodoDTO(); // search null
    Pageable pageable = PageRequest.of(0, 10);
    Boolean completed = null; // no filter

    List<Todo> todos = List.of(new Todo());
    Page<Todo> page = new PageImpl<>(todos, pageable, todos.size());

    when(todoRepository.findAll(pageable)).thenReturn(page);

    // Act
    Page<Todo> result = todoService.listTodos(queryParms, pageable, completed);

    // Assert
    assertThat(result.getTotalElements()).isEqualTo(1);
    Mockito.verify(todoRepository, Mockito.times(1)).findAll(pageable);
  }
}
