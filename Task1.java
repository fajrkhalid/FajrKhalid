package Lab08;

class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("Running: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(getName() + " was interrupted.");
        }
    }
}

public class Task1 {
    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread("Thread-1");
        MyThread t2 = new MyThread("Thread-2");

        System.out.println("t1 state before start: " + t1.getState());
        System.out.println("t2 state before start: " + t2.getState());

        t1.start();
        t2.start();

        while (t1.getState() != Thread.State.TERMINATED || t2.getState() != Thread.State.TERMINATED) {
            System.out.println("t1: " + t1.getState() + " | t2: " + t2.getState());
            Thread.sleep(300);
        }

        System.out.println("t1 final state: " + t1.getState());
        System.out.println("t2 final state: " + t2.getState());
    }
}