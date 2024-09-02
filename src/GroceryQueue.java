import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GroceryQueue {
    private List<Queue<Customer>> queues;
    private final int maxLength;
    private final int numQueues;
    private final Lock lock;
    private final Condition notEmpty;
    public GroceryQueue(int numQueues,int maxLength){
        this.maxLength = maxLength;
        this.numQueues = numQueues;
        this.queues = new ArrayList<>(numQueues);
        for (int i = 0; i < numQueues; i++) {
            queues.add(new LinkedList<>());
        }
        this.lock = new ReentrantLock();
        this.notEmpty = lock.newCondition();
    }   
}
