package PrefixSum.Prefix_With_HashMap.Medium.MaximumSizeSubarraySumEqualK;

import java.util.*;
public class MaximumSizeSubarraySumEqualK {
    /**
     * Finds the maximum length of a contiguous subarray with sum exactly equal to k.
     *
     * Approach: Prefix Sum + HashMap
     *
     * Key Insights:
     * 1. For subarray [i, j] to have sum k: prefix[j] - prefix[i-1] = k
     * 2. Store first occurrence of each prefix sum to maximize length
     * 3. Use HashMap to track earliest index for each prefix sum
     *
     * Time Complexity: O(n) - single pass
     * Space Complexity: O(n) - HashMap may store up to n+1 entries
     *
     * @param nums input array of integers
     * @param k target sum
     * @return maximum length of subarray with sum exactly k, or 0 if none exists
     */
    public static int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> prefixIndex = new HashMap<>();
        prefixIndex.put(0, -1); // prefix sum 0 at index -1 (before array starts)

        int prefixSum = 0;
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];

            // Check if (prefixSum - k) has been seen before
            int target = prefixSum - k;
            if (prefixIndex.containsKey(target)) {
                int length = i - prefixIndex.get(target);
                maxLength = Math.max(maxLength, length);
            }

            // Store first occurrence of this prefix sum (to maximize future lengths)
            prefixIndex.putIfAbsent(prefixSum, i);
        }

        return maxLength;
    }

    // ==================== Comprehensive Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Maximum Length Subarray with Sum Equals K ===\n");

        // Example 1: Standard case with negatives
        int[] test1 = {1, -1, 5, -2, 3};
        int k1 = 3;
        System.out.println("Test 1: nums = " + java.util.Arrays.toString(test1) + ", k = " + k1);
        System.out.println("Output: " + maxSubArrayLen(test1, k1)); // Expected: 4 ([1,-1,5,-2])

        // Example 2: No valid subarray
        int[] test2 = {2, 3, 4};
        int k2 = 10;
        System.out.println("\nTest 2: nums = " + java.util.Arrays.toString(test2) + ", k = " + k2);
        System.out.println("Output: " + maxSubArrayLen(test2, k2)); // Expected: 0

        // Edge Case 1: Single element match
        int[] test3 = {5};
        int k3 = 5;
        System.out.println("\nTest 3: nums = " + java.util.Arrays.toString(test3) + ", k = " + k3);
        System.out.println("Output: " + maxSubArrayLen(test3, k3)); // Expected: 1

        // Edge Case 2: Single element no match
        int[] test4 = {5};
        int k4 = 3;
        System.out.println("\nTest 4: nums = " + java.util.Arrays.toString(test4) + ", k = " + k4);
        System.out.println("Output: " + maxSubArrayLen(test4, k4)); // Expected: 0

        // Edge Case 3: All zeros, k=0
        int[] test5 = {0, 0, 0};
        int k5 = 0;
        System.out.println("\nTest 5: nums = " + java.util.Arrays.toString(test5) + ", k = " + k5);
        System.out.println("Output: " + maxSubArrayLen(test5, k5)); // Expected: 3 (entire array)

        // Edge Case 4: Mixed signs, multiple valid subarrays
        int[] test6 = {1, -1, 0, 1, -1};
        int k6 = 0;
        System.out.println("\nTest 6: nums = " + java.util.Arrays.toString(test6) + ", k = " + k6);
        System.out.println("Output: " + maxSubArrayLen(test6, k6)); // Expected: 5 (entire array)

        // Edge Case 5: Negative k
        int[] test7 = {1, 2, -3, 4};
        int k7 = -3;
        System.out.println("\nTest 7: nums = " + java.util.Arrays.toString(test7) + ", k = " + k7);
        System.out.println("Output: " + maxSubArrayLen(test7, k7)); // Expected: 1 ([-3])

        // Edge Case 6: Large array with long valid subarray
        int[] test8 = new int[1000];
        for (int i = 0; i < 500; i++) test8[i] = 1;
        for (int i = 500; i < 1000; i++) test8[i] = -1;
        int k8 = 0;
        System.out.println("\nTest 8: nums = array of 500 ones + 500 -1s, k = " + k8);
        System.out.println("Output: " + maxSubArrayLen(test8, k8)); // Expected: 1000 (entire array)

        // Edge Case 7: k = 0 with positive numbers only
        int[] test9 = {1, 2, 3};
        int k9 = 0;
        System.out.println("\nTest 9: nums = " + java.util.Arrays.toString(test9) + ", k = " + k9);
        System.out.println("Output: " + maxSubArrayLen(test9, k9)); // Expected: 0

        // Edge Case 8: Multiple occurrences of same prefix sum
        int[] test10 = {1, 0, -1, 1, 0, -1};
        int k10 = 0;
        System.out.println("\nTest 10: nums = " + java.util.Arrays.toString(test10) + ", k = " + k10);
        System.out.println("Output: " + maxSubArrayLen(test10, k10)); // Expected: 6 (entire array)
    }
}
