// Đoạn C sau khi refactor
// Code smell: Switch Statements / Long if-else chain (Vi phạm Open/Closed Principle).
// Khi thêm hình mới phải sửa code class này.
// Giải pháp: Replace Conditional with Polymorphism (Thay thế câu lệnh điều kiện thành đa hình).

interface Shape {
    double getArea();
}

class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }
}

class Triangle implements Shape {
    private double base;
    private double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double getArea() {
        return 0.5 * base * height;
    }
}

class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}
