Given an array of positive integers `nums` and a positive integer `target`, return _the **minimal length** of a_

_whose sum is greater than or equal to_ `target`. If there is no such subarray, return `0` instead.

**Example 1:**

**Input:** target = 7, nums = [2,3,1,2,4,3]
**Output:** 2
**Explanation:** The subarray [4,3] has the minimal length under the problem constraint.

**Example 2:**

**Input:** target = 4, nums = [1,4,4]
**Output:** 1

**Example 3:**

**Input:** target = 11, nums = [1,1,1,1,1,1,1,1]
**Output:** 0

**Constraints:**

- `1 <= target <= 109`
- `1 <= nums.length <= 105`
- `1 <= nums[i] <= 104`

**Follow up:** If you have figured out the `O(n)` solution, try coding another solution of which the time complexity is `O(n log(n))`.

```java
package SlidingWindow.Arrays.Dynamic.Medium.MinimumSizeSubarraySum;

import java.util.Arrays;

public class MinSubArrayLen {

    /**
     * Finds the minimal length of a contiguous subarray in 'nums' 
     * such that the sum of the subarray is at least 'target'.
     * 
     * Assumptions (per problem constraints):
     * - All elements in 'nums' are positive integers.
     * - Subarray must be contiguous (i.e., consecutive in original order).
     * 
     * Approach: Sliding Window (Two Pointers)
     * - Expand window by moving 'right' pointer and adding nums[right] to sum.
     * - When sum >= target, try to shrink from 'left' to find minimal valid window.
     * - Update minLen whenever a valid window is found.
     * 
     * Why sliding window works?
     * - Since all numbers are positive, adding more elements increases sum,
     *   and removing elements decreases sum → monotonic behavior.
     * 
     * Time Complexity: O(n) — each element visited at most twice.
     * Space Complexity: O(1)
     * 
     * @param target the minimum sum required
     * @param nums array of positive integers
     * @return minimal length of contiguous subarray with sum >= target, or 0 if none
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        long sum = 0; // Use long to prevent overflow (though constraints make int safe)
        int minLen = Integer.MAX_VALUE;

        // Expand window with 'right' pointer
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            // Shrink window from the left as long as sum >= target
            // This ensures we find the SMALLEST valid window ending at 'right'
            while (sum >= target) {
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }

        // If minLen was never updated, no valid subarray exists
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    // ==================== Main Method with Comprehensive Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Minimum Size Subarray Sum (Target >= Sum) ===\n");

        // Example 1: Standard case
        int[] test1 = {2, 3, 1, 2, 4, 3};
        int target1 = 7;
        System.out.println("Test 1: nums = " + Arrays.toString(test1) + ", target = " + target1);
        System.out.println("Output: " + minSubArrayLen(target1, test1)); // Expected: 2 ([4,3])

        // Example 2: Single element matches target
        int[] test2 = {1, 4, 4};
        int target2 = 4;
        System.out.println("\nTest 2: nums = " + Arrays.toString(test2) + ", target = " + target2);
        System.out.println("Output: " + minSubArrayLen(target2, test2)); // Expected: 1

        // Example 3: No subarray reaches target
        int[] test3 = {1, 1, 1, 1, 1, 1, 1, 1};
        int target3 = 11;
        System.out.println("\nTest 3: nums = " + Arrays.toString(test3) + ", target = " + target3);
        System.out.println("Output: " + minSubArrayLen(target3, test3)); // Expected: 0

        // Edge Case: Single element >= target
        int[] test4 = {10};
        int target4 = 5;
        System.out.println("\nTest 4: nums = " + Arrays.toString(test4) + ", target = " + target4);
        System.out.println("Output: " + minSubArrayLen(target4, test4)); // Expected: 1

        // Edge Case: Single element < target
        int[] test5 = {5};
        int target5 = 10;
        System.out.println("\nTest 5: nums = " + Arrays.toString(test5) + ", target = " + target5);
        System.out.println("Output: " + minSubArrayLen(target5, test5)); // Expected: 0

        // Edge Case: Exact match with entire array
        int[] test6 = {1, 2, 3, 4};
        int target6 = 10;
        System.out.println("\nTest 6: nums = " + Arrays.toString(test6) + ", target = " + target6);
        System.out.println("Output: " + minSubArrayLen(target6, test6)); // Expected: 4

        // Edge Case: Large target, small numbers
        int[] test7 = {1, 2, 3};
        int target7 = 100;
        System.out.println("\nTest 7: nums = " + Arrays.toString(test7) + ", target = " + target7);
        System.out.println("Output: " + minSubArrayLen(target7, test7)); // Expected: 0
    }
}
```