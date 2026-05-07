import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 4. Nhập n và n số nguyên từ bàn phím.
        System.out.print("Nhap so luong phan tu n: ");
        int n = scanner.nextInt();

        int[] arr = new int[n];
        System.out.println("Nhap " + n + " so nguyen:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // 5. Chia mảng thành k đoạn (ví dụ k = 4).
        int k = 4;
        System.out.print("Nhap so doan k (mac dinh la 4): ");
        int inputK = scanner.nextInt();
        if (inputK > 0) {
            k = inputK;
        }

        // 7. Dùng ExecutorService (fixed thread pool) để submit() các Callable.
        ExecutorService executor = Executors.newFixedThreadPool(k);
        List<Future<Integer>> futures = new ArrayList<>();

        int segmentSize = (int) Math.ceil((double) n / k);

        for (int i = 0; i < k; i++) {
            final int start = i * segmentSize;
            final int end = Math.min(start + segmentSize, n);

            if (start >= n)
                break; // Bỏ qua nếu start vượt quá n

            // 6. Mỗi đoạn tạo một Callable<Integer> trả về tổng của đoạn.
            Callable<Integer> callable = () -> {
                int sum = 0;
                for (int j = start; j < end; j++) {
                    sum += arr[j];
                }
                return sum;
            };

            futures.add(executor.submit(callable));
        }

        int totalSum = 0;
        // 8. Dùng Future.get() để lấy kết quả và cộng lại thành tổng cuối.
        for (Future<Integer> future : futures) {
            try {
                totalSum += future.get();
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Loi khi thuc thi thread: " + e.getMessage());
            }
        }

        // 9. In tổng cuối và đóng ExecutorService đúng cách.
        System.out.println("Tong cuoi cung cua mang la: " + totalSum);

        executor.shutdown();
        scanner.close();
    }
}
