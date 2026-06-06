package com.airtribe.meditrack.interfaces;

public interface Payable {
    boolean processPayment(double amount);
    String getPaymentMethod();


    default void printReceipt(double amount){
        System.out.println("Payment of $" + amount + " processed via " + getPaymentMethod() + ".");
    }
}