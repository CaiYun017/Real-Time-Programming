package Week_07;

public class ThreadComparison {
    private static final int NUM_THREADS = 10;
    private static final int ITERATIONS = 100000;
    private static int normalCounter = 0;
    private static int syncCounter = 0;
    private static final Object lock = new Object();

    public static void main (String[] args) throws InterruptedException {
        //Test normal threads
        long normalStart = System.nanoTime();
        NormalThread[] normalThreads = new NormalThread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            normalThreads[i] = new NormalThread();
            normalThreads[i].start();
        }

        for (NormalThread thread : normalThreads) {
            thread.join();
        }
        long normalEnd = System.nanoTime();
        double normalTime = (normalEnd - normalStart) / 1_000_000_000.0;

        // Test synchronized threads
        long syncStart = System.nanoTime();
        SynchronizedThread[] syncThreads = new SynchronizedThread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            syncThreads[i] = new SynchronizedThread();
            syncThreads[i].start();
        }
        for (SynchronizedThread thread : syncThreads) {
            thread.join();
        }
        long syncEnd = System.nanoTime();
        double syncTime = (syncEnd - syncStart) / 1_000_000_000.0;

        // Display results
        System.out.println("Normal thread = " + String.format("%.8f", normalTime) + " seconds");
        System.out.println("Synchronized thread = " + String.format("%.8f", syncTime) + " seconds");
    }

    static class NormalThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < ITERATIONS; i++) {
                normalCounter++;
            }
        }
    }

    static class SynchronizedThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < ITERATIONS; i++) {
                synchronized (lock) {
                    syncCounter++;
                }
            }
        }

    }
}
