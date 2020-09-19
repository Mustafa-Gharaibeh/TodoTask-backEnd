package com.training.aspire.training.repository;

import com.training.aspire.training.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTaskRepo extends CrudRepository<ProjectTask, Long> {
  ProjectTask getById(Long id);
}
