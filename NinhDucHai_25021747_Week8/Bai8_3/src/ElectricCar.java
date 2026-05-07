public class ElectricCar extends Vehicle {
    protected int batteryPercent; // Pushed down field

    public ElectricCar(String plate, String brand, int batteryPercent) {
        super(plate, brand);
        this.batteryPercent = batteryPercent;
    }

    public void charge(int percent) {
        this.batteryPercent += percent;
        if (this.batteryPercent > 100) {
            this.batteryPercent = 100;
        }
    }

    public int getBatteryPercent() {
        return batteryPercent;
    }

    @Override
    protected String getVehicleType() {
        return "Electric Car";
    }
}
