package com.ghostdovahkiin.sismoney.resource;

import java.util.List;

import com.ghostdovahkiin.sismoney.model.Category;
import com.ghostdovahkiin.sismoney.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryResource {
  @Autowired
  private CategoryRepository categoryRepository;

  @GetMapping
  public ResponseEntity<?> list() {
    List<Category> categories = categoryRepository.findAll();
    return !categories.isEmpty() ? ResponseEntity.ok(categories) : ResponseEntity.noContent().build();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody Category category) {
    categoryRepository.save(category);
  }
}
