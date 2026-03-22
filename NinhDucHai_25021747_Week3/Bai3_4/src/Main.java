public class Main {
    public static void main(String[] args) {

        Animal a = new Dog();


        if (a instanceof Cat) {

            Cat c = (Cat) a;
            c.makeSound();
        } else {

            System.out.println("Đây không phải là Mèo!");
        }
    }
}