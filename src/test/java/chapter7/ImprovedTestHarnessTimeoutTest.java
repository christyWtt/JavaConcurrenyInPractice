package chapter7;

import chapter6.ImprovedTestHarness;
import chapter6.NeverDieTask;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ImprovedTestHarnessTimeoutTest {
    @Test
    public void testTimeoutThreadsCanBeCancelled() throws ExecutionException, InterruptedException {
        ImprovedTestHarness improvedTestHarness = new ImprovedTestHarness();
        Runnable runnableLastTenSeconds = () -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        long duration = improvedTestHarness.timeTasks(2, 5, runnableLastTenSeconds);
        long durationInSeconds = TimeUnit.SECONDS.convert(duration, TimeUnit.NANOSECONDS);
        assertEquals(durationInSeconds, 5L);
    }

    @Test
    public void testThreadsCanBeFinishedBeforeTimeout() throws InterruptedException, ExecutionException {
        ImprovedTestHarness improvedTestHarness = new ImprovedTestHarness();
        Runnable runnableLastFiveSeconds = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        long duration = improvedTestHarness.timeTasks(2, 10000, runnableLastFiveSeconds);
        long durationInSeconds = TimeUnit.SECONDS.convert(duration, TimeUnit.NANOSECONDS);;
        assertNotEquals(durationInSeconds, 2L);
    }



    @Test
    public void testNeverDieTask() throws InterruptedException, ExecutionException {
        ImprovedTestHarness improvedTestHarness = new ImprovedTestHarness();

        NeverDieTask task= new NeverDieTask();
        long duration = improvedTestHarness.timeTasks(2, 2, task);
        long durationInSeconds = TimeUnit.SECONDS.convert(duration, TimeUnit.NANOSECONDS);;
        assertNotEquals(durationInSeconds, 2L);
    }
}
