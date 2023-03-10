package chapter6;

import java.util.concurrent.*;

public class ImprovedTestHarness {
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException, BrokenBarrierException {
        final CyclicBarrier startGate = new CyclicBarrier(nThreads + 1);
        final CyclicBarrier endGate = new CyclicBarrier(nThreads + 1);
        final ExecutorService executorService = Executors.newFixedThreadPool(nThreads);

        try {
            for (int i = 0; i < nThreads; i++) {
                executorService.execute(new Thread(() -> {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.await();
                        }
                    } catch (BrokenBarrierException | InterruptedException ignored) {
                        Thread.currentThread().interrupt();
                    }
                }));
            }
            startGate.await();
            long start = System.nanoTime();

            endGate.await();
            long end = System.nanoTime();
            return end-start;
        } finally {
            executorService.shutdown();
        }
    }

}
