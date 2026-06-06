package com.airtribe.meditrack.entity;
import com.airtribe.meditrack.util.Validator;

public abstract class Person extends MedicalEntity {

    private String name;
    private int age;
    private String phone;
    private String email;


    protected Person(String id, String name, int age, String phone, String email) {
        super(id);
        Validator.validateName(name);
        Validator.validateAge(age);
        Validator.validatePhone(phone);
        Validator.validateEmail(email);
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }




}