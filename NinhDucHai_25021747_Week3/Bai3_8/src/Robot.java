public abstract class Robot {
    private int id;
    private String modelName;
    private int batteryLevel;
    public Robot(int id, String modelName) {
        this.id = id;
        this.modelName = modelName;
    }
    public void changeBattery(){
        batteryLevel = 100;
    }
    public final void showIdentity(){
        System.out.println();
    }

    public int getId() {
        return id;
    }

    public String getModelName(){
        return modelName;
    }

    public abstract void performMainTask();
}