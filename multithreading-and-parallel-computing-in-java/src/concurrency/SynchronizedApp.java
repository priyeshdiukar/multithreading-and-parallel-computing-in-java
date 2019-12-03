package concurrency;

public class SynchronizedApp {

    private static int counter = 0;
    
    //Without synchronized the counter increment operations are not atomic.
    public static synchronized void increment() {
        ++counter;
    }

    public static void process() {
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 500; i++) {
                    increment();
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 500; i++) {
                    increment();
                }

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
    }

    public static void main(String[] args) {
        process();
        System.out.println(counter);
    }
}