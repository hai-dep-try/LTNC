public class Book {

    private String title;
    private String author;
    private double price;

    // Constructor
    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        // 1. Check for Reference Equality
        if (this == obj) return true;
        
        // 2. Check for null and ensure the objects are of the same class
        if (obj == null || getClass() != obj.getClass()) return false;

        // 3. Cast the object to Book
        Book other = (Book) obj;
        
        // 4. Compare each field: use equals() for Strings and == for primitive doubles
        return Double.compare(this.price, other.price) == 0 &&
                this.title.equals(other.title) &&
                this.author.equals(other.author);
    }

    @Override
    public String toString() {
        return "Book{title='" + title + "', author='" + author + "', price=" + price + "}";
    }

    // 4. Main method
    public static void main(String[] args) {
        Book book1 = new Book("Java Programming", "John Doe", 150000);
        Book book2 = new Book("Java Programming", "John Doe", 150000);

        System.out.println("book1: " + book1);
        System.out.println("book2: " + book2);
        System.out.println();

        // Comparison using ==
        // Returns false because they are two different objects in RAM
        System.out.println("Comparison using == : " + (book1 == book2));

        // Comparison using equals()
        // Returns true because they have the same title, author, and price
        System.out.println("Comparison using equals(): " + book1.equals(book2));
    }
}