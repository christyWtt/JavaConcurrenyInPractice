package chapter11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedCollectionGrocery implements Grocery {
    private final List<String> fruits = Collections.synchronizedList(new ArrayList<>());
    private final List<String> vegetables = Collections.synchronizedList(new ArrayList<>());

    public void addFruit(int index, String fruit) {
        fruits.add(index, fruit);
    }
    public void addVegetable(int index, String vegetable) {
        vegetables.add(index, vegetable);
    }
}
