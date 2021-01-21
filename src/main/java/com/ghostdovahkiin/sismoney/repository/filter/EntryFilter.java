package com.ghostdovahkiin.sismoney.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class EntryFilter {
  private String description;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dueDateFrom;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dueDataTo;

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getDueDateFrom() {
    return this.dueDateFrom;
  }

  public void setDueDateFrom(LocalDate dueDateFrom) {
    this.dueDateFrom = dueDateFrom;
  }

  public LocalDate getDueDataTo() {
    return this.dueDataTo;
  }

  public void setDueDataTo(LocalDate dueDataTo) {
    this.dueDataTo = dueDataTo;
  }

}
