package ru.job4j.ood.lsp.menu;

import java.util.Iterator;

public class ConsoleMenuPrinter implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        Iterator<Menu.MenuItemInfo> iterator = menu.iterator();
        StringBuilder resultString = new StringBuilder();
        while (iterator.hasNext()) {
            StringBuilder lineString = new StringBuilder();
            Menu.MenuItemInfo item  = iterator.next();
            int level = getLevel(item.getNumber());
            for (int i = 0; i < level - 1; i++) {
                lineString.append(" ");
            }
            lineString.append(item.getNumber()).append(item.getName()).append(System.lineSeparator());
            resultString.append(lineString.toString());
        }
        System.out.print(resultString.toString());
    }

    private int getLevel(String number) {
        int result = 0;
        for (int i = 0; i < number.split("\\.").length; i++) {
            result++;
        }
        return result;
    }
}
