class Task implements Runnable {
    // 1. Tạo lớp Task có 2 thuộc tính
    private String name;
    private long durationMs;

    public Task(String name, long durationMs) {
        this.name = name;
        this.durationMs = durationMs;
    }

    // 2. Phương thức run()
    @Override
    public void run() {
        System.out.println("Start " + name);
        try {
            // Ngủ một khoảng thời gian
            Thread.sleep(durationMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("End " + name);
    }
}

public class Main {
    public static void main(String[] args) {
        // 3. Trong main
        // Tạo 2 Task khác nhau
        Task task1 = new Task("Lau Nha", 1500); // 1.5 giây
        Task task2 = new Task("Nau Com", 3000); // 3 giây

        // Bọc trong Thread
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        // Gọi start() cho cả hai để chạy song song
        thread1.start();
        thread2.start();

        // Dùng join() để đợi cả hai hoàn thành
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.err.println("Loi khi cho luong: " + e.getMessage());
        }

        // In ra thông báo cuối cùng
        System.out.println("All tasks done.");
    }
}
