public class Solution {
    public int sumOfDigits(int n) {
        int res = 0;
        while (n != 0) {
            res += n % 10;
            n /= 10;
        }
        return res;
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.sumOfDigits(121));
    }
}