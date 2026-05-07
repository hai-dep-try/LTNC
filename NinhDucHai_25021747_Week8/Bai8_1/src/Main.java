public class Main {
    public static void main(String[] args) {
        System.out.println("====== EXERCISE 1: THE SMELL HUNTER ======");
        System.out.println("All refactored code is in separate files.");
        
        // Test snippet A
        A_CalculateFee feeCalculator = new A_CalculateFee();
        System.out.println("\n--- Snippet A: Calculate Fee ---");
        System.out.println("Non-member fee (10 hours, rate 50): " + feeCalculator.calculateFee("Type", 10, 50.0, false));
        System.out.println("Member fee (10 hours, rate 50): " + feeCalculator.calculateFee("Type", 10, 50.0, true));

        // Test snippet C
        System.out.println("\n--- Snippet C: Polymorphism ---");
        Shape rect = new Rectangle(5, 10);
        Shape tri = new Triangle(4, 5);
        Shape circle = new Circle(3);
        System.out.println("Rectangle area (5x10): " + rect.getArea());
        System.out.println("Triangle area (base 4, height 5): " + tri.getArea());
        System.out.printf("Circle area (radius 3): %.5f\n", circle.getArea());
        
        // Test snippet D
        System.out.println("\n--- Snippet D: Data Clumps ---");
        Author author = new Author("Nguyen Van A", "a@email.com", "0123456789", "Hanoi");
        Report report = new Report("Monthly Report", "Content...", author);
        System.out.println("Author extracted to object: " + report.getAuthor().getName());
    }
}
