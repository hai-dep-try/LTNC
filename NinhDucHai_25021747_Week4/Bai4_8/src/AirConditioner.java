class AirConditioner extends Device implements WifiConnectable {
    public AirConditioner(String id, String name) {
        super(id, name);
    }

    @Override
    public void connectWifi() {
        System.out.println(this.name + " connected to wifi");
    }
}
