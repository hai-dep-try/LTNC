import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String name = scanner.next();
            double salary = scanner.nextDouble();

            switch (type) {
                case "E":
                    employees.add(new Employee(name, salary));
                    break;
                case "D":
                    int ot = scanner.nextInt();
                    employees.add(new Developer(name, salary, ot));
                    break;
                case "T":
                    int bugs = scanner.nextInt();
                    employees.add(new Tester(name, salary, bugs));
                    break;
            }
        }

        System.out.println();
        for (Employee emp : employees) {
            emp.printInfo();
            System.out.println();
        }

        scanner.close();
    }
}