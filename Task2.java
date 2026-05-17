package Lab08;

class MyRunnable implements Runnable {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Working...");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted during sleep. Stopping.");
                break; // Exit the loop gracefully
            }
        }
        System.out.println("Thread finished.");
    }
}

public class Task2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyRunnable());
        thread.start();

        Thread.sleep(1000); // Let it work for 1 second

        thread.interrupt(); // Interrupt the thread
    }
}