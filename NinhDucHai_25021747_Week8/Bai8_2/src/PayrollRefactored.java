public class PayrollRefactored {
    // 1. Replace Magic Number with Constant
    private static final double SOCIAL_INSURANCE_RATE = 0.08;
    private static final double HEALTH_INSURANCE_RATE = 0.015;
    
    private static final double PERSONAL_DEDUCTION = 11000000;
    
    private static final double TAX_TIER_1_MAX = 5000000;
    private static final double TAX_TIER_2_MAX = 10000000;
    
    private static final double TAX_RATE_TIER_1 = 0.05;
    private static final double TAX_RATE_TIER_2 = 0.10;
    
    private static final double MAX_TAX_TIER_1 = 250000; // 5.000.000 * 0.05
    private static final double MAX_TAX_TIER_1_AND_2 = 750000; // 250.000 + 5.000.000 * 0.10
    
    public void printPayroll(String name, double baseSalary, int workDays, int totalDays, double taxRate, double bonus) {
        System.out.println("=== AFTER REFACTORING PAYROLL ===");
        System.out.println("Employee: " + name);
        
        // 3. Call sub-methods
        double actualSalary = calculateActualSalary(baseSalary, workDays, totalDays);
        double insurance = calculateInsurance(actualSalary);
        double tax = calculateTax(actualSalary, insurance, taxRate);
        double netSalary = calculateNetSalary(actualSalary, insurance, tax, bonus);
        
        // Print results
        System.out.println("Base Salary: " + baseSalary);
        System.out.println("Work Days: " + workDays + "/" + totalDays);
        System.out.println("Actual Salary: " + actualSalary);
        System.out.println("Insurance: " + insurance);
        System.out.println("Income Tax: " + tax);
        System.out.println("Net Salary: " + netSalary);
    }

    // 2. Extract methods
    private double calculateActualSalary(double baseSalary, int workDays, int totalDays) {
        return baseSalary * workDays / totalDays;
    }

    private double calculateInsurance(double actualSalary) {
        double socialInsuranceAmount = actualSalary * SOCIAL_INSURANCE_RATE;
        double healthInsuranceAmount = actualSalary * HEALTH_INSURANCE_RATE;
        return socialInsuranceAmount + healthInsuranceAmount;
    }

    private double calculateTax(double actualSalary, double insurance, double taxRateTier3) {
        double taxableIncome = actualSalary - insurance - PERSONAL_DEDUCTION;
        
        if (taxableIncome <= 0) {
            return 0;
        }

        if (taxableIncome <= TAX_TIER_1_MAX) {
            return taxableIncome * TAX_RATE_TIER_1;
        } 
        
        if (taxableIncome <= TAX_TIER_2_MAX) {
            double amountInTier2 = taxableIncome - TAX_TIER_1_MAX;
            return MAX_TAX_TIER_1 + (amountInTier2 * TAX_RATE_TIER_2);
        }
        
        double amountInTier3 = taxableIncome - TAX_TIER_2_MAX;
        return MAX_TAX_TIER_1_AND_2 + (amountInTier3 * taxRateTier3);
    }

    private double calculateNetSalary(double actualSalary, double insurance, double tax, double bonus) {
        return actualSalary - insurance - tax + bonus;
    }
}
