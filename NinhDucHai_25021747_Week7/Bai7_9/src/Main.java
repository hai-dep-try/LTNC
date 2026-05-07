import java.util.concurrent.locks.ReentrantLock;

class Counter {
    private int value = 0;
    // 2. Sử dụng ReentrantLock
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        // 6. Thử dùng tryLock() để tránh chờ vô hạn
        // tryLock() sẽ cố gắng lấy chìa khóa.
        // - Nếu lấy được -> trả về true -> tăng giá trị.
        // - Nếu bị luồng khác cầm mất -> trả về false ngay lập tức chứ không thèm đứng
        // chờ -> vô else.
        if (lock.tryLock()) {
            try {
                value++;
            } finally {
                // Luôn luôn unlock trong finally để chống kẹt khóa vĩnh viễn (Deadlock)
                // trong trường hợp có lỗi Exception xảy ra giữa chừng
                lock.unlock();
            }
        } else {
            // khong lay duoc lock, bo qua luot nay
            System.out.println(Thread.currentThread().getName() + " khong lay duoc lock, bo qua.");
        }
    }

    public int getValue() {
        return value;
    }
}

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();

        // Mảng chứa 4 luồng
        Thread[] threads = new Thread[4];

        // 3. Tạo 4 luồng, mỗi luồng tăng counter 10000 lần
        for (int i = 0; i < 4; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    counter.increment();
                }
            }, "Thread-" + (i + 1));

            threads[i].start();
        }

        // 4. Dùng join() để đợi tất cả hoàn thành
        for (int i = 0; i < 4; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.err.println("Loi khi cho luong: " + e.getMessage());
            }
        }

        // 5. In giá trị cuối cùng của counter
        System.out.println("\nKET QUA TONG KET");
        System.out.println("Gia tri cuoi cung cua counter: " + counter.getValue());
        System.out.println("Ghi chu: Neu dung tryLock() thi ket qua chac chan se < 40000 do nhieu lan bi bo qua.");
    }
}
