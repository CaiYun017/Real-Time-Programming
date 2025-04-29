package Week_02;

public class MainThread {
    public static void main(String[] args){
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getId());
    }

    public static class MyThread extends Thread{
    }

    public static class MyThreadThread {
    }
}
