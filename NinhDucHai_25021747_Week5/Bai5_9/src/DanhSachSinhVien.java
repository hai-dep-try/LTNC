import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class DanhSachSinhVien {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = "students.dat";

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(fileName));
            System.out.println("--- NHẬP DANH SÁCH SINH VIÊN ---");
            System.out.println("(Nhập ID là 'END' để dừng nhập)");

            while (true) {
                System.out.print("Nhập ID: ");
                String id = scanner.nextLine();

                // 2. Dừng nhập nếu gặp "END"
                if (id.equals("END")) {
                    break;
                }

                System.out.print("Nhập Tên: ");
                String name = scanner.nextLine();

                System.out.print("Nhập GPA: ");
                // Mẹo: Đọc cả dòng rồi ép kiểu sang double để tránh lỗi "trôi lệnh"
                double gpa = Double.parseDouble(scanner.nextLine());

                // 3. Ghi đối tượng ra tệp
                Student student = new Student(id, name, gpa);
                oos.writeObject(student);
            }
            System.out.println("-> Đã ghi dữ liệu thành công ra tệp " + fileName + "\n");

        } catch (IOException e) {
            System.out.println("Lỗi I/O khi ghi tệp.");
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(fileName));
            System.out.println("--- DANH SÁCH SINH VIÊN ĐỌC TỪ TỆP ---");

            // Lặp vô hạn để đọc, dùng EOFException để thoát
            while (true) {
                // 4. Đọc lại từ tệp và ép kiểu về Student
                Student s = (Student) ois.readObject();
                System.out.println(s.toString());
            }

            // 5. Bắt các ngoại lệ theo đúng yêu cầu đề bài
        } catch (EOFException e) {
            System.out.println("(Đã đọc hết danh sách)");
        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy class Student định nghĩa cho đối tượng.");
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy tệp. Vui lòng kiểm tra lại.");
        } catch (IOException e) {
            System.out.println("Lỗi I/O khi đọc tệp.");
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            scanner.close();
        }
    }
}