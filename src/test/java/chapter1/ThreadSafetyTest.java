package chapter1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSafetyTest {

    private final int THREAD_NUMBER = 10000;

    static class UnsafeSequence {
        private int value;

        public int getNext() {
            return value++;
        }

        public int getValue() {
            return value;
        }
    }

    static class SafeSequence {
        private int value;

        public synchronized int getNext() {
            return value++;
        }

        public int getValue() {
            return value;
        }
    }

    @Test
    public void unsafeThreadTest() throws InterruptedException {
        UnsafeSequence unsafeSequence = new UnsafeSequence();
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUMBER);
        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i=0 ; i<THREAD_NUMBER; i++) {
            executorService.execute(() -> {
                unsafeSequence.getNext();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        Assertions.assertEquals(THREAD_NUMBER, unsafeSequence.getValue(), "Current value is " + unsafeSequence.getValue());
    }

    @Test
    public void safeThreadTest() throws InterruptedException {
        SafeSequence safeSequence = new SafeSequence();
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUMBER);
        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i=0 ; i<THREAD_NUMBER; i++) {
            executorService.execute(() -> {
                safeSequence.getNext();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        Assertions.assertEquals(THREAD_NUMBER, safeSequence.getValue(), "Current value is " + safeSequence.getValue());
    }
}

