package com.ghostdovahkiin.sismoney.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ghostdovahkiin.sismoney.event.CreatedResourceEvent;
import com.ghostdovahkiin.sismoney.model.Entry;
import com.ghostdovahkiin.sismoney.repository.EntryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entries")
public class EntryResource {

  @Autowired
  private EntryRepository entryRepository;
  @Autowired
  private ApplicationEventPublisher publisher;

  @GetMapping
  public ResponseEntity<Object> list() {
    List<Entry> entries = entryRepository.findAll();
    return !entries.isEmpty() ? ResponseEntity.ok(entries) : ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> searchById(@PathVariable Long id) {
    Optional<Entry> foundCategory = entryRepository.findById(id);
    return !foundCategory.isEmpty() ? ResponseEntity.ok(foundCategory) : ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<Entry> create(@Valid @RequestBody Entry entry, HttpServletResponse response) {
    Entry savedEntry = entryRepository.save(entry);
    publisher.publishEvent(new CreatedResourceEvent(this, response, savedEntry.getId()));
    return ResponseEntity.status(HttpStatus.CREATED).body(savedEntry);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> removeByID(@PathVariable Long id) {
    Optional<Entry> foundEntry = entryRepository.findById(id);
    if (foundEntry.isEmpty()) {
      return ResponseEntity.notFound().build();
    } else {
      entryRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    }
  }
}
