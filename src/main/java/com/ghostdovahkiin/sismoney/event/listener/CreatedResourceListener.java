package com.ghostdovahkiin.sismoney.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import com.ghostdovahkiin.sismoney.event.CreatedResourceEvent;

import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class CreatedResourceListener implements ApplicationListener<CreatedResourceEvent> {

  @Override
  public void onApplicationEvent(CreatedResourceEvent event) {
    HttpServletResponse res = event.getRes();
    Long id = event.getId();
    addHeader(res, id);
  }

  private void addHeader(HttpServletResponse res, Long id) {
    URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
    res.setHeader("Location", uri.toASCIIString());
  }

}
