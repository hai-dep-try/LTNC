public class Solution {

    public static long fibonacci(long n) {

        if (n < 0) {
            return -1;
        }

        long a = 0;
        long b = 1;
        long cur = 0;
        for (int i = 2; i <= n; i++) {
            if (a > Long.MAX_VALUE - b) {
                return Long.MAX_VALUE;
            }
            cur = a + b;
            a = b;
            b = cur;


        }
        return cur;
    }

    public static void main(String[] args) {
        long n = 100; // Bạn có thể thay đổi giá trị n ở đây
        long result = fibonacci(n);

        System.out.println(result);
    }
}