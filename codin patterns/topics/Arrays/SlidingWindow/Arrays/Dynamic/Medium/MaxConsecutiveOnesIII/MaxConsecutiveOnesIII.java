package SlidingWindow.Arrays.Dynamic.Medium.MaxConsecutiveOnesIII;

/**
 * Solution for "Max Consecutive Ones III" (LeetCode #1004)
 *
 * Given a binary array and an integer k, this class finds the maximum number
 * of consecutive 1's achievable by flipping at most k 0's to 1's.
 *
 * Approach: Dynamic Sliding Window
 * - Maintain a window [left, right] that contains at most k zeros
 * - Expand the window by moving the right pointer
 * - Shrink from the left when zero count exceeds k
 * - Track the maximum valid window size
 *
 * Time Complexity: O(n) - each element visited at most twice
 * Space Complexity: O(1) - only using a few variables
 */
public class MaxConsecutiveOnesIII {

    /**
     * Finds the length of the longest subarray containing only 1's
     * after flipping at most k 0's.
     *
     * @param nums binary array containing only 0s and 1s
     * @param k    maximum number of 0's that can be flipped
     * @return length of the longest subarray achievable
     */
    public static int longestOnes(int[] nums, int k) {
        int left = 0;           // Left boundary of the sliding window
        int zeroCount = 0;      // Count of zeros in the current window
        int maxLength = 0;      // Maximum valid window length found

        // Expand window by moving right pointer from start to end
        int right = 0;
        while (right < nums.length) {
            // Include current element in the window
            if (nums[right] == 0) {
                zeroCount++;
            }

            // Contract window from left while constraint is violated (zeroCount > k)
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            // Window [left, right] is now valid - update maximum length
            maxLength = Math.max(maxLength, right - left + 1);

            // Move right pointer to expand window
            right++;
        }

        return maxLength;
    }

    /**
     * Demonstrates the solution with comprehensive test cases.
     * Each test case includes expected output for verification.
     */
    public static void main(String[] args) {
        // Test Case 1: Mixed array with multiple zero groups
        int[] nums1 = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k1 = 2;
        System.out.println("Test 1: " + longestOnes(nums1, k1)); // Expected: 6

        // Test Case 2: Complex array with scattered zeros
        int[] nums2 = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int k2 = 3;
        System.out.println("Test 2: " + longestOnes(nums2, k2)); // Expected: 10

        // Test Case 3: All 1's with no flips allowed
        int[] nums3 = {1, 1, 1, 1};
        int k3 = 0;
        System.out.println("Test 3: " + longestOnes(nums3, k3)); // Expected: 4

        // Test Case 4: All 0's with limited flips
        int[] nums4 = {0, 0, 0, 0};
        int k4 = 2;
        System.out.println("Test 4: " + longestOnes(nums4, k4)); // Expected: 2

        // Test Case 5: Edge case - empty array
        int[] nums5 = {};
        int k5 = 1;
        System.out.println("Test 5: " + longestOnes(nums5, k5)); // Expected: 0
    }
}