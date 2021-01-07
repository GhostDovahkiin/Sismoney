package com.ghostdovahkiin.sismoney.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
  public ResponseEntity<Category> create(@RequestBody Category category, HttpServletResponse response) {
    Category savedCategory = categoryRepository.save(category);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(savedCategory.getId())
        .toUri();
    response.setHeader("Location", uri.toASCIIString());
    return ResponseEntity.created(uri).body(savedCategory);
  }
}
