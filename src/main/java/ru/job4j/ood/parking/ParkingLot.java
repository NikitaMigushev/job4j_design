package ru.job4j.ood.parking;

import java.util.List;

public interface ParkingLot {
    int getAvailableSpotsForCars();
    int getAvailableSpotsForTrucks();
    void setAvailableSpotsForCars(int availableSpotsForCars);
    void setAvailableSpotsForTrucks(int availableSpotsForTrucks);
    void addVehicle(Vehicle vehicle);
    void removeVehicle(int id);
    List<Vehicle> getVehicles();
}
