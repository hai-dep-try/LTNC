// Lớp Logger áp dụng Singleton
class Logger {
    // 1. Thuộc tính static và private (dùng volatile để an toàn đa luồng)
    private static volatile Logger instance;

    // 2. Constructor private để cấm dùng từ khóa 'new' từ bên ngoài
    private Logger() {
        // Cố tình để trống
    }

    // 3. Cung cấp hàm lấy instance (Khởi tạo lười - Lazy Initialization)
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger(); // Chỉ tạo đúng 1 lần
                }
            }
        }
        return instance;
    }

    // 4. Các phương thức ghi log
    public void logInfo(String msg) {
        System.out.println("[INFO] " + msg);
    }

    public void logError(String msg) {
        System.out.println("[ERROR] " + msg);
    }
}

// Lớp Test (Main)
public class Main {
    public static void main(String[] args) {
        // Cố tình gọi getInstance() ở 2 nơi khác nhau
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        // Kiểm tra xem hai biến có trỏ về cùng 1 địa chỉ bộ nhớ không
        System.out.println("Logger instances equal: " + (logger1 == logger2));

        // Bắt đầu ghi log thử
        logger1.logInfo("Application started");
        logger2.logInfo("Processing data...");
        logger1.logError("Something went wrong");

        // Không thể làm thế này vì bị báo lỗi đỏ loét ngay (Constructor is private)
        // Logger logger3 = new Logger(); 
    }
}