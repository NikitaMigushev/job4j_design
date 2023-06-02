package ru.job4j.ood.lsp.menu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;
    Menu menu = new SimpleMenu();
    String menuRoot = Menu.ROOT;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void initTest() {
        menu.add(menuRoot, "Сходить в магазин", STUB_ACTION);
        menu.add(menuRoot, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void whenAddThenReturnSame() {
        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Сходить в магазин").get());
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    void whenSelectPresent() {
        assertThat(menu.select("Купить молоко").isPresent()).isTrue();
    }

    @Test
    void whenSelectEmpty() {
        assertThat(menu.select("Купить чай").isPresent()).isFalse();
    }

    @Test
    void whenPrintMenu() {
        ConsoleMenuPrinter printer = new ConsoleMenuPrinter();
        String ls = System.lineSeparator();
        SimpleMenu newMenu = new SimpleMenu();
        newMenu.add(menuRoot, "Сходить в магазин", STUB_ACTION);
        newMenu.add(menuRoot, "Купить продукты", STUB_ACTION);
        newMenu.add("Сходить в магазин", "Купить хлеб", STUB_ACTION);
        printer.print(newMenu);
        StringBuilder expected = new StringBuilder();
        expected.append("1.Сходить в магазин")
                .append(ls)
                .append(" 1.1.Купить хлеб")
                .append(ls)
                .append("2.Купить продукты");
        assertThat(outputStreamCaptor.toString().trim()).isEqualTo(expected.toString());
    }
}