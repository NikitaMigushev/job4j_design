package ru.job4j.ood.parking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StandardParkingLotTest {
    ParkingLot parkingLot;

    @BeforeEach
    void initTest() {
        ParkingLot standardParkingLot = new StandardParkingLot(10, 2);
        parkingLot = standardParkingLot;
    }

    @Test
    void whenCarOccupiesCarSlot() {
        Vehicle car = new Car(1);
        car.park(parkingLot);
        assertThat(parkingLot.getVehicles()).contains(car);
        assertThat(car.getOccupiedParkingSlotType()).isEqualTo(ParkingSlotType.CAR);
        assertThat(car.isParked()).isTrue();
    }

    @Test
    void whenTruckOccupiesTruckSlot() {
        Vehicle truck = new Truck(1, 2);
        truck.park(parkingLot);
        assertThat(parkingLot.getVehicles()).contains(truck);
        assertThat(truck.getOccupiedParkingSlotType()).isEqualTo(ParkingSlotType.TRUCK);
        assertThat(truck.isParked()).isTrue();
    }

    @Test
    void whenBigTruckOccupiesCarSlot() {
        Vehicle truck = new Truck(1, 10);
        truck.park(parkingLot);
        assertThat(parkingLot.getVehicles()).contains(truck);
        assertThat(truck.getOccupiedParkingSlotType()).isEqualTo(ParkingSlotType.TRUCK);
        assertThat(truck.isParked()).isTrue();
    }

    @Test
    void whenTruckOccupiesCarSlot() {
        Vehicle truckA = new Truck(1, 2);
        Vehicle truckB = new Truck(2, 2);
        Vehicle truckC = new Truck(3, 2);
        truckA.park(parkingLot);
        truckB.park(parkingLot);
        truckC.park(parkingLot);
        assertThat(parkingLot.getVehicles()).contains(truckA);
        assertThat(parkingLot.getVehicles()).contains(truckB);
        assertThat(parkingLot.getVehicles()).contains(truckC);
        assertThat(truckC.getOccupiedParkingSlotType()).isEqualTo(ParkingSlotType.CAR);
        assertThat(truckC.isParked()).isTrue();
    }

    @Test
    void whenRemoveCar() {
        Vehicle car = new Car(1);
        car.park(parkingLot);
        assertThat(parkingLot.getVehicles()).contains(car);
        assertThat(car.getOccupiedParkingSlotType()).isEqualTo(ParkingSlotType.CAR);
        car.remove(parkingLot);
        assertThat(car.isParked()).isFalse();
        assertThat(parkingLot.getVehicles()).isEmpty();
    }

    @Test
    void whenRemoveTruck() {
        Vehicle truckA = new Truck(1, 2);
        Vehicle truckB = new Truck(2, 2);
        Vehicle truckC = new Truck(3, 2);
        truckA.park(parkingLot);
        truckB.park(parkingLot);
        truckC.park(parkingLot);
        assertThat(parkingLot.getVehicles()).contains(truckA);
        assertThat(parkingLot.getVehicles()).contains(truckB);
        assertThat(parkingLot.getVehicles()).contains(truckC);
        assertThat(truckC.getOccupiedParkingSlotType()).isEqualTo(ParkingSlotType.CAR);
        assertThat(truckC.isParked()).isTrue();
        truckC.remove(parkingLot);
        assertThat(truckC.isParked()).isFalse();
        assertThat(parkingLot.getVehicles()).doesNotContain(truckC);
    }

    @Test
    void whenCreateTruckSameSizeAsCar() {
        assertThatThrownBy(() -> {
            new Truck(1, 1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenTruckSizeZero() {
        assertThatThrownBy(() -> {
            new Truck(1, 0);
        }).isInstanceOf(IllegalArgumentException.class);
    }


}