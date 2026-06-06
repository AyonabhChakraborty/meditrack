package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.AppointmentStatus;
import java.time.LocalDateTime;


public class Appointment extends MedicalEntity implements Cloneable {

    private Doctor doctor;
    private Patient patient;
    private LocalDateTime appointmentTime; //Immutable after creation
    private AppointmentStatus status;


    public Appointment(String id, Doctor doctor, Patient patient, LocalDateTime appointmentTime) {
        super(id);
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentTime = appointmentTime;
        this.status = AppointmentStatus.PENDING; 
    }


    @Override
    public String describe() {
        return "Appointment ID: " + getId() + ", Doctor: " + doctor.getName() + ", Patient: " + patient.getName() +
                ", Time: " + appointmentTime.toString() + ", Status: " + status.name() + ", Schedule: " + (appointmentTime.isAfter(LocalDateTime.now()) ? "Upcoming" : "Past") + ", Time of Appointment: " + appointmentTime.toString();
    }


    @Override
    public Appointment clone(){
        try{
            Appointment cloned = (Appointment) super.clone();
            //Shallow copy is sufficient as Doctor and Patient are immutable in this context
            cloned.appointmentTime = LocalDateTime.from(this.appointmentTime); //Ensuring immutability
            cloned.status = this.status;
            return cloned;
        } catch (CloneNotSupportedException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return describe();
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }



}