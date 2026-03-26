import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 4. Thực hành
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Đọc số lượng nhân viên
        int n = scanner.nextInt();
        scanner.nextLine(); // Xử lý trôi lệnh enter

        List<Employee> employees = new ArrayList<>();

        // Nhập thông tin nhân viên
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");

            String type = parts[0];
            String id = parts[1];
            String name = parts[2];
            double baseSalary = Double.parseDouble(parts[3]);

            if (type.equals("O")) {
                employees.add(new OfficeWorker(id, name, baseSalary));
            } else if (type.equals("T")) {
                int overtimeHours = Integer.parseInt(parts[4]);
                employees.add(new Technician(id, name, baseSalary, overtimeHours));
            }
        }

        double totalPay = 0;

        // In danh sách và tính tổng lương
        for (Employee emp : employees) {
            double pay = emp.calculatePay();
            totalPay += pay;

            System.out.println(emp.name + " - Pay: " + pay);
            emp.work();
            System.out.println(); // In dòng trống để giống format đề bài
        }

        System.out.println("Total Pay = " + totalPay);
        scanner.close();
    }
}