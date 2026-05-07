// Interface chung cho các loại Export
interface Export{
    void doExport();
}

// Các sản phẩm cụ thể
class PdfExport implements Export {
    public void doExport() { System.out.println("Đang xuất file PDF..."); }
}
class ExcelExport implements Export {
    public void doExport() { System.out.println("Đang xuất file Excel..."); }
}

// Lớp Factory trừu tượng
abstract class ExportFactory {
    protected abstract Export createExport();

    public void executeExport() {
        Export export = createExport();
        export.doExport();
    }
}

// Các Factory con quyết định tạo loại nào
class PdfFactory extends ExportFactory {
    @Override
    protected Export createExport() { return new PdfExport(); }
}
class ExcelFactory extends ExportFactory {
    @Override
    protected Export createExport() { return new ExcelExport(); }
}