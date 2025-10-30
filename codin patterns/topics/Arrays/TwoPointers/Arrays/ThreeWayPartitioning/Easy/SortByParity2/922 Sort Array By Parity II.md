Given an array of integers nums, half of the integers in nums are odd, and the other half are even.

Sort the array so that whenever nums[i] is odd, i is odd, and whenever nums[i] is even, i is even.

Return any answer array that satisfies this condition.

Example 1:

Input: nums = [4,2,5,7]
Output: [4,5,2,7]
Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.

Example 2:

Input: nums = [2,3]
Output: [2,3]

 

Constraints:

    2 <= nums.length <= 2 * 104
    nums.length is even.
    Half of the integers in nums are even.
    0 <= nums[i] <= 1000

 

Follow Up: Could you solve it in-place?


```java
package Arrays.ThreeWayPartitioning.Easy.SortByParity2;

import java.util.Arrays;

public class SortByParity2 {
    /**
     * Sorts the array in-place so that:
     *   - nums[i] is even when i is even
     *   - nums[i] is odd when i is odd
     *
     * Time: O(n) — each element visited at most once
     * Space: O(1) — in-place swaps
     */
    public static int[] sortArrayByParityII(int[] nums) {
        int left = 1; // pointer for odd indices (starts at 1)

        // Traverse only even indices: 0, 2, 4, ...
        for (int right = 0; right < nums.length; right += 2) {
            // If even index has an odd number → needs fixing
            if (nums[right] % 2 == 1) {
                // Find the next odd index (j) that has an even number
                while (nums[left] % 2 == 1) {
                    left += 2; // only check odd positions: 1, 3, 5, ...
                }
                // Swap the misplaced odd (at i) with misplaced even (at j)
                swap(nums, right, left);
            }
        }
        return nums;
    }
    public static void swap(int[] arr, int left, int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {4,2,5,7};
        sortArrayByParityII(nums);
        System.out.println(Arrays.toString(nums));
    }
}

```