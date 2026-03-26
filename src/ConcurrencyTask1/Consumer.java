package ConcurrencyTask1;

public class Consumer implements Runnable{
    private MyBlockingQueue sharedQueue;

    public Consumer(MyBlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true) {
            Thread t = null;
            try {
                t = new Thread(sharedQueue.dequeue());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            t.start();
        }
    }
}
