package concurrency;

class Processor {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("We are in producer...");
            // waits infinitely - a bad practise
            //wait();
            wait(10000);
            System.out.println("Back in producer...");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(1000);
        synchronized (this) {
            System.out.println("We are in consumer...");
            notify();
            //notifyAll();
            Thread.sleep(3000);
        }
    }
}

public class WaitAndNotifyApp {

    public static void main(String[] args) {
        Processor processor = new Processor();
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
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

}