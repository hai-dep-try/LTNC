// 1 & 2. Giao diện và các lớp cụ thể
interface Notification {
    void send(String msg);
}

class EmailNotification implements Notification {
    public void send(String msg) {
        System.out.println("Gửi Email: " + msg);
    }
}

class SmsNotification implements Notification {
    public void send(String msg) {
        System.out.println("Gửi SMS: " + msg);
    }
}

// 3. Lớp trừu tượng định nghĩa Factory Method
abstract class NotificationApp {
    // Gọi phương thức mà không cần dùng từ khóa 'new'
    public void notifyUser(String msg) {
        Notification notification = createNotification();
        notification.send(msg);
    }

    // Abstract Factory method
    protected abstract Notification createNotification();
}

// 4. Các lớp con ghi đè để quyết định loại đối tượng được tạo
class EmailApp extends NotificationApp {
    @Override
    protected Notification createNotification() {
        return new EmailNotification();
    }
}

class SmsApp extends NotificationApp {
    @Override
    protected Notification createNotification() {
        return new SmsNotification();
    }
}

// 5. Hàm chạy chính
public class Main {
    public static void main(String[] args) {
        NotificationApp app1 = new EmailApp();
        app1.notifyUser("Tài khoản của bạn vừa đăng nhập ở thiết bị lạ.");

        NotificationApp app2 = new SmsApp();
        app2.notifyUser("Mã OTP của bạn là 888888.");
    }
}