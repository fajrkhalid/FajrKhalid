package Lab08;

class Message {
    String text;
}

public class PostTask2 {
    public static void main(String[] args) {
        Message message = new Message();

        // Consumer thread - waits for the message
        Thread consumer = new Thread(() -> {
            synchronized (message) {
                System.out.println("Consumer waiting...");
                try {
                    message.wait(); // Goes into WAITING state
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Consumer received: " + message.text);
            }
        });

        // Producer thread - sets the message and notifies
        Thread producer = new Thread(() -> {
            try {
                Thread.sleep(1000); // Simulate some work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (message) {
                message.text = "Hello from Producer!";
                System.out.println("Producer sent message. Notifying...");
                message.notify(); // Wake up consumer
            }
        });
        consumer.start();
        producer.start();
    }
}