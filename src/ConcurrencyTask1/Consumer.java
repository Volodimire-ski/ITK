package ConcurrencyTask1;

public class Consumer implements Runnable {
    private final MyBlockingQueue sharedQueue;
    private final int poisonPill;

    public Consumer(MyBlockingQueue sharedQueue, int poisonPill) {
        this.sharedQueue = sharedQueue;
        this.poisonPill = poisonPill;

    }

    @Override
    public void run() {
        while (true) {
            try {
                int toExecute = (int)sharedQueue.dequeue();
                if(toExecute==poisonPill) break;
                else {
                    System.out.printf("Consumer %s printed %d \n", Thread.currentThread().getName(), toExecute);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.printf("Customer %s finished, queue size is %d \n", Thread.currentThread().getName(), sharedQueue.size());
    }
}
