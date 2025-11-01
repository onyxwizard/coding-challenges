Given an array of integers arr[]  and a number k. Return the maximum sum of a subarray of size k.

Note: A subarray is a contiguous part of any given array.

Examples:

Input: arr[] = [100, 200, 300, 400], k = 2
Output: 700
Explanation: arr2 + arr3 = 700, which is maximum.

Input: arr[] = [1, 4, 2, 10, 23, 3, 1, 0, 20], k = 4
Output: 39
Explanation: arr1 + arr2 + arr3 + arr4 = 39, which is maximum.

Input: arr[] = [100, 200, 300, 400], k = 1
Output: 400
Explanation: arr3 = 400, which is maximum.

Constraints:
1 ≤ arr.size() ≤ 106
1 ≤ arr[i] ≤ 106
1 ≤ k ≤ arr.size()
Expected Complexities
Time Complexity: O(n)
Auxiliary Space: O(1)

```java
package SlidingWindow.FixedSize.Easy.MaxSumSubarray;

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

```