import java.util.ArrayList;
import java.util.List;

// 1. Component (Lớp cha chung)
abstract class FileSystemItem {
    protected String name;
    protected Folder parent; // Lưu tham chiếu đến thư mục cha để tính đường dẫn

    public FileSystemItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setParent(Folder parent) {
        this.parent = parent;
    }

    // Hàm đệ quy tự động tính toán đường dẫn tuyệt đối (VD: /root/docs/a.txt)
    public String getPath() {
        if (parent == null) {
            return "/" + name;
        }
        return parent.getPath() + "/" + name;
    }

    // Phương thức in mà mọi lớp con phải cài đặt
    public abstract void print(String indent);
}

// 2. Leaf 1: File thông thường
class FileItem extends FileSystemItem {
    private int size; // Đơn vị: KB

    public FileItem(String name, int size) {
        super(name);
        this.size = size;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "File: " + name + " (" + size + "KB)");
    }
}

// 3. Leaf 2: Đường dẫn tắt (Shortcut)
class Shortcut extends FileSystemItem {
    private FileSystemItem target;

    public Shortcut(String name, FileSystemItem target) {
        super(name);
        this.target = target;
    }

    @Override
    public void print(String indent) {
        // Tận dụng hàm getPath() của đối tượng target để in ra đường dẫn chuẩn
        System.out.println(indent + "Shortcut: " + name + " -> " + target.getPath());
    }
}

// 4. Composite: Thư mục (Chứa nhiều FileSystemItem khác)
class Folder extends FileSystemItem {
    private List<FileSystemItem> children;

    public Folder(String name) {
        super(name);
        this.children = new ArrayList<>();
    }

    // Khi thêm một phần tử vào Folder, tự động gán Folder này làm cha của phần tử đó
    public void add(FileSystemItem item) {
        item.setParent(this);
        children.add(item);
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "Folder: " + name);
        // Lặp qua tất cả con và tăng khoảng trắng (indent) lên để tạo cấu trúc cây
        for (FileSystemItem child : children) {
            child.print(indent + "    ");
        }
    }
}

// 5. Main Test theo đúng yêu cầu đề bài
public class Main {
    public static void main(String[] args) {
        // Tạo cấu trúc thư mục
        Folder root = new Folder("root");
        Folder docs = new Folder("docs");

        FileItem fileA = new FileItem("a.txt", 12);
        FileItem fileB = new FileItem("b.txt", 8);
        FileItem readme = new FileItem("readme.md", 4);

        // Shortcut trỏ tới file a.txt
        Shortcut shortcutA = new Shortcut("a-shortcut", fileA);

        // Ráp nối các thành phần lại với nhau
        docs.add(fileA);
        docs.add(fileB);
        docs.add(shortcutA);

        root.add(docs);
        root.add(readme);

        // Gọi lệnh in từ root với khoảng trắng ban đầu là rỗng
        root.print("");
    }
}