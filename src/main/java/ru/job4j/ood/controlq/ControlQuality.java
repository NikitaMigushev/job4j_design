package ru.job4j.ood.controlq;

import java.time.LocalDate;
import java.util.List;

public class ControlQuality {
    private List<AbstractStore> stores;

    public ControlQuality(List<AbstractStore> stores) {
        this.stores = stores;
    }

    public void distributeFood(Food food) {
        getDestinationStore(food).addFood(food);
    }

    private double calculateFreshnessPercentage(Food food) {
        double result = 0;
        LocalDate now = LocalDate.now();
        long totalDuration = food.getExpiryDate().toEpochDay() - food.getCreateDate().toEpochDay();
        long elapsedDuration = now.toEpochDay() - food.getCreateDate().toEpochDay();
        if (elapsedDuration > totalDuration) {
            result = 100;
        } else {
            result = (double) elapsedDuration / totalDuration * 100.0;
        }
        return result;
    }

    private AbstractStore getDestinationStore(Food food) {
        AbstractStore resultStore = stores.get(findStoreByName(stores, "Trash"));
        double freshnessPercentage = calculateFreshnessPercentage(food);
        for (AbstractStore store : stores) {
            if (store instanceof Warehouse && freshnessPercentage < 25) {
                resultStore = store;
                break;
            } else if (store instanceof Shop && freshnessPercentage >= 25 && freshnessPercentage <= 75) {
                resultStore = store;
                break;
            } else if (store instanceof Shop && freshnessPercentage > 75 && freshnessPercentage < 100) {
                food.setDiscount(50);
                resultStore = store;
                break;
            }
        }
        return resultStore;
    }

    private int findStoreByName(List<AbstractStore> stores, String name) {
        int index = -1;
        for (int i = 0; i < stores.size(); i++) {
            AbstractStore store = stores.get(i);
            if (name.equals(store.getName())) {
                index = i;
            }
        }
        return index;
    }
}
