package com.training.aspire.training.service;

import com.training.aspire.training.domain.ProjectTask;
import com.training.aspire.training.repository.ProjectTaskRepo;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProjectTaskService {

  private final ProjectTaskRepo projectTaskRepo;

  public ProjectTaskService(ProjectTaskRepo projectTaskRepo) {
    this.projectTaskRepo = projectTaskRepo;
  }

  public ProjectTask saveOrUpdate(ProjectTask projectTask) {

    if (Objects.isNull(projectTask.getStatus()) || "".equals(projectTask.getStatus())) {
      projectTask.setStatus("TO_DO");
    }
    return this.projectTaskRepo.save(projectTask);
  }

  public Iterable<ProjectTask> findAll() {
    return this.projectTaskRepo.findAll();
  }

  public ProjectTask findById(Long id) {
    return this.projectTaskRepo.getById(id);
  }

  public void delete(Long id) {
    ProjectTask projectTask = findById(id);
    this.projectTaskRepo.delete(projectTask);
  }
}
