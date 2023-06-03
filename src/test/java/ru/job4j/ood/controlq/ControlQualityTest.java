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
    private List<Food> foods = new ArrayList<>();

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
        foods.add(apple);
        controlQuality.distributeFood(foods);
        assertThat(stores.get(0).getFoods()).contains(apple);
    }

    @Test
    void whenFoodDistributeToShopWithoutDiscount() {
        LocalDate createDate = LocalDate.now().minusDays(10);
        LocalDate expiryDate = LocalDate.now().plusDays(10);
        Food apple = new Food("Apple", expiryDate, createDate, 100, 0);
        foods.add(apple);
        controlQuality.distributeFood(foods);
        assertThat(stores.get(1).getFoods()).contains(apple);
        assertThat(apple.getDiscount()).isEqualTo(0);
    }

    @Test
    void whenFoodDistributeToShopWithDiscount() {
        LocalDate createDate = LocalDate.now().minusDays(10);
        LocalDate expiryDate = LocalDate.now().plusDays(3);
        Food apple = new Food("Apple", expiryDate, createDate, 100, 0);
        foods.add(apple);
        controlQuality.distributeFood(foods);
        assertThat(stores.get(1).getFoods()).contains(apple);
        assertThat(apple.getDiscount()).isEqualTo(50);
    }

    @Test
    void whenFoodDistributeToTrash() {
        LocalDate createDate = LocalDate.now().minusDays(5);
        LocalDate expiryDate = LocalDate.now().minusDays(1);
        Food apple = new Food("Apple", expiryDate, createDate, 100, 0);
        foods.add(apple);
        controlQuality.distributeFood(foods);
        assertThat(stores.get(2).getFoods()).isEmpty();
    }

    @Test
    void whenCombinedDistribution() {
        Food watermelon = new Food("Watermelon", LocalDate.now().plusDays(20), LocalDate.now(), 100, 0);
        Food apple = new Food("Apple", LocalDate.now().plusDays(10), LocalDate.now().minusDays(10), 100, 0);
        Food mango = new Food("Mango", LocalDate.now().plusDays(3), LocalDate.now().minusDays(10), 100, 0);
        Food kiwi = new Food("Kiwi", LocalDate.now().minusDays(5), LocalDate.now().minusDays(1), 100, 0);
        foods.add(watermelon);
        foods.add(apple);
        foods.add(mango);
        foods.add(kiwi);
        controlQuality.distributeFood(foods);
        assertThat(stores.get(0).getFoods()).contains(watermelon);
        assertThat(stores.get(1).getFoods()).contains(apple);
        assertThat(apple.getDiscount()).isEqualTo(0);
        assertThat(stores.get(1).getFoods()).contains(mango);
        assertThat(mango.getDiscount()).isEqualTo(50);
        assertThat(stores.get(2).getFoods()).isEmpty();
    }
    @Test
    public void testResort() {
        Food watermelon = new Food("Watermelon", LocalDate.now().plusDays(20), LocalDate.now(), 100, 0);
        Food apple = new Food("Apple", LocalDate.now().plusDays(10), LocalDate.now().minusDays(10), 100, 0);
        Food mango = new Food("Mango", LocalDate.now().plusDays(3), LocalDate.now().minusDays(10), 100, 0);
        Food kiwi = new Food("Kiwi", LocalDate.now().minusDays(5), LocalDate.now().minusDays(1), 100, 0);
        foods.add(watermelon);
        foods.add(apple);
        foods.add(mango);
        foods.add(kiwi);
        controlQuality.distributeFood(foods);
        controlQuality.resort();
        assertThat(stores.get(0).getFoods()).contains(watermelon);
        assertThat(stores.get(1).getFoods()).contains(apple);
        assertThat(apple.getDiscount()).isEqualTo(0);
        assertThat(stores.get(1).getFoods()).contains(mango);
        assertThat(mango.getDiscount()).isEqualTo(50);
        assertThat(stores.get(2).getFoods()).isEmpty();
    }
}