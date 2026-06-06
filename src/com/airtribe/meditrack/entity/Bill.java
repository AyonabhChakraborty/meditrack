package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.interfaces.Payable;

public class Bill extends MedicalEntity {
    private final double baseAmount;
    private final double taxAmount;
    private final double totalAmount;
    protected Payable paymentStrategy;
    private boolean isPaid;

    public Bill(String id, double baseAmount){
        super(id);
        this.baseAmount = baseAmount;
        this.taxAmount = baseAmount * com.airtribe.meditrack.constants.Constants.TAX_RATE;
        this.totalAmount = baseAmount + taxAmount;
        this.isPaid = false;
    }


    public boolean pay(){
        if(isPaid){
            return false;
        }
        boolean x = paymentStrategy.processPayment(totalAmount);
        if(x){
            isPaid = true;
            paymentStrategy.printReceipt(totalAmount);
        }
        return x;
    }

    protected String generateBillDetails(){
        return "Bill ID: " + getId() + "\n" +
                "Base Amount: $" + baseAmount + "\n" +
                "Tax Amount: $" + taxAmount + "\n" +
                "Total Amount: $" + totalAmount + "\n" +
                "Payment Status: " + (isPaid ? "Paid" : "Unpaid");
    }

    @Override
    public String describe() {
        return generateBillDetails();
    }
}