package ru.job4j.ood.lsp.car;

public class ElectricCar extends Car {
    @Override
    public void startEngine() {
        throw new UnsupportedOperationException("Electric cars do not have engines");
    }
}
