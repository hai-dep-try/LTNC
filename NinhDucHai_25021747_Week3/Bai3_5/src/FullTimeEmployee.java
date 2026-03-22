public class FullTimeEmployee extends Employee{
    private double baseSalary;
    private double bonus;
    private double penalty;

    public FullTimeEmployee(String name, double baseSalary, double bonus, double penalty) {
        super(name);
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.penalty = penalty;
    }

    @Override
    public double calculateSalary(){
        return baseSalary + (bonus - penalty);
    }
    @Override
    public String getEmployeeType(){
        return "Full-time";
    }
}
