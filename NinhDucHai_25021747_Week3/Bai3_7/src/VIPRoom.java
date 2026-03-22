public class VIPRoom extends Room {
    private static final long price = 2_000_000;
    public VIPRoom(int nights){
        super(nights);
    }
    public long calculateCost(){
        return price*nights;
    }

}
