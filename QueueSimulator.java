import java.util.Random;

public class QueueSimulator {
    private int simulationTime;
    private BankQueue bankQueue;

    public QueueSimulator(int simulationTime, int bankTellers, int bankMaxLength) {
        this.simulationTime = simulationTime;
        this.bankQueue = new BankQueue(bankTellers, bankMaxLength);
    }

    public void simulate() {
        int currentTime = 0;
        int totalCustomersArrived = 0;
        int bankCustomersServed = 0;
        int bankCustomersLeft = 0;

        Random rand = new Random();
        int nextCustomerTime;

        while (currentTime < simulationTime) {
            // nextCustomerTime = rand.nextInt(41) + 20; // Time between 20 and 60 seconds
            nextCustomerTime = rand.nextInt(2) + 1;
            currentTime += nextCustomerTime;

            if (currentTime >= simulationTime) break;

            Customer bankCustomer = new Customer(currentTime);

            if (!bankQueue.addCustomer(bankCustomer)) {
                bankCustomer.setNotServed();
                bankCustomersLeft++;
            }

            totalCustomersArrived++;

            int servedThisTick = bankQueue.processQueue(currentTime);
            bankCustomersServed += servedThisTick;

            // To track the progress of the simulation
            System.out.println("Current Time: " + currentTime);
            System.out.println("Queue Size: " + bankQueue.getQueueSize());
        }

        // Output the results of the simulation
        System.out.println("Bank Queue Simulation Results:");
        System.out.println("Total customers served: " + bankCustomersServed);
        System.out.println("Total customers left: " + bankCustomersLeft);
        System.out.println("Total customers arrived: " + totalCustomersArrived);
    }

    public static void main(String[] args) {
        // int simulationTime = 120;
        int simulationTime = 3000;
        int bankTellers = 3;
        int bankMaxLength = 5;

        QueueSimulator simulator = new QueueSimulator(simulationTime, bankTellers, bankMaxLength);
        simulator.simulate();
    }
}
