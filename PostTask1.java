package Lab08;

import java.util.concurrent.locks.ReentrantLock;

class LockCounter {
    private int count = 0;
    private ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public void tryIncrement() {
        if (lock.tryLock()) {
            try {
                count++;
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " could not get lock, skipping.");
        }
    }

    public int getCount() {
        return count;
    }
}

public class PostTask1 {
    public static void main(String[] args) throws InterruptedException {
        LockCounter counter = new LockCounter();
        int numThreads = 4;
        int incrementsPerThread = 1000;

        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Final count: " + counter.getCount());
        System.out.println("Expected:    " + (numThreads * incrementsPerThread));
    }
}