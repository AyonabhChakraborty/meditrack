package com.airtribe.meditrack.entity;
import java.lang.CloneNotSupportedException;
import java.util.ArrayList;
import java.util.List;


public class Patient extends Person implements Cloneable {

    private List<String> medicalHistory;
    private List<Bill> bills;

    public Patient(String id, String name, int age, String phone, String email) {
        super(id, name, age, phone, email);
        this.medicalHistory = new ArrayList<>();
        this.bills = new ArrayList<>();
    }



    @Override
    public Patient clone(){
        try{ 

            //Why we do deep copy for medicalHistory and bills?
            //By performing a deep copy, 
            // we ensure that each Patient instance has its own separate copy of the medical history and bills,
            //  allowing them to be modified independently without unintended side effects.
            Patient cloned = (Patient) super.clone();
        cloned.medicalHistory = new ArrayList<>(this.medicalHistory);
        cloned.bills = new ArrayList<>(this.bills);
        return cloned;
        } catch (CloneNotSupportedException e){
            throw new AssertionError("Cloning not supported for Patient class.", e);
        }
    }


    //Defensive Getters
    public List<String> getMedicalHistory() {
        return new ArrayList<>(medicalHistory);
    }

    public List<Bill> getBills() {
        return new ArrayList<>(bills);
    }

    public void addBill(Bill bill) {
        this.bills.add(bill);
    }


    @Override
    public String describe() {
        return "Patient with ID=" + getId() + ", Name=" + getName() + ", Age=" + getAge() +
                ", Medical History=" + medicalHistory.size() + " records, Bills=" + bills.size() + " total";
    }
}