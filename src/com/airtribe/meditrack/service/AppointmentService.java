package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.enums.AppointmentStatus;
import com.airtribe.meditrack.pattern.AppointmentNotifier;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;
import com.airtribe.meditrack.util.Validator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class AppointmentService {

    private final DataStore<Appointment> store = new DataStore<>();
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final AppointmentNotifier notifier;

    public AppointmentService() {
        this(new DoctorService(), new PatientService(), new AppointmentNotifier());
    }

    public AppointmentService(DoctorService ds, PatientService ps, AppointmentNotifier notifier) {
    this.doctorService = ds;
    this.patientService = ps;
    this.notifier = notifier;
}

    public Appointment createAppointment(String doctorId, String patientId, LocalDateTime when){
        Doctor d = doctorService.findDoctorById(doctorId);
        Validator.validateDoctorAvailable(d);
        Patient p = patientService.findPatientById(patientId);
        Validator.validateAppointmentDate(when);
        String id = IdGenerator.getInstance().nextAppointmentId();
        Appointment ap = new Appointment(id, d, p, when);
        store.addData(ap);
        notifier.notifyBooked(ap);
        return ap;
    }

    public Appointment findAppointmentById(String id){
        Optional<Appointment> opt = store.findFirst(a -> a.getId().equals(id));
        return opt.orElseThrow(() -> new com.airtribe.meditrack.exception.AppointmentNotFoundException(id));
    }

    public List<Appointment> listAll(){
        return store.getAllData();
    }

    public void cancelAppointment(String id){
        Appointment a = findAppointmentById(id);
        a.setStatus(AppointmentStatus.CANCELLED);
        notifier.notifyCancelled(a);
        store.removeData(a);
    }

    public void changeStatus(String id, AppointmentStatus status){
        Appointment a = findAppointmentById(id);
        a.setStatus(status);
        notifier.notifyStatusChanged(a);
    }

    public AppointmentNotifier getNotifier(){
        return notifier;
    }
}
