package io.github.com.omateusamaral.todo_spring_boot.dtos;

import io.github.com.omateusamaral.todo_spring_boot.entities.Todo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDTO {

    @NotNull(message = "Title is required")
    @Size(max = 10, min = 3, message = "Must be between 3 and 10 characters")
    private String title;
    @NotNull(message = "Description is required")
    @Size(max = 100, min = 3, message = "Must be between 3 and 100 characters")
    private String description;
    @NotNull(message =  "Completed is required")
    private Boolean completed;

        public static TodoDTO fromEntity(Todo todo) {
        return TodoDTO.builder()
                .title(todo.getTitle())
                .description(todo.getDescription())
                .completed(todo.getCompleted())
                .build();
    }

    public Todo toEntity() {
        Todo todo = new Todo();
        todo.setTitle(this.title);
        todo.setDescription(this.description);
        todo.setCompleted(this.completed);
        return todo;
    }

}
