/**
 * The Discount Inspector - Bai 8_7
 * Calculate discount based on price and member type.
 */
public class DiscountCalculator {

    /**
     * Calculate the discount amount for a given price and member type.
     *
     * @param price      the original price (must be >= 0)
     * @param memberType the type of member: "GUEST", "MEMBER", or "VIP"
     * @return the discount amount
     * @throws IllegalArgumentException if price < 0 or memberType is invalid
     */
    public static double calculateDiscount(double price, String memberType) {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be non-negative");
        }

        switch (memberType) {
            case "GUEST":
                return 0;

            case "MEMBER":
                if (price < 100) {
                    return price * 0.05; // 5% discount
                } else {
                    return price * 0.10; // 10% discount
                }

            case "VIP":
                if (price < 100) {
                    return price * 0.15; // 15% discount
                } else {
                    return price * 0.20; // 20% discount
                }

            default:
                throw new IllegalArgumentException("Invalid member type: " + memberType);
        }
    }
}
