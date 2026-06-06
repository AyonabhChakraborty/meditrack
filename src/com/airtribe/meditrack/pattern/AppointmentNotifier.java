package com.airtribe.meditrack.pattern;

import java.util.ArrayList;
import java.util.List;

import com.airtribe.meditrack.interfaces.AppointmentObserver;
import com.airtribe.meditrack.entity.Appointment;


public class AppointmentNotifier {
    private final List<AppointmentObserver> observers = new ArrayList<>();

    public void addObserver(AppointmentObserver observer) {
        observers.add(observer);
    }

    public void subscribe(AppointmentObserver observer) {
        addObserver(observer);
    }

    public void removeObserver(AppointmentObserver observer) {
        observers.remove(observer);
    }

    public void notifyBooked(Appointment x){
        for(AppointmentObserver o : observers){
            o.onAppointmentBooked(x);
        }
    }

    public void notifyCancelled(Appointment x){
        for(AppointmentObserver o : observers){
            o.onAppointmentCancelled(x);
        }
    }

    public void notifyStatusChanged(Appointment x){
        for(AppointmentObserver o : observers){
            o.onAppointmentStatusChanged(x);
        }
    }


    public static AppointmentObserver consoleReminder () {
        return new AppointmentObserver(){
            @Override
            public void onAppointmentBooked(Appointment appointment) {
                System.out.println("Reminder: Appointment booked for " + appointment.getPatient().getName() +
                        " with Dr. " + appointment.getDoctor().getName() +
                        " on " + appointment.getAppointmentTime());
            }

            @Override
            public void onAppointmentCancelled(Appointment appointment) {
                System.out.println("Notice: Appointment cancelled for " + appointment.getPatient().getName() +
                        " with Dr. " + appointment.getDoctor().getName() +
                        " on " + appointment.getAppointmentTime());
            }

            @Override
            public void onAppointmentStatusChanged(Appointment appointment) {
                System.out.println("Update: Appointment status changed for " + appointment.getPatient().getName() +
                        " with Dr. " + appointment.getDoctor().getName() +
                        " on " + appointment.getAppointmentTime() +
                        ". New status: " + appointment.getStatus());
            }
        };
    }




}