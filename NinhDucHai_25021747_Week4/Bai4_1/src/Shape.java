// 1. Thiết kế Abstract Class Shape
abstract class Shape {
    protected int x, y;

    // Constructor khởi tạo toạ độ
    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Hai phương thức abstract
    public abstract void draw();

    public abstract void erase();

    // Phương thức thường moveTo với logic bắt buộc
    public void moveTo(int newX, int newY) {
        erase();            // 1. Gọi erase()
        this.x = newX;      // 2. Cập nhật x, y sang toạ độ mới
        this.y = newY;
        draw();             // 3. Gọi draw()
    }
}
