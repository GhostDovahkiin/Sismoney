package com.ghostdovahkiin.sismoney.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ghostdovahkiin.sismoney.event.CreatedResourceEvent;
import com.ghostdovahkiin.sismoney.model.Category;
import com.ghostdovahkiin.sismoney.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryResource {
  @Autowired
  private CategoryRepository categoryRepository;
  @Autowired
  private ApplicationEventPublisher publisher;

  @GetMapping
  public ResponseEntity<Object> list() {
    List<Category> categories = categoryRepository.findAll();
    return !categories.isEmpty() ? ResponseEntity.ok(categories) : ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<Category> create(@Valid @RequestBody Category category, HttpServletResponse response) {
    Category savedCategory = categoryRepository.save(category);
    publisher.publishEvent(new CreatedResourceEvent(this, response, savedCategory.getId()));
    return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> searchById(@PathVariable Long id) {
    Optional<Category> foundCategory = categoryRepository.findById(id);
    return !foundCategory.isEmpty() ? ResponseEntity.ok(foundCategory) : ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> removeByID(@PathVariable Long id) {
    Optional<Category> foundCategory = categoryRepository.findById(id);
    if (foundCategory.isEmpty()) {
      return ResponseEntity.notFound().build();
    } else {
      categoryRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Category> update(@PathVariable Long id, @Valid @RequestBody Category updatedCategory) {
    return categoryRepository.findById(id).map(category -> {
      category.setCategoryName(updatedCategory.getCategoryName());
      return ResponseEntity.ok(categoryRepository.save(category));
    }).orElseGet(() -> {
      updatedCategory.setId(id);
      categoryRepository.save(updatedCategory);
      return ResponseEntity.badRequest().build();
    });
  }
}
