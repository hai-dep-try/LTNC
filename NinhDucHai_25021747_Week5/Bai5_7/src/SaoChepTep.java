import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class SaoChepTep {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập đường dẫn tệp nguồn: ");
        String sourcePath = scanner.nextLine();

        System.out.print("Nhập đường dẫn tệp đích: ");
        String destPath = scanner.nextLine();

        BufferedReader reader = null;
        PrintWriter writer = null;
        int lineCount = 0;

        try {
            // Khởi tạo luồng đọc trước
            reader = new BufferedReader(new FileReader(sourcePath));

            // Khởi tạo luồng ghi
            writer = new PrintWriter(new FileWriter(destPath));

            String line;
            while ((line = reader.readLine()) != null) {
                writer.println(line);
                lineCount++;
            }

            System.out.println("Đã sao chép thành công: " + lineCount + " dòng.");

        } catch (FileNotFoundException e) {
            // Nếu reader vẫn null tức là lỗi xảy ra ở lúc tạo FileReader
            if (reader == null) {
                System.out.println("Source file not found.");
            } else {
                System.out.println("Cannot create destination file.");
            }
        } catch (IOException e) {
            System.out.println("I/O error.");
            e.printStackTrace();
        } finally {
            // Đảm bảo đóng tệp, cần thêm try-catch riêng vì phương thức close() cũng có thể ném ra IOException
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            scanner.close();
        }
    }
}