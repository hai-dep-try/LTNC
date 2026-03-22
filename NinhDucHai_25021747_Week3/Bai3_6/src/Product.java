public abstract class Product {
    String id;
    protected String name;
    protected double basePrice;
    public Product(String name, double basePrice){
        this.name = name;
        this.basePrice = basePrice;
    }
    public double getFinalPrice(){
        return basePrice;
    }
    public String getName(){
        return name;
    }

}
