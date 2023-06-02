package ru.job4j.ood.controlq;

import java.util.ArrayList;
import java.util.List;

public class Warehouse extends AbstractStore {
    private List<Food> foods = new ArrayList<>();

    @Override
    public boolean accept(Food food, double freshnessPercentage) {
        boolean result = false;
        if (freshnessPercentage < 25) {
            result = true;
        }
        return result;
    }
    @Override
    public void addFood(Food food) {
        foods.add(food);
    }

    @Override
    public List<Food> getFoods() {
        return foods;
    }

    @Override
    public void removeFood(Food food) {
        foods.remove(food);
    }

    @Override
    public void removeAllFood() {
        foods.clear();
    }
}
