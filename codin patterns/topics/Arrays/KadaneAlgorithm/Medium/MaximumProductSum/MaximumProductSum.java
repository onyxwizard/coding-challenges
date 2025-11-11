package KadaneAlgorithm.Medium.MaximumProductSum;

public class MaximumProductSum {
    /**
     * Finds the maximum product of a contiguous subarray.
     *
     * Approach: Dynamic Programming with Max/Min Tracking
     *
     * Key Insights:
     * 1. Negative numbers can turn minimum into maximum when multiplied
     * 2. At each position, track both max and min products ending here
     * 3. New max/min can come from: nums[i], maxSoFar * nums[i], minSoFar * nums[i]
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param nums input array of integers
     * @return maximum product of contiguous subarray
     */
    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Initialize with first element
        int maxProduct = nums[0];
        int minProduct = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];

            // Store current maxProduct before updating (needed for minProduct calculation)
            int tempMax = maxProduct;

            // Calculate new max and min products ending at current position
            maxProduct = Math.max(current, Math.max(maxProduct * current, minProduct * current));
            minProduct = Math.min(current, Math.min(tempMax * current, minProduct * current));

            // Update global maximum
            result = Math.max(result, maxProduct);
        }

        return result;
    }

    // ==================== Comprehensive Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Maximum Product Subarray ===\n");

        // Example 1: Standard case with negative
        int[] test1 = {2, 3, -2, 4};
        System.out.println("Test 1: nums = " + java.util.Arrays.toString(test1));
        System.out.println("DP Approach: " + maxProduct(test1)); // Expected: 6 ([2,3])


        // Example 2: Zero in middle
        int[] test2 = {-2, 0, -1};
        System.out.println("\nTest 2: nums = " + java.util.Arrays.toString(test2) + " (contains zero)");
        System.out.println("DP Approach: " + maxProduct(test2)); // Expected: 0

        // Edge Case 1: Single element
        int[] test3 = {5};
        System.out.println("\nTest 3: nums = " + java.util.Arrays.toString(test3) + " (single element)");
        System.out.println("DP Approach: " + maxProduct(test3)); // Expected: 5

        // Edge Case 2: All negative
        int[] test4 = {-2, -3, -4};
        System.out.println("\nTest 4: nums = " + java.util.Arrays.toString(test4) + " (all negative)");
        System.out.println("DP Approach: " + maxProduct(test4)); // Expected: 12 ([-3,-4])

        // Edge Case 3: Even number of negatives
        int[] test5 = {-2, -3, 4, -5, -6};
        System.out.println("\nTest 5: nums = " + java.util.Arrays.toString(test5) + " (even negatives)");
        System.out.println("DP Approach: " + maxProduct(test5)); // Expected: 360 ([-2,-3,4,-5,-6])

        // Edge Case 4: Odd number of negatives
        int[] test6 = {-2, 3, -4, 5, -6};
        System.out.println("\nTest 6: nums = " + java.util.Arrays.toString(test6) + " (odd negatives)");
        System.out.println("DP Approach: " + maxProduct(test6)); // Expected: 720 (all except last or first)

        // Edge Case 5: Zeros and negatives
        int[] test7 = {0, -2, 0, -3, 0};
        System.out.println("\nTest 7: nums = " + java.util.Arrays.toString(test7) + " (zeros and negatives)");
        System.out.println("DP Approach: " + maxProduct(test7)); // Expected: 0

        // Edge Case 6: Alternating positive/negative
        int[] test8 = {-1, 2, -3, 4, -5};
        System.out.println("\nTest 8: nums = " + java.util.Arrays.toString(test8) + " (alternating)");
        System.out.println("DP Approach: " + maxProduct(test8)); // Expected: 120 (all elements)

        // Edge Case 7: Maximum constraint values
        int[] test9 = {10, -10, 10, -10, 10};
        System.out.println("\nTest 9: nums = " + java.util.Arrays.toString(test9) + " (max/min values)");
        System.out.println("DP Approach: " + maxProduct(test9)); // Expected: 100000 (all elements)

        // Edge Case 8: Product that fits in 32-bit (constraint guarantee)
        int[] test10 = {-2, -3, -4, -5};
        System.out.println("\nTest 10: nums = " + java.util.Arrays.toString(test10) + " (large product)");
        System.out.println("DP Approach: " + maxProduct(test10)); // Expected: 120 ([-2,-3,-4,-5])

        // Performance Comparison
        System.out.println("\n=== Performance Comparison (n=1000) ===");
        int[] performanceTest = new int[1000];
        for (int i = 0; i < 1000; i++) {
            performanceTest[i] = (int) (Math.random() * 21) - 10; // -10 to 10
        }

        // DP Approach
        long start1 = System.currentTimeMillis();
        int result1 = maxProduct(performanceTest);
        long end1 = System.currentTimeMillis();



        System.out.println("DP Approach (n=1000): " + (end1 - start1) + " ms, Result: " + result1);
    }
}
