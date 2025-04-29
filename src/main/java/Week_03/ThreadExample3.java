package Week_03;

public class ThreadExample3 {
    public static class MyRunnable implements Runnable {
        @Override
        public void run(){
            System.out.println("My Thread Running");
            System.out.println("My Thread Finished");
        }
    }

    public static void main (String[] args){
        Thread thread = new Thread(new MyRunnable());
        thread.start();
    }
}
