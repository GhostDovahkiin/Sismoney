package com.ghostdovahkiin.sismoney.repository;

import com.ghostdovahkiin.sismoney.model.Entry;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {

}
