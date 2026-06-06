package com.airtribe.meditrack.entity;

public final class BillSummary {

    private final String patientId;
    private final int totalBills;
    private final double totalPaid;
    private final double totalUnpaid;
    private final double grandTotal;

    public BillSummary(String patientId, int totalBills, double totalPaid, double totalUnpaid) {
        this.patientId = patientId;
        this.totalBills = totalBills;
        this.totalPaid = totalPaid;
        this.totalUnpaid = totalUnpaid;
        this.grandTotal = totalPaid + totalUnpaid;
    }

    public String getPatientId() {
        return patientId;
    }

    public int getTotalBills() {
        return totalBills;
    }

    public double getTotalPaid() {
        return totalPaid;
    }



    public double getTotalUnpaid() {
        return totalUnpaid;
    }

    public double getGrandTotal() {
        return grandTotal;
    }
    
}