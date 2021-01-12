package com.ghostdovahkiin.sismoney.repository;

import com.ghostdovahkiin.sismoney.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
