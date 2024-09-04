import java.util.Random;

public class Customer {
    private int arrivalTime;
    private int serviceTime;
    private int serviceEndTime;
    private boolean served;

    public Customer(int currentTime) {
        this.arrivalTime = currentTime;  // Set the customer's arrival time to the current simulation time
        Random rand = new Random();
        // this.serviceTime = rand.nextInt(241) + 60; // Service time between 60 and 300 seconds
        this.serviceTime = rand.nextInt(3) + 2;
        this.served = false;
        this.serviceEndTime = -1;  // -1 indicates that service has not yet started
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public boolean isServed() {
        return served;
    }

    public void setNotServed() {
        this.served = false;
    }

    public boolean isServiceComplete(int currentTime) {
        return this.serviceEndTime != -1 && currentTime >= this.serviceEndTime;
    }

    public void startService(int currentTime) {
        if (!served) {
            this.serviceEndTime = currentTime + this.serviceTime;
            this.served = true;
        }
    }

    public int getServiceEndTime() {
        return serviceEndTime;
    }
}
