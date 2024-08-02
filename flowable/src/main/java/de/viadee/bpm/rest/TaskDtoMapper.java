package de.viadee.bpm.rest;

import org.flowable.task.api.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskDtoMapper {


  @Mapping(target = "id", source = "id")
  @Mapping(target = "variables", source = "processVariables")
  TaskDto map(Task task);

  List<TaskDto> map(List<Task> task);

}
