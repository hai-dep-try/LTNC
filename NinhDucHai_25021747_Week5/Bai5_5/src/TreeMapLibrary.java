import java.util.Map;
import java.util.TreeMap;

// 2.3. Dùng TreeMap
class TreeMapLibrary implements LibraryManager {
    // Tương tự HashMap nhưng dữ liệu sẽ tự động sort theo Key (ID)
    private Map<String, Book> books = new TreeMap<>();

    @Override
    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    @Override
    public Book searchById(String id) {
        return books.get(id); // O(log n)
    }

    @Override
    public void deleteById(String id) {
        books.remove(id);
    }

    @Override
    public void printAll() {
        System.out.println("--- Danh sách sách (TreeMap - Đã Sort theo ID) ---");
        for (Book book : books.values()) System.out.println(book);
    }
}
