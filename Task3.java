package Lab08;

class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class Task3 {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
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
        // Wait for all threads to finish
        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Final count: " + counter.getCount());
        System.out.println("Expected:    " + (numThreads * incrementsPerThread));
    }
}