package after;

// Extracted Grade calculation logic
public class Grade {
    private double midtermScore;
    private double finalScore;
    private double assignmentScore;

    public Grade(double midtermScore, double finalScore, double assignmentScore) {
        this.midtermScore = midtermScore;
        this.finalScore = finalScore;
        this.assignmentScore = assignmentScore;
    }

    public double getMidtermScore() { return midtermScore; }
    public double getFinalScore() { return finalScore; }
    public double getAssignmentScore() { return assignmentScore; }

    public double calculateFinalGrade() {
        return assignmentScore * 0.2 + midtermScore * 0.3 + finalScore * 0.5;
    }

    public String getAcademicStatus() {
        double grade = calculateFinalGrade();
        if (grade >= 8.5) return "Excellent";
        if (grade >= 7.0) return "Good";
        if (grade >= 5.5) return "Average";
        return "Poor";
    }
}
