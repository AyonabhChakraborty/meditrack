package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.Specialization;

public class Doctor extends Person {

    private static int doctorCount = 0;

    static {
        System.out.println("Doctor class loaded. Initializing doctor-specific resources...");
    }


    private Specialization specialization;
    private String department;
    private boolean available;


    public Doctor(String id, String name, int age, String phone, String email, Specialization specialization, String department) {
        super(id, name, age, phone, email);
        this.specialization = specialization;
        this.department = department;
        this.available = true;
        doctorCount++;
    }


    @Override
    public String describe() {
        return "Doctor with ID=" + getId() + ", Name=" + getName() + ", Age=" + getAge() +
                ", Specialization=" + specialization + ", Department=" + department +
                ", Available=" + available + "";
    }


    public double generateBill(int mins){
        if(mins < 30){
            return 500.0;
        } else if(mins >= 30 && mins <= 60){
            return 500.0 + mins*100.0;
        } else {
            return 750.0;
        }
    }

    public double generateBill() {
        return generateBill(0);
    }

    public double generateBill(int mins, boolean tests){
        return generateBill(mins) + (tests ? 1000.0 : 0.0);
    }


    public Specialization getSpecialization() {
        return specialization;
    }

    public String getDepartment() {
        return department;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public static int getDoctorCount() {
        return doctorCount;
    }


    @Override
    public String toString() {
        return describe();
    }
}