package ru.job4j.ood.parking;

public interface Vehicle {
    int getSize();
    boolean canPark(ParkingLot parkingLot);
    void park(ParkingLot parkingLot);
    void remove(ParkingLot parkingLot);
    int getId();
    ParkingSlotType getOccupiedParkingSlotType();
    boolean isParked();
}
