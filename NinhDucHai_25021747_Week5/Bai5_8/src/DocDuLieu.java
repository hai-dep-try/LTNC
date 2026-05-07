import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DocDuLieu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataInputStream dis = null;

        try {
            System.out.print("Nhập tên tệp cần đọc: ");
            String fileName = scanner.nextLine();

            dis = new DataInputStream(new FileInputStream(fileName));
            System.out.println("Các số đọc được từ tệp:");

            // Lặp vô hạn cho đến khi bắt được EOFException
            while (true) {
                int num = dis.readInt();
                System.out.print(num + " ");
            }

        } catch (EOFException e) {
            // Bắt lỗi EOF (End Of File) để kết thúc việc đọc
            System.out.println("\n(Đã đọc hết dữ liệu trong tệp)");
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy tệp. Vui lòng kiểm tra lại tên.");
        } catch (IOException e) {
            System.out.println("Lỗi I/O khi đọc tệp.");
            e.printStackTrace();
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            scanner.close();
        }
    }
}