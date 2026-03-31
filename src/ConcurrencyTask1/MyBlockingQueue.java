package ConcurrencyTask1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class MyBlockingQueue<T> {
    private final BlockingQueue<T> myQueue;

    public MyBlockingQueue() {
        this.myQueue = new LinkedBlockingDeque<>(10);
    }

    public synchronized void enqueue(T input) throws InterruptedException {
        while(myQueue.remainingCapacity()<1) {
            wait();
        }
        myQueue.offer(input);
        notifyAll();
    }
    public synchronized T dequeue() throws InterruptedException {
        while (myQueue.isEmpty()) {
            wait();
        }
        notifyAll();
        return myQueue.poll();
    }
    public int size() {
        return myQueue.size();
    }
}
