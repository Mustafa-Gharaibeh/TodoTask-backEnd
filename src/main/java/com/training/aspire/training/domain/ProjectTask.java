package com.training.aspire.training.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@NoArgsConstructor
@Data
public class ProjectTask {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Summary cannot be blank")
  private String summary;
  private String acceptanceCriteria;
  private String status;
}
