package com.github.borisgk98.patiencesort;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PatienceSort {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        arr = sort(arr);
        System.out.println();
        for (Integer el : arr) {
            System.out.print(el + " ");
        }
    }

    public static <T extends Comparable<T>> T[] sort(T[] arr) {
        // заполняем стопки
        Pile<T>[] piles = new Pile[arr.length];
        int last = -1;
        for (T el : arr) {
            Pile<T> newpile = new Pile<T>() {{
                add(el);
            }};
            // нужно найти минимальный элемент, который больше либо равен el
            int found = bsearch(piles, newpile);
            if (found != last + 1) {
                piles[found].add(el);
            } else {
                piles[++last] = newpile;
            }
        }

        //получаем отсортированный массив
        PriorityQueue<Pile<T>> pq = new PriorityQueue<Pile<T>>() {{
            for (Pile<T> p : piles) {
                if (p != null) {
                    add(p);
                }
            }
        }};
        T[] rez = Arrays.copyOf(arr, arr.length);
        last = 0;
        while (!pq.isEmpty()) {
            Pile<T> top = pq.poll();
            rez[last++] = top.head();
            top.del();
            if (!top.deque.isEmpty()) {
                pq.add(top);
            }
        }
        return rez;
    }

    public static <T extends Comparable<T>> int bsearch(T[] arr, T el) {
        int l = -1, r = arr.length - 1;
        while (l != r - 1) {
            int m = (l + r) / 2;
            if (arr[m] == null || arr[m].compareTo(el) >= 0) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }
}