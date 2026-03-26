import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// HÀM MAIN XỬ LÝ LOGIC LAMBDA
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Đọc số lượng sinh viên
        int n = Integer.parseInt(scanner.nextLine());
        List<Student> students = new ArrayList<>();

        // Nhập thông tin
        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split(" ");
            String id = input[0];
            String name = input[1];
            double gpa = Double.parseDouble(input[2]);

            // Dùng constructor đầy đủ tham số của Bài 2.2.
            // Do input bài mới không nhập email nên mình truyền tạm "N/A" (Not Available)
            students.add(new Student(id, name, "N/A", gpa));
        }

        // 1. Filter (Lọc): Xóa sinh viên có GPA < 5.0 bằng Lambda
        students.removeIf(student -> student.getGpa() < 5.0);

        System.out.println("After removing GPA < 5.0:");
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println(); // In dòng trống chia cách

        // 2. Sort (Sắp xếp): Sắp xếp theo tên bằng Lambda
        students.sort((s1, s2) -> s1.getName().compareTo(s2.getName()));

        System.out.println("After sorting by name:");
        for (Student student : students) {
            System.out.println(student);
        }

        // 3. Custom Functional Interface: Định nghĩa phép toán
        Operation<Double> add = (a, b) -> a + b;
        Operation<Double> subtract = (a, b) -> a - b;
        Operation<Double> multiply = (a, b) -> a * b;
        Operation<Double> divide = (a, b) -> a / b;

        // (Không yêu cầu in ra màn hình phần này theo Output mẫu,
        // nhưng đã định nghĩa đầy đủ trong code theo đúng đề bài)
    }
}