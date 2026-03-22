public class CentralHub {

    // Single method: registers a SmartLight device
    public void registerDevice(SmartLight light) {
        System.out.println("[HUB] Connecting to device: " + light.getName());
    }
}