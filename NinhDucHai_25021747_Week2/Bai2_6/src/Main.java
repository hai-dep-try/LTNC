public class Main {
    public static void main(String[] args) {
        Account account = new Account("ACC001", 1000);
        account.addTransaction(new Transaction("T1", 500, "2024-01-01"));
        account.addTransaction(new Transaction("T2", 200, "2024-01-02"));

        System.out.println("=== BEFORE HACKER ATTACK ===");
        printHistory(account);

        //  HACKER attempts to corrupt data
        // They get a copy of the array, not the original reference
        Transaction[] stolen = account.getHistory();

        // Attack 1: Assign null to the first element
        stolen[0] = null;

        // Attack 2: Attempt to inject a fraudulent transaction
        stolen[1] = new Transaction("FAKE", 9999999, "HACKED");

        System.out.println("\n=== AFTER HACKER ATTACK ===");
        // Original data remains intact due to Defensive Copying 
        printHistory(account);
    }

    static void printHistory(Account acc) {
        Transaction[] h = acc.getHistory();
        System.out.println("Account: " + acc.getAccountId() + " | Balance: " + acc.getBalance());
        for (int i = 0; i < h.length; i++) {
            System.out.println("  [" + i + "] " + h[i]);
        }
    }
}