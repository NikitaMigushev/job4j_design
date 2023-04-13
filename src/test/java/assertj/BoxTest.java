package assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.withPrecision;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BoxTest {
    @Test
    void isThisSphereTrue() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisSphereFalse() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name)
                .isNotEqualTo("Sphere")
                .isNotEqualTo("Cube");
    }

    @Test
    void whenGetNumberOfVertices4() {
        Box box = new Box(4, 10);
        int rsl = box.getNumberOfVertices();
        int expeted = 4;
        assertThat(rsl)
                .isEqualTo(expeted)
                .isPositive()
                .isEven();
    }

    @Test
    void whenGetNumberOfVerticesMinus1() {
        Box box = new Box(12, 10);
        int rsl = box.getNumberOfVertices();
        int expected = -1;
        assertThat(rsl)
                .isLessThan(0)
                .isBetween(-10, 0)
                .isEqualTo(-1);
    }

    @Test
    void isExistTrue() {
        Box box = new Box(0, 10);
        boolean rsl = box.isExist();
        assertThat(rsl).isTrue();
    }

    @Test
    void isExitFalse() {
        Box box = new Box(12, 10);
        boolean rsl = box.isExist();
        assertThat(rsl).isFalse();
    }

    @Test
    void whenGetArea1256dot637() {
        Box box = new Box(0, 10);
        double rsl = box.getArea();
        double exp = 1256.637;
        assertThat(rsl)
                .isEqualTo(exp, withPrecision(0.001d))
                .isCloseTo(1256.637, Percentage.withPercentage(1.0d));
    }

    @Test
    void getArea() {
        Box box = new Box(4, 10);
        double rsl = box.getArea();
        double exp = 173.205;
        assertThat(rsl)
                .isEqualTo(exp, withPrecision(0.001d))
                .isGreaterThan(0);

    }
}