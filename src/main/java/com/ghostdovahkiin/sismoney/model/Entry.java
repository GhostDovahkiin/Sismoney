package com.ghostdovahkiin.sismoney.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "entries")
public class Entry {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "entry_description")
  private String entryDescription;

  @NotNull
  @Column(name = "due_date")
  private LocalDate dueDate;

  @Column(name = "pay_date")
  private LocalDate payDate;

  @NotNull
  private BigDecimal amount;
  private String observation;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "entry_type")
  private EntryType entryType;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "person_id")
  private Person person;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEntryDescription() {
    return this.entryDescription;
  }

  public void setEntryDescription(String entryDescription) {
    this.entryDescription = entryDescription;
  }

  public LocalDate getDueDate() {
    return this.dueDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }

  public LocalDate getPayDate() {
    return this.payDate;
  }

  public void setPayDate(LocalDate payDate) {
    this.payDate = payDate;
  }

  public BigDecimal getAmount() {
    return this.amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getObservation() {
    return this.observation;
  }

  public void setObservation(String observation) {
    this.observation = observation;
  }

  public EntryType getEntryType() {
    return this.entryType;
  }

  public void setEntryType(EntryType entryType) {
    this.entryType = entryType;
  }

  public Category getCategory() {
    return this.category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Person getPerson() {
    return this.person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Entry)) {
      return false;
    }
    Entry entry = (Entry) o;
    return Objects.equals(id, entry.id) && Objects.equals(entryDescription, entry.entryDescription)
        && Objects.equals(dueDate, entry.dueDate) && Objects.equals(payDate, entry.payDate)
        && Objects.equals(amount, entry.amount) && Objects.equals(observation, entry.observation)
        && Objects.equals(entryType, entry.entryType) && Objects.equals(category, entry.category)
        && Objects.equals(person, entry.person);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, entryDescription, dueDate, payDate, amount, observation, entryType, category, person);
  }
}
