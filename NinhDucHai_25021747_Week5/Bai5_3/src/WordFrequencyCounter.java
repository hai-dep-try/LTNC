import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordFrequencyCounter {
    public static void main(String[] args) {
        // Giả lập đoạn văn bản đầu vào
        String text = "Data Engineering is great! Data Science is also great. " +
                "This is a simple test, just a simple test for data.";

        // --- BƯỚC 1: CHUẨN HÓA ---
        // Chuyển thành chữ thường, xóa tất cả dấu câu (chỉ giữ lại chữ cái a-z và khoảng trắng), sau đó tách từ
        String[] words = text.toLowerCase().replaceAll("[^a-z\\s]", "").split("\\s+");

        // --- BƯỚC 2: ĐẾM TỪ ---
        Map<String, Integer> wordCountMap = new HashMap<>();
        for (String word : words) {
            if (word.isEmpty()) continue; // Bỏ qua các khoảng trắng thừa

            // Logic thêm vào Map theo đúng mô tả của đề bài
            if (!wordCountMap.containsKey(word)) {
                wordCountMap.put(word, 1);
            } else {
                int old_value = wordCountMap.get(word);
                wordCountMap.put(word, old_value + 1);
            }
        }

        // --- BƯỚC 3: SẮP XẾP & THỐNG KÊ ---
        String mostFrequentWord = "";
        int maxCount = 0;
        List<String> uniqueWords = new ArrayList<>();

        // Duyệt qua từng phần tử (Entry) trong Map
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();

            // Tìm từ xuất hiện nhiều nhất
            if (count > maxCount) {
                maxCount = count;
                mostFrequentWord = word;
            }

            // Liệt kê các từ xuất hiện đúng 1 lần
            if (count == 1) {
                uniqueWords.add(word);
            }
        }

        // In kết quả
        System.out.println("Văn bản gốc: " + text);
        System.out.println("\n--- KẾT QUẢ THỐNG KÊ ---");
        System.out.println("Từ xuất hiện nhiều nhất: '" + mostFrequentWord + "' (" + maxCount + " lần)");
        System.out.println("Các từ chỉ xuất hiện 1 lần (Unique words): " + uniqueWords);
    }
}