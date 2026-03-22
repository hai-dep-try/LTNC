public class Student {
    private String id;
    private String name;
    private String email;
    private double gpa;

    public Student(){
        this.id = "";
        this.name = "";
        this.email = "";
        this.gpa = 0.0;
    }
    public Student(String id, String name){
        this.id = id;
        this.name = name;
        this.email = "";
        this.gpa = 0.0;
    }

    public Student(Student other) {
        this.id = other.id;
        this.name = other.name;
        this.email = other.email;
        this.gpa = other.gpa;
    }
    // \\d{8} là có phải số có 8 chữ số mỗi chữ số từ 0 đến 9 không
    public void setId(String id) {
        if (id == null || !id.matches("\\d{8}")) {
            System.out.println("Loi: Id phai gom 8 chu so ");
        } else {
            this.id = id;
        }
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) { //trim().isEmpty là bỏ toàn bộ dấu cách ở đầu và cuối rồi kiểm tra có empty
            System.out.println("Loi: Ten khong duoc de trong");
        } else if (!name.matches("[a-zA-ZÀ-ỹ\\s]+")) {
            System.out.println("Loi: Ten khong duoc chua so hoac ky tu dac biet ");
        } else {
            this.name = name;
        }
    }

    public void setGpa(double gpa) {
        if (gpa < 0.0 || gpa > 4.0) {
            System.out.println("Loi: GPA phai tu 0 den 4. Giu nguyen " + this.gpa);
        } else {
            this.gpa = gpa;
        }
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[\\w.]+@[\\w.]+\\.[a-zA-Z]{2,}$")) {
            System.out.println("Loi: Email khong hop le");
        } else {
            this.email = email;
        }
    }

    public void printInfo() {
        System.out.println("Id: "+ id + " Ten: " + name + " Email: " + email + " Gpa: " + gpa);
    }

    public static void main(String[] args) {
        Student sv1 = new Student();
        sv1.setId("25021001");
        sv1.setEmail("conbo@gmail.com");
        sv1.setName("Nguyen Văn A");
        sv1.setGpa(3.5);

        Student sv2 = new Student("25021002", "Nguyen Van B");
        sv2.setEmail("conbo2@gmail.com");
        sv2.setGpa(3.7);
        sv1.setGpa(-1);

        Student sv3 = new Student(sv1);
        sv3.setId("25021003");
        sv1.printInfo();

    }
}
