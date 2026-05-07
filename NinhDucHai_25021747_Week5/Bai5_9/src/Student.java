import java.io.Serializable;

// 1. Khởi tạo lớp Student cài đặt Serializable
class Student implements Serializable {
    private String id;
    private String name;
    private double gpa;

    public Student(String id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | GPA: " + gpa;
    }
}
