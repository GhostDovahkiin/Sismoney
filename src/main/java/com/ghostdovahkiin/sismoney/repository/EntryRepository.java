package com.ghostdovahkiin.sismoney.repository;

import com.ghostdovahkiin.sismoney.model.Entry;
import com.ghostdovahkiin.sismoney.repository.entry.EntryRepositoryQuery;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long>, EntryRepositoryQuery {

}
