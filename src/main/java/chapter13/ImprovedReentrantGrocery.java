package chapter13;

import chapter11.Grocery;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ImprovedReentrantGrocery implements Grocery {

    private final List<String> fruits = new ArrayList<>();
    private final List<String> vegetables = new ArrayList<>();
    private final ReentrantLock fruitLock;
    private final ReentrantLock vegetableLock;

    public ImprovedReentrantGrocery(boolean isLockFair) {
        this.fruitLock = new ReentrantLock(isLockFair);
        this.vegetableLock = new ReentrantLock(isLockFair);
    }

    @Override
    public void addFruit(int index, String fruit) {
        fruitLock.lock();
        try {
            fruits.add(index, fruit);
        } finally {
            fruitLock.unlock();
        }
    }

    @Override
    public void addVegetable(int index, String vegetable) {
        vegetableLock.lock();
        try {
            vegetables.add(index, vegetable);
        } finally {
            vegetableLock.unlock();
        }
    }
}
