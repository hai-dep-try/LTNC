package after;

// Combination of standard extracted models to run the output function 
public class Transcript {
    private Student student;
    private Course course;
    private Grade grade;

    public Transcript(Student student, Course course, Grade grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    public void printTranscript() {
        System.out.println("Student: " + student.getName() + " (" + student.getId() + ")");
        System.out.println("Course: " + course.getCourseName() + " (" + course.getCourseId() + ") - " + course.getCredits() + " credits");
        System.out.println("Midterm: " + grade.getMidtermScore() + " | Final: " + grade.getFinalScore() + " | Assignment: " + grade.getAssignmentScore());
        System.out.printf("Overall Grade: %.1f - Status: %s%n", grade.calculateFinalGrade(), grade.getAcademicStatus());
    }
}
