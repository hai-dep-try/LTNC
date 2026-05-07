import before.*;
import after.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("====== EXERCISE 4: REFACTOR PARKING RECEIPT ======\n");

        runBeforeRefactoring();
        System.out.println("\n------------------------------------------------\n");
        runAfterRefactoring();
    }

    private static void runBeforeRefactoring() {
        System.out.println("=== BEFORE REFACTORING ===");
        before.ParkingCustomer customer = new before.ParkingCustomer("Nguyen Van A");

        customer.addTicket(new before.ParkingTicket(new before.Vehicle("29A-12345", before.Vehicle.CAR), 3));
        customer.addTicket(new before.ParkingTicket(new before.Vehicle("29B-99887", before.Vehicle.BIKE), 4));
        customer.addTicket(new before.ParkingTicket(new before.Vehicle("29C-55667", before.Vehicle.TRUCK), 6));

        System.out.println(customer.receipt());
    }

    private static void runAfterRefactoring() {
        System.out.println("=== AFTER REFACTORING (Polymorphism) ===");
        after.ParkingCustomer customer = new after.ParkingCustomer("Nguyen Van A");

        customer.addTicket(new after.ParkingTicket(new after.Car("29A-12345"), 3));
        customer.addTicket(new after.ParkingTicket(new after.Bike("29B-99887"), 4));
        customer.addTicket(new after.ParkingTicket(new after.Truck("29C-55667"), 6));

        System.out.println(customer.receipt());
    }
}
