import java.util.Scanner;

// 5. Hàm Main chạy chương trình
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Khởi tạo các khu vực lưu trữ riêng biệt
        LibrarySection<Book> bookSection = new LibrarySection<>();
        LibrarySection<DVD> dvdSection = new LibrarySection<>();

        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();
        scanner.nextLine(); // Đọc bỏ dòng trống

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split(" ");
            String type = input[0];
            String id = input[1];
            String name = input[2];

            if (type.equals("B")) {
                String author = input[3];
                int pages = Integer.parseInt(input[4]);
                bookSection.addItem(new Book(id, name, author, pages));
            } else if (type.equals("D")) {
                String director = input[3];
                int duration = Integer.parseInt(input[4]);
                dvdSection.addItem(new DVD(id, name, director, duration));
            }
        }

        // In kết quả
        System.out.println("Khu vực Sách:");
        bookSection.displayItems();

        System.out.println("\nKhu vực DVD:");
        dvdSection.displayItems();

        scanner.close();
    }
}