package ConcurrencyTask1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        MyBlockingQueue<Integer> threadQueue = new MyBlockingQueue();
        try (ExecutorService executorService = Executors.newFixedThreadPool(5)) {
            for (int i = 0; i < 5; i++) {
                executorService.submit(new Consumer(threadQueue, -1));
                executorService.submit(new Consumer(threadQueue, -1));
                executorService.submit(new Producer(threadQueue, -1,2));
            }
        }
        System.out.println("Queue size after work: "+threadQueue.size());
    }
}
