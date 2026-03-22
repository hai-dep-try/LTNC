public class BankAccount {
    private final String accountNumber;
    private double balance;
    private String ownerName;

    public BankAccount(String accountNumber, String ownerName) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = 0;
    }

    public BankAccount(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        if (balance < 0) {
            System.out.println("Error");
            this.balance = 0;

        } else {
            this.balance = balance;
        }
    }

    public void deposit(double amount){
            if (amount < 0){
                System.out.println("Error");
                return;
            }
            balance += amount;
            System.out.println("Success deposit");


    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            System.out.println("Success withdraw");
            return true;
        }
        System.out.println("Error");
        return false;
    }

    public double getBalance(){
        return balance;
    }

    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount("001", "Ninh Duc Hai");

        acc1.deposit(-100);//Error
        acc1.deposit(1000000);//Success

        acc1.withdraw(9999999);//Error


        boolean ketQua = acc1.withdraw(500000);
        System.out.println("Ket qua: " + ketQua);

        System.out.println("So du cuoi " + acc1.ownerName + ": " + acc1.getBalance() + " VND");

    }
}
