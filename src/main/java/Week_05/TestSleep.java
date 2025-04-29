package Week_05;

public class TestSleep {
    public static void main(String[] args) {
        // Create 20 threads
        for (int i = 1; i <= 20; i++) {
            final int threadNumber = i; // Capture thread number for each thread
            Thread thread = new Thread(new Task(threadNumber));
            thread.start();

            // Optional: small sleep to stagger thread start for clearer output
            try {
                Thread.sleep(50); // 50ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Task implements Runnable {
        private int threadNumber;

        public Task(int threadNumber) {
            this.threadNumber = threadNumber;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 3; i++) { // Print 3 times
                    System.out.println("Thread-" + threadNumber + ": ONE");
                    Thread.sleep(100); // 100ms pause to simulate real-time spacing

                    System.out.println("Thread-" + threadNumber + ": TWO");
                    Thread.sleep(100);

                    System.out.println("Thread-" + threadNumber + ": THREE");
                    Thread.sleep(100);

                    System.out.println("Thread-" + threadNumber + ": XXXXXXXXXX");
                    Thread.sleep(200); // Slightly longer after the block
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
