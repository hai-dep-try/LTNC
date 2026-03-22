public class Account {
    private final String accountId;
    private double balance;
    private Transaction[] history;
    private int count; // số giao dịch hiện tại

    public Account(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
        this.history = new Transaction[100]; // giả sử tối đa 100 giao dịch
        this.count = 0;
    }

    public void addTransaction(Transaction t) {
        if (t == null) return;
        history[count++] = t;
        balance += t.getAmount();
    }

    // KEY: Trả về DEEP COPY, không trả về mảng gốc
    public Transaction[] getHistory() {
        Transaction[] copy = new Transaction[count];
        for (int i = 0; i < count; i++) {
            // Transaction đã immutable nên chỉ cần copy tham chiếu là đủ
            copy[i] = history[i];
        }
        return copy;
    }

    public String getAccountId() { return accountId; }
    public double getBalance()   { return balance; }
}