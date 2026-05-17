package Lab08;

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class BankAccount {
    private int balance;

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() + " deposited " + amount + " | Balance: " + balance);
    }

    public synchronized void withdraw(int amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Cannot withdraw " + amount + ", balance is only " + balance);
        }
        balance -= amount;
        System.out.println(Thread.currentThread().getName() + " withdrew " + amount + " | Balance: " + balance);
    }

    public int getBalance() {
        return balance;
    }
}

public class PostTask5 {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount(500);

        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                try {
                    int action = (int) (Math.random() * 2); // 0 = deposit, 1 = withdraw
                    if (action == 0) {
                        account.deposit(50);
                    } else {
                        account.withdraw(100);
                    }
                    Thread.sleep(100);
                } catch (InsufficientFundsException e) {
                    System.out.println(Thread.currentThread().getName() + " WARNING: " + e.getMessage());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Final balance: " + account.getBalance());
    }
}