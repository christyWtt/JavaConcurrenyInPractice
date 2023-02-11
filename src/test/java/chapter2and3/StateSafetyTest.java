package chapter2and3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StateSafetyTest {

    /**
     * Verify the problem described in Listing 3.6: Other threads can access and modify the contents of the array.
     *
     * @throws InterruptedException exception
     */
    @Test
    public void unsafeStatesTest() throws InterruptedException {
        UnsafeStates unsafeStates = new UnsafeStates();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {
            String[] originalStates = unsafeStates.getStates();
            originalStates[0] = "Test";
            countDownLatch.countDown();
        });

        countDownLatch.await();
        executorService.shutdown();
        String firstState = unsafeStates.getStates()[0];
        Assertions.assertEquals(firstState, "AK", "The first state is changed and the new value is " + firstState);
    }

    /**
     * Verify whether the problem still exists after add final keyword.
     *
     * @throws InterruptedException exception
     */
    @Test
    public void finalUnsafeStatesTest() throws InterruptedException {
        FinalUnsafeStates finalUnsafeStates = new FinalUnsafeStates();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {
            String[] originalStates = finalUnsafeStates.getStates();
            originalStates[0] = "Test";
            countDownLatch.countDown();
        });

        countDownLatch.await();
        executorService.shutdown();
        String firstState = finalUnsafeStates.getStates()[0];
        Assertions.assertEquals(firstState, "AK", "The first state is changed and the new value is " + firstState);
    }

    /**
     * Verify the issue "Other threads can access and modify the contents of the array" has been fixed.
     *
     * @throws InterruptedException exception
     */
    @Test
    public void safeStatesTest() throws InterruptedException {
        SafeStates safeStates = new SafeStates();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {
            String[] originalStates = safeStates.getStates();
            originalStates[0] = "Test";
            countDownLatch.countDown();
        });

        countDownLatch.await();
        executorService.shutdown();
        String firstState = safeStates.getStates()[0];
        Assertions.assertEquals(firstState, "AK", "The first state is changed and the new value is " + firstState);
    }
}

class UnsafeStates {
    private String [] states = new String[] {
            "AK", "AL"
    };

    public String[] getStates() {
        return states;
    }
}

class FinalUnsafeStates {
    private final String [] states = new String[] {
            "AK", "AL"
    };

    public String[] getStates() {
        return states;
    }
}

class SafeStates {
    private final String [] states = new String[] {
            "AK", "AL"
    };

    public String[] getStates() {
        return Arrays.copyOf(states, states.length);
    }
}