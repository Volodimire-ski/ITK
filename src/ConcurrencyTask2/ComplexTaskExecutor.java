package ConcurrencyTask2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class ComplexTaskExecutor {
    private List<List<Integer>> resultOfAllThreads;
    private int numberOfComplexTasks;
    private CyclicBarrier barrier;


    public ComplexTaskExecutor(int numberOfComplexTasks) {
        resultOfAllThreads = Collections.synchronizedList(new ArrayList<>());
        this.numberOfComplexTasks = numberOfComplexTasks;
        barrier = new CyclicBarrier(3,()->{
            System.out.println("Barrier is broken");
            if(resultOfAllThreads.size()==numberOfComplexTasks) {
                int result = resultOfAllThreads.stream().flatMap(Collection::stream).mapToInt(a -> a).sum();
                System.out.println("Final result after barrier: " + result);
            }
        });
    }
    public void executeTasks(int countOfTasks) throws InterruptedException {
        try(ExecutorService service = Executors.newFixedThreadPool(3)) {
            for(int i = 0;i<countOfTasks;i++) {
                service.submit(() -> {
                    ComplexTask task = new ComplexTask();
                    resultOfAllThreads.add(task.execute());
                    try {
                        System.out.println(Thread.currentThread().getName()+" reached the barrier");
                        barrier.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (BrokenBarrierException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }
    public class ComplexTask {
        private List<Integer> result;

        public List<Integer> execute() {
            result = new ArrayList<>();
            for(int i = 0;i<10;i++) {
                int r = (int)(Math.random()*100);
                result.add(r);
            }
            return result;
        }
    }
}
