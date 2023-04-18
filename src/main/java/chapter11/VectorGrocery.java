package chapter11;

import java.util.List;
import java.util.Vector;

public class VectorGrocery implements Grocery {
    private final List<String> fruits = new Vector<>();
    private final List<String> vegetables = new Vector<>();

    public void addFruit(int index, String fruit) {
        fruits.add(index, fruit);
    }
    public void addVegetable(int index, String vegetable) {
        vegetables.add(index, vegetable);
    }
}
