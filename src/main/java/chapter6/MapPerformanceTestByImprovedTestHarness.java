package chapter6;

import chapter4.ImprovedMap;
import chapter5.MapOperator;

import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;

public class MapPerformanceTestByImprovedTestHarness {
    public static final int THREAD_NUMBER = 10;

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        ImprovedTestHarness improvedTestHarness = new ImprovedTestHarness();

        System.out.printf("Thread number:[%d], Insert elements number: [%d], The [%s]'s execution time:[%d]%n", THREAD_NUMBER, MapOperator.COUNT, "synchronizedMap", improvedTestHarness.timeTasks(THREAD_NUMBER, new MapOperator(Collections.synchronizedMap(new HashMap<>()))));
        System.out.printf("Thread number:[%d], Insert elements number: [%d], The [%s]'s execution time:[%d]%n", THREAD_NUMBER, MapOperator.COUNT, "concurrentHashMap", improvedTestHarness.timeTasks(THREAD_NUMBER, new MapOperator(new ConcurrentHashMap<>())));
        System.out.printf("Thread number:[%d], Insert elements number: [%d], The [%s]'s execution time:[%d]%n", THREAD_NUMBER, MapOperator.COUNT, "improvedMap", improvedTestHarness.timeTasks(THREAD_NUMBER, new MapOperator(new ImprovedMap<>(new HashMap<>()))));
    }
}
