import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class BookStore {
    private final Map<String, Integer> stock = new HashMap<>();
    // 2. Dùng ReentrantReadWriteLock
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public int getStock(String title) {
        // readLock cho getStock
        readLock.lock();
        try {
            int qty = stock.getOrDefault(title, 0);
            System.out.println(
                    Thread.currentThread().getName() + " [DOC] Dang doc sach: '" + title + "' - So luong: " + qty);
            Thread.sleep(100); // Giả lập kẹt một chút khi đọc
            return qty;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return 0;
        } finally {
            readLock.unlock();
        }
    }

    public void addBook(String title, int qty) {
        // writeLock cho addBook
        writeLock.lock();
        try {
            System.out.println(
                    Thread.currentThread().getName() + " [GHI] Bat dau nhap kho: '" + title + "' (+" + qty + ")");
            int currentQty = stock.getOrDefault(title, 0);
            stock.put(title, currentQty + qty);
            Thread.sleep(200); // Giả lập thời gian nhập kho
            System.out.println(
                    Thread.currentThread().getName() + " [GHI] Hoan tat nhap kho. Tong hien co: " + stock.get(title));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            writeLock.unlock();
        }
    }

    public void borrow(String title, int qty) {
        // writeLock cho borrow
        writeLock.lock();
        try {
            System.out
                    .println(Thread.currentThread().getName() + " [GHI] Xin muon sach: '" + title + "' (-" + qty + ")");
            int currentQty = stock.getOrDefault(title, 0);
            if (currentQty >= qty) {
                stock.put(title, currentQty - qty);
                System.out.println(Thread.currentThread().getName()
                        + " [GHI] Muon thanh cong. Chut nua ban di. Con lai: " + stock.get(title));
            } else {
                System.out.println(Thread.currentThread().getName() + " [GHI] Muon that bai! Khong du sach '" + title
                        + "' (Con chua toi " + qty + " cuon).");
            }
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            writeLock.unlock();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BookStore store = new BookStore();

        // Khoi tao mot vai sach co san
        System.out.println("KHOI TAO DU LIEU");
        store.addBook("Design Patterns", 5);
        store.addBook("Clean Code", 10);

        // 3 luồng đọc (in số lượng sách)
        for (int i = 1; i <= 3; i++) {
            Thread reader = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    store.getStock("Design Patterns");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ignored) {
                    }
                }
            }, "ReaderThread-" + i);
            reader.start();
        }

        // 2 luồng ghi (mượn/nhập sách)
        Thread writer1 = new Thread(() -> {
            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {
            } // Đợi 1 lúc rồi ghi
            store.borrow("Design Patterns", 3);
        }, "WriterThread-1");

        Thread writer2 = new Thread(() -> {
            try {
                Thread.sleep(150);
            } catch (InterruptedException ignored) {
            }
            store.addBook("Design Patterns", 10);
        }, "WriterThread-2");

        writer1.start();
        writer2.start();
    }
}
