// 1. Component: Interface gốc
interface Notifier {
    void send(String msg);
}

// 2. Concrete Component: Kênh gửi mặc định cốt lõi
class EmailNotifier implements Notifier {
    @Override
    public void send(String msg) {
        System.out.println("Gửi Email: " + msg);
    }
}

// 3. Base Decorator: Lớp trang trí trừu tượng
abstract class NotifierDecorator implements Notifier {
    protected Notifier wrapper; // Biến này giữ đối tượng bị "bọc" bên trong

    public NotifierDecorator(Notifier wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public void send(String msg) {
        // Chuyển tiếp lệnh gọi cho đối tượng bên trong nó xử lý trước
        wrapper.send(msg);
    }
}

// 4. Concrete Decorators: Các kênh gắn thêm
class SMSNotifier extends NotifierDecorator {
    public SMSNotifier(Notifier wrapper) {
        super(wrapper);
    }

    @Override
    public void send(String msg) {
        super.send(msg); // Để lớp bọc bên trong (VD: Email) gửi trước
        System.out.println("Gửi SMS: " + msg); // Xong rồi mình tự gửi thêm SMS
    }
}

class FacebookNotifier extends NotifierDecorator {
    public FacebookNotifier(Notifier wrapper) {
        super(wrapper);
    }

    @Override
    public void send(String msg) {
        super.send(msg);
        System.out.println("Gửi Facebook Messenger: " + msg);
    }
}

// 5 & 6. Test hệ thống
public class Main {
    public static void main(String[] args) {
        String thongBao = "Mã OTP của bạn là 123456. Tuyệt đối không chia sẻ cho ai!";

        System.out.println("Kịch bản 1: Chỉ gửi Email (Cơ bản)");
        Notifier thongBaoCoBan = new EmailNotifier();
        thongBaoCoBan.send(thongBao);

        System.out.println("\nKịch bản 2: Gửi Email + SMS");
        // Lấy thông báo Email, bọc lớp SMS ra ngoài
        Notifier thongBaoVip = new SMSNotifier(new EmailNotifier());
        thongBaoVip.send(thongBao);

        System.out.println("\nKịch bản 3: Gửi Full Kênh (Email + Facebook + SMS)");
        // Bọc chồng chất lên nhau (như búp bê Nga)
        Notifier thongBaoKhanCap = new SMSNotifier(
                new FacebookNotifier(
                        new EmailNotifier()));
        thongBaoKhanCap.send(thongBao);
    }
}