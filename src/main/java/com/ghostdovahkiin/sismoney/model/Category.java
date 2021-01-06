package com.ghostdovahkiin.sismoney.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;

@Entity
@Table(name = "category")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String categoryName;

  public Category() {
  }

  public Category(long id, String categoryName) {
    this.id = id;
    this.categoryName = categoryName;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCategoryName() {
    return this.categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public Category id(long id) {
    this.id = id;
    return this;
  }

  public Category categoryName(String categoryName) {
    this.categoryName = categoryName;
    return this;
  }

}
