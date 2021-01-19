package com.ghostdovahkiin.sismoney.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SismoneyExceptionHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private MessageSource messageSource;

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    String userMessage = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
    String devMessage = ex.getCause().toString();
    List<Erro> errors = Arrays.asList(new Erro(userMessage, devMessage));
    return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    List<Erro> errors = createErrorList(ex.getBindingResult());
    return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
  }

  @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violation")
  @ExceptionHandler({ javax.validation.ConstraintViolationException.class, DataIntegrityViolationException.class })
  private ResponseEntity<Object> handleNullArgument(ConstraintViolationException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    String userMessage = messageSource.getMessage("data.violation", null, LocaleContextHolder.getLocale());
    String devMessage = ex.toString();
    List<Erro> errors = Arrays.asList(new Erro(userMessage, devMessage));
    return handleExceptionInternal(ex, errors, headers, HttpStatus.CONFLICT, request);
  }

  private List<Erro> createErrorList(BindingResult bindingResult) {
    List<Erro> errors = new ArrayList<>();
    for (FieldError fieldError : bindingResult.getFieldErrors()) {
      String devMessage = fieldError.toString();
      String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
      errors.add(new Erro(userMessage, devMessage));
    }

    return errors;
  }

  @ExceptionHandler({ EmptyResultDataAccessException.class })
  @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Data used to retrieve does not exist")
  public ResponseEntity<Object> handleEmptyResultDataAcessException(EmptyResultDataAccessException ex,
      WebRequest request) {
    String userMessage = messageSource.getMessage("resource.not.found", null, LocaleContextHolder.getLocale());
    String devMessage = ex.toString();
    List<Erro> errors = Arrays.asList(new Erro(userMessage, devMessage));
    return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  public static class Erro {

    private String userMessage;
    private String devMessage;

    public Erro(String userMessage, String devMessage) {
      this.userMessage = userMessage;
      this.devMessage = devMessage;
    }

    public String getuserMessage() {
      return userMessage;
    }

    public String getdevMessage() {
      return devMessage;
    }

  }

}
