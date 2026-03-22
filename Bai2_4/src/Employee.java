public class Employee {
    private String name;
    private MyDate birthday;

    // Constructor
    public Employee(String name, MyDate birthday) {
        this.name = name;
        this.birthday = new MyDate(birthday); // Deep copy MyDate
    }

    // Copy Constructor - Deep Copy
    public Employee(Employee other) {
        this.name = other.name;
        this.birthday = new MyDate(other.birthday); // Deep copy để tránh Shallow Copy
    }

    public MyDate getBirthday() { return birthday; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', birthday=" + birthday + "}";
    }
}