package ru.job4j.ood.controlq;

import java.util.ArrayList;
import java.util.List;

public class Trash extends AbstractStore {
    private List<Food> foods = new ArrayList<>();

    @Override
    public boolean accept(Food food, double freshnessPercentage) {
        boolean result = false;
        if (freshnessPercentage == 100) {
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
}
