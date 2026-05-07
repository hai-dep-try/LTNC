class Worker implements Runnable {
    // 3. Dung tu khoa volatile de dam bao moi luong doc gia tri moi nhat tu RAM
    // 4. Vi sao can volatile?
    // Neu khong co volatile, luong Worker co the doc gia tri cu tu CPU Cache
    // nen khong thay duoc khi Main dat running = false -> vong lap chay vo tan.
    // Co volatile thi buoc moi luong phai doc/ghi thang tu RAM chinh.
    private volatile boolean running = true;

    // Phương thức stop để đặt running = false
    public void stop() {
        running = false;
        System.out.println("\n[Main] Da phat tin hieu dung (stop).");
    }

    @Override
    public void run() {
        System.out.println("[Worker] Bat dau lam viec...");
        // Lặp trong khi running = true
        while (running) {
            System.out.println("Working...");

            // Ngủ 100ms để màn hình console không bị trôi quá nhanh trong 1 giây
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("[Worker] Da nhan lenh va cham dut cong viec an toan!");
    }
}

public class Main {
    public static void main(String[] args) {
        // 2. Trong main
        // Tạo Worker
        Worker worker = new Worker();

        // Chạy bằng Thread
        Thread thread = new Thread(worker);
        thread.start();

        // Cho luồng chạy khoảng 1 giây
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

        // Gọi stop() để dừng luồng
        worker.stop();

        // Đợi luồng kết thúc bằng join()
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("[Main] Ket thuc chuong trinh.");
    }
}
