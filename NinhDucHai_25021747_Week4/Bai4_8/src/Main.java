import java.util.Scanner;

//5.Hàm xử lý main input/output
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hub hub = new Hub();

        // Nhập số lượng thiết bị
        int n = Integer.parseInt(scanner.nextLine());

        // Nhập từng thiết bị
        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split(" ");
            String type = input[0];
            String id = input[1];
            String name = input[2];

            switch (type) {
                case "L":
                    hub.addDevice(new SmartLight(id, name));
                    break;
                case "AC":
                    hub.addDevice(new AirConditioner(id, name));
                    break;
                case "S":
                    hub.addDevice(new SmartSpeaker(id, name));
                    break;
                case "C":
                    hub.addDevice(new WindowCurtain(id, name));
                    break;
            }
        }

        // Thực thi các yêu cầu của Hub
        hub.turnOffAll();
        System.out.println(); // In dòng trống theo Output mẫu
        hub.setupWifi();
    }
}