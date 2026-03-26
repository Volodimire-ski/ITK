package ConcurrencyTask1;

public class Producer implements Runnable{
    private MyBlockingQueue sharedQueue;

    public Producer(MyBlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true) {
            Runnable r = () -> {
                System.out.println("New thread was launched" +Thread.currentThread().getName());
            };
            try {
                sharedQueue.enqueue(r);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
