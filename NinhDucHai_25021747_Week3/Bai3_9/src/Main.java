import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        List<IPayable> payableList = new ArrayList<>();


        for (int i = 0; i < n; i++) {
            String[] input = sc.nextLine().split(" ");
            String type = input[0];

            if (type.equals("S")) {
                payableList.add(new PartTimeStaff(input[1], input[2],
                        Integer.parseInt(input[3]), Double.parseDouble(input[4])));
            } else if (type.equals("I")) {
                payableList.add(new Invoice(input[1],
                        Integer.parseInt(input[2]), Double.parseDouble(input[3])));
            }
        }


        double totalPayment = 0;
        for (IPayable item : payableList) {
            double payment = item.getPaymentAmount();
            totalPayment += payment;

            if (item instanceof PartTimeStaff) {
                PartTimeStaff staff = (PartTimeStaff) item;
                System.out.println("PartTimeStaff " + staff.getName() + " - Payment: " + payment);
            } else if (item instanceof Invoice) {
                Invoice invoice = (Invoice) item;
                System.out.println("Invoice " + invoice.getItemName() + " - Payment: " + payment);
            }
        }

        System.out.println("Total Payment = " + totalPayment);
        sc.close();
    }
}