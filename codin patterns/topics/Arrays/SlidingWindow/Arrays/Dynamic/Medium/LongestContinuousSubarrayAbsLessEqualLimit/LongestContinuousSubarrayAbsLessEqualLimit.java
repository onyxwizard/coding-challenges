package SlidingWindow.Arrays.Dynamic.Medium.LongestContinuousSubarrayAbsLessEqualLimit;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestContinuousSubarrayAbsLessEqualLimit {

    /**
     * Finds the length of the longest contiguous subarray such that the absolute difference
     * between any two elements is <= limit.
     *
     * Key Insight:
     * In any subarray, max absolute difference = max - min.
     * So we maintain a sliding window where: max(window) - min(window) <= limit.
     *
     * Approach:
     * - Use two deques:
     *     * maxDeque: stores indices in decreasing order of values (front = max)
     *     * minDeque: stores indices in increasing order of values (front = min)
     * - Expand window with 'right'
     * - If window becomes invalid (max - min > limit), shrink from 'left'
     * - Update answer with valid window size
     *
     * Time Complexity: O(n) — each element added/removed once
     * Space Complexity: O(n) — for deques
     *
     * @param nums input array of integers
     * @param limit maximum allowed absolute difference
     * @return length of longest valid subarray
     */
    public static int longestSubarray(int[] nums, int limit) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Deque<Integer> maxDeque = new ArrayDeque<>(); // decreasing: front = index of max
        Deque<Integer> minDeque = new ArrayDeque<>(); // increasing: front = index of min

        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < nums.length; right++) {
            // Maintain maxDeque in decreasing order (largest at front)
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[right]) {
                maxDeque.pollLast();
            }
            maxDeque.offerLast(right);

            // Maintain minDeque in increasing order (smallest at front)
            while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[right]) {
                minDeque.pollLast();
            }
            minDeque.offerLast(right);

            // Shrink window from the left while it's invalid
            // Condition: max - min > limit
            while (nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > limit) {
                // If the leftmost index is the current max, remove it from maxDeque
                if (maxDeque.peekFirst() == left) {
                    maxDeque.pollFirst();
                }
                // If the leftmost index is the current min, remove it from minDeque
                if (minDeque.peekFirst() == left) {
                    minDeque.pollFirst();
                }
                // Move left pointer to shrink window
                left++;
            }

            // Now the window [left, right] is valid
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    // ==================== Main Method with Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Longest Subarray with Absolute Diff <= Limit ===\n");

        // Example 1: Expected output = 2
        int[] test1 = {8, 2, 4, 7};
        int limit1 = 4;
        System.out.println("Test 1: nums = " + java.util.Arrays.toString(test1) + ", limit = " + limit1);
        System.out.println("Output: " + longestSubarray(test1, limit1)); // 2

        // Example 2: Expected output = 4
        int[] test2 = {10, 1, 2, 4, 7, 2};
        int limit2 = 5;
        System.out.println("\nTest 2: nums = " + java.util.Arrays.toString(test2) + ", limit = " + limit2);
        System.out.println("Output: " + longestSubarray(test2, limit2)); // 4

        // Example 3: Expected output = 3
        int[] test3 = {4, 2, 2, 2, 4, 4, 2, 2};
        int limit3 = 0;
        System.out.println("\nTest 3: nums = " + java.util.Arrays.toString(test3) + ", limit = " + limit3);
        System.out.println("Output: " + longestSubarray(test3, limit3)); // 3

        // Edge: single element
        int[] test4 = {5};
        int limit4 = 0;
        System.out.println("\nTest 4: nums = " + java.util.Arrays.toString(test4) + ", limit = " + limit4);
        System.out.println("Output: " + longestSubarray(test4, limit4)); // 1

        // Edge: all same elements
        int[] test5 = {3, 3, 3, 3};
        int limit5 = 0;
        System.out.println("\nTest 5: nums = " + java.util.Arrays.toString(test5) + ", limit = " + limit5);
        System.out.println("Output: " + longestSubarray(test5, limit5)); // 4

        // Edge: strictly increasing, large limit
        int[] test6 = {1, 2, 3, 4, 5};
        int limit6 = 10;
        System.out.println("\nTest 6: nums = " + java.util.Arrays.toString(test6) + ", limit = " + limit6);
        System.out.println("Output: " + longestSubarray(test6, limit6)); // 5
    }
}