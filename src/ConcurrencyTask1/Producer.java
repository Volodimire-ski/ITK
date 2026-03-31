package ConcurrencyTask1;

public class Producer implements Runnable{
    private final MyBlockingQueue sharedQueue;
    private final int poisonPill;
    private final int ppPerProducer;

    public Producer(MyBlockingQueue<Integer> sharedQueue,int poisonPill,int ppPerProducer) {
        this.sharedQueue = sharedQueue;
        this.poisonPill = poisonPill;
        this.ppPerProducer = ppPerProducer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 7; i++) {
                int produced = (int) (Math.random() * 100);
                sharedQueue.enqueue(produced);
                System.out.printf("Producer %s put %d to queue \n", Thread.currentThread().getName(), produced);
            }
            for(int i = 0;i<ppPerProducer;i++) {
                sharedQueue.enqueue(poisonPill);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
