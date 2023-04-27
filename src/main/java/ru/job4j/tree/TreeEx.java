package ru.job4j.tree;

import java.util.*;

public class TreeEx<T> {
    private T value;
    private List<TreeEx<T>> children;

    private TreeEx(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public static <T> TreeEx<T> of(T value) {
        return new TreeEx<>(value);
    }

    public TreeEx<T> addChild(T value) {
        TreeEx<T> newChild = new TreeEx<>(value);
        children.add(newChild);
        return newChild;
    }

    public static <T> Optional<TreeEx<T>> search(T value, TreeEx<T> root) {
        Queue<TreeEx<T>> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeEx<T> currentNode = queue.remove();
            if (currentNode.getValue().equals(value)) {
                return Optional.of(currentNode);
            } else {
                queue.addAll(currentNode.getChildren());
            }
        }
        return Optional.empty();
    }

    public T getValue() {
        return value;
    }

    public List<TreeEx<T>> getChildren() {
        return children;
    }

    public static void main(String[] args) {
        TreeEx<Integer> root = TreeEx.of(10);
        TreeEx<Integer> rootFirstChild = root.addChild(2);
        TreeEx<Integer> depthMostChild = rootFirstChild.addChild(3);
        TreeEx<Integer> rootSecondChild = root.addChild(4);

        System.out.println(TreeEx.search(4, root));
    }
}
