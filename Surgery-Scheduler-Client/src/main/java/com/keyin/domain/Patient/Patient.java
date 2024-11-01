package com.keyin.domain.Patient;

import com.keyin.domain.Address.Address;

public class Patient {
  private long id;
  private String mcpNumber;
  private String name;
  private Address address;

  public String getMcpNumber() {
    return mcpNumber;
  }

  public void setMcpNumber(String mcpNumber) {
    this.mcpNumber = mcpNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }
}
