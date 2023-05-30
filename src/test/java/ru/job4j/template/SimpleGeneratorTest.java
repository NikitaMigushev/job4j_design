package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled("Тесты отключены")
class SimpleGeneratorTest {
    @Test
    public void whenProduceTemplateSuccessful() {
        Generator generator = new SimpleGenerator();
        String template = "{item}, price: {price}";
        Map<String, String> items = new HashMap<>();
        items.put("item", "apple");
        items.put("price", "99.99");
        String result = generator.produce(template, items);
        assertThat(result).isEqualTo("apple, price: 99.99");
    }

    @Test
    public void whenProduceTemplateFailsBecauseNoSuchKey() {
        Generator generator = new SimpleGenerator();
        String template = "{item}, price: {price}";
        Map<String, String> items = new HashMap<>();
        items.put("item", "apple");
        items.put("size", "1");
        String result = generator.produce(template, items);
        assertThatThrownBy(() -> generator.produce(template, items)).isInstanceOf(IllegalArgumentException.class);
    }
}