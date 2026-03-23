// 3. Thực hành và kiểm thử
public class Main {
    public static void main(String[] args) {
        // Viết chương trình tạo một Hero
        Hero hero = new Hero();

        // Thử ép kiểu Hero về CanSwim để gọi hàm swim()
        CanSwim swimmer = (CanSwim) hero;
        swimmer.swim(); // Output: Hero is swimming

        // Thử ép kiểu Hero về CanFight để gọi hàm fight()
        CanFight fighter = (CanFight) hero;
        fighter.fight(); // Output: Đấm bốc...
    }
}