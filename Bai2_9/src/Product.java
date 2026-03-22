import java.util.Scanner;

public class Product {

    //Instance fields
    private String name;
    private double price;
    private int    quantity;
    private double discount;

    // Static Fields
    private static double taxRate      = 0.1;   // VAT mặc định 10 %
    private static double totalRevenue = 0.0;   // Tổng doanh thu toàn chuỗi

    // Constructor
    public Product(String name, double price, int quantity, double discount) {
        this.name     = name;
        this.price    = price;
        this.quantity = quantity;
        this.discount = discount;
    }

    // Methods

    /** Cập nhật mức thuế VAT mới cho toàn hệ thống */
    public static void updateTaxRate(double newRate) {
        taxRate = newRate;
    }

    /**
     * Tính giá cuối cùng sau khi trừ giảm giá và cộng thuế VAT.
     * finalPrice = (price - discount) × (1 + taxRate)
     */
    public double calculateFinalPrice() {
        return (price - discount) * (1 + taxRate);
    }

    /** Cập nhật mức giảm giá mới cho sản phẩm */
    public void updateDiscount(double newDiscount) {
        this.discount = newDiscount;
    }

    /**
     * Bán hàng:
     *   - Nếu amount <= quantity: trừ tồn kho, cộng doanh thu, in thành công.
     *   - Nếu amount >  quantity: in lỗi ra System.err.
     */
    public void sell(int amount) {
        if (amount <= quantity) {
            double revenue = amount * calculateFinalPrice();
            quantity      -= amount;
            totalRevenue  += revenue;
            System.out.printf("Bán thành công %d sản phẩm \"%s\". "
                            + "Thu được: %.2f VND. Còn lại trong kho: %d%n",
                    amount, name, revenue, quantity);
        } else {
            System.err.printf("Không đủ hàng trong kho cho sản phẩm \"%s\" "
                            + "(yêu cầu %d, tồn kho %d).%n",
                    name, amount, quantity);
        }
    }

    // Getters (hỗ trợ in ấn)
    public String getName()          { return name; }
    public static double getTotalRevenue() { return totalRevenue; }

    // Main
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Nhập thông tin 2 sản phẩm
        System.out.println("=== Nhập thông tin sản phẩm 1 ===");
        System.out.print("Tên      : "); String name1     = sc.nextLine();
        System.out.print("Giá      : "); double price1    = sc.nextDouble();
        System.out.print("Số lượng : "); int    qty1      = sc.nextInt();
        System.out.print("Giảm giá : "); double discount1 = sc.nextDouble();
        sc.nextLine(); // consume newline

        System.out.println("\n=== Nhập thông tin sản phẩm 2 ===");
        System.out.print("Tên      : "); String name2     = sc.nextLine();
        System.out.print("Giá      : "); double price2    = sc.nextDouble();
        System.out.print("Số lượng : "); int    qty2      = sc.nextInt();
        System.out.print("Giảm giá : "); double discount2 = sc.nextDouble();
        sc.nextLine();

        Product p1 = new Product(name1, price1, qty1, discount1);
        Product p2 = new Product(name2, price2, qty2, discount2);

        // 2. Thực hiện giao dịch
        System.out.println("\n=== Thực hiện giao dịch ===");
        System.out.printf("Nhập số lượng muốn mua cho \"%s\": ", p1.getName());
        int buy1 = sc.nextInt();
        p1.sell(buy1);

        System.out.printf("Nhập số lượng muốn mua cho \"%s\": ", p2.getName());
        int buy2 = sc.nextInt();
        p2.sell(buy2);

        // 3i. In giá cuối cùng của p1 và p2 (taxRate hiện tại)
        System.out.println("\n=== Giá cuối cùng (taxRate = " + taxRate + ") ===");
        System.out.printf("  %-20s → %.2f VND%n", p1.getName(), p1.calculateFinalPrice());
        System.out.printf("  %-20s → %.2f VND%n", p2.getName(), p2.calculateFinalPrice());

        // 3ii. Giảm thuế xuống 8 %
        Product.updateTaxRate(0.08);
        System.out.println("\n=== Sau khi gọi updateTaxRate(0.08) ===");
        System.out.printf("  %-20s → %.2f VND%n", p1.getName(), p1.calculateFinalPrice());
        System.out.printf("  %-20s → %.2f VND%n", p2.getName(), p2.calculateFinalPrice());

        // 3iii. Thay đổi giảm giá của p1 thành 10.0
        p1.updateDiscount(10.0);
        System.out.println("\n=== Sau khi gọi p1.updateDiscount(10.0) ===");
        System.out.printf("  %-20s → %.2f VND%n", p1.getName(), p1.calculateFinalPrice());
        System.out.printf("  %-20s → %.2f VND%n", p2.getName(), p2.calculateFinalPrice());

        // 4. Tổng doanh thu
        System.out.printf("%n=== Tổng doanh thu toàn hệ thống: %.2f VND ===%n", Product.getTotalRevenue());

        sc.close();
    }
}