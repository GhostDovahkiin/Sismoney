package com.ghostdovahkiin.sismoney.repository.entry;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;
import com.ghostdovahkiin.sismoney.model.Entry;
import com.ghostdovahkiin.sismoney.model.Entry_;
import com.ghostdovahkiin.sismoney.repository.filter.EntryFilter;

public class EntryRepositoryImpl implements EntryRepositoryQuery {

  @PersistenceContext
  private EntityManager manager;

  @Override
  public List<Entry> filter(EntryFilter entryFilter) {
    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<Entry> criteria = builder.createQuery(Entry.class);
    Root<Entry> root = criteria.from(Entry.class);

    // Create restrictions
    Predicate[] predicates = createRestrictions(entryFilter, builder, root);
    criteria.where(predicates);

    TypedQuery<Entry> query = manager.createQuery(criteria);
    return query.getResultList();
  }

  private Predicate[] createRestrictions(EntryFilter entryFilter, CriteriaBuilder builder, Root<Entry> root) {
    List<Predicate> predicates = new ArrayList<>();
    if (entryFilter.getDescription() != null) {
      predicates.add(builder.like(builder.lower(root.get(Entry_.entryDescription)),
          "%" + entryFilter.getDescription().toLowerCase()));
    }

    if (entryFilter.getDueDataTo() != null) {
      predicates.add(builder.greaterThanOrEqualTo(root.get(Entry_.dueDate), entryFilter.getDueDateFrom()));
    }

    if (entryFilter.getDueDateFrom() != null) {
      predicates.add(builder.lessThanOrEqualTo(root.get(Entry_.dueDate), entryFilter.getDueDataTo()));

    }
    return predicates.toArray(new Predicate[predicates.size()]);
  }

}
