package com.keyin.domain.Address;

public class Address {
  private long id;
  private int houseNumber;
  private String street;
  private String city;
  private String postalCode;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getHouseNumber() {
    return houseNumber;
  }

  public void setHouseNumber(int houseNumber) {
    this.houseNumber = houseNumber;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }
}