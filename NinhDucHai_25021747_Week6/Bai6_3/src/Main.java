// 1. Tạo các interface cho sản phẩm
interface Button {
    void render();
}
interface Checkbox {
    void render();
}

// Cài đặt họ sản phẩm Windows
class WindowsButton implements Button {
    public void render() { System.out.println("Render Windows Button"); }
}
class WindowsCheckbox implements Checkbox {
    public void render() { System.out.println("Render Windows Checkbox"); }
}

// Cài đặt họ sản phẩm Mac
class MacButton implements Button {
    public void render() { System.out.println("Render Mac Button"); }
}
class MacCheckbox implements Checkbox {
    public void render() { System.out.println("Render Mac Checkbox"); }
}

// 2. Tạo Abstract Factory
interface UIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// 3. Cài đặt các Factory cụ thể
class WindowsFactory implements UIFactory {
    public Button createButton() { return new WindowsButton(); }
    public Checkbox createCheckbox() { return new WindowsCheckbox(); }
}

class MacFactory implements UIFactory {
    public Button createButton() { return new MacButton(); }
    public Checkbox createCheckbox() { return new MacCheckbox(); }
}

// Lớp Client sử dụng Factory
class Application {
    private Button button;
    private Checkbox checkbox;

    // Client chỉ quan tâm đến interface, không cần biết class cụ thể
    public Application(UIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.render();
        checkbox.render();
    }
}

// 4. Main test thử
public class Main {
    public static void main(String[] args) {
        String config = "win"; // Giả lập tham số truyền vào
        UIFactory factory;

        if (config.equalsIgnoreCase("win")) {
            factory = new WindowsFactory();
        } else {
            factory = new MacFactory();
        }

        Application app = new Application(factory);
        app.paint();
    }
}