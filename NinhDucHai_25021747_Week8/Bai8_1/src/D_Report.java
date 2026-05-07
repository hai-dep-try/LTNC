// Đoạn D sau khi refactor
// Code smell: Data Clumps (Các trường authorEmail, authorName, authorPhone, authorAddress luôn xuất hiện cùng nhau).
// Giải pháp: Extract Class, gộp nhóm các biến liên quan vào một class Author.

class Author {
    private String name;
    private String email;
    private String phone;
    private String address;

    public Author(String name, String email, String phone, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
}

class Report {
    private String title;
    private String content;
    private Author author;

    public Report(String title, String content, Author author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
    
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public Author getAuthor() { return author; }
}
