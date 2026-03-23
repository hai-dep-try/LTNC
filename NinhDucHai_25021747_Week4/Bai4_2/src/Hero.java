// Lớp Hero kế thừa ActionCharacter và triển khai các Interface
class Hero extends ActionCharacter implements CanFly, CanSwim, CanFight {

    // Bắt buộc phải override các hàm của CanFly và CanSwim vì class cha chưa có
    @Override
    public void fly() {
        System.out.println("Hero is flying");
    }

    @Override
    public void swim() {
        System.out.println("Hero is swimming");
    }

    // LƯU Ý QUAN TRỌNG:
    // Chúng ta KHÔNG CẦN implement lại hàm fight() của interface CanFight ở đây.
    // Vì lớp cha ActionCharacter đã cung cấp sẵn một public method tên là fight()
    // khớp hoàn toàn với signature của interface CanFight.
}
