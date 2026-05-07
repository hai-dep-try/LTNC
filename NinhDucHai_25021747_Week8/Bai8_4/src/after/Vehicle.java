package after;

public abstract class Vehicle {
    private final String plate;

    public Vehicle(String plate) {
        this.plate = plate;
    }

    public String getPlate() {
        return plate;
    }

    // Polymorphic methods to handle logic
    public abstract double calculateFee(int hours);
    public abstract int calculateBonusPoints(int hours);
}
