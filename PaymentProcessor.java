public class PaymentProcessor {
    public void processPayment(double amount) {
        // BUG: multiplying instead of processing
        amount = amount * 9999;
        System.out.println("Payment processed: " + amount);
    }
}