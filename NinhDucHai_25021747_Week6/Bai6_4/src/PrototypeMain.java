import java.util.ArrayList;
import java.util.List;

// 1. Lớp ReportTemplate
class ReportTemplate implements Cloneable {
    private String title;
    private String footer;
    private List<String> sections;

    public ReportTemplate(String title, String footer, List<String> sections) {
        this.title = title;
        this.footer = footer;
        this.sections = sections; // Nên clone cả list truyền vào nếu cẩn thận hơn
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // 2. Cài đặt clone (Deep Copy để an toàn cho List)
    @Override
    public ReportTemplate clone() {
        // Tạo một list mới và copy toàn bộ phần tử sang (Deep copy)
        List<String> clonedSections = new ArrayList<>(this.sections);
        return new ReportTemplate(this.title, this.footer, clonedSections);
    }

    public void printReport() {
        System.out.println("Title: " + title + " | Footer: " + footer + " | Sections: " + sections);
    }
}

// 3 & 4. Test hàm main
public class PrototypeMain {
    public static void main(String[] args) {
        // Tạo sections gốc
        List<String> initialSections = new ArrayList<>();
        initialSections.add("Mở đầu");
        initialSections.add("Nội dung");

        // Tạo template gốc
        ReportTemplate originalReport = new ReportTemplate("Báo cáo Gốc", "Trang 1", initialSections);

        // Sinh ra 2 bản sao và chỉnh sửa tiêu đề
        ReportTemplate clone1 = originalReport.clone();
        clone1.setTitle("Báo cáo Tài chính - Quý 1");

        ReportTemplate clone2 = originalReport.clone();
        clone2.setTitle("Báo cáo Nhân sự - Tháng 4");

        // In ra để kiểm chứng (Template gốc phải giữ nguyên chữ "Báo cáo Gốc")
        System.out.println("In Báo Cáo");
        originalReport.printReport();
        clone1.printReport();
        clone2.printReport();
    }
}