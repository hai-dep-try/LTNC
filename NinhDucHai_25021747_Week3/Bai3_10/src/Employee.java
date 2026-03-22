public class Employee {
    protected String name;
    protected double baseSalary;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public double getBonus() {
        return baseSalary * 0.1;
    }

    public void printInfo() {
        System.out.println(name + " - Bonus: " + getBonus());
    }
}