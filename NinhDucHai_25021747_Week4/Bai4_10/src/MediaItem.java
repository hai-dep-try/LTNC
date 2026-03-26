// 1. Abstract Class
abstract class MediaItem {
    protected String id;
    protected String name;

    public MediaItem(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Phương thức trừu tượng thể hiện tính Đa hình
    public abstract void displayInfo();
}
