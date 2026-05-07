/**
 * Main class - Demo for DiscountCalculator (Bai 8_7)
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Bai 8_7: The Discount Inspector ===\n");

        // Demo: GUEST
        System.out.println("GUEST, price=50  -> discount = " + DiscountCalculator.calculateDiscount(50, "GUEST"));
        System.out.println("GUEST, price=150 -> discount = " + DiscountCalculator.calculateDiscount(150, "GUEST"));

        // Demo: MEMBER
        System.out.println("MEMBER, price=50  -> discount = " + DiscountCalculator.calculateDiscount(50, "MEMBER"));
        System.out.println("MEMBER, price=150 -> discount = " + DiscountCalculator.calculateDiscount(150, "MEMBER"));

        // Demo: VIP
        System.out.println("VIP, price=50  -> discount = " + DiscountCalculator.calculateDiscount(50, "VIP"));
        System.out.println("VIP, price=150 -> discount = " + DiscountCalculator.calculateDiscount(150, "VIP"));

        // Demo: Invalid price
        try {
            DiscountCalculator.calculateDiscount(-10, "GUEST");
        } catch (IllegalArgumentException e) {
            System.out.println("\nprice=-10 -> Exception: " + e.getMessage());
        }

        // Demo: Invalid memberType
        try {
            DiscountCalculator.calculateDiscount(50, "UNKNOWN");
        } catch (IllegalArgumentException e) {
            System.out.println("memberType=UNKNOWN -> Exception: " + e.getMessage());
        }
    }
}
