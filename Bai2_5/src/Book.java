public class Book {

    private String title;
    private String author;
    private double price;

    //construct
    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false; //2 kiểu object khác nhau sẽ return false;

        Book other = (Book) obj; // ép kiểu obj về Book
        return this.title.equals(other.title) &&
                this.author.equals(other.author) &&
                this.price == other.price; //so sánh từng thông tin, dùng equals cho string và == cho double (kieu nguyen thuy)
    }

    @Override
    public String toString() {
        return "Book{title='" + title + "', author='" + author + "', price=" + price + "}";//method in ra sach gi
    }

    // 4. Main method
    public static void main(String[] args) {
        Book book1 = new Book("Lap Trinh Java", "Nguyen Van A", 150000);
        Book book2 = new Book("Lap Trinh Java", "Nguyen Van A", 150000);

        System.out.println("book1: " + book1);
        System.out.println("book2: " + book2);
        System.out.println();

        // So sánh bằng ==
        System.out.println("So sánh bằng == : " + (book1 == book2));//false do la 2 object khac nhau trong RAM

        // So sánh bằng equals
        System.out.println("So sánh bằng equals(): " + book1.equals(book2));//true do cung author, title, price
    }
}


