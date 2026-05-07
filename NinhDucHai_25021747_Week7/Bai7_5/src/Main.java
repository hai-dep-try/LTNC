import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Nhập m đơn hàng
        System.out.print("Nhap so luong don hang m: ");
        int m = scanner.nextInt();

        // Chuan bi danh sach task, log va bo dem
        List<Callable<Boolean>> tasks = new ArrayList<>();
        final List<String> logs = new ArrayList<>();
        final AtomicInteger successCount = new AtomicInteger(0); // dem so don thanh cong

        System.out.println("NHAP THONG TIN DON HANG");
        for (int i = 0; i < m; i++) {
            System.out.print("Don hang " + (i + 1) + " - ID: ");
            String id = scanner.next();
            System.out.print("Don hang " + (i + 1) + " - processMs: ");
            long processMs = scanner.nextLong();

            // 3. Mỗi đơn hàng là một Callable<Boolean>
            tasks.add(() -> {
                System.out.println("Start " + id); // in start + id
                try {
                    Thread.sleep(processMs); // sleep processMs
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return false;
                }

                // Nếu > 1500ms thì coi là thất bại
                boolean isSuccess = processMs <= 1500;
                String status = isSuccess ? "DONE " : "FAIL ";

                // ghi log vao list chung, can synchronized de tranh xung dot
                synchronized (logs) {
                    logs.add(status + id);
                }

                // tang bo dem neu thanh cong
                if (isSuccess) {
                    successCount.incrementAndGet();
                }

                return isSuccess; // trả về true/false
            });
        }

        // 2. Dùng ExecutorService
        System.out.println("\nBAT DAU XU LY DON HANG");
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<Boolean>> futures = new ArrayList<>();

        for (Callable<Boolean> task : tasks) {
            futures.add(executor.submit(task));
        }

        // 6. Sau khi Future.get() xong hết
        for (Future<Boolean> future : futures) {
            try {
                // Ta chỉ chờ get() để chắn chắn từng task hoàn thành.
                // Kết quả true/false đã được đếm bằng successCount ngầm trong hàm Callable rồi.
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Loi khi chay task: " + e.getMessage());
            }
        }

        // 6. In kết quả tổng kết
        System.out.println("\nKET QUA TONG KET");
        System.out.println("Success = " + successCount.get());
        System.out.println("Danh sach log theo thu tu hoan thanh:");
        for (String log : logs) {
            System.out.println(log);
        }

        // 7. Đóng ExecutorService
        executor.shutdown();
        scanner.close();
    }
}
