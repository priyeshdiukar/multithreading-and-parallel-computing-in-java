package concurrency;

class Worker implements Runnable {
    //Without the volatile keyword there is a good chance that this app goes into an infinite loop.
    private volatile Boolean isTerminated = false;

    @Override
    public void run() {
        while (!isTerminated) {
            System.out.println("Hello from worker class...");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public Boolean getIsTerminated() {
        return isTerminated;
    }

    public void setIsTerminated(Boolean isTerminated) {
        this.isTerminated = isTerminated;
    }

}

public class VolatileApp {

    public static void main(String[] args) {
        Worker worker = new Worker();
        Thread thread = new Thread(worker);
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.setIsTerminated(true);
        System.out.println("Finished...");

    }
}