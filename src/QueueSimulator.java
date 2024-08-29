import java.util.Random;

public class QueueSimulator {
    private final BankQueue bankQueue;
    private final int simulationTime; // in seconds
    private final Random random = new Random();
    private volatile boolean simulationRunning = true;
    private final int tellers;
    private int k = 0;
    public QueueSimulator(BankQueue bankQueue, int simulationTime, int tellers) {
        this.bankQueue = bankQueue;
        this.simulationTime = simulationTime;
        this.tellers = tellers;
    }

    public void startSimulation() {
        Thread simulationThread = new Thread(this::simulate);
        Thread customerArrivalThread = new Thread(this::customerArrival);
        Thread[] tellerThreads = new Thread[tellers];
        simulationThread.start();
        customerArrivalThread.start();

        for (int i = 0; i < tellers; i++) {
            tellerThreads[i] = new Thread(this::serveCustomer);
            tellerThreads[i].start();
        }
        try {
            simulationThread.join();
            customerArrivalThread.join();
            for (Thread tellerThread : tellerThreads) {
                tellerThread.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    private void simulate() {
        while ( k < simulationTime ) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            k++;
        }
        simulationRunning = false;
        bankQueue.signalAll();
        System.out.println("Simulation ended");
        System.out.println("Total Customers served: " + bankQueue.getserved());
        System.out.println("Total Customers left: " + bankQueue.getleft());
        System.out.println("Remaining Customer in the queue: " + bankQueue.queueSize());
    }

    private void customerArrival() {
        while (simulationRunning) {
            Customer customer = new Customer(k);
            if (!bankQueue.addCustomer(customer)) {
                bankQueue.incrementLeft();
            }
            try {
                Thread.sleep((2 + random.nextInt(5)) * 1000L); // sleep for 2 to 6 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void serveCustomer() {
        while (simulationRunning) {
            try {
                Customer customer = bankQueue.getNextCustomer();
                if (customer != null) {
                    Thread.sleep(customer.getServiceTime() * 1000L); //service time ekdom first ei constructor dye set kra ase randomly
                    bankQueue.incrementServed();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
