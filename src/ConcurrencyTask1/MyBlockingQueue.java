package ConcurrencyTask1;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;

public class MyBlockingQueue implements Collection{
    private BlockingQueue<Runnable> myQueue;

    public MyBlockingQueue() {
        this.myQueue = new LinkedBlockingQueue<>(20);
    }

    public BlockingQueue<Runnable> getMyQueue() {
        return myQueue;
    }

    public synchronized void enqueue(Runnable input) throws InterruptedException {
        while(myQueue.remainingCapacity()<1) {
            wait(100);
        }
        myQueue.offer(input);
        notify();
    }
    public synchronized Runnable dequeue() throws InterruptedException {
        while (myQueue.isEmpty()) {
            wait(100);
        }
        notify();
        return myQueue.poll();
    }
    public int size() {
        return myQueue.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

}
