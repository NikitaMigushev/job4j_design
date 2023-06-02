package ru.job4j.ood.dip;

public class CustomerService {
    private DatabaseConnection databaseConnection;

    public CustomerService() {
        this.databaseConnection = new DatabaseConnection();
    }
}
