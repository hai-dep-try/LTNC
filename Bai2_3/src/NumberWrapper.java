public class NumberWrapper {
    private int value;

    public NumberWrapper(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

    public static void swap(NumberWrapper a, NumberWrapper b) {
        int temp = a.getValue();
        a.setValue(b.getValue());
        b.setValue(temp);
    }

    public static void main(String[] args) {
        NumberWrapper n1 = new NumberWrapper(5);
        NumberWrapper n2 = new NumberWrapper(10);
        swap(n1, n2);
        System.out.println(n1.getValue());
        System.out.println(n2.getValue());

    }

}
