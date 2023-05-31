package ru.job4j.ood.isp;

public interface DataAccess {
    void connect();
    void executeQuery();
    void saveData();
    void closeConnection();
}
