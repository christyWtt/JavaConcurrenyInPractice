package chapter7;

import chapter6.ImprovedTestHarness;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ImprovedTestHarnessTimeoutTest {
    @Test
    public void testTimeoutThreadsCanBeCancelled() throws ExecutionException, InterruptedException {
        ImprovedTestHarness improvedTestHarness = new ImprovedTestHarness();
        Runnable runnableLastTwoSeconds = () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        long duration = improvedTestHarness.timeTasks(2, 1, runnableLastTwoSeconds);
        long durationInSeconds = TimeUnit.SECONDS.convert(duration, TimeUnit.NANOSECONDS);
        assertEquals(durationInSeconds, 1L);
    }

    @Test
    public void testThreadsCanBeFinishedBeforeTimeout() throws InterruptedException, ExecutionException {
        ImprovedTestHarness improvedTestHarness = new ImprovedTestHarness();
        Runnable runnableLastOneSeconds = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        long duration = improvedTestHarness.timeTasks(2, 2, runnableLastOneSeconds);
        long durationInSeconds = TimeUnit.SECONDS.convert(duration, TimeUnit.NANOSECONDS);;
        assertNotEquals(durationInSeconds, 2L);
    }
}
