package after;

public class FragileOrder extends Order {
    public FragileOrder(double weight, double distance) {
        super(weight, distance);
    }

    @Override
    public double getDeliveryFee() {
        return weight * 5000 + distance * 700 + 20000;
    }

    @Override
    public String getLabel() {
        return "[FRAGILE]";
    }
}
