public class Main {
    public static void main(String[] args) {
        System.out.println("====== EXERCISE 3: REFACTOR SMALL STEPS ======");
        
        // Create 3 vehicle types
        MotorBike bike = new MotorBike("29A1-12345", "Honda", 2.5);
        Car car = new Car("30H-99999", "Toyota", 15.0);
        ElectricCar eCar = new ElectricCar("51G-88888", "VinFast", 80);
        
        // Print base info for all
        System.out.println(bike.getInfo());
        System.out.println(car.getInfo());
        System.out.println(eCar.getInfo());
        
        // Test refuel and charge behaviors
        System.out.println("\n--- Testing Specific Behaviors ---");
        System.out.println("Motorbike fuel before: " + bike.getFuelLevel() + "L");
        bike.refuel(3.5);
        System.out.println("Motorbike fuel after refueling 3.5L: " + bike.getFuelLevel() + "L");
        
        System.out.println("Electric car battery before: " + eCar.getBatteryPercent() + "%");
        eCar.charge(15);
        System.out.println("Electric car battery after charging 15%: " + eCar.getBatteryPercent() + "%");
    }
}
