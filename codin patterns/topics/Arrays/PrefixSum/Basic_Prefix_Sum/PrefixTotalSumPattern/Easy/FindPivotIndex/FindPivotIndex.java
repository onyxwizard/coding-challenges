package PrefixSum.Basic_Prefix_Sum.PrefixTotalSumPattern.Easy.FindPivotIndex;

public class FindPivotIndex {
    /**
     * Finds the leftmost pivot index in the array.
     *
     * Pivot index definition: sum of elements strictly to the left
     * equals sum of elements strictly to the right.
     *
     * Approach:
     * 1. Calculate total sum of array
     * 2. Iterate through array, maintaining left sum
     * 3. For each index i: rightSum = totalSum - leftSum - nums[i]
     * 4. If leftSum == rightSum, return i
     *
     * Time Complexity: O(n) - two passes through array
     * Space Complexity: O(1) - constant extra space
     *
     * @param nums input array of integers
     * @return leftmost pivot index, or -1 if none exists
     */
    public static int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // Calculate total sum of array
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;

        // Check each index as potential pivot
        for (int i = 0; i < nums.length; i++) {
            // Right sum = total sum - left sum - current element
            int rightSum = totalSum - leftSum - nums[i];

            if (leftSum == rightSum) {
                return i;
            }

            // Update left sum for next iteration
            leftSum += nums[i];
        }

        return -1;
    }

    // ==================== Comprehensive Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Find Pivot Index ===\n");

        // Example 1: Standard case
        int[] test1 = {1, 7, 3, 6, 5, 6};
        System.out.println("Test 1: nums = " + java.util.Arrays.toString(test1));
        System.out.println("Output: " + pivotIndex(test1)); // Expected: 3

        // Example 2: No pivot index
        int[] test2 = {1, 2, 3};
        System.out.println("\nTest 2: nums = " + java.util.Arrays.toString(test2));
        System.out.println("Output: " + pivotIndex(test2)); // Expected: -1

        // Example 3: Pivot at left edge
        int[] test3 = {2, 1, -1};
        System.out.println("\nTest 3: nums = " + java.util.Arrays.toString(test3));
        System.out.println("Output: " + pivotIndex(test3)); // Expected: 0

        // Edge Case 1: Single element
        int[] test4 = {1};
        System.out.println("\nTest 4: nums = " + java.util.Arrays.toString(test4) + " (single element)");
        System.out.println("Output: " + pivotIndex(test4)); // Expected: 0 (left=0, right=0)

        // Edge Case 2: All zeros
        int[] test5 = {0, 0, 0, 0};
        System.out.println("\nTest 5: nums = " + java.util.Arrays.toString(test5) + " (all zeros)");
        System.out.println("Output: " + pivotIndex(test5)); // Expected: 0 (leftmost)

        // Edge Case 3: Pivot at right edge
        int[] test6 = {-1, 1, 0};
        System.out.println("\nTest 6: nums = " + java.util.Arrays.toString(test6) + " (pivot at right edge)");
        System.out.println("Output: " + pivotIndex(test6)); // Expected: 2 (left=-1+1=0, right=0)

        // Edge Case 4: Negative numbers
        int[] test7 = {-1, -1, 0, 1, 1};
        System.out.println("\nTest 7: nums = " + java.util.Arrays.toString(test7) + " (negative numbers)");
        System.out.println("Output: " + pivotIndex(test7)); // Expected: 2

        // Edge Case 5: Large array with pivot in middle
        int[] test8 = {1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1};
        System.out.println("\nTest 8: nums = " + java.util.Arrays.toString(test8));
        System.out.println("Output: " + pivotIndex(test8)); // Expected: 5

        // Edge Case 6: No pivot in large array
        int[] test9 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("\nTest 9: nums = " + java.util.Arrays.toString(test9) + " (no pivot)");
        System.out.println("Output: " + pivotIndex(test9)); // Expected: -1

        // Edge Case 7: Two potential pivots (return leftmost)
        int[] test10 = {2,3,7,2,2,1};
        System.out.println("\nTest 10: nums = " + java.util.Arrays.toString(test10) + " (multiple pivots)");
        System.out.println("Output: " + pivotIndex(test10)); // Expected: 0 (not 3)
    }
}
