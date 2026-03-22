import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
    }

    public void printReceipt() {
        double total = 0;
        for (Product p : products) {
            double price = p.getFinalPrice();
            total += price;
            String type = p instanceof Electronics ? "Electronics" : "Food";
            System.out.println(p.getName() + " - " + type + " - " + price);
        }
        System.out.println("Total = " + total);
    }
}