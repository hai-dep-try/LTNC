//3.Concrete class
class SmartLight extends Device implements Adjustable {
    public SmartLight(String id, String name) {
        super(id, name);
    }

    @Override
    public void adjustLevel(int level) {
        System.out.println(this.name + " brightness set to " + level);
    }
}
