package com.airtribe.meditrack.interfaces;

import com.airtribe.meditrack.entity.Appointment;

public interface AppointmentObserver {
    void onAppointmentBooked(Appointment appointment);
    void onAppointmentCancelled(Appointment appointment);
    void onAppointmentStatusChanged(Appointment appointment);
}


