package Lab08;

class LifecycleThread extends Thread {
    public LifecycleThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(getName() + " has started.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(getName() + " was interrupted.");
        }
        System.out.println(getName() + " is ending.");
    }
}

public class PostTask3 {
    public static void main(String[] args) throws InterruptedException {
        LifecycleThread t1 = new LifecycleThread("Thread-1");
        LifecycleThread t2 = new LifecycleThread("Thread-2");

        System.out.println("t1 state: " + t1.getState());
        System.out.println("t2 state: " + t2.getState());

        t1.start();
        t2.start();

        while (t1.getState() != Thread.State.TERMINATED || t2.getState() != Thread.State.TERMINATED) {
            System.out.println("t1: " + t1.getState() + " | t2: " + t2.getState());
            Thread.sleep(300);
        }

        t1.join();
        t2.join();

        System.out.println("Done! Both threads terminated.");
    }
}