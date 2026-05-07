class Logger {
    // Ngăn khởi tạo từ bên ngoài
    private Logger() {}

    // Dùng helper class để khởi tạo lười và an toàn đa luồng
    private static class LoggerHelper {
        private static final Logger INSTANCE = new Logger();
    }

    public static Logger getInstance() {
        return LoggerHelper.INSTANCE;
    }

    public void log(String message) {
        System.out.println("[LOG]: " + message);
    }
}