abstract class Employee {
    protected String name;
    protected String dob;
    protected String id;
    public Employee(String name ){

        this.name = name;
    }
    public String getName(){
        return name;
    }
    public abstract double calculateSalary();
    public abstract String getEmployeeType();

}
