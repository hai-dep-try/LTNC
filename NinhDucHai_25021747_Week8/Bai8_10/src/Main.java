/**
 * Main class - Manual demo for BankAccount (Bai 8_10)
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Bai 8_10: The Bank Account Tester ===\n");

        BankAccount acc = new BankAccount("ACC-001", "Nguyen Van A", 500);
        System.out.println("Account: " + acc.getAccountNumber());
        System.out.println("Initial balance: " + acc.getBalance());

        // Deposit
        acc.deposit(200);
        System.out.println("After deposit(200): " + acc.getBalance());

        // Withdraw success
        boolean ok = acc.withdraw(300);
        System.out.println("withdraw(300) = " + ok + ", balance = " + acc.getBalance());

        // Withdraw fail
        ok = acc.withdraw(500);
        System.out.println("withdraw(500) = " + ok + ", balance = " + acc.getBalance());

        // Exception cases
        try {
            acc.deposit(0);
        } catch (IllegalArgumentException e) {
            System.out.println("\ndeposit(0) → " + e.getMessage());
        }
        try {
            acc.withdraw(-10);
        } catch (IllegalArgumentException e) {
            System.out.println("withdraw(-10) → " + e.getMessage());
        }

        // Consistency test
        System.out.println("\n--- Consistency test ---");
        BankAccount acc2 = new BankAccount("ACC-002", "Tran Thi B");
        System.out.println("Start: " + acc2.getBalance());
        acc2.deposit(500);
        System.out.println("+500: " + acc2.getBalance());
        System.out.println("-200: " + acc2.withdraw(200) + ", balance=" + acc2.getBalance());
        System.out.println("-400: " + acc2.withdraw(400) + ", balance=" + acc2.getBalance());
        System.out.println("Final: " + acc2.getBalance() + " (expected 300)");
    }
}
