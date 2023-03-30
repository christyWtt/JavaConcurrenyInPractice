package chapter6;

public class NeverDieTask implements Runnable {
    private volatile long count;

    @Override
    public void run() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            try {
                count++;
                System.out.println(count);
                Thread.currentThread().sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Hehe, interrupt again just helpless...");
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            } catch (Exception e) {
                System.out.println("Xian zhe ye shi xian ze...");
                throw new RuntimeException(e);
            }
        }
    }

    public long getCount() {
        return count;
    }
}