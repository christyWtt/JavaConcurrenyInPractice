package chapter10;

public class LockOrderingDeadlockTest {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new OrderingDeadlockThread(lock1, lock2);
        Thread thread2 = new OrderingDeadlockThread(lock2, lock1);
        thread1.start();
        thread2.start();
    }

}

class OrderingDeadlockThread extends Thread {
    private final Object lock1;
    private final Object lock2;

    public OrderingDeadlockThread(Object lock1, Object lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        synchronized (lock1) {
            try {
                Thread.sleep(5000);
                System.out.printf("Thread name:[%s], obtain the lock:[%s], then try to obtain lock:[%s]%n", Thread.currentThread().getName(), lock1, lock2);
                synchronized (lock2) {
                    System.out.printf("Thread name:[%s], obtain the lock:[%s]", Thread.currentThread().getName(), lock2);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }
    }
}
