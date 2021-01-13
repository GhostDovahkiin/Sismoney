package com.ghostdovahkiin.sismoney.resource;

import com.ghostdovahkiin.sismoney.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ghostdovahkiin.sismoney.event.CreatedResourceEvent;
import com.ghostdovahkiin.sismoney.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonResource {

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private ApplicationEventPublisher publisher;

  @PostMapping
  public ResponseEntity<Person> criar(@Valid @RequestBody Person person, HttpServletResponse response) {
    Person savedPerson = personRepository.save(person);

    publisher.publishEvent(new CreatedResourceEvent(this, response, savedPerson.getId()));

    return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
  }

  @GetMapping
  public ResponseEntity<Object> list() {
    List<Person> persons = personRepository.findAll();
    return !persons.isEmpty() ? ResponseEntity.ok(persons) : ResponseEntity.noContent().build();
  }

  @GetMapping("/{codigo}")
  public ResponseEntity<Object> buscarPeloCodigo(@PathVariable Long codigo) {
    Optional<Person> foundPerson = personRepository.findById(codigo);
    return !foundPerson.isEmpty() ? ResponseEntity.ok(foundPerson) : ResponseEntity.notFound().build();
  }
}
