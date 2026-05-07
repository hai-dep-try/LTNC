public class Main {
    public static void main(String[] args) {
        System.out.println("1. SINGLETON");
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        logger1.log("Test ghi log.");
        System.out.println("Hai logger giống nhau? " + (logger1 == logger2));

        System.out.println("\n2. FACTORY METHOD");
        ExportFactory pdfFactory = new PdfFactory();
        pdfFactory.executeExport(); // Client hoàn toàn không dùng 'new PdfExport()'

        System.out.println("\n3. ADAPTER");
        OldPlayer oldSystem = new OldPlayer();
        Player newPlayer = new PlayerAdapter(oldSystem);
        newPlayer.play("BaiHat.mp3");

        System.out.println("\n4. PROTOTYPE");
        Configuration originalConfig = new Configuration("Dark Mode", "VN");
        Configuration clonedConfig = originalConfig.clone();

        clonedConfig.setTheme("Light Mode"); // Chỉnh sửa bản sao

        System.out.print("Bản gốc: "); originalConfig.printConfig();
        System.out.print("Bản sao: "); clonedConfig.printConfig();
    }
}