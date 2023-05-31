package ru.job4j.ood.parking;

import java.util.Objects;

public class Car implements Vehicle {
    private int id;
    private int size;
    private ParkingSlotType occupiedParkingSlotType;
    private boolean parked;

    public Car(int id) {
        this.id = id;
        this.size = 1;
        this.parked = false;
    }

    public boolean canPark(ParkingLot parkingLot) {
        boolean result = false;
        if (parkingLot.getAvailableSpotsForCars() > 0) {
            result = true;
        }
        return result;
    }

    public void park(ParkingLot parkingLot) {
        occupiedParkingSlotType = ParkingSlotType.CAR;
        parked = true;
        parkingLot.setAvailableSpotsForCars(parkingLot.getAvailableSpotsForCars() - size);
        parkingLot.addVehicle(this);
    }

    public void remove(ParkingLot parkingLot) {
        parked = false;
        parkingLot.removeVehicle(this.id);
        parkingLot.setAvailableSpotsForCars(parkingLot.getAvailableSpotsForCars() + size);
    }

    @Override
    public int getSize() {
        return size;
    }

    public ParkingSlotType getOccupiedParkingSlotType() {
        return occupiedParkingSlotType;
    }

    public boolean isParked() {
        return parked;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id && size == car.size && parked == car.parked && occupiedParkingSlotType == car.occupiedParkingSlotType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, size, occupiedParkingSlotType, parked);
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", size=" + size
                + ", occupiedParkingSlotType=" + occupiedParkingSlotType
                + ", parked=" + parked
                + '}';
    }
}
