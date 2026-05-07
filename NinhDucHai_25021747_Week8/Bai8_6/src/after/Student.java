package after;

// Extracted Student logic (Extends Person for the TA requirement)
public class Student extends Person {
    private double gpa;

    public Student(String studentId, String name, double gpa) {
        super(studentId, name);
        this.gpa = gpa;
    }

    public double getGpa() { return gpa; }
}
