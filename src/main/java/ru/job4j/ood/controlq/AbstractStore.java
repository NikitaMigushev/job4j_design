package ru.job4j.ood.controlq;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractStore implements Store {
    protected List<Food> foods = new ArrayList<>();

    public void addFood(Food food) {
        foods.add(food);
    }

    @Override
    public List<Food> getFoods() {
        return foods;
    }

    public abstract boolean accept(Food food, double freshnessPercentage);

    @Override
    public void removeFood(Food food) {
        foods.remove(food);
    }

    @Override
    public void removeAllFood() {
        foods.clear();
    }
}
