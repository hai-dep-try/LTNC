public class Solution {
    public int secondLargest(int[] arr) {
        int max = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        for (int x : arr) {
            if (x > max ) {
                second = max;
                max = x;
            } else if (x > second && x < max) {
                second = x;
            }
        }
        return (second == Integer.MIN_VALUE) ? -1: second;
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {1, 3, 4, 5, 2};
        System.out.println(sol.secondLargest(arr));
    }
}