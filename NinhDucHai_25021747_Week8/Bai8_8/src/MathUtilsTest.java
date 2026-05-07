import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test class for MathUtils.
 * Covers EP (Equivalence Partitioning) and BVA (Boundary Value Analysis).
 */
public class MathUtilsTest {

    // ========================================================================
    // Lifecycle hooks
    // ========================================================================

    /**
     * @BeforeAll bắt buộc phải là static vì:
     * - JUnit 5 mặc định tạo một INSTANCE MỚI của test class cho MỖI test method
     *   (mô hình "per-method") để đảm bảo các test độc lập, không ảnh hưởng lẫn nhau.
     * - @BeforeAll chạy DUY NHẤT MỘT LẦN trước TẤT CẢ các test, tức là trước khi
     *   bất kỳ instance nào được tạo ra.
     * - Vì chưa có instance nào tồn tại ở thời điểm đó, phương thức buộc phải là
     *   static (thuộc về class, không thuộc về instance) để JUnit có thể gọi được.
     */
    @BeforeAll
    static void initAll() {
        System.out.println("=== Bắt đầu chạy MathUtilsTest ===");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("=== Kết thúc ===");
    }

    // ========================================================================
    // TEST max(int a, int b) — Equivalence Partitioning
    // ========================================================================

    // --- EP1: a > b → trả về a ---
    @Test
    @DisplayName("max: a > b → returns a")
    void testMax_aGreaterThanB() {
        assertEquals(10, MathUtils.max(10, 5));
    }

    // --- EP2: a == b → trả về a (hoặc b, giá trị bằng nhau) ---
    @Test
    @DisplayName("max: a == b → returns that value")
    void testMax_aEqualsB() {
        assertEquals(7, MathUtils.max(7, 7));
    }

    // --- EP3: a < b → trả về b ---
    @Test
    @DisplayName("max: a < b → returns b")
    void testMax_aLessThanB() {
        assertEquals(20, MathUtils.max(3, 20));
    }

    // ========================================================================
    // TEST max(int a, int b) — Boundary Value Analysis
    // ========================================================================

    // --- BVA: Integer.MIN_VALUE ---
    @Test
    @DisplayName("max: both MIN_VALUE → returns MIN_VALUE")
    void testMax_bothMinValue() {
        assertEquals(Integer.MIN_VALUE, MathUtils.max(Integer.MIN_VALUE, Integer.MIN_VALUE));
    }

    @Test
    @DisplayName("max: MIN_VALUE vs 0 → returns 0")
    void testMax_minValueVsZero() {
        assertEquals(0, MathUtils.max(Integer.MIN_VALUE, 0));
    }

    @Test
    @DisplayName("max: MIN_VALUE vs MAX_VALUE → returns MAX_VALUE")
    void testMax_minValueVsMaxValue() {
        assertEquals(Integer.MAX_VALUE, MathUtils.max(Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    // --- BVA: Integer.MAX_VALUE ---
    @Test
    @DisplayName("max: both MAX_VALUE → returns MAX_VALUE")
    void testMax_bothMaxValue() {
        assertEquals(Integer.MAX_VALUE, MathUtils.max(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("max: MAX_VALUE vs 0 → returns MAX_VALUE")
    void testMax_maxValueVsZero() {
        assertEquals(Integer.MAX_VALUE, MathUtils.max(Integer.MAX_VALUE, 0));
    }

    // --- BVA: Negative values ---
    @Test
    @DisplayName("max: both negative → returns larger negative")
    void testMax_bothNegative() {
        assertEquals(-1, MathUtils.max(-5, -1));
    }

    // ========================================================================
    // TEST divide(int a, int b) — Equivalence Partitioning
    // ========================================================================

    // --- EP1: b > 0 (divisor dương) ---
    @Test
    @DisplayName("divide: b > 0 → normal division")
    void testDivide_positiveB() {
        assertEquals(5, MathUtils.divide(10, 2));
    }

    // --- EP2: b < 0 (divisor âm) ---
    @Test
    @DisplayName("divide: b < 0 → negative result")
    void testDivide_negativeB() {
        assertEquals(-5, MathUtils.divide(10, -2));
    }

    // --- EP3: b == 0 → IllegalArgumentException ---
    @Test
    @DisplayName("divide: b == 0 → throws IllegalArgumentException")
    void testDivide_byZero() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> MathUtils.divide(10, 0)
        );
        assertEquals("Divider must not be zero", ex.getMessage());
    }

    // ========================================================================
    // TEST divide — Additional edge cases
    // ========================================================================

    @Test
    @DisplayName("divide: integer division truncates toward zero")
    void testDivide_integerTruncation() {
        assertEquals(3, MathUtils.divide(7, 2));   // 7/2 = 3 (truncated)
    }

    @Test
    @DisplayName("divide: 0 / positive → 0")
    void testDivide_zeroDividend() {
        assertEquals(0, MathUtils.divide(0, 5));
    }

    @Test
    @DisplayName("divide: negative / negative → positive")
    void testDivide_bothNegative() {
        assertEquals(5, MathUtils.divide(-10, -2));
    }
}
