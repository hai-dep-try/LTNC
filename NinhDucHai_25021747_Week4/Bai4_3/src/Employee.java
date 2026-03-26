// 2. Thiết kế Abstract Class
// Implement IWorkable nhưng không cần ghi đè (override) hàm work() vì đây là class trừu tượng
abstract class Employee implements IWorkable {
    protected String id;
    protected String name;
    protected double baseSalary;

    public Employee(String id, String name, double baseSalary) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    // Phương thức abstract yêu cầu các class con phải tự định nghĩa
    public abstract double calculatePay();
}
