package com.ghostdovahkiin.sismoney.repository.entry;

import java.util.List;

import com.ghostdovahkiin.sismoney.model.Entry;
import com.ghostdovahkiin.sismoney.repository.filter.EntryFilter;

public interface EntryRepositoryQuery {
  public List<Entry> filter(EntryFilter entryFilter);
}
