package com.airtribe.meditrack.pattern;

import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.enums.PaymentType;
import com.airtribe.meditrack.interfaces.Payable;
import com.airtribe.meditrack.util.IdGenerator;

public class BillFactory {

    private static class CreditCardPayment implements Payable {
        @Override
        public boolean processPayment(double amount){
            if(amount > 0) return true;
            return false;
        }
        @Override
        public String getPaymentMethod() {
            return "Credit Card";
        }
        public void printReceipt(double amount){
            System.out.println("Payment of $" + amount + " processed via Credit Card.");
        }
    }

    private static class DebitCardPayment implements Payable {
        @Override
        public boolean processPayment(double amount){
            if(amount > 0) return true;
            return false;
        }
        @Override
        public String getPaymentMethod() {
            return "Debit Card";
        }
        public void printReceipt(double amount){
            System.out.println("Payment of $" + amount + " processed via Debit Card.");
        }
    }

    private static class UPIPayment implements Payable {
        @Override
        public boolean processPayment(double amount){
            if(amount > 0) return true;
            return false;
        }
        @Override
        public String getPaymentMethod() {
            return "UPI";
        }
        public void printReceipt(double amount){
            System.out.println("Payment of $" + amount + " processed via UPI.");
        }
    }


    public static class CreditCardBill extends Bill {
        public CreditCardBill(String id, double baseAmount) {
            super(id, baseAmount);
            this.paymentStrategy = new CreditCardPayment();

        }
        @Override
        public String generateBillDetails() {
            return "Credit Card Bill\n" + super.generateBillDetails();
        }
    }


    public static class DebitCardBill extends Bill {
        public DebitCardBill(String id, double baseAmount) {
            super(id, baseAmount);
            this.paymentStrategy = new DebitCardPayment();
        }
        @Override
        public String generateBillDetails() {
            return "Debit Card Bill\n" + super.generateBillDetails();
        }
    }


    public static class UPIBill extends Bill {
        private final String upiId;
        public UPIBill(String id, double baseAmount, String upiId) {
            super(id, baseAmount);
            this.paymentStrategy = new UPIPayment();
            this.upiId = upiId;
        }
        @Override
        public String generateBillDetails() {
            return "UPI Bill\n" + super.generateBillDetails();
        }
        public String getUpiId() {
            return upiId;
        }
    }


    public static Bill createBill(PaymentType type, double amount, String upiId){
        switch (type) {
            case CREDIT_CARD:
                return new CreditCardBill(IdGenerator.getInstance().nextBillId(), amount);
            case DEBIT_CARD:
                return new DebitCardBill(IdGenerator.getInstance().nextBillId(), amount);
            case UPI:
                return new UPIBill(IdGenerator.getInstance().nextBillId(), amount, upiId);
            default:
                throw new IllegalArgumentException("Unsupported payment type: " + type);
        }
    }




}