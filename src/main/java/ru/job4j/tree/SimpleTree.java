package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Method finds Node by parent and adds child Node. If child already exists then false,
     * otherwise true.
     *
     * @param parent - Node that we are looking for
     * @param child  - Node that we add as a child to parent Node
     * @return boolean
     */
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> parentN = findBy(parent);
        if (parentN.isPresent() && findBy(child).isEmpty()) {
            parentN.get().children.add(new Node<>(child));
            rsl = true;
        }
        return rsl;
    }

    public Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> predicate = el -> el.value.equals(value);
        return findByPredicate(predicate);
    }

    public boolean isBinary() {
        Predicate<Node<E>> predicate = el -> el.children.size() > 2;
        return findByPredicate(predicate).isEmpty();
    }

    private List<Node<E>> traverseTree() {
        List<Node<E>> nodes = new ArrayList<>();
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            nodes.add(node);
            queue.addAll(node.children);
        }
        return nodes;
    }
}
