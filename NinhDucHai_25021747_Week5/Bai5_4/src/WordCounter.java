import java.util.HashMap;
import java.util.Map;

public class WordCounter {
    // Khai báo HashMap để lưu trữ từ (Key) và số lần xuất hiện (Value)
    private HashMap<String, Integer> wordMap;

    public WordCounter() {
        wordMap = new HashMap<>();
    }

    // Phương thức phân tích văn bản
    public void analyze(String text) {
        // B1: Chuẩn hóa chuỗi (chuyển về chữ thường, bỏ dấu chấm/phẩy)
        // Dùng regex "[,.]" để thay thế dấu chấm, phẩy thành chuỗi rỗng
        String normalizedText = text.toLowerCase().replaceAll("[,.]", "");

        // B2: Tách chuỗi thành mảng các từ (cắt theo các khoảng trắng liên tiếp)
        String[] words = normalizedText.split("\\s+");

        // B3: Duyệt mảng
        for (String word : words) {
            if (word.isEmpty()) {
                continue; // Bỏ qua nếu có chuỗi rỗng sau khi split
            }

            // Kiểm tra từ đã có trong Map chưa
            if (wordMap.containsKey(word)) {
                // Nếu từ đã có: Lấy giá trị cũ + 1 rồi put lại
                int currentCount = wordMap.get(word);
                wordMap.put(word, currentCount + 1);
            } else {
                // Nếu từ chưa có: put từ đó vào Map với giá trị 1
                wordMap.put(word, 1);
            }
        }
    }

    // Phương thức hiển thị kết quả
    public void displayResult() {
        if (wordMap.isEmpty()) {
            System.out.println("Văn bản trống, chưa có dữ liệu.");
            return;
        }


        String mostFrequentWord = "";
        int maxCount = 0;

        // In ra danh sách và đồng thời tìm từ xuất hiện nhiều nhất
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();

            System.out.println(word + ": " + count);

            // Cập nhật từ xuất hiện nhiều nhất
            if (count > maxCount) {
                maxCount = count;
                mostFrequentWord = word;
            }
        }


        System.out.println("Từ xuất hiện nhiều nhất là: '" + mostFrequentWord + "' với " + maxCount + " lần.");
    }

    // Hàm main để chạy thử nghiệm nghiệm code
    public static void main(String[] args) {
        WordCounter counter = new WordCounter();
        String sampleText = "Hôm nay là một ngày đẹp trời. Trời rất đẹp, và hôm nay tôi học Java.";

        System.out.println("Văn bản gốc: " + sampleText + "\n");

        counter.analyze(sampleText);
        counter.displayResult();
    }
}