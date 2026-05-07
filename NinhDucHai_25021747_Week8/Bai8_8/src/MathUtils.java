/**
 * Utility class providing basic math operations.
 */
public class MathUtils {

    /**
     * Returns the larger of two integers.
     * If both are equal, returns that value.
     *
     * @param a first integer
     * @param b second integer
     * @return the larger of a and b
     */
    public static int max(int a, int b) {
        if (a >= b) return a;
        return b;
    }

    /**
     * Divides a by b using integer division.
     *
     * @param a the dividend
     * @param b the divisor (must not be zero)
     * @return a / b
     * @throws IllegalArgumentException if b is zero
     */
    public static int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divider must not be zero");
        }
        return a / b;
    }
}
