import java.util.ArrayList;
import java.util.List;

// 2.1. Dùng ArrayList
class ArrayListLibrary implements LibraryManager {
    private List<Book> books = new ArrayList<>();

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public Book searchById(String id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public void deleteById(String id) {
        // Dùng removeIf để xóa nhanh gọn và an toàn
        books.removeIf(book -> book.getId().equals(id));
    }

    @Override
    public void printAll() {
        System.out.println("--- Danh sách sách (ArrayList) ---");
        for (Book book : books) System.out.println(book);
    }
}
