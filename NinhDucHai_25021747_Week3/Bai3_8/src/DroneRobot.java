public class DroneRobot  extends Robot implements GPS, Flyable{
    public DroneRobot(int id, String modelName){
        super(id, modelName);
    }
    public void fly(){
        System.out.println(getModelName() + " flying");
    }
    public void getCoordinates(){
        System.out.println(getModelName() + " getting coordinates");
    }


    public void performMainTask(){
        System.out.println(getModelName() + " performing main task");

    }
}
