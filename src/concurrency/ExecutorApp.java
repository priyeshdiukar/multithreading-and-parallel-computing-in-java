package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ExecutorWorker implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}

/*
 * ExecutorService executorService = Executors.newCachedThreadPool(); - Going to
 * return an executorservice that can dynamically reuse threads - Before
 * starting a job —> it going to check whether there finished the job... if yes,
 * reuse them. - If there are no waiting threads —> it is going to create
 * another one - Good for the processor... effective solution.
 * 
 * ExecutorService executorService = Executors.newFixedThreadPool(N); - maximize
 * the number of threads. - If we want to start a job —> if all the threads are
 * busy, we have to wait for one to terminate
 * 
 * ExecutorService executorService = Executors.newSingIeThreadExecutor() ; - It
 * uses a single thread for the job. execute () -> runnable + callable submit()
 * -> runnable
 */
public class ExecutorApp {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        /* No multitasking */
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        /* Create as many threads as needed */
        //ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
           executorService.submit(new ExecutorWorker());
        }
        executorService.shutdown();
    }

}