/**
 * Main class - Manual demo for MathUtils (Bai 8_8)
 * (JUnit tests are in MathUtilsTest.java, run via run.sh)
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Bai 8_8: The First JUnit ===\n");

        // Demo max()
        System.out.println("max(10, 5)  = " + MathUtils.max(10, 5));
        System.out.println("max(7, 7)   = " + MathUtils.max(7, 7));
        System.out.println("max(3, 20)  = " + MathUtils.max(3, 20));
        System.out.println("max(MIN, MAX) = " + MathUtils.max(Integer.MIN_VALUE, Integer.MAX_VALUE));

        // Demo divide()
        System.out.println("\ndivide(10, 2)  = " + MathUtils.divide(10, 2));
        System.out.println("divide(10, -2) = " + MathUtils.divide(10, -2));
        System.out.println("divide(7, 2)   = " + MathUtils.divide(7, 2));

        // Demo exception
        try {
            MathUtils.divide(10, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("\ndivide(10, 0) -> Exception: " + e.getMessage());
        }
    }
}
