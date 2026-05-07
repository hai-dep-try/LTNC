import java.util.Arrays;

// 1. Interface mà hệ thống yêu cầu
interface Sorter {
    int[] sort(int[] arr);
}

// 2. Thư viện cũ (Adaptee)
class LegacySorter {
    public int[] quickSort(int[] arr) {
        System.out.println("Đang sort bằng thư viện cũ");
        Arrays.sort(arr); // Giả lập thuật toán sort
        return arr;
    }
}

// 3. Lớp Adapter
class SorterAdapter implements Sorter {
    private LegacySorter legacySorter;

    public SorterAdapter(LegacySorter legacySorter) {
        this.legacySorter = legacySorter;
    }

    @Override
    public int[] sort(int[] arr) {
        // Chuyển đổi yêu cầu từ sort() sang quickSort()
        return legacySorter.quickSort(arr);
    }
}

// 4. Test hàm main
public class AdapterMain {
    public static void main(String[] args) {
        int[] numbers = {5, 2, 9, 1, 5, 6};

        // Cắm 'ổ cắm cũ' vào 'bộ chuyển đổi'
        LegacySorter oldSorter = new LegacySorter();
        Sorter adapter = new SorterAdapter(oldSorter);

        // Client gọi hàm sort() chuẩn của interface
        int[] sortedNumbers = adapter.sort(numbers);
        System.out.println("Kết quả: " + Arrays.toString(sortedNumbers));
    }
}