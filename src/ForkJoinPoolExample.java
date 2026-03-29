import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolExample {
    public static void main(String[] args) {
        int n = 10; // Вычисление факториала для числа 10

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FactorialTask factorialTask = new FactorialTask(n);

        long result = forkJoinPool.invoke(factorialTask);

        System.out.println("Факториал " + n + "! = " + result);
    }
    private static class FactorialTask extends RecursiveTask<Long> {
        private int number;
        public FactorialTask(int n) {
            number = n;
        }


        @Override
        protected Long compute() {
            if (number==0) return (long)0;
            else if(number==1) return (long)1;
            else {
                FactorialTask newFactTask = new FactorialTask(number-1);
                newFactTask.fork();
                return (long)number*newFactTask.join();
            }
        }
    }
}
