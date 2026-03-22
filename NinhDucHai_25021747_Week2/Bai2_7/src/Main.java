public class Main {
    public static void main(String[] args) {
        Product[] arr = {
                new Product(1, "Laptop", 1000.0),
                new Product(2, "Mouse", 25.0)
        };

        Inventory kho = new Inventory(arr);

        arr[0].setPrice(5000.0);

        kho.printInventory();
    }
}