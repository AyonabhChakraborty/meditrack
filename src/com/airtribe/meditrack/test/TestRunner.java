package com.airtribe.meditrack.test;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.MedicalEntity;
import com.airtribe.meditrack.pattern.AppointmentNotifier;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;

public class TestRunner {

    private static final DoctorService      ds  = new DoctorService();
    private static final PatientService     ps  = new PatientService();
    private static final AppointmentNotifier n   = new AppointmentNotifier();
    private static final AppointmentService  as  = new AppointmentService(ds,ps, n);

    static {
        n.subscribe(AppointmentNotifier.consoleReminder());
    }

    public static void runAll() {
        test01_Singletons();
        test02_AddDoctorsAndPatients();
        test03_Validation();
        test04_BookAppointment();
        test05_CancelAppointment();
        test06_PolymorphismAndOverloading();
        test07_BillFactoryAndPayment();
        test08_SearchStrategies();
        test09_DeepCopyClone();
        test10_ImmutableBillSummary();
        test11_StaticVsInstance();
        test12_Enums();
        test13_ExceptionHandling();
    }

    static void test01_Singletons() {}
    static void test02_AddDoctorsAndPatients() {}
    static void test03_Validation() {}
    static void test04_BookAppointment() {}
    static void test05_CancelAppointment() {}
    static void test07_BillFactoryAndPayment() {}
    static void test08_SearchStrategies() {}
    static void test09_DeepCopyClone() {}
    static void test10_ImmutableBillSummary() {}
    static void test11_StaticVsInstance() {}
    static void test12_Enums() {}
    static void test13_ExceptionHandling() {}

    static void test06_PolymorphismAndOverloading() {
        if (ds.getAllDoctors().isEmpty() || ps.getAllPatients().isEmpty()) return;
        Doctor doc = ds.getAllDoctors().get(0);
        System.out.println(doc.generateBill());
        System.out.println(doc.generateBill(45));
        System.out.println(doc.generateBill(45, true));

        MedicalEntity e1 = ds.getAllDoctors().get(0);
        MedicalEntity e2 = ps.getAllPatients().get(0);
        System.out.println(e1.describe());
        System.out.println(e2.describe());
    }
}
