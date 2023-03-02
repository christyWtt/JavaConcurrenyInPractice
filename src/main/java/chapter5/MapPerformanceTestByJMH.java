package chapter5;

import chapter4.ImprovedMap;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class MapPerformanceTestByJMH {
    public static final int COUNT = 50000;

    @Fork(value = 2)
    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @BenchmarkMode(Mode.Throughput)
    public static void testSynchronizedMap() throws InterruptedException {
        putValuesWithMultipleThread(Collections.synchronizedMap(new HashMap<>()));
    }

    @Fork(value = 2)
    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @BenchmarkMode(Mode.Throughput)
    public static void testConcurrentHashMap() throws InterruptedException {
        putValuesWithMultipleThread(new ConcurrentHashMap<>());
    }

    @Fork(value = 2)
    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @BenchmarkMode(Mode.Throughput)
    public static void testImprovedMap() throws InterruptedException {
        putValuesWithMultipleThread(new ImprovedMap<>(new HashMap<>()));
    }

    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }

    private static void putValuesWithMultipleThread(Map<Integer, Integer> map) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(10);
        for(int i=0; i<10; i++) {
            pool.submit(() -> {
                for (int j=0; j<COUNT; j++) {
                    map.put(j, j);
                }
                latch.countDown();
            });
        }
        latch.await();
        pool.shutdown();
    }
}
