public class Solution {
    public boolean isPalindrone(int n) {
        int res = 0;
        while (n > res) {
            res = res*10 + n % 10;
            n /= 10;
        }
        return n == res || n == res / 10;
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.isPalindrone(121));
    }
}