package Week_06;

public class MultiplicationTableThreads {
    public static void main(String[] args) {
        // Create and start 3 threads
        for (int i = 0; i < 3; i++) {
            final int threadNumber = i;
            final int tableNumber = i + 1; // 1, 2, or 3
            new Thread(() -> {
                for (int j = 1; j <= 3; j++) {
                    System.out.printf("Thread-%d: %d*%d = %d%n",
                            threadNumber, tableNumber, j, tableNumber * j);
                    try {
                        // Small delay to allow interleaving of threads
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}

// Solved: Implemented multithreaded multiplication table