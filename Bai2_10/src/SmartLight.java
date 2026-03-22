public class SmartLight {

    // 1. Fields (Properties) - all are private
    private String id;
    private String name;
    private int brightness;

    // 2. Constructors

    // Constructor 1: takes 3 parameters
    public SmartLight(String id, String name, int brightness) {
        this.id = id;
        this.name = name;
        this.brightness = brightness;
    }

    // Constructor 2: takes 2 parameters, calls Constructor 1 with default brightness = 50
    public SmartLight(String id, String name) {
        this(id, name, 50);
    }

    // 3. Methods

    // Update to a new brightness level
    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    // Overloading: takes a String preset
    public void setBrightness(String preset) {
        if (preset.equals("MAX")) {
            this.setBrightness(100);
        } else if (preset.equals("MIN")) {
            this.setBrightness(10);
        } else if (preset.equals("ECO")) {
            this.setBrightness(30);
        }
    }

    // Register device to the central controller
    public void connectToHub(CentralHub hub) {
        hub.registerDevice(this);
    }

    // Getter for name (used by CentralHub)
    public String getName() {
        return name;
    }

    // Getter for brightness (used for printing results)
    public int getBrightness() {
        return brightness;
    }

    // 4. Main Method
    public static void main(String[] args) {
        // Initialize the hub from the CentralHub class
        CentralHub hub = new CentralHub();

        // Create light 'l1' using the full Constructor
        SmartLight l1 = new SmartLight("L01", "Living Room Light", 80);

        // Create light 'l2' using the 2-parameter Constructor
        SmartLight l2 = new SmartLight("L02", "Bedroom Light");

        // Execute setBrightness with the "ECO" preset
        l2.setBrightness("ECO");

        // Connect both lights to the hub
        l1.connectToHub(hub);
        l2.connectToHub(hub);

        // Print current brightness of both lights to verify
        System.out.println("Brightness l1 (" + l1.getName() + "): " + l1.getBrightness());
        System.out.println("Brightness l2 (" + l2.getName() + "): " + l2.getBrightness());
    }
}