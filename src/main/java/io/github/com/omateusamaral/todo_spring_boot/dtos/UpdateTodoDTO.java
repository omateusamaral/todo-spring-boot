package io.github.com.omateusamaral.todo_spring_boot.dtos;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class UpdateTodoDTO {

    @Size(max = 10, message = "Title must be at most 10 characters")
    private String title;

    @Size(max = 100, message = "Description must be at most 100 characters")
    private String description;

    private Boolean completed;
}