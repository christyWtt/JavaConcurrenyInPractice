package chapter13;

import chapter11.Grocery;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReentrantLockPerformanceTestByJMH {
    public static final int COUNT = 10000;
    public static final int THREAD_NUMBER = 10;

    @Benchmark
    public void testNonFairLock() throws InterruptedException {
        ImprovedReentrantGrocery nonFairGrocery = new ImprovedReentrantGrocery(false);
        addFood(nonFairGrocery);
    }

    @Benchmark
    public void testFairLock() throws InterruptedException {
        ImprovedReentrantGrocery fairGrocery = new ImprovedReentrantGrocery(true);
        addFood(fairGrocery);
    }

    private void addFood(Grocery grocery) throws InterruptedException {
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
                .include(ReentrantLockPerformanceTestByJMH.class.getSimpleName())
                .resultFormat(ResultFormatType.JSON)
                .build();
        new Runner(opts).run();
    }
}
