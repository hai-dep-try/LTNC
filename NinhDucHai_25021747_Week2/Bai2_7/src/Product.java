public class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() { return price; }
    public String getName() { return name; }
    public int getId() { return id; }

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=$" + price + "}";
    }
}