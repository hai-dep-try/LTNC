import java.util.ArrayList;
import java.util.List;

// Lớp Kho bãi sử dụng Generics với Bounded Type (<T extends Product>)
class Warehouse<T extends Product> {
    private List<T> items;

    public Warehouse() {
        this.items = new ArrayList<>();
    }

    // Nhập kho
    public void add(T item) {
        items.add(item);
    }

    // Xuất kho
    public void remove(T item) {
        items.remove(item);
    }

    // Kiểm kê
    public void displayInventory() {
        for (T item : items) {
            System.out.println(item.getInventoryInfo());
        }
    }
}
