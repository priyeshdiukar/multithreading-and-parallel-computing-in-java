package concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock - It has the same behavior as the "synchronized approach" with
 * some additional features.
 * 
 * new ReentrantLock (boolean fairness parameter) - fairness parameter: if it is
 * set to be true -> the longest-waiting thread will get the lock. fairness ==
 * false -> there is no access order.
 * 
 * IMPORTANT : we have to use try—catch block when doing critical section that
 * may throw exceptions — we call unlock ( ) in the finally block!
 */
public class ReentrantLockApp {
    private static int counter = 0;
    private static Lock lock = new ReentrantLock();

    public static void increment() {
        lock.lock();
        try {
            for (int i = 0; i < 10000; i++) {
                counter++;
            }
        } 
        /* To prevent thread starvation. A good practise. */
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                increment();
            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter is: " + counter);
    }
}