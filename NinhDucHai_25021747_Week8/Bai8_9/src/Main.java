/**
 * Main class - Manual demo for GradeClassifier (Bai 8_9)
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Bai 8_9: The Buggy Trap ===\n");

        double[] testGpas = {0.0, 2.5, 4.99, 5.0, 5.5, 6.49, 6.5, 7.0, 7.99, 8.0, 9.0, 10.0};

        for (double gpa : testGpas) {
            String result = GradeClassifier.classifyGrade(gpa);
            System.out.printf("GPA = %-5.2f → %s%n", gpa, result);
        }

        // Exception cases
        double[] invalidGpas = {-0.1, 10.1};
        for (double gpa : invalidGpas) {
            try {
                GradeClassifier.classifyGrade(gpa);
            } catch (IllegalArgumentException e) {
                System.out.printf("GPA = %-5.1f → Exception: %s%n", gpa, e.getMessage());
            }
        }
    }
}
