package PrefixSum.Prefix_With_HashMap.Medium.ContiguousArray;

import java.util.HashMap;

public class ContiguousArray {
    public static int findMaxLength(int[] nums) {
        int count = 0, sum = 0;
        int prefixSum = 0;
        HashMap<Integer,Integer> prefixCount = new HashMap<>();
        prefixCount.put(0,-1);

        for(int i = 0; i< nums.length;i++){
            int elem = nums[i];
            nums[i] = elem == 1 ? 1 : -1;
        }

        for(int i = 0; i< nums.length;i++){
            prefixSum += nums[i];

            if (prefixCount.containsKey(prefixSum)) {
                // Same prefix sum seen before → subarray between has sum 0
                int length = i - prefixCount.get(prefixSum);
                count = Math.max(count, length);
            } else {
                // Store first occurrence of this prefix sum
                prefixCount.put(prefixSum, i);
            }
        }

        return count;
    }
    public static void main(String[] args) {
        System.out.println("=== Contiguous Array - Maximum Length with Equal 0s and 1s ===\n");

        // Example 1: Basic case
        int[] test1 = {0, 1};
        System.out.println("Test 1: nums = " + java.util.Arrays.toString(test1));
        System.out.println("Output: " + findMaxLength(test1)); // Expected: 2

        // Example 2: Multiple valid subarrays
        int[] test2 = {0, 1, 0};
        System.out.println("\nTest 2: nums = " + java.util.Arrays.toString(test2));
        System.out.println("Output: " + findMaxLength(test2)); // Expected: 2

        // Example 3: Longer valid subarray
        int[] test3 = {0, 1, 1, 1, 1, 1, 0, 0, 0};
        System.out.println("\nTest 3: nums = " + java.util.Arrays.toString(test3));
        System.out.println("Output: " + findMaxLength(test3)); // Expected: 6
        // Explanation: [1,1,1,0,0,0] → indices 2-7

        // Edge Case 1: All 1s
        int[] test4 = {1, 1, 1, 1};
        System.out.println("\nTest 4: nums = " + java.util.Arrays.toString(test4) + " (all 1s)");
        System.out.println("Output: " + findMaxLength(test4)); // Expected: 0

        // Edge Case 2: All 0s
        int[] test5 = {0, 0, 0, 0};
        System.out.println("\nTest 5: nums = " + java.util.Arrays.toString(test5) + " (all 0s)");
        System.out.println("Output: " + findMaxLength(test5)); // Expected: 0

        // Edge Case 3: Single element
        int[] test6 = {1};
        System.out.println("\nTest 6: nums = " + java.util.Arrays.toString(test6) + " (single element)");
        System.out.println("Output: " + findMaxLength(test6)); // Expected: 0

        // Edge Case 4: Alternating pattern
        int[] test7 = {0, 1, 0, 1, 0, 1};
        System.out.println("\nTest 7: nums = " + java.util.Arrays.toString(test7) + " (alternating)");
        System.out.println("Output: " + findMaxLength(test7)); // Expected: 6 (entire array)

        // Edge Case 5: Empty array (though constraints say n>=1)
        int[] test8 = {};
        System.out.println("\nTest 8: nums = [] (empty array)");
        System.out.println("Output: " + findMaxLength(test8)); // Expected: 0

        // Edge Case 6: Long valid subarray in middle
        int[] test9 = {1, 1, 0, 0, 1, 1, 0, 0, 1, 1};
        System.out.println("\nTest 9: nums = " + java.util.Arrays.toString(test9));
        System.out.println("Output: " + findMaxLength(test9)); // Expected: 8 (indices 1-8)

        // Edge Case 7: Valid subarray at beginning
        int[] test10 = {0, 1, 1, 1, 1};
        System.out.println("\nTest 10: nums = " + java.util.Arrays.toString(test10));
        System.out.println("Output: " + findMaxLength(test10)); // Expected: 2 ([0,1])
    }
}
