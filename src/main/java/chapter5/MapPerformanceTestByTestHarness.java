package chapter5;

import chapter4.ImprovedMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapPerformanceTestByTestHarness {
    public static final int THREAD_NUMBER = 10;

    public static void main(String[] args) throws InterruptedException {
        final Map<Integer, Integer> synchronizedMap =  Collections.synchronizedMap(new HashMap<>());
        final Map<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        final Map<Integer, Integer> improvedMap = new ImprovedMap<>(new HashMap<>());
        TestHarness testHarness = new TestHarness();

        System.out.printf("Thread number:[%d], Insert elements number: [%d], The [%s]'s execution time:[%d]%n", THREAD_NUMBER, MapOperator.COUNT, "synchronizedMap", testHarness.timeTasks(THREAD_NUMBER, new MapOperator(synchronizedMap)));
        System.out.printf("Thread number:[%d], Insert elements number: [%d], The [%s]'s execution time:[%d]%n", THREAD_NUMBER, MapOperator.COUNT, "concurrentHashMap", testHarness.timeTasks(THREAD_NUMBER, new MapOperator(concurrentHashMap)));
        System.out.printf("Thread number:[%d], Insert elements number: [%d], The [%s]'s execution time:[%d]%n", THREAD_NUMBER, MapOperator.COUNT, "improvedMap", testHarness.timeTasks(THREAD_NUMBER, new MapOperator(improvedMap)));
    }
}
