package com.ghostdovahkiin.sismoney.model;

import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "person")
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String personName;

  @Embedded
  private Address address;

  @NotNull
  private Boolean active;

  public Person() {
  }

  public Person(Long id, String personName, Address address, Boolean active) {
    this.id = id;
    this.personName = personName;
    this.address = address;
    this.active = active;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getpersonName() {
    return this.personName;
  }

  public void setpersonName(String personName) {
    this.personName = personName;
  }

  public Address getAddress() {
    return this.address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Boolean isActive() {
    return this.active;
  }

  public Boolean getActive() {
    return this.active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Person id(Long id) {
    setId(id);
    return this;
  }

  public Person personName(String personName) {
    setpersonName(personName);
    return this;
  }

  public Person address(Address address) {
    setAddress(address);
    return this;
  }

  public Person active(Boolean active) {
    setActive(active);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Person)) {
      return false;
    }
    Person person = (Person) o;
    return Objects.equals(id, person.id) && Objects.equals(personName, person.personName)
        && Objects.equals(address, person.address) && Objects.equals(active, person.active);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, personName, address, active);
  }

  @Override
  public String toString() {
    return "{" + " id='" + getId() + "'" + ", personName='" + getpersonName() + "'" + ", address='" + getAddress() + "'"
        + ", active='" + isActive() + "'" + "}";
  }

}
