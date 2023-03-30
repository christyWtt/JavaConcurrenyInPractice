package chapter8;

import chapter6.ImprovedTestHarness;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimingThreadPoolTest {
    private static final int THREAD_NUMBER = 20;
    private static final int TIMEOUT_SETTING = 10;

    @Test
    public void testPreStartAllCoreThreadsThreadPool_PerformanceBetterThan_NotPreStartCoreThreadsThreadPool() throws ExecutionException, InterruptedException {
        ImprovedTestHarness improvedTestHarness = new ImprovedTestHarness();

        Runnable runnableLastTenSeconds = () -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        long notPreStartAllCoreThreadPoolRuntime = improvedTestHarness.timeTasks(THREAD_NUMBER, TIMEOUT_SETTING, runnableLastTenSeconds, false);
        long preStartAllCoreThreadPoolRuntime = improvedTestHarness.timeTasks(THREAD_NUMBER, TIMEOUT_SETTING, runnableLastTenSeconds, true);

        System.out.println("not pre start all core thread total time is \t" + notPreStartAllCoreThreadPoolRuntime + " " +
                "milliseconds");
        System.out.println("pre start all core thread total time is \t\t" + preStartAllCoreThreadPoolRuntime + " " +
                "milliseconds");

        assertTrue(preStartAllCoreThreadPoolRuntime < notPreStartAllCoreThreadPoolRuntime);
    }
}
