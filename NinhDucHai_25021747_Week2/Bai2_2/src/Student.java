public class Student {
    private String id;
    private String name;
    private String email;
    private double gpa;

    // Default Constructor
    public Student() {
        this.id = "";
        this.name = "";
        this.email = "";
        this.gpa = 0.0;
    }

    // Constructor with ID and Name
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.email = "";
        this.gpa = 0.0;
    }

    // Copy Constructor - Creates a new object based on an existing one
    public Student(Student other) {
        this.id = other.id;
        this.name = other.name;
        this.email = other.email;
        this.gpa = other.gpa;
    }

    // \d{8} checks if the string contains exactly 8 digits (0-9)
    public void setId(String id) {
        if (id == null || !id.matches("\\d{8}")) {
            System.out.println("Error: ID must consist of exactly 8 digits.");
        } else {
            this.id = id;
        }
    }

    public void setName(String name) {
        // trim().isEmpty() removes leading/trailing spaces and checks if the string is empty
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: Name cannot be empty.");
        } else {
            this.name = name;
        }
    }

    public void setGpa(double gpa) {
        if (gpa < 0.0 || gpa > 4.0) {
            System.out.println("Error: GPA must be between 0.0 and 4.0. Keeping current value: " + this.gpa);
        } else {
            this.gpa = gpa;
        }
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[\\w.]+@[\\w.]+\\.[a-zA-Z]{2,}$")) {
            System.out.println("Error: Invalid email format.");
        } else {
            this.email = email;
        }
    }

    public void printInfo() {
        System.out.println("ID: " + id + " | Name: " + name + " | Email: " + email + " | GPA: " + gpa);
    }

    public static void main(String[] args) {
        // Using Default Constructor
        Student s1 = new Student();
        s1.setId("25021001");
        s1.setEmail("example@gmail.com");
        s1.setName("John Doe");
        s1.setGpa(3.5);

        // Using Parametric Constructor
        Student s2 = new Student("25021002", "Jane Smith");
        s2.setEmail("jane@gmail.com");
        s2.setGpa(3.7);
        
        // Testing validation
        s1.setGpa(-1); // Should trigger an error
    }
}