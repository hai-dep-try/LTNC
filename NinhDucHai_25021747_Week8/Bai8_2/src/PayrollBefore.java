public class PayrollBefore {
    public void printPayroll(String name, double baseSalary, int workDays, int totalDays, double taxRate, double bonus) {
        System.out.println("=== BEFORE REFACTORING PAYROLL ===");
        System.out.println("Employee: " + name);
        
        double actualSalary = baseSalary * workDays / totalDays;
        
        double insurance = actualSalary * 0.08 + actualSalary * 0.015;
        
        double taxableIncome = actualSalary - insurance - 11000000;
        double tax = 0;
        if (taxableIncome > 0) {
            if (taxableIncome <= 5000000) tax = taxableIncome * 0.05;
            else if (taxableIncome <= 10000000) tax = 250000 + (taxableIncome - 5000000) * 0.10;
            else tax = 750000 + (taxableIncome - 10000000) * taxRate;
        }
        
        double netSalary = actualSalary - insurance - tax + bonus;
        
        System.out.println("Base Salary: " + baseSalary);
        System.out.println("Work Days: " + workDays + "/" + totalDays);
        System.out.println("Actual Salary: " + actualSalary);
        System.out.println("Insurance: " + insurance);
        System.out.println("Income Tax: " + tax);
        System.out.println("Net Salary: " + netSalary);
    }
}
