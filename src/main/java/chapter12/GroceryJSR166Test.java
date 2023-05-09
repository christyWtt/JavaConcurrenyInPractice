package chapter12;

import chapter11.*;
import cn.hutool.core.util.ReflectUtil;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GroceryJSR166Test extends JSR166TestCase {
    private static final int N_THREADS = Runtime.getRuntime().availableProcessors();
    private static final int EXECUTE_TIMES = 10000;
    private static final String FRUITS_FILED = "fruits";
    private static final String VEGETABLES_FILED = "vegetables";


    public void testSynchronizedGrocery() {
        CheckedBarrier checkedBarrier = new CheckedBarrier(N_THREADS);
        ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS);
        SynchronizedGrocery synchronizedGrocery = new SynchronizedGrocery();

        for (int i = 0; i < N_THREADS; i++) {
            executorService.submit(() -> {
                checkedBarrier.await();
                performGroceryTest(synchronizedGrocery);
            });
        }
        joinPool(executorService);

        threadAssertEquals(EXECUTE_TIMES * N_THREADS, getGroceryFieldSize(synchronizedGrocery, FRUITS_FILED));
        threadAssertEquals(EXECUTE_TIMES * N_THREADS, getGroceryFieldSize(synchronizedGrocery, VEGETABLES_FILED));
    }

    public void testSynchronizedCollectionGrocery() {
        CheckedBarrier checkedBarrier = new CheckedBarrier(N_THREADS);
        ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS);
        SynchronizedCollectionGrocery synchronizedCollectionGrocery = new SynchronizedCollectionGrocery();

        for (int i = 0; i < N_THREADS; i++) {
            executorService.submit(() -> {
                checkedBarrier.await();
                performGroceryTest(synchronizedCollectionGrocery);
            });
        }
        joinPool(executorService);

        threadAssertEquals(EXECUTE_TIMES * N_THREADS, getGroceryFieldSize(synchronizedCollectionGrocery, FRUITS_FILED));
        threadAssertEquals(EXECUTE_TIMES * N_THREADS, getGroceryFieldSize(synchronizedCollectionGrocery, VEGETABLES_FILED));
    }

    public void testSingleLockGrocery() {
        CheckedBarrier checkedBarrier = new CheckedBarrier(N_THREADS);
        ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS);
        SingleLockGrocery singleLockGrocery = new SingleLockGrocery();

        for (int i = 0; i < N_THREADS; i++) {
            executorService.submit(() -> {
                checkedBarrier.await();
                performGroceryTest(singleLockGrocery);
            });
        }
        joinPool(executorService);

        threadAssertEquals(EXECUTE_TIMES * N_THREADS, getGroceryFieldSize(singleLockGrocery, FRUITS_FILED));
        threadAssertEquals(EXECUTE_TIMES * N_THREADS, getGroceryFieldSize(singleLockGrocery, VEGETABLES_FILED));
    }

    public void testCopyOnWriteGrocery() {
        CheckedBarrier checkedBarrier = new CheckedBarrier(N_THREADS);
        ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS);
        CopyOnWriteGrocery copyOnWriteGrocery = new CopyOnWriteGrocery();

        for (int i = 0; i < N_THREADS; i++) {
            executorService.submit(() -> {
                checkedBarrier.await();
                performGroceryTest(copyOnWriteGrocery);
            });
        }
        joinPool(executorService);

        threadAssertEquals(EXECUTE_TIMES * N_THREADS, getGroceryFieldSize(copyOnWriteGrocery, FRUITS_FILED));
        threadAssertEquals(EXECUTE_TIMES * N_THREADS, getGroceryFieldSize(copyOnWriteGrocery, VEGETABLES_FILED));
    }

    public void testVectorGrocery() {
        CheckedBarrier checkedBarrier = new CheckedBarrier(N_THREADS);
        ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS);
        VectorGrocery vectorGrocery = new VectorGrocery();

        for (int i = 0; i < N_THREADS; i++) {
            executorService.submit(() -> {
                checkedBarrier.await();
                performGroceryTest(vectorGrocery);
            });
        }
        joinPool(executorService);

        threadAssertEquals(EXECUTE_TIMES * N_THREADS, getGroceryFieldSize(vectorGrocery, FRUITS_FILED));
        threadAssertEquals(EXECUTE_TIMES * N_THREADS, getGroceryFieldSize(vectorGrocery, VEGETABLES_FILED));
    }

    private void performGroceryTest(Grocery grocery) {
        for (int i = 0; i < EXECUTE_TIMES; i++) {
            grocery.addFruit(i, String.valueOf(i));
            grocery.addVegetable(i, String.valueOf(i));
        }
    }

    /**
     * For testing purposes only.
     */
    private int getGroceryFieldSize(Grocery grocery, String field) {
        List<?> fieldObj = (List<?>) ReflectUtil.getFieldValue(grocery, field);
        return fieldObj.size();
    }
}
