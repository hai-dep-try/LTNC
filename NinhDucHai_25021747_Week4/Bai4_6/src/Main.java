public class Main {
    public static void main(String[] args) {
        // 1. Thử nghiệm với mảng Integer
        Integer[] intArray = {5, 1, 3, 2};
        ArrayUtils.sort(intArray);
        ArrayUtils.printArray(intArray); // Output: 1 2 3 5 

        // 2. Thử nghiệm với mảng String
        String[] strArray = {"Java", "C++", "Python"};
        ArrayUtils.sort(strArray);
        ArrayUtils.printArray(strArray); // Output: C++ Java Python 

        // 3. Thử nghiệm với mảng Student
        Student[] students = {
                new Student("Nam"),
                new Student("An"),
                new Student("Binh")
        };
        ArrayUtils.sort(students);
        ArrayUtils.printArray(students); // Output: An Binh Nam
    }
}