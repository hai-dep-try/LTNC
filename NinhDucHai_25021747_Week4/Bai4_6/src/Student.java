// Lớp Student bắt buộc phải implements Comparable để có thể so sánh
class Student implements Comparable<Student> {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    // Định nghĩa logic so sánh: So sánh theo tên (Alpha-bê)
    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
