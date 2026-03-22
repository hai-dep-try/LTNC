public class Account {
    private final String accountId;
    private double balance;
    private Transaction[] history;
    private int count; // current number of transactions

    public Account(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
        this.history = new Transaction[100]; // assuming a maximum of 100 transactions
        this.count = 0;
    }

    public void addTransaction(Transaction t) {
        if (t == null) return;
        
        // Ensure we don't exceed the array capacity
        if (count < history.length) {
            history[count++] = t;
            balance += t.getAmount();
        }
    }

    // KEY: Returns a DEEP COPY of the array, not the original reference
    public Transaction[] getHistory() {
        Transaction[] copy = new Transaction[count];
        for (int i = 0; i < count; i++) {
            // Since Transaction is immutable, copying the reference is sufficient
            copy[i] = history[i];
        }
        return copy;
    }

    public String getAccountId() { 
        return accountId; 
    }
    
    public double getBalance() { 
        return balance; 
    }
}