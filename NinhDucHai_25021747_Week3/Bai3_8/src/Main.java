import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int n = Integer.parseInt(sc.nextLine());
        List<Robot> robotList = new ArrayList<>();


        for (int i = 0; i < n; i++) {
            String[] data = sc.nextLine().split(" ");
            String type = data[0];
            int id = Integer.parseInt(data[1]);
            String modelName = data[2];


            switch (type) {
                case "DR":
                    robotList.add(new DroneRobot(id, modelName));
                    break;
                case "FR":
                    robotList.add(new FishRobot(id, modelName));
                    break;
                case "AR":
                    robotList.add(new AmphibiousRobot(id, modelName));
                    break;
            }
        }

        for (Robot robot : robotList) {
            robot.performMainTask();


            if (robot instanceof Flyable) {
                ((Flyable) robot).fly();
            }
            if (robot instanceof Swimmable) {
                ((Swimmable) robot).swim();
            }
            if (robot instanceof GPS) {
                ((GPS) robot).getCoordinates();
            }

            System.out.println(); 
        }
        sc.close();
    }
}