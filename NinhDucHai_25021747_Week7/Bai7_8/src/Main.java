import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    // Hàm kiểm tra số nguyên tố
    static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        // Đọc tất cả dữ liệu đầu vào trước
        int[][] arrays = new int[n][];
        for (int i = 0; i < n; i++) {
            int m = scanner.nextInt();
            arrays[i] = new int[m];
            for (int j = 0; j < m; j++) {
                arrays[i][j] = scanner.nextInt();
            }
        }

        // Hai thread pool riêng biệt cho hai giai đoạn
        int poolSize = Runtime.getRuntime().availableProcessors();
        ExecutorService pool1 = Executors.newFixedThreadPool(poolSize);
        ExecutorService pool2 = Executors.newFixedThreadPool(poolSize);

        List<Future<List<Integer>>> futures1 = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            final int[] arr = arrays[i];

            // Giai doan 1: loc so nguyen to
            // Khong goi pool2.submit() trong task cua pool1
            Callable<List<Integer>> stage1Task = () -> {
                List<Integer> primes = new ArrayList<>();
                for (int num : arr) {
                    if (isPrime(num)) primes.add(num);
                }
                return primes;
            };
            futures1.add(pool1.submit(stage1Task));
        }

        // Vong lap polling: kiem tra isDone() de biet task nao xong truoc
        // task nao xong truoc thi submit Stage 2 truoc
        boolean[] isDoneStage1 = new boolean[n];
        int completedCount = 0;

        List<Future<Long>> futures2 = new ArrayList<>();

        while (completedCount < n) {
            for (int i = 0; i < n; i++) {
                // isDone() kiểm tra chớp nhoáng, không chặn luồng Main
                if (!isDoneStage1[i] && futures1.get(i).isDone()) {
                    try {
                        // isDone() tra ve true thi get() lay ngay, khong cho
                        List<Integer> primes = futures1.get(i).get();
                        System.out.println("Stage 1 - Array " + i + ": " + primes);

                        final int idx = i;
                        // Luong Main submit vao pool2
                        Callable<Long> stage2Task = () -> {
                            long sum = 0;
                            String type;
                            if (primes.size() % 2 == 0) {
                                for (int p : primes) sum += (long) p * p;
                                type = "sum of squares";
                            } else {
                                for (int p : primes) sum += (long) p * p * p;
                                type = "sum of cubes";
                            }
                            System.out.println("Stage 2 - Array " + idx + ": " + type + " = " + sum);
                            return sum;
                        };
                        futures2.add(pool2.submit(stage2Task));

                        isDoneStage1[i] = true;
                        completedCount++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                // Cho luong Main ngu 10ms de tranh quay CPU lien tuc
                Thread.sleep(10);
            } catch (InterruptedException ignored) {}
        }

        // Thu thập tổng cuối cùng từ Stage 2
        long total = 0;
        for (Future<Long> f2 : futures2) {
            try {
                total += f2.get();
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Loi: " + e.getMessage());
            }
        }

        System.out.println("Total = " + total);

        pool1.shutdown();
        pool2.shutdown();
        scanner.close();
    }
}
