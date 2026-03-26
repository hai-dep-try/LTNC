import java.util.ArrayList;
import java.util.List;

// 4.Lớp quản lý trung tâm hub
class Hub {
    private List<Device> devices;

    public Hub() {
        this.devices = new ArrayList<>();
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    // Tắt toàn bộ: Duyệt qua tất cả các Device
    public void turnOffAll() {
        System.out.println("Turn Off All Devices:");
        for (Device device : devices) {
            device.turnOff();
        }
    }

    // Cài đặt Wifi: Chỉ gọi khi thiết bị có hỗ trợ Wifi
    public void setupWifi() {
        System.out.println("Setup Wifi:");
        for (Device device : devices) {
            // Toán tử instanceof kiểm tra xem đối tượng có thực thi Interface này không
            if (device instanceof WifiConnectable) {
                // Ép kiểu (Casting) từ Device sang WifiConnectable để gọi hàm
                ((WifiConnectable) device).connectWifi();
            }
        }
    }
}
