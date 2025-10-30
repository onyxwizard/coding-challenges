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
