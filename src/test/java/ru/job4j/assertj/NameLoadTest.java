package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import ru.job4j.assertj.NameLoad;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmptyException() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkDoesNotContainEqualsException() {
        NameLoad nameLoad = new NameLoad();
        String nameA = "1=A";
        String nameB = "2=B";
        String nameC = "3C";

        assertThatThrownBy(() -> nameLoad.parse(nameA, nameB, nameC))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("this name: %s does not contain the symbol \"=\"", nameC));
    }

    @Test
    void checkDoesNotContainKeyException() {
        NameLoad nameLoad = new NameLoad();
        String nameA = "1=A";
        String nameB = "=B";
        String nameC = "3=C";

        assertThatThrownBy(() -> nameLoad.parse(nameA, nameB, nameC))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("this name: %s does not contain a key", nameB));
    }

    @Test
    void checkDoesNotContainValueException() {
        NameLoad nameLoad = new NameLoad();
        String nameA = "1=";
        String nameB = "2=B";
        String nameC = "3=C";

        assertThatThrownBy(() -> nameLoad.parse(nameA, nameB, nameC))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("this name: %s does not contain a value", nameA));
    }
}