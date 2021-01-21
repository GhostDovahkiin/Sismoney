package com.ghostdovahkiin.sismoney.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ghostdovahkiin.sismoney.event.CreatedResourceEvent;
import com.ghostdovahkiin.sismoney.exceptionhandler.SismoneyExceptionHandler.Erro;
import com.ghostdovahkiin.sismoney.model.Entry;
import com.ghostdovahkiin.sismoney.repository.EntryRepository;
import com.ghostdovahkiin.sismoney.repository.filter.EntryFilter;
import com.ghostdovahkiin.sismoney.service.EntryService;
import com.ghostdovahkiin.sismoney.service.exception.AbsentOrInactivePersonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
  @Autowired
  private EntryService entryService;
  @Autowired
  private MessageSource messageSource;

  @GetMapping
  public List<Entry> listFiltered(EntryFilter entryFilter) {
    return entryRepository.filter(entryFilter);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> searchById(@PathVariable Long id) {
    Optional<Entry> foundCategory = entryRepository.findById(id);
    return !foundCategory.isEmpty() ? ResponseEntity.ok(foundCategory) : ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<Entry> create(@Valid @RequestBody Entry entry, HttpServletResponse response) {
    Entry savedEntry = entryService.saveEntry(entry);
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

  @ExceptionHandler({ AbsentOrInactivePersonException.class })
  public ResponseEntity<Object> handleAbsentOrInactivePersonException(AbsentOrInactivePersonException ex) {
    String userMessage = messageSource.getMessage("absent.or.inactive", null, LocaleContextHolder.getLocale());
    String devMessage = ex.toString();
    List<Erro> errors = Arrays.asList(new Erro(userMessage, devMessage));
    return ResponseEntity.badRequest().body(errors);
  }
}
