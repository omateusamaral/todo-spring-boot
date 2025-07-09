package io.github.com.omateusamaral.todo_spring_boot.mappers;

import io.github.com.omateusamaral.todo_spring_boot.dtos.UpdateTodoDTO;
import io.github.com.omateusamaral.todo_spring_boot.entities.Todo;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface TodoMapper {

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateFromDto(UpdateTodoDTO dto, @MappingTarget Todo entity);
}
