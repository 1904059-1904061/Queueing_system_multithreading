import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GroceryQueue {
    private final Queue<Customer> queue;
    private final int maxLength;
    private final int numQueues;
    public GroceryQueue(int numQueues,int maxLength){
        this.queue = new LinkedList<>();
        this.maxLength = maxLength;
        this.numQueues = numQueues;
    }   
}
