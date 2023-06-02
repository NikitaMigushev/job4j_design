package ru.job4j.ood.controlq;

import java.util.List;

public interface Store {
    void addFood(Food food);
    List<Food> getFoods();
    void removeFood(Food food);
    void removeAllFood();
}
