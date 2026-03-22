public final class Transaction { //final class khong cho ke thua de override
    //final khong cho gan lai
    private final String transactionId;
    private final double amount;
    private final String timestamp;

    //construct
    public Transaction(String transactionId, double amount, String timestamp) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getTransactionId() { return transactionId; }
    public double getAmount()        { return amount; }
    public String getTimestamp()     { return timestamp; }

    @Override
    public String toString() {
        return "[" + transactionId + " | " + amount + " | " + timestamp + "]";
    }
}