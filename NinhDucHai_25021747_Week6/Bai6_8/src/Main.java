// 8. Lớp Report chứa dữ liệu
class Report {
    private String title;
    private String content;

    public Report(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() { return title; }
    public String getContent() { return content; }
}

// 7. Interface chung cho mọi định dạng (Strategy)
interface ReportFormatter {
    String format(Report report);
}

// 9. Cài đặt các Formatter cụ thể
class JsonFormatter implements ReportFormatter {
    @Override
    public String format(Report report) {
        // Giả lập format JSON
        return "{\n" +
                "  \"title\": \"" + report.getTitle() + "\",\n" +
                "  \"content\": \"" + report.getContent() + "\"\n" +
                "}";
    }
}

class XmlFormatter implements ReportFormatter {
    @Override
    public String format(Report report) {
        // Giả lập format XML
        return "<Report>\n" +
                "  <title>" + report.getTitle() + "</title>\n" +
                "  <content>" + report.getContent() + "</content>\n" +
                "</Report>";
    }
}

// 5, 6, 10. Lớp ReportService (Context)
class ReportService {
    // Không còn chuỗi if-else. Nó chỉ chứa một interface.
    private ReportFormatter formatter;

    // Nhận Formatter từ bên ngoài truyền vào (Dependency Injection)
    public ReportService(ReportFormatter formatter) {
        this.formatter = formatter;
    }

    // (Tùy chọn) Thêm setter để đổi định dạng linh hoạt lúc đang chạy
    public void setFormatter(ReportFormatter formatter) {
        this.formatter = formatter;
    }

    // Hàm export giờ cực kỳ gọn gàng
    public String export(Report data) {
        // Ủy quyền hoàn toàn việc format cho đối tượng đang được nạp
        return formatter.format(data);
    }
}

// 11. Main chạy thử
public class Main {
    public static void main(String[] args) {
        Report myReport = new Report("Báo cáo tháng 4", "Doanh thu đạt chỉ tiêu 100%");

        // Muốn xuất JSON? Cắm JsonFormatter vào!
        ReportService service = new ReportService(new JsonFormatter());
        System.out.println("KẾT QUẢ JSON");
        System.out.println(service.export(myReport));

        // Khách hàng đổi ý muốn xuất XML? Chỉ cần rút cục JSON ra, cắm cục XML vào!
        service.setFormatter(new XmlFormatter());
        System.out.println("\nKẾT QUẢ XML");
        System.out.println(service.export(myReport));
    }
}