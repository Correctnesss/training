package course2.lesson5;

public class WaitNotifyApp {

    Object lock = new Object();

    private String currentLetter = "A";

    public static void main(String[] args) {

        //ABABABABABABABABAB
        WaitNotifyApp app = new WaitNotifyApp();
        Thread t1 = new Thread(() -> {
            app.printA();
        });

        Thread t2 = new Thread(() -> {
            app.printB();
        });

        t1.start();
        t2.start();
    }

    public void printA() {
        synchronized (lock) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (!currentLetter.equals("A")) {
                        lock.wait();
                    }
                    System.out.print("A");
                    currentLetter = "B";
                    lock.notifyAll();
                }
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    public void printB() {
        synchronized (lock) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (!currentLetter.equals("B")) {
                        lock.wait();
                    }
                    System.out.print("B");
                    currentLetter = "A";
                    lock.notifyAll();
                }
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
