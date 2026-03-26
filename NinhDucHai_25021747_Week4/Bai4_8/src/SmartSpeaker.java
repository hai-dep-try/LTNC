class SmartSpeaker extends Device implements WifiConnectable, Adjustable {
    public SmartSpeaker(String id, String name) {
        super(id, name);
    }

    @Override
    public void connectWifi() {
        System.out.println(this.name + " connected to wifi");
    }

    @Override
    public void adjustLevel(int level) {
        System.out.println(this.name + " volume set to " + level);
    }
}
