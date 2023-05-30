package ru.job4j.ood.ocp.payment;

public class PaymentProcessor {
    public void processPayment(Payment payment) {
        if (payment instanceof CreditCardPayment) {
            System.out.println("creditCardPayment");
        } else if (payment instanceof PayPalPayment) {
            System.out.println("PayPalPayment");
        } else if (payment instanceof BankTranferPayment) {
            System.out.println("BankTransferPayment");
        }
    }
}
