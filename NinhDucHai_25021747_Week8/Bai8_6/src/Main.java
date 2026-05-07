import before.StudentManager;
import after.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("====== EXERCISE 6: THE GOD CLASS ======\n");

        runBeforeRefactoring();
        System.out.println("\n------------------------------------------------\n");
        runAfterRefactoring();
    }

    private static void runBeforeRefactoring() {
        System.out.println("=== BEFORE REFACTORING ===");
        StudentManager manager = new StudentManager(
            "STU123", "Nguyen Van A", 3.2,
            "CS101", "Introduction to Programming", 3,
            8.0, 9.0, 7.5
        );
        manager.printTranscript();
    }

    private static void runAfterRefactoring() {
        System.out.println("=== AFTER REFACTORING (Extract Class) ===");
        Student student = new Student("STU123", "Nguyen Van A", 3.2);
        Course course = new Course("CS101", "Introduction to Programming", 3);
        Grade grade = new Grade(8.0, 9.0, 7.5);
        
        Transcript transcript = new Transcript(student, course, grade);
        transcript.printTranscript();
        
        System.out.println("\n=== ADDING NEW TEACHING ASSISTANT ===");
        TeachingAssistant ta = new TeachingAssistant("TA999", "Tran Van B");
        System.out.println("Teaching Assistant created: " + ta.getName() + " (" + ta.getId() + ")");
    }
}
