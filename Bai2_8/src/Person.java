public class Person {
    // 1. Fields (private)
    private String name;
    private Person me;

    // 2. Constructor
    public Person(String name) {
        this.name = name;
    }

    // 3. Methods
    public void setMe(Person other) {
        this.me = other;
    }

    public Person getMe() {
        return this.me;
    }

    public String getName() {
        return this.name;
    }

    // 4. Main
    public static void main(String[] args) {
        // Khởi tạo đối tượng Person, gán cho biến p
        Person p = new Person("Alice");

        // Set tham chiếu me đến chính đối tượng p đang trỏ
        p.setMe(p);

        // Truy cập getName() thông qua biến tham chiếu me của p
        System.out.println(p.getMe().getName()); // Output: Alice

        // Set p = null
        p = null;
    }
}
