package chapter6;

import java.text.SimpleDateFormat;
import java.util.concurrent.*;

public class ImprovedTestHarness {
    public static final SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");

    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException, BrokenBarrierException {
        final CyclicBarrier startGate = new CyclicBarrier(nThreads + 1);
        final CyclicBarrier endGate = new CyclicBarrier(nThreads + 1);
        final ExecutorService executorService = Executors.newFixedThreadPool(nThreads);

        try {
            for (int i = 0; i < nThreads; i++) {
                executorService.execute(() -> {
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
                });
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

    public long timeTasksWithFuture(int nThreads, final Runnable task) throws InterruptedException, ExecutionException {
        final ExecutorService executorService = Executors.newFixedThreadPool(nThreads);

        Callable<Long> getNanoTime = () -> System.nanoTime();

        FutureTask<Long> startFutureTask = new FutureTask<>(getNanoTime);
        CyclicBarrier startBarrier = new CyclicBarrier(nThreads, startFutureTask);

        FutureTask<Long> endFutureTask = new FutureTask<>(getNanoTime);
        CyclicBarrier endBarrier = new CyclicBarrier(nThreads, endFutureTask);
        try {
            for (int i = 0; i < nThreads; i++) {
                executorService.submit(() -> {
                    try {
                        startBarrier.await();
                        task.run();
                        endBarrier.await();
                    } catch (InterruptedException | BrokenBarrierException ignored) {
                        Thread.currentThread().interrupt();
                    }
                });
            }

            long start = startFutureTask.get();
            long end = endFutureTask.get();
            return end - start;
        } finally {
            executorService.shutdown();
        }
    }

    public long timeTasks(int nThreads, int timeoutInSeconds, final Runnable task) throws ExecutionException, InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(nThreads);

        Callable<Long> getNanoTime = System::nanoTime;

        try {
            FutureTask<Long> startFutureTask = new FutureTask<>(getNanoTime);
            CyclicBarrier startBarrier = new CyclicBarrier(nThreads, startFutureTask);

            FutureTask<Long> endFutureTask = new FutureTask<>(getNanoTime);
            CyclicBarrier endBarrier = new CyclicBarrier(nThreads, endFutureTask);

            for (int i = 0; i < nThreads; i++) {
                executorService.submit(() -> {
                    try {
                        startBarrier.await();
                        System.out.println("Job is ready to run on " + Thread.currentThread().getName() + " " + formatter.format(System.currentTimeMillis()));
                        timedRun(task, timeoutInSeconds);
                        System.out.println("Job runs on " + Thread.currentThread().getName() + " is finished at " + formatter.format(System.currentTimeMillis()));
                        endBarrier.await();
                    } catch (BrokenBarrierException | InterruptedException ignoredException) {
                    }
                });
            }
            long start = startFutureTask.get();
            long end = endFutureTask.get();
            return end - start;
        } finally {
            executorService.shutdown();
        }
    }

    private void timedRun(final Runnable task, int timeoutInSeconds) {

        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> submittedJob = executorService.submit(task);
        String currentThreadName = Thread.currentThread().getName();
        String currentTime = formatter.format(System.currentTimeMillis());

        try {
            submittedJob.get(timeoutInSeconds, TimeUnit.SECONDS);
        } catch (ExecutionException ignoredException) {
            System.out.println("Catch ExecutionException on " + currentThreadName + " " + currentTime);
        } catch (InterruptedException ignoredException) {
            System.out.println("Catch interruptedException on " + currentThreadName + " " + currentTime);
        } catch (TimeoutException ignoredException) {
            System.out.println("Catch timeoutException on " + currentThreadName + " " + currentTime);
        } finally {
            submittedJob.cancel(true);
            executorService.shutdown();
        }
    }
}
