package course2.lesson5;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceConditionApp {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Object lock = new Object();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
//                synchronized (lock) {
                    counter.increment();
//                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
//                synchronized (lock) {
                    counter.increment();
//                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();


        System.out.println("Counter = " + counter.getValue());

    }

    public static class Counter {
        private AtomicInteger value;

        public Counter() {
            this.value = new AtomicInteger(0);
        }

        public int getValue() {
            return value.intValue();
        }

        public void increment() {
            value.incrementAndGet();
        }

    }
}
