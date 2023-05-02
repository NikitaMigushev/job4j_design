package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/appWithoutComment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenAppWithComments() {
        String path = "./data/appWithComments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username")).isNull();
    }

    @Test
    void whenAppWithIlligalArgumentNoValue() {
        String path = "./data/appWithIllegalArg.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenAppWithIlligalArgumentNoKey() {
        String path = "./data/appWithIllegalArgNoKey.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenAppWithIlligalArgumentOnlyEquals() {
        String path = "./data/appWithIllegalArgOnlyEquals.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }
}