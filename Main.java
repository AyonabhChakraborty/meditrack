import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import com.airtribe.meditrack.pattern.AppointmentNotifier;
import com.airtribe.meditrack.test.TestRunner;
import com.airtribe.meditrack.enums.Specialization;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        DoctorService ds = new DoctorService();
        PatientService ps = new PatientService();
        AppointmentNotifier notifier = new AppointmentNotifier();
        AppointmentService as = new AppointmentService(ds, ps, notifier);

        notifier.subscribe(AppointmentNotifier.consoleReminder());

        Doctor d1 = ds.addDoctor("Alice Smith", 45, "9991112223", "alice@clinic.com",
                Specialization.CARDIOLOGY, "Cardio");
        Patient p1 = ps.addPatient("Bob Jones", 30, "8882223334", "bob@example.com");

        System.out.println("Created Doctor: " + d1.describe());
        System.out.println("Created Patient: " + p1.describe());

        LocalDateTime when = LocalDateTime.now().plusDays(2).withHour(10).withMinute(0);
        try {
            var ap = as.createAppointment(d1.getId(), p1.getId(), when);
            System.out.println("Booked appointment: " + ap.describe());
        } catch (Exception e) {
            System.err.println("Failed to book appointment: " + e.getMessage());
        }

        System.out.println("--- Running TestRunner.runAll() (stubs) ---");
        TestRunner.runAll();

        System.out.println("Done.");
    }
}
