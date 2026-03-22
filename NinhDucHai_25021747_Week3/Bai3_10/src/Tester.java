class Tester extends Employee {
    private int bugsFound;

    public Tester(String name, double baseSalary, int bugsFound) {
        super(name, baseSalary);
        this.bugsFound = bugsFound;
    }

    @Override
    public double getBonus() {
        return super.getBonus() + (bugsFound * 50000);
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Tặng tool Test");
    }
}
