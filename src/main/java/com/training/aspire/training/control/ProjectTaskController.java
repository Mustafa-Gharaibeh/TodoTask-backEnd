package com.training.aspire.training.control;

import com.training.aspire.training.domain.ProjectTask;
import com.training.aspire.training.service.ProjectTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/board")
@CrossOrigin
public class ProjectTaskController {

  private final ProjectTaskService projectTaskService;

  public ProjectTaskController(ProjectTaskService projectTaskService) {
    this.projectTaskService = projectTaskService;
  }

  @PostMapping("")
  public ResponseEntity<?> addPTToBoard(
      @Valid @RequestBody ProjectTask projectTask, BindingResult result) {

    if (result.hasErrors()) {
      Map<String, String> errorMap = new HashMap<>();
      result
          .getFieldErrors()
          .forEach(
              fieldError -> errorMap.put(fieldError.getField(), fieldError.getDefaultMessage()));
      return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
    }

    ProjectTask newProjectTask =
        projectTaskService.saveOrUpdate(Objects.requireNonNull(projectTask));
    return new ResponseEntity<ProjectTask>(newProjectTask, HttpStatus.CREATED);
  }

  @GetMapping("/all")
  public Iterable<ProjectTask> getAllProjectTask() {
    return this.projectTaskService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getProjectTaskById(@Valid @PathVariable Long id) {
    ProjectTask theProjectTask = this.projectTaskService.findById(id);
    return new ResponseEntity<ProjectTask>(theProjectTask, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteProjectTask(@Valid @PathVariable Long id) {
    this.projectTaskService.delete(id);
    return new ResponseEntity<String>("Project Task Deleted", HttpStatus.OK);
  }
}
