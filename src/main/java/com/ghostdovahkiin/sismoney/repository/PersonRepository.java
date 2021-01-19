package com.ghostdovahkiin.sismoney.repository;

import com.ghostdovahkiin.sismoney.model.Person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
