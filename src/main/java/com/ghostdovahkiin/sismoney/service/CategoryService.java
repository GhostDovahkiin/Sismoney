package com.ghostdovahkiin.sismoney.service;

import com.ghostdovahkiin.sismoney.model.Category;
import com.ghostdovahkiin.sismoney.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  public Category updateCategory(Long id, Category updatedCategory) {
    return categoryRepository.findById(id).map(category -> {
      category.setCategoryName(updatedCategory.getCategoryName());
      return categoryRepository.save(category);
    }).orElseGet(() -> {
      throw new EmptyResultDataAccessException(1);
    });
  }
}
