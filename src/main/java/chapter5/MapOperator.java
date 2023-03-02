package chapter5;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapOperator implements Runnable{
    public static final int COUNT = 100000;

    private final Map<Integer, Integer> map;

    public MapOperator(Map<Integer, Integer> map) {
        this.map = map;
    }

    public void run() {
        ExecutorService pool = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(COUNT);
        for (int i=0; i<COUNT; i++) {
            final int index = i;
            Runnable task = () -> {
                map.put(index, index);
                latch.countDown();
            };
            pool.submit(task);
        }

        try {
            latch.await();
            pool.shutdown();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
