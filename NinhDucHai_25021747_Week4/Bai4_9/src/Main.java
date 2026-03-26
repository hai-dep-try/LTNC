import java.util.Scanner;

// Lớp chạy chương trình chính
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Khởi tạo các kho đơn năng
        Warehouse<Food> foodWarehouse = new Warehouse<>();
        Warehouse<Electronic> electronicWarehouse = new Warehouse<>();

        // Đọc số lượng mặt hàng
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            scanner.nextLine(); // Loại bỏ ký tự xuống dòng

            for (int i = 0; i < n; i++) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");

                if (parts.length >= 4) {
                    String type = parts[0];
                    String id = parts[1];
                    String name = parts[2];

                    if (type.equals("F")) {
                        String expiryDate = parts[3];
                        foodWarehouse.add(new Food(id, name, expiryDate));
                    } else if (type.equals("E")) {
                        int warrantyMonths = Integer.parseInt(parts[3]);
                        electronicWarehouse.add(new Electronic(id, name, warrantyMonths));
                    }
                }
            }

            // In kết quả đầu ra
            System.out.println("Kho Thực phẩm:");
            foodWarehouse.displayInventory();

            System.out.println("\nKho Điện tử:");
            electronicWarehouse.displayInventory();
        }
        scanner.close();
    }
}