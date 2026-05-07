package after;

public class Truck extends Vehicle {
    public Truck(String plate) {
        super(plate);
    }

    @Override
    public double calculateFee(int hours) {
        return 15 + hours * 4;
    }

    @Override
    public int calculateBonusPoints(int hours) {
        return (hours > 5) ? 1 : 0; // Extra bonus for >5 hours
    }
}
