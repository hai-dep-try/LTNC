
class Student {
    // 1. Encapsulation: Tất cả thuộc tính phải là private
    private String id;
    private String name;
    private String email;
    private double gpa;

    // 2. Constructors
    // - Không tham số
    public Student() {
        this.id = "";
        this.name = "";
        this.email = "";
        this.gpa = 0.0;
    }

    // - Có tham số (id, name)
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.email = "";
        this.gpa = 0.0;
    }

    // - Có đầy đủ tham số
    public Student(String id, String name, String email, double gpa) {
        this.id = id;
        this.name = name;
        this.email = email;
        setGpa(gpa); // Dùng setter để validation
    }

    // - Copy constructor
    public Student(Student other) {
        this.id = other.id;
        this.name = other.name;
        this.email = other.email;
        this.gpa = other.gpa;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getGpa() {
        return gpa;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // 3. Validation
    public void setGpa(double gpa) {
        // Đã nới lỏng lên 10.0 để khớp với test case (7.5, 8.0...) của bài mới
        if (gpa >= 0.0 && gpa <= 10.0) {
            this.gpa = gpa;
        } else {
            System.out.println("Lỗi: GPA không hợp lệ.");
        }
    }

    // Format output đúng theo yêu cầu của bài tập mới: [id] [name] [gpa]
    @Override
    public String toString() {
        return id + " " + name + " " + gpa;
    }
}
