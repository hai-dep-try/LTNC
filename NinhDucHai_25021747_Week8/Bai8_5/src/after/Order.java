package after;

public abstract class Order {
    protected double weight;
    protected double distance;

    public Order(double weight, double distance) {
        this.weight = weight; 
        this.distance = distance;
    }

    public abstract double getDeliveryFee();
    public abstract String getLabel();
}
