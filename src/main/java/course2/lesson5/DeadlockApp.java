package course2.lesson5;

public class DeadlockApp {

    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();
        new Thread(()->{
            synchronized (lock1){
                System.out.println("Thread 1 locked lock1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2){
                    System.out.println("Thread 1 locked lock2");
                }
            }
        }).start();

        new Thread(()->{
            synchronized (lock2){
                System.out.println("Thread 2 locked lock1");
                synchronized (lock1){
                    System.out.println("Thread 2 locked lock2");
                }
            }
        }).start();
    }
}
