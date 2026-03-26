import java.util.ArrayList;
import java.util.List;

// 4. Lớp Generic quản lý khu vực
class LibrarySection<T extends MediaItem> {
    private List<T> items = new ArrayList<>();

    public void addItem(T item) {
        items.add(item);
    }

    public void removeItem(T item) {
        items.remove(item);
    }

    public void displayItems() {
        for (T item : items) {
            item.displayInfo();
        }
    }
}
