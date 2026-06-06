package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;
import com.airtribe.meditrack.util.Validator;
import java.util.List;

public class PatientService {

    private final DataStore<Patient> store = new DataStore<>();

    public Patient addPatient(String name, int age, String phone, String email){
        Validator.validateName(name);
        Validator.validateAge(age);
        Validator.validatePhone(phone);
        Validator.validateEmail(email);
        String id = IdGenerator.getInstance().nextPatientId();
        Patient p = new Patient(id, name, age, phone, email);
        store.addData(p);
        return p;
    }

    public Patient findPatientById(String id){
        return Validator.validatePatientExists(id, store);
    }

    public List<Patient> listAll(){
        return store.getAllData();
    }

    public List<Patient> getAllPatients(){
        return listAll();
    }

    public boolean removePatient(Patient p){
        return store.removeData(p);
    }

    public void addMedicalHistory(String patientId, String record){
        Patient p = findPatientById(patientId);
        p.getMedicalHistory().add(record);
    }

    public void addBillToPatient(String patientId, Bill bill){
        if (bill == null) throw new IllegalArgumentException("bill cannot be null");
        Patient p = findPatientById(patientId);
        p.addBill(bill);
    }
}
