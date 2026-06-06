package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;
import com.airtribe.meditrack.util.Validator;
import java.util.List;

public class DoctorService {

    private final DataStore<Doctor> store = new DataStore<>();

    public Doctor addDoctor(String name, int age, String phone, String email,
                            com.airtribe.meditrack.enums.Specialization specialization,
                            String department) {
        Validator.validateName(name);
        Validator.validateAge(age);
        Validator.validatePhone(phone);
        Validator.validateEmail(email);
        String id = IdGenerator.getInstance().nextDoctorId();
        Doctor d = new Doctor(id, name, age, phone, email, specialization, department);
        store.addData(d);
        return d;
    }

    public Doctor findDoctorById(String id){
        return Validator.validateDoctorExists(id, store);
    }

    public List<Doctor> listAll(){
        return store.getAllData();
    }

    public List<Doctor> getAllDoctors(){
        return listAll();
    }

    public boolean removeDoctor(Doctor d){
        return store.removeData(d);
    }

    public void setAvailability(String id, boolean available){
        Doctor d = findDoctorById(id);
        d.setAvailable(available);
    }
}
