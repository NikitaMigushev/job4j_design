package ru.job4j.ood.parking;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StandardParkingLot implements ParkingLot {
    List<Vehicle> parkedVehicles = new ArrayList<>();
    private int availableSpotsForCars;
    private int availableSpotsForTrucks;

    public StandardParkingLot(int availableSpotsForCars, int availableSpotsForTrucks) {
        this.availableSpotsForCars = availableSpotsForCars;
        this.availableSpotsForTrucks = availableSpotsForTrucks;
    }

    public void addVehicle(Vehicle vehicle) {
        parkedVehicles.add(vehicle);
    }

    public List<Vehicle> getVehicles() {
        return parkedVehicles;
    }

    public void removeVehicle(int id) {
        parkedVehicles.remove(findById(id));
    }

    private int findById(int id) {
        int index = -1;
        for (int i = 0; i < parkedVehicles.size(); i++) {
            Vehicle vehicle = parkedVehicles.get(i);
            if (vehicle.getId() == id) {
                index = i;
                break;
            }

        }
        return index;
    }

    @Override
    public int getAvailableSpotsForCars() {
        return availableSpotsForCars;
    }

    @Override
    public int getAvailableSpotsForTrucks() {
        return availableSpotsForTrucks;
    }

    public void setAvailableSpotsForCars(int availableSpotsForCars) {
        this.availableSpotsForCars = availableSpotsForCars;
    }

    public void setAvailableSpotsForTrucks(int availableSpotsForTrucks) {
        this.availableSpotsForTrucks = availableSpotsForTrucks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StandardParkingLot that = (StandardParkingLot) o;
        return availableSpotsForCars == that.availableSpotsForCars && availableSpotsForTrucks == that.availableSpotsForTrucks;
    }

    @Override
    public int hashCode() {
        return Objects.hash(availableSpotsForCars, availableSpotsForTrucks);
    }

    @Override
    public String toString() {
        return "StandardParkingLot{"
                + "availableSpotsForCars=" + availableSpotsForCars
                + ", availableSpotsForTrucks=" + availableSpotsForTrucks
                + '}';
    }
}
