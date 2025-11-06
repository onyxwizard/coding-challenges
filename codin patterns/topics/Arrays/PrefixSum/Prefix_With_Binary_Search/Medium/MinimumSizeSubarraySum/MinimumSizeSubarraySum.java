package PrefixSum.Prefix_With_Binary_Search.Medium.MinimumSizeSubarraySum;

import java.util.*;
public class MinimumSizeSubarraySum {
    /**
     * Finds the minimal length of a contiguous subarray with sum >= target.
     *
     * Approach: Prefix Sum + Binary Search
     *
     * Key Insights:
     * 1. Since all numbers are positive, prefix sum is strictly increasing
     * 2. For each ending index j, find smallest starting index i such that:
     *    prefix[j+1] - prefix[i] >= target → prefix[i] <= prefix[j+1] - target
     * 3. Use binary search to find the rightmost valid starting position
     *
     * Time Complexity: O(n log n) - n binary searches, each O(log n)
     * Space Complexity: O(n) - for prefix sum array
     *
     * @param target the minimum sum required
     * @param nums array of positive integers
     * @return minimal length of subarray with sum >= target, or 0 if none exists
     */
    public static int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        // Build prefix sum array: prefix[0] = 0, prefix[i] = sum of nums[0...i-1]
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int minLength = Integer.MAX_VALUE;

        // For each ending position j (0 to n-1)
        for (int j = 0; j < n; j++) {
            // We need prefix[j+1] - prefix[i] >= target
            // → prefix[i] <= prefix[j+1] - target
            int required = prefix[j + 1] - target;

            // If even the full array from 0 to j doesn't meet target, skip
            if (required < 0) {
                continue;
            }

            // Binary search in prefix[0...j] for the rightmost index i where prefix[i] <= required
            int left = 0, right = j;
            int bestStart = -1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (prefix[mid] <= required) {
                    bestStart = mid; // This is a valid starting position
                    left = mid + 1;  // Try to find a larger index (closer to j)
                } else {
                    right = mid - 1; // prefix[mid] is too large, go left
                }
            }

            // If we found a valid starting position
            if (bestStart != -1) {
                minLength = Math.min(minLength, j - bestStart + 1);
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    // ==================== Alternative: Using Built-in Binary Search ====================
    /**
     * Alternative implementation using Arrays.binarySearch
     * Note: Arrays.binarySearch returns the index if found, or (-(insertion point) - 1) if not found
     * We need the largest index <= required, so we use the insertion point logic
     */
    public static int minSubArrayLenAlternative(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int minLength = Integer.MAX_VALUE;

        for (int j = 0; j < n; j++) {
            int required = prefix[j + 1] - target;
            if (required < 0) continue;

            // Find the rightmost position where prefix[i] <= required
            // Arrays.binarySearch returns the index if exact match, or insertion point
            int pos = Arrays.binarySearch(prefix, 0, j + 1, required);

            if (pos >= 0) {
                // Exact match found, but there might be duplicates, so find rightmost
                while (pos + 1 <= j && prefix[pos + 1] == required) {
                    pos++;
                }
                minLength = Math.min(minLength, j - pos + 1);
            } else {
                // No exact match, insertion point = -(pos) - 1
                int insertionPoint = -(pos + 1);
                if (insertionPoint > 0) {
                    // The element before insertion point is <= required
                    minLength = Math.min(minLength, j - (insertionPoint - 1) + 1);
                }
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    // ==================== Comprehensive Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Minimum Size Subarray Sum (Binary Search Approach) ===\n");

        // Example 1: Standard case
        int[] test1 = {2, 3, 1, 2, 4, 3};
        int target1 = 7;
        System.out.println("Test 1: nums = " + java.util.Arrays.toString(test1) + ", target = " + target1);
        System.out.println("Output: " + minSubArrayLen(target1, test1)); // Expected: 2

        // Example 2: Single element
        int[] test2 = {1, 4, 4};
        int target2 = 4;
        System.out.println("\nTest 2: nums = " + java.util.Arrays.toString(test2) + ", target = " + target2);
        System.out.println("Output: " + minSubArrayLen(target2, test2)); // Expected: 1

        // Example 3: No valid subarray
        int[] test3 = {1, 1, 1, 1, 1, 1, 1, 1};
        int target3 = 11;
        System.out.println("\nTest 3: nums = " + java.util.Arrays.toString(test3) + ", target = " + target3);
        System.out.println("Output: " + minSubArrayLen(target3, test3)); // Expected: 0

        // Edge Case 1: Exact match with first element
        int[] test4 = {10, 2, 3};
        int target4 = 10;
        System.out.println("\nTest 4: nums = " + java.util.Arrays.toString(test4) + ", target = " + target4);
        System.out.println("Output: " + minSubArrayLen(target4, test4)); // Expected: 1

        // Edge Case 2: Entire array needed
        int[] test5 = {1, 2, 3, 4};
        int target5 = 10;
        System.out.println("\nTest 5: nums = " + java.util.Arrays.toString(test5) + ", target = " + target5);
        System.out.println("Output: " + minSubArrayLen(target5, test5)); // Expected: 4

        // Edge Case 3: Large target
        int[] test6 = {1, 2, 3};
        int target6 = 100;
        System.out.println("\nTest 6: nums = " + java.util.Arrays.toString(test6) + ", target = " + target6);
        System.out.println("Output: " + minSubArrayLen(target6, test6)); // Expected: 0

        // Edge Case 4: All elements same
        int[] test7 = {3, 3, 3, 3, 3};
        int target7 = 7;
        System.out.println("\nTest 7: nums = " + java.util.Arrays.toString(test7) + ", target = " + target7);
        System.out.println("Output: " + minSubArrayLen(target7, test7)); // Expected: 3

        // Performance Test: Large array
        int[] test8 = new int[100000];
        Arrays.fill(test8, 1);
        int target8 = 50000;
        long startTime = System.currentTimeMillis();
        int result8 = minSubArrayLen(target8, test8);
        long endTime = System.currentTimeMillis();
        System.out.println("\nTest 8: Large array (100,000 elements, all 1s), target = " + target8);
        System.out.println("Output: " + result8 + " (Time: " + (endTime - startTime) + " ms)");
        // Expected: 50000
    }
}
