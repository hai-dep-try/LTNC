package before;

public class StudentManager {
    private String studentId;
    private String name;
    private double gpa;

    private String courseId;
    private String courseName;
    private int credits;

    private double midtermScore;
    private double finalScore;
    private double assignmentScore;

    public StudentManager(String studentId, String name, double gpa, String courseId, String courseName, int credits, double midtermScore, double finalScore, double assignmentScore) {
        this.studentId = studentId; this.name = name; this.gpa = gpa;
        this.courseId = courseId; this.courseName = courseName; this.credits = credits;
        this.midtermScore = midtermScore; this.finalScore = finalScore; this.assignmentScore = assignmentScore;
    }

    public double calculateFinalGrade() {
        return assignmentScore * 0.2 + midtermScore * 0.3 + finalScore * 0.5;
    }

    public String getAcademicStatus() {
        double grade = calculateFinalGrade();
        if (grade >= 8.5) return "Excellent";
        if (grade >= 7.0) return "Good";
        if (grade >= 5.5 ) return "Average";
        return "Poor";
    }

    public void printTranscript() {
        System.out.println("Student: " + name + " (" + studentId + ")");
        System.out.println("Course: " + courseName + " (" + courseId + ") - " + credits + " credits");
        System.out.println("Midterm: " + midtermScore + " | Final: " + finalScore + " | Assignment: " + assignmentScore);
        System.out.printf("Overall Grade: %.1f - Status: %s%n", calculateFinalGrade(), getAcademicStatus());
    }
}
