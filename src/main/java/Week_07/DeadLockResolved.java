package Week_07;

public class DeadLockResolved implements Runnable {
    private static final Object resource1 = new Object();
    private static final Object resource2 = new Object();

    public static void main (String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new DeadLockResolved(),"thread-1");
        Thread thread2 = new Thread(new DeadLockResolved(),"thread-2");

        thread1.start();
        thread2.start();

        //Thread monitoring
        while (thread1.isAlive() || thread1.isAlive()) {
            System.out.println("[Monitor]" + thread1.getName() + ": " + thread1.getState());
            System.out.println("[Monitor]" + thread2.getName() + ": " + thread2.getState());
            Thread.sleep(500);
        }
        System.out.println("[Main] All threads complete without deadlock.");
    }

    @Override
    public void run() {
        for (int i = 0; i<5; i++){
            synchronized (resource1) {
                System.out.println("[" + Thread.currentThread().getName() + "] Locked resource 1.");
                try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }

                synchronized (resource2) {
                    System.out.println("[" + Thread.currentThread().getName() + "] Locked resource 2.");
                    System.out.println("[" + Thread.currentThread().getName() + "] Doing work...");
                }
            }
        }
    }
}
