package com.ghostdovahkiin.sismoney.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class CreatedResourceEvent extends ApplicationEvent {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private transient HttpServletResponse res;
  private Long id;

  public CreatedResourceEvent(Object source, HttpServletResponse res, Long id) {
    super(source);
    this.res = res;
    this.id = id;
  }

  public HttpServletResponse getRes() {
    return this.res;
  }

  public void setRes(HttpServletResponse res) {
    this.res = res;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}
