package com.airtribe.meditrack.util;

public class IdGenerator {
    
    //We make the IdGenerator a Singleton to guarantee 
    // a single source of truth for identity across the entire application.
    // Because multiple instances could lead to ID collisions, especially in a multi-threaded environment.
    private static final IdGenerator INSTANCE = new IdGenerator();
    private IdGenerator() {}
    public static IdGenerator getInstance() {
        return INSTANCE;
    }

    private int doctorIdCounter = 10000;
    private int patientIdCounter = 20000;
    private int appointmentIdCounter = 30000;
    private int billIdCounter = 40000;


    public synchronized String nextDoctorId() {
        return "D" + doctorIdCounter++;
    }

    public synchronized String nextPatientId() {
        return "P" + patientIdCounter++;
    }

    public synchronized String nextAppointmentId() {
        return "A" + appointmentIdCounter++;
    }

    public synchronized String nextBillId() {
        return "B" + billIdCounter++;
    }

}