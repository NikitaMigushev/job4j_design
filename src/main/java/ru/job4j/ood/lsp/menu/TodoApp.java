package ru.job4j.ood.lsp.menu;

import java.util.Optional;
import java.util.Scanner;

public class TodoApp {
    private Menu menu;
    private MenuPrinter menuPrinter;

    public TodoApp(Menu menu, MenuPrinter menuPrinter) {
        this.menu = menu;
        this.menuPrinter = menuPrinter;
    }

    public void addItemToRoot(String itemName, ActionDelegate actionDelegate) {
        menu.add(Menu.ROOT, itemName, actionDelegate);
    }

    public void addItemToParent(String parentName, String itemName, ActionDelegate actionDelegate) {
        menu.add(parentName, itemName, actionDelegate);
    }

    public void performAction(String itemName) {
        Optional<Menu.MenuItemInfo> menuItem = menu.select(itemName);
        menuItem.ifPresent(item -> item.getActionDelegate().delegate());
    }

    public void printMenu() {
        menuPrinter.print(menu);
    }

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        MenuPrinter menuPrinter = new ConsoleMenuPrinter();
        TodoApp todoApp = new TodoApp(menu, menuPrinter);
        todoApp.addItemToRoot("Item 1", () -> System.out.println("Action 1"));
        todoApp.addItemToRoot("Item 2", () -> System.out.println("Action 2"));
        todoApp.addItemToParent("Item 2", "Subitem 1", () -> System.out.println("Action 3"));
        todoApp.addItemToParent("Item 2", "Subitem 2", () -> System.out.println("Action 4"));
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            todoApp.printMenu();
            System.out.print("Введите название элемента меню для выполнения действия (или 'exit' для выхода): ");
            input = scanner.nextLine();

            if (!input.equalsIgnoreCase("exit")) {
                todoApp.performAction(input);
                System.out.println();
            }
        } while (!input.equalsIgnoreCase("exit"));
    }
}
