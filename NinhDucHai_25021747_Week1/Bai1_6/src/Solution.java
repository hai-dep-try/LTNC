public class Solution {
    public boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
    public static void main(String[] args) {
    Solution sol = new Solution();
    System.out.println(sol.isPrime(54));
    System.out.println(sol.isPrime(-5));
    System.out.println(sol.isPrime(9973));
    }
}
