public class AppConfig {
    // 1. Dùng volatile để đảm bảo đồng bộ bộ nhớ giữa các luồng
    /*
    private static volatile AppConfig instance;

    // 2. Các thuộc tính mẫu
    private String appName;
    private String version;
    private String logLevel;

     */


    private static volatile AppConfig instance;
    private String appName;
    private String version;
    private String logLevel;

    // 3. Private constructor để ngăn chặn khởi tạo từ bên ngoài bằng 'new'
    private AppConfig() {
        this.appName = "My Super App";
        this.version = "1.0.0";
        this.logLevel = "DEBUG";
    }

    // 4. Hàm getInstance() áp dụng Khởi tạo lười & An toàn đa luồng
    public static AppConfig getInstance() {
        if (instance == null) { // Check lần 1 (không có lock để tăng hiệu năng)
            synchronized (AppConfig.class) {
                if (instance == null) { // Check lần 2 (trong vùng an toàn)
                    instance = new AppConfig();
                }
            }
        }
        return instance;
    }



    // Getter & Setter (optional, viết cho đủ bộ)
    public String getAppName() { return appName; }
    public String getVersion() { return version; }
    public String getLogLevel() { return logLevel; }


}

