// Interface định nghĩa các chức năng chuẩn
interface LibraryManager {
    void addBook(Book book);

    Book searchById(String id);

    void deleteById(String id);

    void printAll();
}
