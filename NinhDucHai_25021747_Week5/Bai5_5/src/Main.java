// 2. Cài đặt hệ thống quản lý

// 3. Yêu cầu chương trình Main
public class Main {
    public static void testLibrary(LibraryManager lib, String name) {

        // a. Thêm ít nhất 5 cuốn sách (để ID lộn xộn để test TreeMap)
        lib.addBook(new Book("B03", "Atomic Habits", "James Clear", 2018));
        lib.addBook(new Book("B01", "Man's Search for Meaning", "Viktor Frankl", 1946));
        lib.addBook(new Book("B05", "Clean Code", "Robert C. Martin", 2008));
        lib.addBook(new Book("B02", "Deep Work", "Cal Newport", 2016));
        lib.addBook(new Book("B04", "The Pragmatic Programmer", "David Thomas", 1999));

        // b. In danh sách kết quả (Xem TreeMap có sort không)
        lib.printAll();

        // c. Thực hiện tìm kiếm
        System.out.println("\n-> Tìm kiếm sách có ID 'B05':");
        Book found = lib.searchById("B05");
        System.out.println(found != null ? found : "Không tìm thấy!");

        // d. Thực hiện xóa
        System.out.println("\n-> Xóa sách có ID 'B02' và in lại:");
        lib.deleteById("B02");
        lib.printAll();
    }

    public static void main(String[] args) {
        // e. Thực hiện với cả 3 cách lưu trữ
        LibraryManager arrayListLib = new ArrayListLibrary();
        testLibrary(arrayListLib, "ARRAY LIST");

        LibraryManager hashMapLib = new HashMapLibrary();
        testLibrary(hashMapLib, "HASH MAP");

        LibraryManager treeMapLib = new TreeMapLibrary();
        testLibrary(treeMapLib, "TREE MAP");
    }
}