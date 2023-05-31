package ru.job4j.ood.parking;

import java.util.Objects;

public class Truck implements Vehicle {
    private int id;
    private int size;
    private ParkingSlotType occupiedParkingSlotType;
    private boolean parked;
    private ParkingSlotType occupiedParkingLotType;

    public Truck(int id, int size) {
        if (size <= 1) {
            throw new IllegalArgumentException("Truck size should be > 1");
        }
        this.size = size;
        this.id = id;
    }

    public boolean canPark(ParkingLot parkingLot) {
        boolean result = false;
        if (parkingLot.getAvailableSpotsForTrucks() > 0) {
            result = true;
        } else if (parkingLot.getAvailableSpotsForTrucks() > size) {
            result = true;
        }
        return result;
    }

    public void park(ParkingLot parkingLot) {
        if (parkingLot.getAvailableSpotsForTrucks() > 0) {
            occupiedParkingSlotType = ParkingSlotType.TRUCK;
            parked = true;
            parkingLot.setAvailableSpotsForTrucks(parkingLot.getAvailableSpotsForTrucks() - 1);
            parkingLot.addVehicle(this);
        } else {
            occupiedParkingSlotType = ParkingSlotType.CAR;
            parked = true;
            parkingLot.setAvailableSpotsForCars(parkingLot.getAvailableSpotsForCars() - size);
            parkingLot.addVehicle(this);
        }
    }

    public void remove(ParkingLot parkingLot) {
        parked = false;
        parkingLot.removeVehicle(this.id);
        if (occupiedParkingSlotType == ParkingSlotType.TRUCK) {
            parkingLot.setAvailableSpotsForTrucks(parkingLot.getAvailableSpotsForTrucks() + 1);
        } else {
            parkingLot.setAvailableSpotsForCars(parkingLot.getAvailableSpotsForCars() + size);
        }
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

    public ParkingSlotType getOccupiedParkingLotType() {
        return occupiedParkingLotType;
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
        Truck truck = (Truck) o;
        return id == truck.id && size == truck.size && parked == truck.parked && occupiedParkingSlotType == truck.occupiedParkingSlotType && occupiedParkingLotType == truck.occupiedParkingLotType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, size, occupiedParkingSlotType, parked, occupiedParkingLotType);
    }

    @Override
    public String toString() {
        return "Truck{"
                + "id=" + id
                + ", size=" + size
                + ", occupiedParkingSlotType=" + occupiedParkingSlotType
                + ", parked=" + parked
                + ", occupiedParkingLotType=" + occupiedParkingLotType
                + '}';
    }
}
