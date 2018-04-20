package com.github.borisgk98.patiencesort;


import java.util.Deque;
import java.util.LinkedList;

class Pile<T extends Comparable<T>> implements Comparable<Pile<T>> {
    public Deque<T> deque = new LinkedList<>();

    public void add(T elem) {
        deque.addLast(elem);
    }

    public void del() {
        deque.removeLast();
    }

    public T head() {
        return deque.getLast();
    }

    @Override
    public boolean equals(Object o) {
        return deque.getFirst().equals(((Pile<T>) o).deque.getFirst());
    }

    @Override
    public int compareTo(Pile<T> o) {
        return head().compareTo(o.head());
    }
}