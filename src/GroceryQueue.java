import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GroceryQueue {
    private final Queue<Customer> queue;
    private final int maxLength;
    private final int numQueues;
    private int served = 0;
    private int left = 0;
    private final Lock lock;
    private final Condition notEmpty;
    public GroceryQueue(int numQueues,int maxLength){
        this.queue = new LinkedList<>();
        this.maxLength = maxLength;
        this.numQueues = numQueues;
    }   
    public boolean addCustomer(Customer customer) {
        lock.lock();
        try {
            if (queue.size() < maxLength) {
                queue.offer(customer);
                return true;
            } else {
                return false;
            }
        } finally {
            lock.unlock();
        }
    }
    public Customer getNextCustomer() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                if (!queue.isEmpty()) {
                    return queue.poll();
                }
            }
            return queue.poll();
        } finally {
            lock.unlock();
        }
    }
}
