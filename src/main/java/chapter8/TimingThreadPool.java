package chapter8;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class TimingThreadPool extends ThreadPoolExecutor {
    private static final ThreadLocal<Long> startTime = new ThreadLocal<>();
    private static final Logger log = Logger.getLogger("TimingThreadPool");
    private static final AtomicLong numberTasks = new AtomicLong();
    private static final AtomicLong totalTime = new AtomicLong();

    public TimingThreadPool(int corePoolSize, boolean shouldStartAllCoreThreads) {
        super(corePoolSize, corePoolSize, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        if (shouldStartAllCoreThreads) {
            prestartAllCoreThreads();
        }
    }

    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        log.fine(String.format("Thread %s: start %s", t, r));
        startTime.set(System.nanoTime());
    }

    protected void afterExecute(Runnable r, Throwable t) {
        try {
            long endTime = System.nanoTime();
            long taskTime = endTime - startTime.get();
            numberTasks.incrementAndGet();
            totalTime.addAndGet(taskTime);
            log.fine(String.format("Thread %s: end %s, time=%dns", t, r, taskTime));

        }finally {
            super.afterExecute(r, t);
        }
    }

    protected void terminated(){
        try {
            log.info(String.format("Terminated: avg time=%dns", totalTime.get()/numberTasks.get()));
        }finally {
            super.terminated();
        }
    }
}
