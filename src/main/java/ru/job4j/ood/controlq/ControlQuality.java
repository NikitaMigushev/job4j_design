package ru.job4j.ood.controlq;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private List<AbstractStore> stores;

    public ControlQuality(List<AbstractStore> stores) {
        this.stores = stores;
    }

    public void distributeFood(List<Food> foods) {
        for (Food food : foods) {
            double freshnessPercentage = FreshnessPercentage.calculate(food);
            for (AbstractStore store : stores) {
                if (store.accept(food, freshnessPercentage)) {
                    store.addFood(food);
                    break;
                }
            }
        }
    }
    public void resort() {
        List<Food> allFoods = new ArrayList<>();
        for (AbstractStore store : stores) {
            List<Food> foods = store.getFoods();
            allFoods.addAll(foods);
            store.removeAllFood();
        }
        distributeFood(allFoods);
    }
}
