public class Inventory {
    Product[] items;

    public Inventory(Product[] initialItems) {
        this.items = initialItems;
    }

    public void printInventory() {
        for (Product p : items) {
            System.out.println(p);
        }
    }
}