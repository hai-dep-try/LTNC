public class Main {
    public static void main(String[] args) {
        // Tạo một task để các luồng cùng thực thi
        Runnable task = () -> {
            AppConfig config = AppConfig.getInstance();
            System.out.println(Thread.currentThread().getName() + " - HashCode: " + config.hashCode());
        };

        // Tạo 2 luồng
        Thread thread1 = new Thread(task, "Thread 1");
        Thread thread2 = new Thread(task, "Thread 2");

        // Bắt đầu chạy 2 luồng cùng lúc
        thread1.start();
        thread2.start();
    }
}