package test;

import java.util.ArrayList;

public class Plate<T> {
    private ArrayList<T> items;
    private T item;

    public Plate(T item) {
        this.item = item;
    }

    public Plate(ArrayList<T> bananas) {
        this.items = bananas;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }


}
