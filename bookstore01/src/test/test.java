package test;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        ArrayList<Banana> bananas = new ArrayList<Banana>();
        bananas.add(new Banana());
        bananas.add(new Banana());
        bananas.add(new Banana());
        bananas.add(new Banana());
        Plate<? extends Fruit> p1 = new Plate<Banana>(bananas);

//        p1.setItem(new Apple());
    }
}
