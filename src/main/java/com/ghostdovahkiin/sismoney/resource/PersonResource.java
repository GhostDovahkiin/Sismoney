package com.ghostdovahkiin.sismoney.resource;

import com.ghostdovahkiin.sismoney.repository.PersonRepository;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ghostdovahkiin.sismoney.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/persons")
public class PersonResource {
  @Autowired
  private PersonRepository personRepository;

  @PostMapping
  public ResponseEntity<Person> criar(@Valid @RequestBody Person person, HttpServletResponse response) {
    Person savedPerson = personRepository.save(person);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(savedPerson.getId())
        .toUri();
    response.setHeader("Location", uri.toASCIIString());

    return ResponseEntity.created(uri).body(savedPerson);
  }

  @GetMapping("/{codigo}")
  public ResponseEntity<Object> buscarPeloCodigo(@PathVariable Long codigo) {
    Optional<Person> foundPerson = personRepository.findById(codigo);
    return !foundPerson.isEmpty() ? ResponseEntity.ok(foundPerson) : ResponseEntity.notFound().build();
  }

}
