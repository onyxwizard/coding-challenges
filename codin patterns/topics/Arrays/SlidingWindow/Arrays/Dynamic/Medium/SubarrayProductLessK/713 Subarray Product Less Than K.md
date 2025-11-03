Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.

Example 1:

Input: nums = [10,5,2,6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are:
[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.

Example 2:

Input: nums = [1,2,3], k = 0
Output: 0

Constraints:

    1 <= nums.length <= 3 * 104
    1 <= nums[i] <= 1000
    0 <= k <= 106


```java
package SlidingWindow.Arrays.Dynamic.Medium.SubarrayProductLessK;

public class SubarrayProductLessK {

    /**
     * Counts the number of contiguous subarrays where the product of all elements is strictly less than k.
     *
     * Assumptions (per problem constraints):
     * - All elements in 'nums' are positive integers (1 <= nums[i] <= 1000)
     * - 0 <= k <= 1_000_000
     *
     * Approach: Sliding Window
     * - Expand the window by moving the 'right' pointer.
     * - Maintain a running product of elements in the current window.
     * - If the product becomes >= k, shrink the window from the 'left' until valid.
     * - For each valid window [left, right], there are (right - left + 1) new subarrays
     *   ending at 'right' that satisfy the condition.
     *
     * Time Complexity: O(n) — each element is visited at most twice.
     * Space Complexity: O(1)
     *
     * @param nums Array of positive integers
     * @param k Threshold (exclusive) for subarray product
     * @return Number of contiguous subarrays with product < k
     */
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        // Since all numbers are >= 1, the smallest possible product is 1.
        // Thus, if k <= 1, no non-empty subarray can have product < k.
        if (k <= 1) {
            return 0;
        }

        int left = 0;
        int product = 1;
        int count = 0;

        for (int right = 0; right < nums.length; right++) {
            // Expand window to include nums[right]
            product *= nums[right];

            // Shrink window from the left while product is too large
            while (product >= k) {
                product /= nums[left];
                left++;
            }

            // All subarrays ending at 'right' and starting from 'left' to 'right' are valid
            count += right - left + 1;
        }

        return count;
    }

    // ==================== Main Method with Comprehensive Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Subarray Product Less Than K ===\n");

        // Example 1 from problem statement
        int[] test1 = {10, 5, 2, 6};
        int k1 = 100;
        System.out.println("Test 1: nums = " + java.util.Arrays.toString(test1) + ", k = " + k1);
        System.out.println("Output: " + numSubarrayProductLessThanK(test1, k1)); // Expected: 8

        // Example 2: k = 0 → no valid subarray
        int[] test2 = {1, 2, 3};
        int k2 = 0;
        System.out.println("\nTest 2: nums = " + java.util.Arrays.toString(test2) + ", k = " + k2);
        System.out.println("Output: " + numSubarrayProductLessThanK(test2, k2)); // Expected: 0

        // Edge: k = 1 → product must be < 1, but min product = 1
        int[] test3 = {1};
        int k3 = 1;
        System.out.println("\nTest 3: nums = " + java.util.Arrays.toString(test3) + ", k = " + k3);
        System.out.println("Output: " + numSubarrayProductLessThanK(test3, k3)); // Expected: 0

        // Edge: single valid element
        int[] test4 = {2};
        int k4 = 3;
        System.out.println("\nTest 4: nums = " + java.util.Arrays.toString(test4) + ", k = " + k4);
        System.out.println("Output: " + numSubarrayProductLessThanK(test4, k4)); // Expected: 1

        // All ones: every subarray is valid if k > 1
        int[] test5 = {1, 1, 1};
        int k5 = 2;
        System.out.println("\nTest 5: nums = " + java.util.Arrays.toString(test5) + ", k = " + k5);
        System.out.println("Output: " + numSubarrayProductLessThanK(test5, k5)); // Expected: 6

        // Large elements, small k → no valid subarrays
        int[] test6 = {1000, 1000};
        int k6 = 500;
        System.out.println("\nTest 6: nums = " + java.util.Arrays.toString(test6) + ", k = " + k6);
        System.out.println("Output: " + numSubarrayProductLessThanK(test6, k6)); // Expected: 0

        // Empty-like edge (though constraints guarantee n >= 1, included for robustness)
        int[] test7 = {};
        int k7 = 10;
        System.out.println("\nTest 7: nums = [], k = " + k7);
        System.out.println("Output: " + numSubarrayProductLessThanK(test7, k7)); // Expected: 0
    }
}
```