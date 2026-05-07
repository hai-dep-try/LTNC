package before;

public class Vehicle {
    public static final int CAR = 0;
    public static final int BIKE = 1;
    public static final int TRUCK = 2;

    private final String plate;
    private final int type;

    public Vehicle(String plate, int type) {
        this.plate = plate;
        this.type = type;
    }
    public String getPlate() { return plate; }
    public int getType() { return type; }
}
