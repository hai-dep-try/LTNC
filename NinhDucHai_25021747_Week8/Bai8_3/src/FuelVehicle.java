public abstract class FuelVehicle extends Vehicle {
    protected double fuelLevel; // Pushed down field

    public FuelVehicle(String plate, String brand, double fuelLevel) {
        super(plate, brand);
        this.fuelLevel = fuelLevel;
    }

    // Refuel logic is also centralized here instead of duplicated
    public void refuel(double liters) {
        this.fuelLevel += liters;
    }

    public double getFuelLevel() {
        return fuelLevel;
    }
}
