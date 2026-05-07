import java.util.HashMap;
import java.util.Map;

// 2.2. Dùng HashMap
class HashMapLibrary implements LibraryManager {
    // Key là ID sách, Value là đối tượng Book
    private Map<String, Book> books = new HashMap<>();

    @Override
    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    @Override
    public Book searchById(String id) {
        return books.get(id); // O(1)
    }

    @Override
    public void deleteById(String id) {
        books.remove(id);
    }

    @Override
    public void printAll() {
        System.out.println("--- Danh sách sách (HashMap) ---");
        for (Book book : books.values()) System.out.println(book);
    }
}
