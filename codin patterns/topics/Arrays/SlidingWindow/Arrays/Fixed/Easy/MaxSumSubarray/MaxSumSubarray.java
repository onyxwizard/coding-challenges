package SlidingWindow.Arrays.Fixed.Easy.MaxSumSubarray;

public class MaxSumSubarray {
    /**
     * Finds the maximum sum of a contiguous subarray of size k using sliding window technique.
     *
     * Approach:
     * 1. Calculate sum of first window (first k elements)
     * 2. Slide the window by one element at a time:
     *    - Subtract the leftmost element of previous window
     *    - Add the new rightmost element
     * 3. Track the maximum sum encountered
     *
     * Time Complexity: O(n) - single pass through the array
     * Space Complexity: O(1) - only using a few variables
     *
     * @param arr Input array of integers
     * @param k Size of subarray window
     * @return Maximum sum of any contiguous subarray of size k
     */
    public static int maxSubarraySum(int k, int[] arr) {
        // Calculate sum of first window
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        int maxSum = windowSum;

        // Slide window from start to end in array
        for (int i = k; i < arr.length; i++) {
            // Remove first element of previous window and add last element of current window
            windowSum = windowSum - arr[i - k] + arr[i];
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {100, 200, 300, 400};
        int k = 2;
        int res = maxSubarraySum(k,arr);
        System.out.println(res);
    }
}
