
class Electronic extends Product {
    private int warrantyMonths;

    public Electronic(String id, String name, int warrantyMonths) {
        super(id, name);
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public String getInventoryInfo() {
        return name + " - " + warrantyMonths + " tháng bảo hành";
    }
}
