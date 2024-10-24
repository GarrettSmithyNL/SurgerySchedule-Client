package com.keyin.domain.Doctor;

import java.util.List;

import com.keyin.domain.Hospital.Hospital;
import com.keyin.domain.types.SurgeryTypes;

public class Doctor {
  private long id;
  private String name;
  private List<SurgeryTypes> listOfPossibleSurgeries;
  private List<Hospital> listOfWorkableHospitals;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<SurgeryTypes> getListOfPossibleSurgeries() {
    return listOfPossibleSurgeries;
  }

  public void setListOfPossibleSurgeries(List<SurgeryTypes> listOfPossibleSurgeries) {
    this.listOfPossibleSurgeries = listOfPossibleSurgeries;
  }

  public List<Hospital> getListOfWorkableHospitals() {
    return listOfWorkableHospitals;
  }

  public void setListOfWorkableHospitals(List<Hospital> listOfWorkableHospitals) {
    this.listOfWorkableHospitals = listOfWorkableHospitals;
  }
}
