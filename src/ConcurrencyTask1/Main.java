package ConcurrencyTask1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueue threadQueue = new MyBlockingQueue();
        Thread producer = new Thread(new Producer(threadQueue));
        Thread consumer = new Thread(new Consumer(threadQueue));
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        producer.start();
        consumer.start();
        Thread.currentThread().sleep(50);
        executorService.invokeAll(threadQueue);
    }
}
