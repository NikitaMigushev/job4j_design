package ru.job4j.ood.controlq;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractStore {
    protected List<Food> foods = new ArrayList<>();

    public abstract String getName();

    public void addFood(Food food) {
        foods.add(food);
    }

    public List<Food> getFoods() {
        return foods;
    }
}
