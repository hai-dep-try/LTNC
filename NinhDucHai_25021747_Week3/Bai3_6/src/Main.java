import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Order order = new Order();

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {

            String[] parts = sc.nextLine().split("\"");
            String type = parts[0].trim();
            String name = parts[1];
            String[] data = parts[2].trim().split(" ");

            double price = Double.parseDouble(data[0]);

            if (type.equals("E")) {
                double warranty = Double.parseDouble(data[1]);
                order.addProduct(new Electronics(name, price, warranty));
            } else if (type.equals("F")) {
                LocalDate date = LocalDate.parse(data[1]);
                order.addProduct(new Food(name, price, date));
            }
        }

        order.printReceipt();
        sc.close();
    }
}