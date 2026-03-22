import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String roomType = sc.next();
        int nights = sc.nextInt();
        Room room;
        if (roomType.equals("S")){
            room = new StandardRoom(nights);
        } else {
            room = new VIPRoom(nights);
        }
        System.out.println(room.calculateCost());
        sc.close();

    }
}
