public class Main {
    public static void main(String[] args) {
        Account account = new Account("ACC001", 1000);
        account.addTransaction(new Transaction("T1", 500, "2024-01-01"));
        account.addTransaction(new Transaction("T2", 200, "2024-01-02"));

        System.out.println("=== TRƯỚC KHI HACKER TẤN CÔNG ===");
        printHistory(account);

        // 🏴‍☠️ HACKER cố tình phá hoại
        Transaction[] stolen = account.getHistory();

        // Tấn công 1: Gán null vào phần tử đầu
        stolen[0] = null;

        // Tấn công 2: Cố tình tạo transaction giả và thay thế
        stolen[1] = new Transaction("FAKE", 9999999, "HACKED");

        System.out.println("\n=== SAU KHI HACKER TẤN CÔNG ===");
        printHistory(account); // Dữ liệu gốc vẫn nguyên vẹn ✅
    }

    static void printHistory(Account acc) {
        Transaction[] h = acc.getHistory();
        System.out.println("Account: " + acc.getAccountId() + " | Balance: " + acc.getBalance());
        for (int i = 0; i < h.length; i++) {
            System.out.println("  [" + i + "] " + h[i]);
        }
    }
}
