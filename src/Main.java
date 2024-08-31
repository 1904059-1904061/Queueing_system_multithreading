public class Main {
    public static void main(String[] args) {
        int tellers = 3;
        int maxLength = 5;
        int simulationMinutes = 2;
        int simulationSeconds = simulationMinutes * 5;
        BankQueue bankQueue = new BankQueue(tellers,maxLength);
        QueueSimulator simulator = new QueueSimulator(bankQueue,simulationSeconds);
        simulator.startSimulation();
    }
}
