public class StringTest {

    // Hàm 1: Đo thời gian nối chuỗi bằng String
    public static void useString() {
        long startTime = System.currentTimeMillis();

        String s = "";
        for (int i = 0; i < 100000; i++) {
            s += "Hello";
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Thời gian dùng String (ms): " + (endTime - startTime));
    }

    // Hàm 2: Đo thời gian nối chuỗi bằng StringBuffer
    public static void useStringBuffer() {
        long startTime = System.currentTimeMillis();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 100000; i++) {
            sb.append("Hello");
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Thời gian dùng StringBuffer (ms): " + (endTime - startTime));
    }

    // Hàm 3: Phân tích đoạn văn bản
    public static void contentAnalysis(String text) {
        // 1. Đếm số lượng câu
        String[] sentences = text.split("[.?!]+");
        int sentenceCount = 0;

        for (int i = 0; i < sentences.length; i++) {
            if (!sentences[i].trim().isEmpty()) {
                sentenceCount++;
            }
        }
        System.out.println("Số lượng câu: " + sentenceCount);

        // 2. Tìm và thay thế "Java" thành "Python"
        String replacedText = text.replace("Java", "Python");
        System.out.println("Văn bản sau thay thế: " + replacedText);
    }

    public static void main(String[] args) {
        useString();
        useStringBuffer();

        String sampleText = "Xin chào! Bạn có thích học Java không? Java là một ngôn ngữ tuyệt vời. Hãy code Java mỗi ngày!!!";
        contentAnalysis(sampleText);
    }
}