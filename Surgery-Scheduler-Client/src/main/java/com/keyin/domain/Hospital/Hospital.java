package com.keyin.domain.Hospital;

import com.keyin.domain.Address.Address;

import com.keyin.domain.types.SurgeryTypes;

import java.util.List;

public class Hospital {
  private long id;
  private Address addressID;
  private String name;
  private List<SurgeryTypes> listOfSurgeriesThatCanBeDone;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Address getAddressID() {
    return addressID;
  }

  public void setAddressID(Address addressID) {
    this.addressID = addressID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<SurgeryTypes> getListOfSurgeriesThatCanBeDone() {
    return listOfSurgeriesThatCanBeDone;
  }

  public void setListOfSurgeriesThatCanBeDone(List<SurgeryTypes> listOfSurgeriesThatCanBeDone) {
    this.listOfSurgeriesThatCanBeDone = listOfSurgeriesThatCanBeDone;
  }
}