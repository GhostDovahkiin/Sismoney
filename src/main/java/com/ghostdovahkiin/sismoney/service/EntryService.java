package com.ghostdovahkiin.sismoney.service;

import com.ghostdovahkiin.sismoney.model.Entry;
import com.ghostdovahkiin.sismoney.model.Person;
import com.ghostdovahkiin.sismoney.repository.EntryRepository;
import com.ghostdovahkiin.sismoney.repository.PersonRepository;
import com.ghostdovahkiin.sismoney.service.exception.AbsentOrInactivePersonException;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntryService {

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private EntryRepository entryRepository;

  public Entry saveEntry(Entry entry) {
    Optional<Person> person = personRepository.findById(entry.getPerson().getId());
    if (person.isEmpty() || !person.get().isInactive()) {
      throw new AbsentOrInactivePersonException();
    }
    return entryRepository.save(entry);
  }
}
