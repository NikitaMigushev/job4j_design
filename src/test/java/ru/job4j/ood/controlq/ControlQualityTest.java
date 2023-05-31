package ru.job4j.ood.controlq;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ControlQualityTest {
    private List<AbstractStore> stores = new ArrayList<>();
    private ControlQuality controlQuality;

    @BeforeEach
    void initEach() {
        AbstractStore warehouse = new Warehouse();
        AbstractStore shop = new Shop();
        AbstractStore trash = new Trash();
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);
        controlQuality = new ControlQuality(stores);
    }

    @Test
    void whenFoodDistributeToWarehouse() {
        LocalDate createDate = LocalDate.now();
        LocalDate expiryDate = LocalDate.now().plusDays(20);
        Food apple = new Food("Apple", expiryDate, createDate, 100, 0);
        controlQuality.distributeFood(apple);
        assertThat(stores.get(0).getFoods()).contains(apple);
    }

    @Test
    void whenFoodDistributeToShopWithoutDiscount() {
        LocalDate createDate = LocalDate.now().minusDays(10);
        LocalDate expiryDate = LocalDate.now().plusDays(10);
        Food apple = new Food("Apple", expiryDate, createDate, 100, 0);
        controlQuality.distributeFood(apple);
        assertThat(stores.get(1).getFoods()).contains(apple);
        assertThat(apple.getDiscount()).isEqualTo(0);
    }

    @Test
    void whenFoodDistributeToShopWithDiscount() {
        LocalDate createDate = LocalDate.now().minusDays(10);
        LocalDate expiryDate = LocalDate.now().plusDays(3);
        Food apple = new Food("Apple", expiryDate, createDate, 100, 0);
        controlQuality.distributeFood(apple);
        assertThat(stores.get(1).getFoods()).contains(apple);
        assertThat(apple.getDiscount()).isEqualTo(50);
    }

    @Test
    void whenFoodDistributeToTrash() {
        LocalDate createDate = LocalDate.now().minusDays(5);
        LocalDate expiryDate = LocalDate.now().minusDays(1);
        Food apple = new Food("Apple", expiryDate, createDate, 100, 0);
        controlQuality.distributeFood(apple);
        assertThat(stores.get(2).getFoods()).contains(apple);
    }
}