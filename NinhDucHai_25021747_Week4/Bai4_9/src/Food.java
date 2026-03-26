// Lớp Thực phẩm
class Food extends Product {
    private String expiryDate;

    public Food(String id, String name, String expiryDate) {
        super(id, name);
        this.expiryDate = expiryDate;
    }

    @Override
    public String getInventoryInfo() {
        return name + " - " + expiryDate;
    }
}
