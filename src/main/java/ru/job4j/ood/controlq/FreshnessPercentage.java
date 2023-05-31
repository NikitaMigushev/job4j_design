package ru.job4j.ood.controlq;

import java.time.LocalDate;

public class FreshnessPercentage {
    public static double calculate(Food food) {
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
}
