// 2.abstract class
abstract class Device {
    protected String id;
    protected String name;
    protected boolean isOn;

    public Device(String id, String name) {
        this.id = id;
        this.name = name;
        this.isOn = false; // Mặc định thiết bị tắt
    }

    // Các thiết bị đều có chung logic tắt
    public void turnOff() {
        this.isOn = false;
        System.out.println(this.name + " turned off");
    }

    public void turnOn() {
        this.isOn = true;
        System.out.println(this.name + " turned on");
    }

    public String getName() {
        return name;
    }
}
