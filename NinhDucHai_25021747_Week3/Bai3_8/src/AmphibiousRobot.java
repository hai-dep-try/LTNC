public class AmphibiousRobot extends Robot implements Swimmable, GPS, Flyable{
    public AmphibiousRobot(int id, String modelName){
        super(id, modelName);
    }
    public void swim(){
        System.out.println(getModelName() + " swimming");

    }
    public void performMainTask(){
        System.out.println(getModelName() + " performing main task");
    }
    public void fly(){
        System.out.println(getModelName() + " flying");
    }
    public void getCoordinates(){
        System.out.println(getModelName() + " getting coordinates");
    }

}
