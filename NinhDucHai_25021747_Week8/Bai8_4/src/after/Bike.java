package after;

public class Bike extends Vehicle {
    public Bike(String plate) {
        super(plate);
    }

    @Override
    public double calculateFee(int hours) {
        double fee = 5;
        if (hours > 3) {
            fee += (hours - 3) * 2;
        }
        return fee;
    }

    @Override
    public int calculateBonusPoints(int hours) {
        return 0; // No extra points
    }
}
