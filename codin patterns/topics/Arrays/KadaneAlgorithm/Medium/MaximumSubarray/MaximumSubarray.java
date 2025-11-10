package KadaneAlgorithm.Medium.MaximumSubarray;

public class MaximumSubarray {
    /**
     * Finds the maximum sum of a contiguous subarray using Kadane's algorithm.
     *
     * Approach: Dynamic Programming (Kadane's Algorithm)
     *
     * Key Insights:
     * 1. At each position, decide whether to extend previous subarray or start new
     * 2. currentSum = max(nums[i], currentSum + nums[i])
     * 3. Track maximum currentSum seen so far
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param nums input array of integers
     * @return maximum sum of contiguous subarray
     */
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Either extend previous subarray or start new one
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            // Update global maximum
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    // ==================== Comprehensive Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Maximum Subarray ===\n");

        // Example 1: Classic case with negatives
        int[] test1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Test 1: nums = " + java.util.Arrays.toString(test1));
        System.out.println("Kadane's: " + maxSubArray(test1)); // Expected: 6 ([4,-1,2,1])


        // Example 2: Single element
        int[] test2 = {1};
        System.out.println("\nTest 2: nums = " + java.util.Arrays.toString(test2) + " (single element)");
        System.out.println("Kadane's: " + maxSubArray(test2)); // Expected: 1

        // Example 3: All positive
        int[] test3 = {5, 4, -1, 7, 8};
        System.out.println("\nTest 3: nums = " + java.util.Arrays.toString(test3) + " (mostly positive)");
        System.out.println("Kadane's: " + maxSubArray(test3)); // Expected: 23 (entire array)

        // Edge Case 1: All negative
        int[] test4 = {-5, -2, -8, -1};
        System.out.println("\nTest 4: nums = " + java.util.Arrays.toString(test4) + " (all negative)");
        System.out.println("Kadane's: " + maxSubArray(test4)); // Expected: -1 (single element)

        // Edge Case 2: Alternating positive/negative
        int[] test5 = {-1, 2, -3, 4, -5, 6};
        System.out.println("\nTest 5: nums = " + java.util.Arrays.toString(test5) + " (alternating)");
        System.out.println("Kadane's: " + maxSubArray(test5)); // Expected: 6 ([6])

        // Edge Case 3: Large array
        int[] test6 = new int[100000];
        for (int i = 0; i < 100000; i++) {
            test6[i] = (i % 2 == 0) ? 1 : -1;
        }
        System.out.println("\nTest 6: Large alternating array (100,000 elements)");
        long startTime = System.currentTimeMillis();
        int result6 = maxSubArray(test6);
        long endTime = System.currentTimeMillis();
        System.out.println("Kadane's Result: " + result6 + " (Time: " + (endTime - startTime) + " ms)");

        // Edge Case 4: Maximum constraint values
        int[] test7 = {10000, -10000, 10000, -10000, 10000};
        System.out.println("\nTest 7: nums = " + java.util.Arrays.toString(test7) + " (max/min values)");
        System.out.println("Kadane's: " + maxSubArray(test7)); // Expected: 10000

        // Performance Comparison
        System.out.println("\n=== Performance Comparison (n=10000) ===");
        int[] performanceTest = new int[10000];
        for (int i = 0; i < 10000; i++) {
            performanceTest[i] = (int) (Math.random() * 20000) - 10000;
        }

        // Kadane's Algorithm
        long start1 = System.currentTimeMillis();
        int result1 = maxSubArray(performanceTest);
        long end1 = System.currentTimeMillis();


        System.out.println("Kadane's (n=10000): " + (end1 - start1) + " ms, Result: " + result1);

    }

}
