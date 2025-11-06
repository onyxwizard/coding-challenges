package PrefixSum.Basic_Prefix_Sum.PrefixTotalSumPattern.Easy.LeftRightSumDiff;

public class LeftRightSumDiff {
    /**
     * Calculates the absolute difference between left and right sums for each index.
     *
     * For each index i:
     * - leftSum[i] = sum of elements from 0 to i-1
     * - rightSum[i] = sum of elements from i+1 to n-1
     * - answer[i] = |leftSum[i] - rightSum[i]|
     *
     * Approach:
     * 1. Calculate total sum of array
     * 2. Iterate through array, maintaining running left sum
     * 3. For each index i: rightSum = totalSum - leftSum - nums[i]
     * 4. Compute absolute difference and store in result
     *
     * Time Complexity: O(n) - single pass
     * Space Complexity: O(1) extra space (excluding output array)
     *
     * @param nums input array of integers
     * @return array containing absolute differences of left and right sums
     */
    public static int[] leftRightDifference(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] answer = new int[n];

        // Calculate total sum of array
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;

        for (int i = 0; i < n; i++) {
            // Right sum = total sum - left sum - current element
            int rightSum = totalSum - leftSum - nums[i];

            // Calculate absolute difference
            answer[i] = Math.abs(leftSum - rightSum);

            // Update left sum for next iteration
            leftSum += nums[i];
        }

        return answer;
    }

    // ==================== Comprehensive Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Left and Right Sum Differences ===\n");

        // Example 1: Standard case
        int[] test1 = {10, 4, 8, 3};
        System.out.println("Test 1: nums = " + java.util.Arrays.toString(test1));
        int[] result1 = leftRightDifference(test1);
        System.out.println("Output: " + java.util.Arrays.toString(result1));
        // Expected: [15, 1, 11, 22]
        // leftSum = [0, 10, 14, 22]
        // rightSum = [15, 11, 3, 0]
        // diff = [|0-15|, |10-11|, |14-3|, |22-0|] = [15, 1, 11, 22]

        // Example 2: Single element
        int[] test2 = {1};
        System.out.println("\nTest 2: nums = " + java.util.Arrays.toString(test2) + " (single element)");
        int[] result2 = leftRightDifference(test2);
        System.out.println("Output: " + java.util.Arrays.toString(result2));
        // Expected: [0]
        // leftSum = [0], rightSum = [0], diff = [0]

        // Edge Case 1: Two elements
        int[] test3 = {5, 3};
        System.out.println("\nTest 3: nums = " + java.util.Arrays.toString(test3) + " (two elements)");
        int[] result3 = leftRightDifference(test3);
        System.out.println("Output: " + java.util.Arrays.toString(result3));
        // Expected: [3, 5]
        // leftSum = [0, 5], rightSum = [3, 0], diff = [3, 5]

        // Edge Case 2: All same elements
        int[] test4 = {2, 2, 2, 2};
        System.out.println("\nTest 4: nums = " + java.util.Arrays.toString(test4) + " (all same)");
        int[] result4 = leftRightDifference(test4);
        System.out.println("Output: " + java.util.Arrays.toString(result4));
        // Expected: [6, 2, 2, 6]
        // leftSum = [0, 2, 4, 6]
        // rightSum = [6, 4, 2, 0]
        // diff = [6, 2, 2, 6]

        // Edge Case 3: Increasing sequence
        int[] test5 = {1, 2, 3, 4, 5};
        System.out.println("\nTest 5: nums = " + java.util.Arrays.toString(test5) + " (increasing)");
        int[] result5 = leftRightDifference(test5);
        System.out.println("Output: " + java.util.Arrays.toString(result5));
        // Expected: [14, 11, 6, 1, 10]
        // leftSum = [0, 1, 3, 6, 10]
        // rightSum = [14, 12, 9, 5, 0]
        // diff = [14, 11, 6, 1, 10]

        // Edge Case 4: Large numbers
        int[] test6 = {100000, 50000, 25000};
        System.out.println("\nTest 6: nums = " + java.util.Arrays.toString(test6) + " (large numbers)");
        int[] result6 = leftRightDifference(test6);
        System.out.println("Output: " + java.util.Arrays.toString(result6));
        // Expected: [75000, 75000, 150000]
        // leftSum = [0, 100000, 150000]
        // rightSum = [75000, 25000, 0]
        // diff = [75000, 75000, 150000]

        // Edge Case 5: Maximum constraint size
        int[] test7 = new int[1000];
        for (int i = 0; i < 1000; i++) {
            test7[i] = 1;
        }
        System.out.println("\nTest 7: nums = array of 1000 ones (max constraint)");
        int[] result7 = leftRightDifference(test7);
        System.out.println("Output first 5: " + java.util.Arrays.toString(java.util.Arrays.copyOf(result7, 5)));
        System.out.println("Output last 5: " + java.util.Arrays.toString(java.util.Arrays.copyOfRange(result7, 995, 1000)));
        // Expected: [999, 997, 995, ..., 995, 997, 999]
    }
}
