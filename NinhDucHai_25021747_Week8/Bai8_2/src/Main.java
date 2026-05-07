public class Main {
    public static void main(String[] args) {
        String name = "Nguyen Van A";
        double baseSalary = 30000000;
        int workDays = 22;
        int totalDays = 22;
        double taxRate = 0.15; 
        double bonus = 2000000;

        System.out.println("==================================================");
        
        PayrollBefore before = new PayrollBefore();
        before.printPayroll(name, baseSalary, workDays, totalDays, taxRate, bonus);

        System.out.println("\n==================================================\n");

        PayrollRefactored after = new PayrollRefactored();
        after.printPayroll(name, baseSalary, workDays, totalDays, taxRate, bonus);
        
        System.out.println("==================================================");
    }
}
