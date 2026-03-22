public abstract class Room {
    protected int nights;
    public Room(int nights){
        this.nights = nights;
    }
    public abstract long calculateCost();
}
