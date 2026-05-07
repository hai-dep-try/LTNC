import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test class for GradeClassifier.
 * Test cases designed purely from JavaDoc specification (black-box).
 *
 * Spec:
 *   [0.0, 5.0)  → "Yếu"
 *   [5.0, 6.5)  → "Trung bình"
 *   [6.5, 8.0)  → "Khá"
 *   [8.0, 10.0] → "Giỏi"
 *   Ngoài [0.0, 10.0]: IllegalArgumentException
 */
public class GradeClassifierTest {

    // ========================================================================
    // EP — Equivalence Partitioning
    // ========================================================================

    // --- EP1: Invalid — gpa < 0 ---
    @Test
    @DisplayName("EP: gpa < 0 → IllegalArgumentException")
    void testEP_negativeGpa() {
        assertThrows(IllegalArgumentException.class,
                () -> GradeClassifier.classifyGrade(-1.0));
    }

    // --- EP2: Invalid — gpa > 10 ---
    @Test
    @DisplayName("EP: gpa > 10 → IllegalArgumentException")
    void testEP_overTenGpa() {
        assertThrows(IllegalArgumentException.class,
                () -> GradeClassifier.classifyGrade(11.0));
    }

    // --- EP3: [0.0, 5.0) → "Yếu" ---
    @Test
    @DisplayName("EP: gpa=2.5 → Yếu")
    void testEP_yeu() {
        assertEquals("Yếu", GradeClassifier.classifyGrade(2.5));
    }

    // --- EP4: [5.0, 6.5) → "Trung bình" ---
    @Test
    @DisplayName("EP: gpa=5.5 → Trung bình")
    void testEP_trungBinh() {
        assertEquals("Trung bình", GradeClassifier.classifyGrade(5.5));
    }

    // --- EP5: [6.5, 8.0) → "Khá" ---
    @Test
    @DisplayName("EP: gpa=7.0 → Khá")
    void testEP_kha() {
        assertEquals("Khá", GradeClassifier.classifyGrade(7.0));
    }

    // --- EP6: [8.0, 10.0] → "Giỏi" ---
    @Test
    @DisplayName("EP: gpa=9.0 → Giỏi")
    void testEP_gioi() {
        assertEquals("Giỏi", GradeClassifier.classifyGrade(9.0));
    }

    // ========================================================================
    // BVA — Boundary Value Analysis
    // ========================================================================

    // --- Ranh giới 0.0 (biên dưới toàn cục) ---
    @Test
    @DisplayName("BVA: gpa=-0.01 → Exception (ngay dưới 0)")
    void testBVA_belowZero() {
        assertThrows(IllegalArgumentException.class,
                () -> GradeClassifier.classifyGrade(-0.01));
    }

    @Test
    @DisplayName("BVA: gpa=0.0 → Yếu (biên dưới)")
    void testBVA_zero() {
        assertEquals("Yếu", GradeClassifier.classifyGrade(0.0));
    }

    @Test
    @DisplayName("BVA: gpa=0.01 → Yếu (ngay trên 0)")
    void testBVA_justAboveZero() {
        assertEquals("Yếu", GradeClassifier.classifyGrade(0.01));
    }

    // --- Ranh giới 5.0 (Yếu ↔ Trung bình) ---
    @Test
    @DisplayName("BVA: gpa=4.99 → Yếu (ngay dưới 5)")
    void testBVA_justBelow5() {
        assertEquals("Yếu", GradeClassifier.classifyGrade(4.99));
    }

    @Test
    @DisplayName("BVA: gpa=5.0 → Trung bình (ranh giới)")
    void testBVA_exactly5() {
        // Theo JavaDoc: [5.0, 6.5) → "Trung bình", nên 5.0 phải là "Trung bình"
        assertEquals("Trung bình", GradeClassifier.classifyGrade(5.0));
    }

    @Test
    @DisplayName("BVA: gpa=5.01 → Trung bình (ngay trên 5)")
    void testBVA_justAbove5() {
        assertEquals("Trung bình", GradeClassifier.classifyGrade(5.01));
    }

    // --- Ranh giới 6.5 (Trung bình ↔ Khá) ---
    @Test
    @DisplayName("BVA: gpa=6.49 → Trung bình (ngay dưới 6.5)")
    void testBVA_justBelow6_5() {
        assertEquals("Trung bình", GradeClassifier.classifyGrade(6.49));
    }

    @Test
    @DisplayName("BVA: gpa=6.5 → Khá (ranh giới)")
    void testBVA_exactly6_5() {
        // Theo JavaDoc: [6.5, 8.0) → "Khá", nên 6.5 phải là "Khá"
        assertEquals("Khá", GradeClassifier.classifyGrade(6.5));
    }

    @Test
    @DisplayName("BVA: gpa=6.51 → Khá (ngay trên 6.5)")
    void testBVA_justAbove6_5() {
        assertEquals("Khá", GradeClassifier.classifyGrade(6.51));
    }

    // --- Ranh giới 8.0 (Khá ↔ Giỏi) ---
    @Test
    @DisplayName("BVA: gpa=7.99 → Khá (ngay dưới 8)")
    void testBVA_justBelow8() {
        assertEquals("Khá", GradeClassifier.classifyGrade(7.99));
    }

    @Test
    @DisplayName("BVA: gpa=8.0 → Giỏi (ranh giới)")
    void testBVA_exactly8() {
        assertEquals("Giỏi", GradeClassifier.classifyGrade(8.0));
    }

    @Test
    @DisplayName("BVA: gpa=8.01 → Giỏi (ngay trên 8)")
    void testBVA_justAbove8() {
        assertEquals("Giỏi", GradeClassifier.classifyGrade(8.01));
    }

    // --- Ranh giới 10.0 (biên trên toàn cục) ---
    @Test
    @DisplayName("BVA: gpa=9.99 → Giỏi (ngay dưới 10)")
    void testBVA_justBelow10() {
        assertEquals("Giỏi", GradeClassifier.classifyGrade(9.99));
    }

    @Test
    @DisplayName("BVA: gpa=10.0 → Giỏi (biên trên)")
    void testBVA_exactly10() {
        assertEquals("Giỏi", GradeClassifier.classifyGrade(10.0));
    }

    @Test
    @DisplayName("BVA: gpa=10.01 → Exception (ngay trên 10)")
    void testBVA_justAbove10() {
        assertThrows(IllegalArgumentException.class,
                () -> GradeClassifier.classifyGrade(10.01));
    }

    // ========================================================================
    // Exception message verification (Yêu cầu 5)
    // ========================================================================

    @Test
    @DisplayName("Exception: gpa=-0.1 → message chứa giá trị GPA")
    void testException_negativePt1() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> GradeClassifier.classifyGrade(-0.1)
        );
        assertTrue(ex.getMessage().contains("-0.1"),
                "Message should contain the invalid GPA value");
    }

    @Test
    @DisplayName("Exception: gpa=10.1 → message chứa giá trị GPA")
    void testException_aboveTenPt1() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> GradeClassifier.classifyGrade(10.1)
        );
        assertTrue(ex.getMessage().contains("10.1"),
                "Message should contain the invalid GPA value");
    }
}
