package com.airtribe.meditrack.util;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.enums.Specialization;
import com.airtribe.meditrack.exception.InvalidDataException;
import java.time.LocalDateTime;

public final class Validator {

    private Validator() {}  // prevent instantiation from outside

    

    // Returns the found Doctor so callers don't need a separate findFirst call
    public static Doctor validateDoctorExists(String doctorId,
                                                DataStore<Doctor> store) {
        return store.findFirst(d -> d.getId().equals(doctorId))
                .orElseThrow(() ->
                    new InvalidDataException("Doctor not found: " + doctorId));
    }
    
    // Checks if doctor is available for appointments (e.g., not on leave)
    public static void validateDoctorAvailable(Doctor doctor) {
        if (!doctor.isAvailable())
            throw new InvalidDataException(
                "Doctor " + doctor.getName() + " is not available.");
    }
    

    //Validates that doctor has valid specialization for the appointment type
    public static void validateSpecialization(Doctor doctor,
                                               Specialization required) {
        if (doctor.getSpecialization() != required)
            throw new InvalidDataException(
                "Doctor " + doctor.getName()
                + " does not have specialization: " + required);
    }
    

    //Ensures that doctor belongs to the correct department for the appointment type
    public static void validateDoctorDepartment(Doctor doctor,
                                                  String expectedDept) {
        if (!doctor.getDepartment().equalsIgnoreCase(expectedDept))
            throw new InvalidDataException(
                "Doctor " + doctor.getName()
                + " is not in department: " + expectedDept);
    }

    // Returns Patient if found, so callers can use it directly without another findFirst call
    public static Patient validatePatientExists(String patientId,
                                                   DataStore<Patient> store) {
        return store.findFirst(p -> p.getId().equals(patientId))
                .orElseThrow(() ->
                    new InvalidDataException("Patient not found: " + patientId));
    }

    // Validates that the appointment date is in the future
    public static void validateAppointmentDate(LocalDateTime dateTime) {
        if (dateTime == null)
            throw new InvalidDataException("Appointment date cannot be null.");
        if (dateTime.isBefore(LocalDateTime.now()))
            throw new InvalidDataException(
                "Appointment date must be in the future: " + dateTime);
    }

  // Validating name, age, phone, and email for both Doctor and Patient entities
  // These can be called from both Doctor and Patient constructors or setters to ensure data integrity
    public static void validateName(String name) {
        if (name == null || name.isBlank())
            throw new InvalidDataException("Name cannot be blank.");
    }

    public static void validateAge(int age) {
        if (age < 0 || age > 130)
            throw new InvalidDataException(
                "Age must be between 0 and 130. Got: " + age);
    }

    public static void validatePhone(String phone) {
        if (phone != null && !phone.isBlank()
                && !phone.matches("\\d{10}"))
            throw new InvalidDataException(
                "Phone must be 10 digits. Got: " + phone);
    }

    public static void validateEmail(String email) {
        if (email != null && !email.isBlank()
                && !email.contains("@"))
            throw new InvalidDataException(
                "Invalid email address: " + email);
    }

    
    public static void validateAmount(double amount) {
        if (amount <= 0)
            throw new InvalidDataException(
                "Amount must be greater than zero. Got: " + amount);
    }
}