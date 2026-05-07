import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test class for BankAccount.
 * EP & BVA for deposit() and withdraw().
 * Initial balance = 500 before each test (via @BeforeEach).
 */
public class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        // Số dư ban đầu 500 trước mỗi test
        account = new BankAccount("ACC-001", "Nguyen Van A", 500);
    }

    // ========================================================================
    // deposit(double amount) — Equivalence Partitioning
    // ========================================================================

    // --- EP1: amount > 0 (valid) → nạp thành công ---
    @Test
    @DisplayName("deposit: amount > 0 → balance increases")
    void testDeposit_validAmount() {
        account.deposit(200);
        assertEquals(700, account.getBalance(), 0.001);
    }

    // --- EP2: amount == 0 (invalid) → IllegalArgumentException ---
    @Test
    @DisplayName("deposit: amount == 0 → throws IllegalArgumentException")
    void testDeposit_zero() {
        assertThrows(IllegalArgumentException.class,
                () -> account.deposit(0));
    }

    // --- EP3: amount < 0 (invalid) → IllegalArgumentException ---
    @Test
    @DisplayName("deposit: amount < 0 → throws IllegalArgumentException")
    void testDeposit_negative() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> account.deposit(-100)
        );
        assertTrue(ex.getMessage().contains("lớn hơn 0"));
    }

    // ========================================================================
    // deposit(double amount) — Boundary Value Analysis
    // ========================================================================

    @Test
    @DisplayName("deposit BVA: amount = -0.01 → Exception (ngay dưới 0)")
    void testDeposit_BVA_justBelowZero() {
        assertThrows(IllegalArgumentException.class,
                () -> account.deposit(-0.01));
    }

    @Test
    @DisplayName("deposit BVA: amount = 0.0 → Exception (tại 0)")
    void testDeposit_BVA_exactlyZero() {
        assertThrows(IllegalArgumentException.class,
                () -> account.deposit(0.0));
    }

    @Test
    @DisplayName("deposit BVA: amount = 0.01 → success (ngay trên 0)")
    void testDeposit_BVA_justAboveZero() {
        account.deposit(0.01);
        assertEquals(500.01, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("deposit BVA: amount rất lớn → balance tăng đúng")
    void testDeposit_BVA_largeAmount() {
        account.deposit(1_000_000);
        assertEquals(1_000_500, account.getBalance(), 0.001);
    }

    // ========================================================================
    // withdraw(double amount) — Equivalence Partitioning
    // ========================================================================

    // --- EP1: 0 < amount <= balance (valid) → rút thành công ---
    @Test
    @DisplayName("withdraw: 0 < amount <= balance → true, balance decreases")
    void testWithdraw_validAmount() {
        assertTrue(account.withdraw(200));
        assertEquals(300, account.getBalance(), 0.001);
    }

    // --- EP2: amount > balance (valid amount, insufficient funds) → false ---
    @Test
    @DisplayName("withdraw: amount > balance → false, balance unchanged")
    void testWithdraw_insufficientFunds() {
        assertFalse(account.withdraw(600));
        assertEquals(500, account.getBalance(), 0.001);
    }

    // --- EP3: amount == 0 (invalid) → IllegalArgumentException ---
    @Test
    @DisplayName("withdraw: amount == 0 → throws IllegalArgumentException")
    void testWithdraw_zero() {
        assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(0));
    }

    // --- EP4: amount < 0 (invalid) → IllegalArgumentException ---
    @Test
    @DisplayName("withdraw: amount < 0 → throws IllegalArgumentException")
    void testWithdraw_negative() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> account.withdraw(-50)
        );
        assertTrue(ex.getMessage().contains("lớn hơn 0"));
    }

    // ========================================================================
    // withdraw(double amount) — Boundary Value Analysis
    // ========================================================================

    @Test
    @DisplayName("withdraw BVA: amount = -0.01 → Exception (ngay dưới 0)")
    void testWithdraw_BVA_justBelowZero() {
        assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(-0.01));
    }

    @Test
    @DisplayName("withdraw BVA: amount = 0.0 → Exception (tại 0)")
    void testWithdraw_BVA_exactlyZero() {
        assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(0.0));
    }

    @Test
    @DisplayName("withdraw BVA: amount = 0.01 → true (ngay trên 0)")
    void testWithdraw_BVA_justAboveZero() {
        assertTrue(account.withdraw(0.01));
        assertEquals(499.99, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("withdraw BVA: amount = 499.99 → true (ngay dưới balance)")
    void testWithdraw_BVA_justBelowBalance() {
        assertTrue(account.withdraw(499.99));
        assertEquals(0.01, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("withdraw BVA: amount = 500.0 → true (đúng bằng balance)")
    void testWithdraw_BVA_exactlyBalance() {
        assertTrue(account.withdraw(500.0));
        assertEquals(0.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("withdraw BVA: amount = 500.01 → false (ngay trên balance)")
    void testWithdraw_BVA_justAboveBalance() {
        assertFalse(account.withdraw(500.01));
        assertEquals(500, account.getBalance(), 0.001);
    }

    // ========================================================================
    // Consistency test: chuỗi thao tác tuần tự
    // ========================================================================

    @Test
    @DisplayName("Consistency: 0 → +500 → -200(ok) → -400(fail) → balance=300")
    void testConsistency_sequentialOperations() {
        // Bắt đầu với số dư = 0
        BankAccount acc = new BankAccount("ACC-002", "Tran Thi B");
        assertEquals(0.0, acc.getBalance(), 0.001);

        // Nạp 500
        acc.deposit(500);
        assertEquals(500.0, acc.getBalance(), 0.001);

        // Rút 200 → thành công
        assertTrue(acc.withdraw(200));
        assertEquals(300.0, acc.getBalance(), 0.001);

        // Rút 400 → thất bại (chỉ còn 300)
        assertFalse(acc.withdraw(400));
        assertEquals(300.0, acc.getBalance(), 0.001);

        // Kiểm tra số dư cuối = 300
        assertEquals(300.0, acc.getBalance(), 0.001,
                "Số dư cuối phải đúng bằng 300 sau chuỗi thao tác");
    }
}
