public abstract class Vehicle {
    protected String plate;
    protected String brand;

    public Vehicle(String plate, String brand) {
        this.plate = plate;
        this.brand = brand;
    }

    // Template method for getting info formatting (Pulled Up logic)
    public String getInfo() {
        return getVehicleType() + " [" + plate + "] - " + brand;
    }

    // Let subclasses define their specific name (e.g. Car, Motorbike...)
    protected abstract String getVehicleType();
}
