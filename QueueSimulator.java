import java.util.Random;

public class QueueSimulator {
    private int simulationTime;
    private BankQueue bankQueue;
    private GroceryQueues groceryQueues;

    public QueueSimulator(int simulationTime, int bankTellers, int bankMaxLength, int numCashiers, int groceryMaxLength) {
        this.simulationTime = simulationTime;
        this.bankQueue = new BankQueue(bankTellers, bankMaxLength);
        this.groceryQueues = new GroceryQueues(numCashiers, groceryMaxLength);
    }

    // Run the simulation, process customers, and generate results
    public void simulate() {
        int currentTime = 0;
        int totalCustomersArrived = 0;
        int bankCustomersServed = 0;
        int groceryCustomersServed = 0;
        int bankCustomersLeft = 0;
        int groceryCustomersLeft = 0;

        Random rand = new Random();
        int nextCustomerTime;

        while (currentTime < simulationTime) {
            nextCustomerTime = rand.nextInt(2) + 1; 
            currentTime += nextCustomerTime;

            if (currentTime >= simulationTime) break;

            // BankQueue customer
            Customer bankCustomer = new Customer(currentTime);
            if (!bankQueue.addCustomer(bankCustomer)) {
                bankCustomer.setNotServed();
                bankCustomersLeft++;
            }
            totalCustomersArrived++;
            bankCustomersServed += bankQueue.processQueue(currentTime);

            // GroceryQueue customer
            Customer groceryCustomer = new Customer(currentTime);
            if (!groceryQueues.addCustomer(groceryCustomer, currentTime)) {
                groceryCustomersLeft++;
            }
            totalCustomersArrived++;
            groceryCustomersServed += groceryQueues.processQueues(currentTime);

            // Output the current state of the simulation
            // System.out.println("Current Time: " + currentTime);
            // System.out.println("Bank Queue Size: " + bankQueue.getQueueSize());
            // System.out.println("Total Grocery Queue Size: " + groceryQueues.getTotalQueueSize());
        }

        // Final results of the simulation
        System.out.println("Bank Queue Simulation Results:");
        System.out.println("Total customers served: " + bankCustomersServed);
        System.out.println("Total customers left: " + bankCustomersLeft);
        System.out.println("Total customers arrived: " + totalCustomersArrived);

        System.out.println("Grocery Queue Simulation Results:");
        System.out.println("Total customers served: " + groceryCustomersServed);
        System.out.println("Total customers left: " + groceryCustomersLeft);
        System.out.println("Total customers arrived: " + totalCustomersArrived);
    }

    public static void main(String[] args) {
        int simulationTime = 3000;
        // int simulationTime = 7200;
        int bankTellers = 3;
        int bankMaxLength = 5;
        int numCashiers = 3;
        int groceryMaxLength = 2;

        QueueSimulator simulator = new QueueSimulator(simulationTime, bankTellers, bankMaxLength, numCashiers, groceryMaxLength);
        simulator.simulate();
    }
}
