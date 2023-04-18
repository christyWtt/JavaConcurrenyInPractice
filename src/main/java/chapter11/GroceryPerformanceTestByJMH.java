package chapter11;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class GroceryPerformanceTestByJMH {
    public static final int COUNT = 10000;
    public static final int THREAD_NUMBER = 10;

    @Benchmark
    public static void testSynchronizedGrocery() throws InterruptedException {
        SynchronizedGrocery synchronizedGrocery = new SynchronizedGrocery();
        addFood(synchronizedGrocery);
    }

    @Benchmark
    public static void testSingleLockGrocery() throws InterruptedException {
        SingleLockGrocery singleLockGrocery = new SingleLockGrocery();
        addFood(singleLockGrocery);
    }

    @Benchmark
    public static void testCopyOnWriteGrocery() throws InterruptedException {
        CopyOnWriteGrocery copyOnWriteGrocery = new CopyOnWriteGrocery();
        addFood(copyOnWriteGrocery);
    }

    @Benchmark
    public static void testVectorGrocery() throws InterruptedException {
        VectorGrocery vectorGrocery = new VectorGrocery();
        addFood(vectorGrocery);
    }

    @Benchmark
    public static void testSynchronizedCollectionGrocery() throws InterruptedException {
        SynchronizedCollectionGrocery synchronizedCollectionGrocery = new SynchronizedCollectionGrocery();
        addFood(synchronizedCollectionGrocery);
    }

    private static void addFood(Grocery grocery) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(THREAD_NUMBER);
        for(int i=0; i<THREAD_NUMBER; i++) {
            pool.submit(() -> {
                for (int j=0; j<COUNT; j++) {
                    grocery.addFruit(j, "Fruit:" + j);
                    grocery.addVegetable(j, "Vegetable:" + j);
                }
                latch.countDown();
            });
        }
        latch.await();
        pool.shutdown();
    }

    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(GroceryPerformanceTestByJMH.class.getSimpleName())
                .resultFormat(ResultFormatType.JSON)
                .build();
        new Runner(opts).run();
    }
}
