import java.util.InputMismatchException;
import java.util.Scanner;

public class PhepChiaAnToan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Nhập a: ");
            int a = scanner.nextInt();

            System.out.print("Nhập b: ");
            int b = scanner.nextInt();

            System.out.println("Kết quả: " + (a / b));

        } catch (InputMismatchException e) {
            System.out.println("Lỗi: Vui lòng nhập số nguyên.");
        } catch (ArithmeticException e) {
            System.out.println("Lỗi: Không thể chia cho 0.");
        } finally {
            System.out.println("Program finished.");
            scanner.close();
        }
    }
}