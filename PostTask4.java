package Lab08;

class InvalidTaskException extends RuntimeException {
    public InvalidTaskException(String message) {
        super(message);
    }
}

class TaskRunnable implements Runnable {
    private int input;

    public TaskRunnable(int input) {
        this.input = input;
    }

    @Override
    public void run() {
        if (input < 0) {
            throw new InvalidTaskException("Invalid input: " + input + " (negative numbers not allowed)");
        }
        System.out.println("Task executed with input: " + input);
    }
}

public class PostTask4 {
    public static void main(String[] args) {
        // Thread with valid input
        Thread t1 = new Thread(new TaskRunnable(10));

        // Thread with invalid input — set an UncaughtExceptionHandler
        Thread t2 = new Thread(new TaskRunnable(-5));
        t2.setUncaughtExceptionHandler((thread, ex) -> {
            System.out.println("Caught exception in " + thread.getName() + ": " + ex.getMessage());
        });

        t1.start();
        t2.start();
    }
}