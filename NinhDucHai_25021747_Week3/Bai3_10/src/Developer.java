public class Developer extends Employee {
    private int overtimeHours;

    public Developer(String name, double baseSalary, int overtimeHours) {
        super(name, baseSalary);
        this.overtimeHours = overtimeHours;
    }

    @Override
    public double getBonus() {
        return super.getBonus() + (overtimeHours * 200000);
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Tặng khóa học AWS");
    }
}
