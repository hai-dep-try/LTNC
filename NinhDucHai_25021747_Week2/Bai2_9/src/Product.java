import java.util.Scanner;

public class Product {

    // Instance fields
    private String name;
    private double price;
    private int    quantity;
    private double discount;

    // Static Fields
    private static double taxRate      = 0.1;   // Default VAT 10%
    private static double totalRevenue = 0.0;   // Total revenue for the entire chain

    // Constructor
    public Product(String name, double price, int quantity, double discount) {
        this.name     = name;
        this.price    = price;
        this.quantity = quantity;
        this.discount = discount;
    }

    // Methods

    /** Updates the VAT rate for the entire system */
    public static void updateTaxRate(double newRate) {
        taxRate = newRate;
    }

    /**
     * Calculates final price after discount and VAT.
     * Formula: finalPrice = (price - discount) * (1 + taxRate)
     */
    public double calculateFinalPrice() {
        return (price - discount) * (1 + taxRate);
    }

    /** Updates the discount for a specific product */
    public void updateDiscount(double newDiscount) {
        this.discount = newDiscount;
    }

    /**
     * Sell process:
     * - If amount <= quantity: decrease stock, add to revenue, print success.
     * - If amount >  quantity: print error to System.err.
     */
    public void sell(int amount) {
        if (amount <= quantity) {
            double revenue = amount * calculateFinalPrice();
            quantity      -= amount;
            totalRevenue  += revenue;
            System.out.printf("Successfully sold %d of \"%s\". "
                            + "Revenue: %.2f USD. Remaining stock: %d%n",
                    amount, name, revenue, quantity);
        } else {
            System.err.printf("Insufficient stock for \"%s\" "
                            + "(Requested: %d, Available: %d).%n",
                    name, amount, quantity);
        }
    }

    // Getters
    public String getName()          { return name; }
    public static double getTotalRevenue() { return totalRevenue; }

    // Main
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input information for 2 products
        System.out.println("Enter information for Product 1:");
        System.out.print("Name     : "); String name1     = sc.nextLine();
        System.out.print("Price    : "); double price1    = sc.nextDouble();
        System.out.print("Quantity : "); int    qty1      = sc.nextInt();
        System.out.print("Discount : "); double discount1 = sc.nextDouble();
        sc.nextLine(); // consume newline

        System.out.println("\nEnter information for Product 2:");
        System.out.print("Name     : "); String name2     = sc.nextLine();
        System.out.print("Price    : "); double price2    = sc.nextDouble();
        System.out.print("Quantity : "); int    qty2      = sc.nextInt();
        System.out.print("Discount : "); double discount2 = sc.nextDouble();
        sc.nextLine();

        Product p1 = new Product(name1, price1, qty1, discount1);
        Product p2 = new Product(name2, price2, qty2, discount2);

        // 2. Perform transactions
        System.out.println("\n--- Performing Transactions ---");
        System.out.printf("Enter quantity to buy for \"%s\": ", p1.getName());
        int buy1 = sc.nextInt();
        p1.sell(buy1);

        System.out.printf("Enter quantity to buy for \"%s\": ", p2.getName());
        int buy2 = sc.nextInt();
        p2.sell(buy2);

        // 3i. Print final price (current taxRate)
        System.out.println("\nFinal Price (Current taxRate = " + taxRate + "):");
        System.out.printf("  %-20s -> %.2f USD%n", p1.getName(), p1.calculateFinalPrice());
        System.out.printf("  %-20s -> %.2f USD%n", p2.getName(), p2.calculateFinalPrice());

        // 3ii. Update tax rate to 8%
        Product.updateTaxRate(0.08);
        System.out.println("\nAfter calling updateTaxRate(0.08):");
        System.out.printf("  %-20s -> %.2f USD%n", p1.getName(), p1.calculateFinalPrice());
        System.out.printf("  %-20s -> %.2f USD%n", p2.getName(), p2.calculateFinalPrice());

        // 3iii. Change discount for p1
        p1.updateDiscount(10.0);
        System.out.println("\nAfter calling p1.updateDiscount(10.0):");
        System.out.printf("  %-20s -> %.2f USD%n", p1.getName(), p1.calculateFinalPrice());
        System.out.printf("  %-20s -> %.2f USD%n", p2.getName(), p2.calculateFinalPrice());

        // 4. Total system revenue
        System.out.printf("%n=== Total System Revenue: %.2f USD ===%n", Product.getTotalRevenue());

        sc.close();
    }
}