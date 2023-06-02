package ru.job4j.ood.dip;

public class OrderService {
    private EmailService emailService;

    public OrderService() {
        this.emailService = new EmailService();
    }
}
