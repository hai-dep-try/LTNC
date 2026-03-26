// Lớp cha trừu tượng (Hàng hóa)
abstract class Product {
    protected String id;
    protected String name;

    public Product(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Phương thức trừu tượng để in thông tin kiểm kê tùy theo loại hàng
    public abstract String getInventoryInfo();
}
