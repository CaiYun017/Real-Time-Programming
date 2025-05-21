package Week_09;

public class Main {
    public static void main(String[] args) {
        BankAccountWithLock account = new BankAccountWithLock(1000);

        // Thread to read balance
        Runnable readTask = () -> {
            for (int i = 0; i < 3; i++) {
                account.getBalance();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // Thread to withdraw money
        Runnable withdrawTask = () -> {
            for (int i = 0; i < 2; i++) {
                account.withdraw(300);
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // Create threads
        Thread t1 = new Thread(readTask, "Reader-1");
        Thread t2 = new Thread(readTask, "Reader-2");
        Thread t3 = new Thread(withdrawTask, "Withdrawer-1");
        Thread t4 = new Thread(withdrawTask, "Withdrawer-2");

        // Start threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
