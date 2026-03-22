public class Main {
    public static void main(String[] args) {
        // Tạo emp1 với ngày sinh 1/1/2000
        MyDate date1 = new MyDate(1, 1, 2000);
        Employee emp1 = new Employee("Nguyen Van A", date1);

        // Tạo emp2 bằng cách sao chép emp1
        Employee emp2 = new Employee(emp1);

        // Thay đổi ngày sinh của emp1 thành 2/2/2022
        emp1.getBirthday().setDay(2);
        emp1.getBirthday().setMonth(2);
        emp1.getBirthday().setYear(2022);

        // In ngày sinh của emp2 (phải vẫn là 1/1/2000)
        System.out.println("Ngày sinh emp2: " + emp2.getBirthday());
    }
}
