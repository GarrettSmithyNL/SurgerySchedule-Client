package com.keyin.domain.Surgery;

import com.keyin.domain.Doctor.Doctor;
import com.keyin.domain.Hospital.Hospital;
import com.keyin.domain.Patient.Patient;
import com.keyin.domain.types.SurgeryTypes;

import java.util.Date;

public class Surgery {
  private long id;
  private Doctor doctorDoingSurgery;
  private Patient patient;
  private Hospital hospital;
  private Date timeStart;
  private Date timeEnd;
  private long durationOfSurgery;
  private SurgeryTypes typeOfSurgery;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Doctor getDoctorDoingSurgery() {
    return doctorDoingSurgery;
  }

  public void setDoctorDoingSurgery(Doctor doctorDoingSurgery) {
    this.doctorDoingSurgery = doctorDoingSurgery;
  }

  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }

  public Hospital getHospital() {
    return hospital;
  }

  public void setHospital(Hospital hospital) {
    this.hospital = hospital;
  }

  public Date getTimeStart() {
    return timeStart;
  }

  public void setTimeStart(Date timeStart) {
    this.timeStart = timeStart;
  }

  public Date getTimeEnd() {
    return timeEnd;
  }

  public void setTimeEnd(Date timeEnd) {
    this.timeEnd = timeEnd;
  }

  public long getDurationOfSurgery() {
    return durationOfSurgery;
  }

  public void setDurationOfSurgery(long durationOfSurgery) {
    this.durationOfSurgery = durationOfSurgery;
  }

  public SurgeryTypes getTypeOfSurgery() {
    return typeOfSurgery;
  }

  public void setTypeOfSurgery(SurgeryTypes typeOfSurgery) {
    this.typeOfSurgery = typeOfSurgery;
  }
}
