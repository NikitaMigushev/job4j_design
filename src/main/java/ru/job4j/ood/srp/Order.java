package ru.job4j.ood.srp;

public interface Order {
    void addItem(Item item);
    void removeItem(Item item);
    void proccessPayment();
    void sendConfirmationEmail();
}
