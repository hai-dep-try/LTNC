package before;

public class Order {
    private String type; // "STANDARD", "EXPRESS", "FRAGILE"
    private double weight;
    private double distance;

    public Order(String type, double weight, double distance) {
        this.type = type; 
        this.weight = weight; 
        this.distance = distance;
    }

    public double getDeliveryFee() {
        if (type.equals("STANDARD")) {
            return weight * 3000 + distance * 500;
        } else if (type.equals("EXPRESS")) {
            return (weight * 3000 + distance * 500) * 1.5;
        } else if (type.equals("FRAGILE")) {
            return weight * 5000 + distance * 700 + 20000;
        }
        throw new IllegalArgumentException("Invalid order type: " + type);
    }

    public String getLabel() {
        if (type.equals("STANDARD")) return "[STANDARD]";
        if (type.equals("EXPRESS"))  return "[EXPRESS]";
        if (type.equals("FRAGILE"))  return "[FRAGILE]";
        return "[UNKNOWN]";
    }
}
