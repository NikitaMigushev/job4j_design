package ru.job4j.ood.controlq;

public class Shop extends AbstractStore {

    @Override
    public boolean accept(Food food, double freshnessPercentage) {
        if (freshnessPercentage > 75 && freshnessPercentage < 100) {
            food.setDiscount(50);
        }
        return freshnessPercentage >= 25 && freshnessPercentage < 100;
    }
}
