
public class Solution {
    public int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);

        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.gcd(1234234, 12341234));
    }
}
