import before.*;
import after.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("====== EXERCISE 5: DELIVERY CALCULATOR ======\n");

        runBeforeRefactoring();
        System.out.println("\n------------------------------------------------\n");
        runAfterRefactoring();
    }

    private static void runBeforeRefactoring() {
        System.out.println("=== BEFORE REFACTORING ===");
        before.Order order1 = new before.Order("STANDARD", 10, 5);
        before.Order order2 = new before.Order("EXPRESS", 10, 5);
        before.Order order3 = new before.Order("FRAGILE", 10, 5);
        before.Order order4 = new before.Order("STANDARD", 20, 15);
        
        before.Order[] orders = {order1, order2, order3, order4};
        
        for (before.Order order : orders) {
            System.out.println(order.getLabel() + " - Fee: " + order.getDeliveryFee());
        }
    }

    private static void runAfterRefactoring() {
        System.out.println("=== AFTER REFACTORING (Polymorphism) ===");
        after.Order order1 = new after.StandardOrder(10, 5);
        after.Order order2 = new after.ExpressOrder(10, 5);
        after.Order order3 = new after.FragileOrder(10, 5);
        after.Order order4 = new after.StandardOrder(20, 15);
        
        after.Order[] orders = {order1, order2, order3, order4};
        
        for (after.Order order : orders) {
            System.out.println(order.getLabel() + " - Fee: " + order.getDeliveryFee());
        }
        
        System.out.println("\n=== ADDING NEW BULKY ORDER ===");
        after.Order bulky = new after.BulkyOrder(50, 10);
        System.out.println(bulky.getLabel() + " - Fee: " + bulky.getDeliveryFee());
    }
}
