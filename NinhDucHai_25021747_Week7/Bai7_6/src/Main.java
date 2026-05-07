import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 1. Nhập n mảng
        if (!scanner.hasNextInt()) {
            System.out.println("Input khong hop le");
            scanner.close(); // Đóng cẩn thận trước khi thoát để tránh Resource Leak
            return;
        }
        int n = scanner.nextInt();

        // 2. Sử dụng ExecutorService quản lý thực thi
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            // Nhập độ dài mảng và các phần tử
            int m = scanner.nextInt();
            int[] arr = new int[m];
            for (int j = 0; j < m; j++) {
                arr[j] = scanner.nextInt();
            }

            // Gọi từng mảng vào Callable riêng biệt
            Callable<Integer> task = () -> {
                // Thuật toán O(n) tìm số lớn thứ 2
                Integer max1 = null;
                Integer max2 = null;

                for (int num : arr) {
                    if (max1 == null || num > max1) {
                        max2 = max1; // Max 1 rớt xuống thành Max 2
                        max1 = num;  // Cập nhật Max 1 mới
                    } else if (num < max1 && (max2 == null || num > max2)) {
                        max2 = num;  // Cập nhật Max 2
                    }
                }
                
                // Trả về max2. Nếu không tìm được (VD: mảng 1 phần tử hoặc tất cả giống nhau) thì trả về null -> 4. Xử lý k crash
                return max2;
            };

            futures.add(executor.submit(task));
        }

        int sum = 0;

        // 3. Dùng Future.get() để lấy kết quả và cộng lại
        for (int i = 0; i < n; i++) {
            try {
                // Việc lấy get() ở đây đảm bảo in ra đúng thứ tự mảng từ 0 đến n-1 do list futures lưu tuần tự
                Integer secondLargest = futures.get(i).get();
                
                // 5. In kết quả từng mảng
                if (secondLargest != null) {
                    System.out.println("Array " + i + ": second largest = " + secondLargest);
                    sum += secondLargest;
                } else {
                    System.out.println("Array " + i + ": Not found");
                }
            } catch (Exception e) {
                System.out.println("Array " + i + ": Loi xu ly ngam (" + e.getMessage() + ")");
            }
        }

        System.out.println("Sum = " + sum);

        executor.shutdown();
        scanner.close();
    }
}
