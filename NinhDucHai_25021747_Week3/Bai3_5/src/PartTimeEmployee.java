public class PartTimeEmployee extends Employee{
    private double hourlyRate;
    private double workingHours;
    public PartTimeEmployee(String name, double hourlyRate, double workingHours) {
        super(name);
        this.hourlyRate = hourlyRate;
        this.workingHours = workingHours;
    }
    @Override
    public double calculateSalary(){
        return workingHours*hourlyRate;
    }

    @Override
    public String getEmployeeType() {
        return "Part-time";
    }
}
