// Lớp BankAccount mô phỏng tài khoản ngân hàng
class BankAccount {
    private int balance = 0;

    // 2. Cài các phương thức deposit và withdraw dạng synchronized
    public synchronized void deposit(int amount) {
        balance += amount;
    }

    public synchronized void withdraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }
}

public class Main {
    public static void main(String[] args) {
        // 1. Tạo BankAccount
        BankAccount account = new BankAccount();

        // 3. Tạo luồng A: lặp 1000 lần deposit(100)
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account.deposit(100);
            }
        });

        // 3. Tạo luồng B: lặp 1000 lần withdraw(100)
        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account.withdraw(100);
            }
        });

        threadA.start();
        threadB.start();

        try {
            // 4. Dùng join() để luồng main đợi 2 luồng A, B hoàn thành
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            System.err.println("Lỗi khi đợi luồng: " + e.getMessage());
        }

        // 4, 5. In final balance và kiểm tra kỳ vọng
        System.out.println("Final balance: " + account.getBalance());
        System.out.println("Ky vong: 0 (Vì deposit 1000*100 và withdraw 1000*100 bù trừ cho nhau, không bị race condition).");
    }
}
