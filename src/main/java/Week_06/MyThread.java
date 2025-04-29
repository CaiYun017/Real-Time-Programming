package Week_06;

import java.util.Scanner;

public class MyThread extends Thread {
    private volatile boolean running = true;
    private int counter = 0;

    public void run() {
        while (running) {
            System.out.println("Thread is running - Message #" + (++counter));
            try {
                Thread.sleep(500); // Add small delay to prevent flooding the console
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread terminated gracefully");
    }

    public void shutdown() {
        running = false;
    }
}

class MyVolatile {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();

        System.out.println("Press ENTER to stop the thread...");
        new Scanner(System.in).nextLine(); // Wait for ENTER key

        thread.shutdown();

        try {
            thread.join(); // Wa
            // it for the thread to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread exiting");
    }
}
