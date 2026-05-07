import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Khởi tạo ngoại lệ tự định nghĩa
class InvalidConfigException extends Exception {
    public InvalidConfigException(String message) {
        super(message);
    }
}

public class DocCauHinh {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập đường dẫn file config: ");
        String filePath = scanner.nextLine();

        BufferedReader reader = null;
        Map<String, String> configMap = new HashMap<>();

        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;

            // 1 & 2. Đọc file và lưu vào Map
            while ((line = reader.readLine()) != null) {
                // Bỏ qua dòng trống nếu có
                if (line.trim().isEmpty()) continue;

                // Tách chuỗi theo dấu "=" đầu tiên gặp được
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    configMap.put(parts[0].trim(), parts[1].trim());
                }
            }

            // 3. Kiểm tra dữ liệu (Ném ra ngoại lệ nếu vi phạm)
            if (!configMap.containsKey("username")) {
                throw new InvalidConfigException("Missing username");
            }
            if (!configMap.containsKey("timeout")) {
                throw new InvalidConfigException("Missing timeout");
            }

            // parseInt có thể ném ra NumberFormatException
            int timeout = Integer.parseInt(configMap.get("timeout"));
            if (timeout <= 0) {
                throw new InvalidConfigException("timeout must be > 0");
            }

            if (configMap.containsKey("maxConnections")) {
                int maxConnections = Integer.parseInt(configMap.get("maxConnections"));
                if (maxConnections < 1) {
                    throw new InvalidConfigException("maxConnections must be >= 1");
                }
            }

            // 6. Nếu cấu hình hợp lệ (không bị văng lỗi ở trên)
            System.out.println("--- Các cấu hình đã đọc ---");
            for (Map.Entry<String, String> entry : configMap.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }
            System.out.println("Config loaded successfully.");

            // 4. Bắt và xử lý các ngoại lệ theo yêu cầu
        } catch (FileNotFoundException e) {
            System.out.println("Config file not found.");
        } catch (IOException e) {
            System.out.println("I/O error.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
        } catch (InvalidConfigException e) {
            System.out.println("Invalid config: " + e.getMessage());
        } finally {
            // 5. Đảm bảo đóng file
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 6. Luôn in ra dòng này dù có lỗi hay không
            System.out.println("Program finished.");
            scanner.close();
        }
    }
}