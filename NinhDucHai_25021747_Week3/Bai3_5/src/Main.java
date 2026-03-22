import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        Employee[] arr = new Employee[n];

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();

            char type = line.charAt(0);

            int firstQuote = line.indexOf("\"");
            int lastQuote = line.lastIndexOf("\"");

            String name = "";
            if (firstQuote != -1 && lastQuote != -1 && lastQuote > firstQuote) {
                name = line.substring(firstQuote + 1, lastQuote);
            }

            String rest = line.substring(lastQuote + 1).trim();
            String[] parts = rest.split("\\s+");

            if (type == 'F') {
                double baseSalary = Double.parseDouble(parts[0]);
                double bonus = Double.parseDouble(parts[1]);
                double penalty = Double.parseDouble(parts[2]);

                arr[i] = new FullTimeEmployee(name, baseSalary, bonus, penalty);
            } else {
                double workingHours = Double.parseDouble(parts[0]);
                double hourlyRate = Double.parseDouble(parts[1]);

                arr[i] = new PartTimeEmployee(name, workingHours, hourlyRate);
            }
        }

        for (Employee e : arr) {
            String type = (e instanceof FullTimeEmployee) ? "Full-time" : "Part-time";
            System.out.println(e.name + " - " + type + " - " + e.calculateSalary());
        }

        sc.close();
    }
}