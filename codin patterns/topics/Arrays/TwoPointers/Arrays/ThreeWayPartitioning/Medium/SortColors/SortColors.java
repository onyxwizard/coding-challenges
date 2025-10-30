package Arrays.ThreeWayPartitioning.Medium.SortColors;

import java.util.Arrays;

/**
 * Solution to the "Sort Colors" problem (LeetCode #75) using the
 * Three-Way Partitioning (Dutch National Flag) algorithm.
 *
 * Problem: Given an array `nums` containing only 0s, 1s, and 2s,
 * sort the array in-place so that:
 * - All 0s come first,
 * - Followed by all 1s,
 * - Then all 2s.
 *
 * Approach: Uses three pointers to partition the array into three regions
 * in a single pass with O(n) time and O(1) space.
 */
public class SortColors {

    /**
     * Sorts the input array in-place using the Three-Way Partitioning technique.
     *
     * @param nums Input array containing only 0, 1, and 2.
     */
    public static void sortColors(int[] nums) {
        // Edge case: empty or single-element array is already sorted
        if (nums == null || nums.length <= 1) {
            return;
        }

        // Three pointers to maintain the following invariant at all times:
        // [0, left)        --> all 0s (correctly placed)
        // [left, mid)      --> all 1s (correctly placed)
        // [mid, right]     --> unprocessed elements
        // (right, end]     --> all 2s (correctly placed)
        int left = 0;           // boundary for the next 0
        int mid = 0;            // current element being examined
        int right = nums.length - 1; // boundary for the next 2

        // Process elements while there are unprocessed items (mid <= right)
        while (mid <= right) {
            if (nums[mid] == 0) {
                // Current element is 0 → belongs at the front.
                // Swap it to the 'left' position (start of 1s or unprocessed zone).
                swap(nums, left, mid);
                // After swap:
                // - The 0 is now in the correct zone.
                // - The element moved to 'mid' must be a 1 (or another 0),
                //   so it's safe to advance both pointers.
                left++;
                mid++;
            } else if (nums[mid] == 1) {
                // Current element is 1 → already in the correct middle zone.
                // Just move forward.
                mid++;
            } else if (nums[mid] == 2) {
                // Current element is 2 → belongs at the end.
                // Swap it to the 'right' position (end of unprocessed zone).
                swap(nums, mid, right);
                // After swap:
                // - The 2 is now in the correct zone (beyond 'right').
                // - BUT the element swapped into 'mid' came from the unprocessed
                //   right side and hasn't been examined yet → DO NOT increment 'mid'.
                right--;
            }
        }
    }

    /**
     * Swaps two elements in the given array.
     *
     * @param arr   The array in which elements are to be swapped.
     * @param i     Index of the first element.
     * @param j     Index of the second element.
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Main method for testing the sortColors function.
     * Example input: {1,1,1,0,0,2,0,0}
     * Expected output: [0, 0, 0, 0, 1, 1, 1, 2]
     */
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 0, 0, 2, 0, 0};
        System.out.println("Original array: " + Arrays.toString(nums));

        sortColors(nums);

        System.out.println("Sorted array:   " + Arrays.toString(nums));
    }
}