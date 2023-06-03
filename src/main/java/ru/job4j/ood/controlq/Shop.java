package ru.job4j.ood.controlq;

public class Shop extends AbstractStore {

    @Override
    public boolean accept(Food food, double freshnessPercentage) {
        boolean result = false;
        if (freshnessPercentage >= 25 && freshnessPercentage <= 75) {
            result = true;
        } else if (freshnessPercentage > 75 && freshnessPercentage < 100) {
            food.setDiscount(50);
            result = true;
        }
        return result;
    }
}
