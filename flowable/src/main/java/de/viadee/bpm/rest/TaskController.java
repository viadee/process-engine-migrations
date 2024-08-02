package de.viadee.bpm.rest;

import org.flowable.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static de.viadee.bpm.process.ProcessContext.STATUS_ACCEPTED;


@RestController()
public class TaskController {
  private static final Logger log = LoggerFactory.getLogger(TaskController.class);

  private final TaskService taskService;
  private final TaskDtoMapper taskDtoMapper;

  public TaskController(final TaskService taskService,
                        final TaskDtoMapper taskDtoMapper) {
    this.taskService = taskService;
    this.taskDtoMapper = taskDtoMapper;
  }


  @GetMapping("/tasks")
  public ResponseEntity<List<TaskDto>> getTasks() {

    var tasks = taskService.createTaskQuery()
                           .includeProcessVariables().list();

    return ResponseEntity.ok()
                         .body(taskDtoMapper.map(tasks));
  }


  @PostMapping("/tasks/complete/{taskId}")
  public ResponseEntity<String> complete(@PathVariable(name = "taskId") String taskId,
                                         @RequestBody Map<String, Object> variables) {

    if (!variables.containsKey(STATUS_ACCEPTED)) {
      return ResponseEntity.badRequest()
                           .body("The variable '" + STATUS_ACCEPTED + "' is required");
    }

    log.info("Completing task with id {}", taskId);
    taskService.complete(taskId, variables);

    return ResponseEntity.ok()
                         .body("task completed [" + taskId + "]");
  }
}
