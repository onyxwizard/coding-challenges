package PrefixSum.Prefix_With_HashMap.Medium.SubarraySumDivK;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumDivK {
    /**
     * Counts the number of contiguous subarrays with sum divisible by k.
     *
     * Approach: Prefix Sum + Modular Arithmetic + HashMap
     *
     * Key Insights:
     * 1. If prefix[i] % k == prefix[j] % k, then subarray (i+1 to j) is divisible by k
     * 2. Handle negative modulo by normalizing to positive range [0, k-1]
     * 3. Use HashMap to count frequency of each modulo remainder
     *
     * Time Complexity: O(n)
     * Space Complexity: O(min(n, k)) - at most k different remainders
     *
     * @param nums input array of integers
     * @param k divisor (k >= 2)
     * @return number of subarrays with sum divisible by k
     */
    public static int subarraysDivByK(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> modCount = new HashMap<>();
        modCount.put(0, 1); // empty prefix sum has remainder 0

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            prefixSum += num;

            // Normalize modulo to handle negative numbers
            int mod = prefixSum % k;
            if (mod < 0) {
                mod += k;
            }
            // Alternative one-liner: int mod = (prefixSum % k + k) % k;

            // If this modulo has been seen before, add its frequency
            if (modCount.containsKey(mod)) {
                count += modCount.get(mod);
            }

            // Update frequency of current modulo
            modCount.put(mod, modCount.getOrDefault(mod, 0) + 1);
        }

        return count;
    }

    // ==================== Comprehensive Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Subarray Sums Divisible by K ===\n");

        // Example 1: Standard case
        int[] test1 = {4, 5, 0, -2, -3, 1};
        int k1 = 5;
        System.out.println("Test 1: nums = " + java.util.Arrays.toString(test1) + ", k = " + k1);
        System.out.println("Output: " + subarraysDivByK(test1, k1)); // Expected: 7

        // Example 2: No valid subarrays
        int[] test2 = {5};
        int k2 = 9;
        System.out.println("\nTest 2: nums = " + java.util.Arrays.toString(test2) + ", k = " + k2);
        System.out.println("Output: " + subarraysDivByK(test2, k2)); // Expected: 0

        // Edge Case 1: All zeros
        int[] test3 = {0, 0, 0};
        int k3 = 1;
        System.out.println("\nTest 3: nums = " + java.util.Arrays.toString(test3) + ", k = " + k3);
        System.out.println("Output: " + subarraysDivByK(test3, k3)); // Expected: 6 (all subarrays)

        // Edge Case 2: Negative numbers with positive k
        int[] test4 = {-1, 2, 9};
        int k4 = 2;
        System.out.println("\nTest 4: nums = " + java.util.Arrays.toString(test4) + ", k = " + k4);
        System.out.println("Output: " + subarraysDivByK(test4, k4)); // Expected: 2

        // Edge Case 3: Single element divisible by k
        int[] test5 = {10};
        int k5 = 5;
        System.out.println("\nTest 5: nums = " + java.util.Arrays.toString(test5) + ", k = " + k5);
        System.out.println("Output: " + subarraysDivByK(test5, k5)); // Expected: 1

        // Edge Case 4: k = 2 (even/odd sums)
        int[] test6 = {1, 2, 3, 4};
        int k6 = 2;
        System.out.println("\nTest 6: nums = " + java.util.Arrays.toString(test6) + ", k = " + k6);
        System.out.println("Output: " + subarraysDivByK(test6, k6)); // Expected: 3

        // Edge Case 5: Large array with many valid subarrays
        int[] test7 = {1, 1, 1, 1, 1};
        int k7 = 1;
        System.out.println("\nTest 7: nums = " + java.util.Arrays.toString(test7) + ", k = " + k7);
        System.out.println("Output: " + subarraysDivByK(test7, k7)); // Expected: 15 (all subarrays)

        // Edge Case 6: Alternating positive/negative
        int[] test8 = {2, -2, 2, -2, 2};
        int k8 = 4;
        System.out.println("\nTest 8: nums = " + java.util.Arrays.toString(test8) + ", k = " + k8);
        System.out.println("Output: " + subarraysDivByK(test8, k8)); // Expected: 3
    }
}
