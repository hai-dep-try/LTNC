public class Main {
    public static void main(String[] args) {
        // 2. Ứng dụng thực tế
        Pair<String, Integer> agePair = new Pair<>("Tuổi", 20);
        Pair<String, String> idPair = new Pair<>("Mã SV", "SV001");
        Pair<Integer, Double> coordsPair = new Pair<>(105, 21.5);

        // In kết quả ra màn hình
        System.out.println(agePair);
        System.out.println(idPair);
        System.out.println(coordsPair);

        // 3. Thử nghiệm lỗi (Cố tình gán sai kiểu dữ liệu)
        /* Bỏ comment dòng dưới đây, IDE (như IntelliJ, Eclipse) hoặc trình biên dịch 
           sẽ gạch đỏ báo lỗi ngay lập tức: 
           "Required type: Integer, Provided: String"
        */
        // agePair.setValue("Hai mươi"); 
        
        /*
           Khởi tạo sai kiểu cũng sẽ bị chặn ngay lúc gõ code:
        */
        // Pair<String, Integer> errorPair = new Pair<>("Lỗi", "Một trăm");
    }
}