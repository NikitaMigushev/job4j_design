package ru.job4j.ood.lsp.menu;

import java.util.List;

public interface MenuItem {
    String getName();
    List<MenuItem> getChildren();
    ActionDelegate getActionDelegate();
}
