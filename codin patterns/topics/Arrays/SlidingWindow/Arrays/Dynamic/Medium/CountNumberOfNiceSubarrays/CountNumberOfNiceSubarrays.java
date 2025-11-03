package SlidingWindow.Arrays.Dynamic.Medium.CountNumberOfNiceSubarrays;

import java.util.HashMap;
import java.util.Map;

public class CountNumberOfNiceSubarrays {

    /**
     * Counts the number of contiguous subarrays with exactly k odd numbers.
     *
     * Approach: Transform to binary array (1=odd, 0=even), then use prefix sum + hashmap.
     *
     * Key Insight:
     * Let prefixSum = total odd numbers seen so far.
     * We want subarrays where (prefixSum - k) has been seen before.
     *
     * Example:
     * nums = [1,1,2,1,1], k = 3
     * binary = [1,1,0,1,1]
     * prefix: 0,1,2,2,3,4
     * At prefix=3: need prefix=0 → count += 1
     * At prefix=4: need prefix=1 → count += 2 (prefix=1 occurred at index 1 and 2)
     * Total = 1 + 2 = 3? But expected=2.
     *
     * Wait, let's recalculate:
     * prefix[0] = 0
     * i=0: num=1 → prefix=1 → need 1-3=-2 → 0
     * i=1: num=1 → prefix=2 → need -1 → 0
     * i=2: num=2 → prefix=2 → need -1 → 0
     * i=3: num=1 → prefix=3 → need 0 → count += 1 (prefix 0 at start)
     * i=4: num=1 → prefix=4 → need 1 → count += 1 (prefix 1 at i=0)
     * Total = 2 ✅
     *
     * So it works.
     *
     * Steps:
     * 1. Initialize map with {0: 1} (empty prefix)
     * 2. For each number:
     *    - if odd, increment prefixSum
     *    - check if (prefixSum - k) exists in map
     *    - add count to result
     *    - update map with current prefixSum
     *
     * Time: O(n), Space: O(n)
     */
    public static int numberOfSubarrays(int[] nums, int k) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1); // empty prefix has sum 0

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            // Update prefix sum: add 1 if odd, 0 if even
            if (num % 2 == 1) {
                prefixSum++;
            }

            // Check if (prefixSum - k) has been seen
            if (prefixCount.containsKey(prefixSum - k)) {
                count += prefixCount.get(prefixSum - k);
            }

            // Update map with current prefix sum
            prefixCount.put(prefixSum, prefixCount.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }

    // ==================== Alternative: Sliding Window (At Most K) ====================
    /**
     * Alternative approach:
     * number of subarrays with exactly k odds =
     * (number with at most k odds) - (number with at most k-1 odds)
     *
     * This works because the property "number of odds" is monotonic.
     */
    public static int numberOfSubarraysSlidingWindow(int[] nums, int k) {
        return atMostKOdds(nums, k) - atMostKOdds(nums, k - 1);
    }

    private static int atMostKOdds(int[] nums, int k) {
        if (k < 0) return 0;

        int left = 0;
        int oddCount = 0;
        int result = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] % 2 == 1) {
                oddCount++;
            }

            while (oddCount > k) {
                if (nums[left] % 2 == 1) {
                    oddCount--;
                }
                left++;
            }

            // All subarrays ending at 'right' and starting from 'left' to 'right' are valid
            result += right - left + 1;
        }

        return result;
    }

    // ==================== Main Method ====================
    public static void main(String[] args) {
        System.out.println("=== Count Number of Nice Subarrays ===\n");

        // Example 1
        int[] nums1 = {1, 1, 2, 1, 1};
        int k1 = 3;
        System.out.println("Test 1: nums = " + java.util.Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Output (HashMap): " + numberOfSubarrays(nums1, k1)); // Expected: 2
        System.out.println("Output (Sliding Window): " + numberOfSubarraysSlidingWindow(nums1, k1)); // Expected: 2

        // Example 2
        int[] nums2 = {2, 4, 6};
        int k2 = 1;
        System.out.println("\nTest 2: nums = " + java.util.Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Output (HashMap): " + numberOfSubarrays(nums2, k2)); // Expected: 0
        System.out.println("Output (Sliding Window): " + numberOfSubarraysSlidingWindow(nums2, k2)); // Expected: 0

        // Example 3
        int[] nums3 = {2, 2, 2, 1, 2, 2, 1, 2, 2, 2};
        int k3 = 2;
        System.out.println("\nTest 3: nums = " + java.util.Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Output (HashMap): " + numberOfSubarrays(nums3, k3)); // Expected: 16
        System.out.println("Output (Sliding Window): " + numberOfSubarraysSlidingWindow(nums3, k3)); // Expected: 16

        // Edge: k = 1, single odd
        int[] nums4 = {1};
        int k4 = 1;
        System.out.println("\nTest 4: nums = " + java.util.Arrays.toString(nums4) + ", k = " + k4);
        System.out.println("Output: " + numberOfSubarrays(nums4, k4)); // Expected: 1

        // Edge: k = 1, all odds
        int[] nums5 = {1, 1, 1};
        int k5 = 1;
        System.out.println("\nTest 5: nums = " + java.util.Arrays.toString(nums5) + ", k = " + k5);
        System.out.println("Output: " + numberOfSubarrays(nums5, k5)); // Expected: 3
        // Subarrays: [1], [1], [1]
    }
}
