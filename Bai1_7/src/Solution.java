public class Solution {
    public int reverse(int n) {
        int res = 0;
        while (n != 0) {
            res = res * 10 + n % 10;
            n /= 10;
        }
        return res;
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.reverse(41234125));
    }
}
