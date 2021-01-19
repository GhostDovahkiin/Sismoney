package com.ghostdovahkiin.sismoney.service;

import com.ghostdovahkiin.sismoney.model.Person;
import com.ghostdovahkiin.sismoney.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
  @Autowired
  private PersonRepository personRepository;

  public Person updatePerson(Long id, Person updatedPerson) {
    return personRepository.findById(id).map(person -> {
      person.setpersonName(updatedPerson.getpersonName());
      person.setActive(updatedPerson.getActive());
      person.setAddress(updatedPerson.getAddress());
      return personRepository.save(person);
    }).orElseGet(() -> {
      throw new EmptyResultDataAccessException(1);
    });
  }
}
