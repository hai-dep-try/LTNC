public class StandardRoom extends Room{
    private final long price = 500_000;
    public StandardRoom(int nights){
        super(nights);

    }
    @Override
    public long calculateCost(){
        long total = price * nights;
        if (nights > 3){
            total = (long)(total * 0.95);
        }
        return total;
    }
}
