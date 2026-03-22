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
        // Initialize a Person object, assigned to reference variable 'p'
        Person p = new Person("Alice");

        // Set the 'me' reference to point to the same object 'p' is pointing to
        p.setMe(p);

        // Access getName() through the 'me' reference of object 'p'
        if (p.getMe() != null) {
            System.out.println("Name via self-reference: " + p.getMe().getName()); 
        }

        // Set p to null (The object is now eligible for Garbage Collection)
        p = null;
        
        System.out.println("Reference 'p' has been cleared.");
    }
}