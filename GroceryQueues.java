import java.util.Random;

public class GroceryQueues {
    private GroceryQueue[] queues;
    private int maxQueueLength;

    public GroceryQueues(int numCashiers, int maxQueueLength) {
        this.maxQueueLength = maxQueueLength;
        this.queues = new GroceryQueue[numCashiers];
        for (int i = 0; i < numCashiers; i++) {
            queues[i] = new GroceryQueue(i, maxQueueLength);
        }
    }

    public int getTotalQueueSize() {
        int totalSize = 0;
        for (GroceryQueue queue : queues) {
            totalSize += queue.getQueueSize();
        }
        return totalSize;
    }

    public GroceryQueue getShortestQueue() {
        GroceryQueue shortestQueue = queues[0];
        for (GroceryQueue queue : queues) {
            if (queue.getQueueSize() < shortestQueue.getQueueSize()) {
                shortestQueue = queue;
            } else if (queue.getQueueSize() == shortestQueue.getQueueSize()) {
                Random rand = new Random();
                if (rand.nextBoolean()) {
                    shortestQueue = queue;
                }
            }
        }
        return shortestQueue;
    }

    public boolean addCustomer(Customer customer) {
        GroceryQueue queue = getShortestQueue();
        return queue.addCustomer(customer);
    }

    public int processQueues(int currentTime) {
        int servedCount = 0;
        for (GroceryQueue queue : queues) {
            servedCount += queue.processQueue(currentTime);
        }
        return servedCount;
    }
}
