import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class GhiDuLieu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataOutputStream dos = null;

        try {
            System.out.print("Nhập tên tệp cần ghi (vd: numbers.dat): ");
            String fileName = scanner.nextLine();

            System.out.print("Nhập số lượng số nguyên n = ");
            int n = scanner.nextInt();

            dos = new DataOutputStream(new FileOutputStream(fileName));

            for (int i = 0; i < n; i++) {
                System.out.print("Nhập số thứ " + (i + 1) + ": ");
                int num = scanner.nextInt();
                dos.writeInt(num); // Ghi số nguyên vào tệp nhị phân
            }

            System.out.println("Đã ghi xong " + n + " số vào tệp " + fileName);

        } catch (IOException e) {
            System.out.println("Lỗi I/O khi thao tác với tệp.");
            e.printStackTrace();
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            scanner.close();
        }
    }
}