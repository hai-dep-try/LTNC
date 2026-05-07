package after;

public class ParkingTicket {
    private final Vehicle vehicle;
    private final int hours;

    public ParkingTicket(Vehicle vehicle, int hours) {
        this.vehicle = vehicle;
        this.hours = hours;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getHours() {
        return hours;
    }

    // Extract Method + Move Method
    public double getFee() {
        return vehicle.calculateFee(hours);
    }

    public int getBonusPoints() {
        // Base 1 point for every ticket, plus extra by vehicle type
        return 1 + vehicle.calculateBonusPoints(hours);
    }
}
