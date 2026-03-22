public class Electronics extends Product {
    private double warrantyFee;

    public Electronics(String name, double basePrice, double warrantyFee) {
        super(name, basePrice);
        this.warrantyFee = warrantyFee;
    }

    @Override
    public double getFinalPrice() {
        return basePrice + (basePrice * 0.10) + warrantyFee;
    }
}