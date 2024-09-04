import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankQueue {
    private Queue <Customer> queue;
    private int tellers;
    private int maxQueueLength;
    private Lock lock;

    public BankQueue(int tellers, int maxQueueLength) {
        this.tellers = tellers;
        this.maxQueueLength = maxQueueLength;
        this.queue = new LinkedList <> ();
        this.lock = new ReentrantLock();
    }

    public int getQueueSize() {
        return queue.size();
    }

    public boolean addCustomer(Customer customer) {
        lock.lock();
        try {
            if (queue.size() < maxQueueLength) {
                queue.add(customer);
                return true;
            } else {
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    public int processQueue(int currentTime) {
        lock.lock();
        int servedCount = 0;
        try {
            for (int i = 0; i < tellers; i++) {
                if (!queue.isEmpty()) {
                    Customer customer = queue.peek();
                    if (customer.isServiceComplete(currentTime)) {
                        queue.poll();
                        servedCount++;
                    } else if (!customer.isServed()) {
                        customer.startService(currentTime);
                    }
                }
            }
        } finally {
            lock.unlock();
        }
        return servedCount;
    }
}
