import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    // Hàm kiểm tra số nguyên tố
    static boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int poolSize = Runtime.getRuntime().availableProcessors(); // Lấy số lõi CPU của máy
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int m = scanner.nextInt();
            int[] arr = new int[m];
            for (int j = 0; j < m; j++) {
                arr[j] = scanner.nextInt();
            }

            // Mỗi mảng được xử lý bởi một Callable riêng biệt, chạy song song
            Callable<Integer> task = () -> {
                int count = 0;
                for (int num : arr) {
                    if (isPrime(num)) {
                        count++;
                    }
                }
                return count;
            };

            futures.add(executor.submit(task));
        }

        // Tổng hợp kết quả sau khi tất cả các luồng hoàn thành
        int maxPrimes = -1;

        for (int i = 0; i < n; i++) {
            try {
                int primeCount = futures.get(i).get();
                System.out.println("Array " + i + ": " + primeCount);

                if (primeCount > maxPrimes) {
                    maxPrimes = primeCount;
                }
            } catch (Exception e) {
                System.out.println("Array " + i + ": Loi (" + e.getMessage() + ")");
            }
        }

        // In mảng có nhiều số nguyên tố nhất
        // Nếu có nhiều mảng cùng đứng đầu thì in tất cả
        List<Integer> winners = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            try {
                int primeCount = futures.get(i).get();
                if (primeCount == maxPrimes) {
                    winners.add(i);
                }
            } catch (Exception e) {
                // Đã xử lý ở trên
            }
        }

        if (winners.size() == 1) {
            System.out.println("Most primes: Array " + winners.get(0) + " with " + maxPrimes + " primes");
        } else {
            StringBuilder sb = new StringBuilder("Most primes: ");
            for (int i = 0; i < winners.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append("Array ").append(winners.get(i));
            }
            sb.append(" with ").append(maxPrimes).append(" primes");
            System.out.println(sb.toString());
        }

        executor.shutdown();
        scanner.close();
    }
}
