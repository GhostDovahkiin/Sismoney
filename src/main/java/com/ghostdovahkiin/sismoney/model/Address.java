package com.ghostdovahkiin.sismoney.model;

import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
  private String street;
  private String number;
  private String aditional;
  private String zipCode;
  private String city;
  private String state;

  public Address() {
  }

  public Address(String street, String number, String aditional, String zipCode, String city, String state) {
    this.street = street;
    this.number = number;
    this.aditional = aditional;
    this.zipCode = zipCode;
    this.city = city;
    this.state = state;
  }

  public String getStreet() {
    return this.street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getNumber() {
    return this.number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getAditional() {
    return this.aditional;
  }

  public void setAditional(String aditional) {
    this.aditional = aditional;
  }

  public String getZipCode() {
    return this.zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Address street(String street) {
    setStreet(street);
    return this;
  }

  public Address number(String number) {
    setNumber(number);
    return this;
  }

  public Address aditional(String aditional) {
    setAditional(aditional);
    return this;
  }

  public Address zipCode(String zipCode) {
    setZipCode(zipCode);
    return this;
  }

  public Address city(String city) {
    setCity(city);
    return this;
  }

  public Address state(String state) {
    setState(state);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Address)) {
      return false;
    }
    Address address = (Address) o;
    return Objects.equals(street, address.street) && Objects.equals(number, address.number)
        && Objects.equals(aditional, address.aditional) && Objects.equals(zipCode, address.zipCode)
        && Objects.equals(city, address.city) && Objects.equals(state, address.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(street, number, aditional, zipCode, city, state);
  }

  @Override
  public String toString() {
    return "{" + " street='" + getStreet() + "'" + ", number='" + getNumber() + "'" + ", aditional='" + getAditional()
        + "'" + ", zipCode='" + getZipCode() + "'" + ", city='" + getCity() + "'" + ", state='" + getState() + "'"
        + "}";
  }

}
