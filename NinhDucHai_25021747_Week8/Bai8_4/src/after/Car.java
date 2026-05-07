package after;

public class Car extends Vehicle {
    public Car(String plate) {
        super(plate);
    }

    @Override
    public double calculateFee(int hours) {
        double fee = 10;
        if (hours > 2) {
            fee += (hours - 2) * 3;
        }
        return fee;
    }

    @Override
    public int calculateBonusPoints(int hours) {
        return 0; // No extra points
    }
}
