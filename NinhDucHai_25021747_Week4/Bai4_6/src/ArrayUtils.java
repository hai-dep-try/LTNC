// Class tiện ích chứa các hàm Generic
class ArrayUtils {

    // Hàm hoán đổi vị trí tổng quát
    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Hàm sắp xếp Bubble Sort tổng quát
    // Bắt buộc kiểu T phải có khả năng so sánh (extends Comparable<T>)
    public static <T extends Comparable<T>> void sort(T[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Sử dụng compareTo thay vì toán tử '>'
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    // Hàm hỗ trợ in mảng ra màn hình (để output giống yêu cầu)
    public static <T> void printArray(T[] array) {
        for (T item : array) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}
