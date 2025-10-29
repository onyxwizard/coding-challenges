You are given two positive integer arrays nums1 and nums2, both of length n.

The absolute sum difference of arrays nums1 and nums2 is defined as the sum of |nums1[i] - nums2[i]| for each 0 <= i < n (0-indexed).

You can replace at most one element of nums1 with any other element in nums1 to minimize the absolute sum difference.

Return the minimum absolute sum difference after replacing at most one element in the array nums1. Since the answer may be large, return it modulo 109 + 7.

|x| is defined as:

    x if x >= 0, or
    -x if x < 0.

 

Example 1:

Input: nums1 = [1,7,5], nums2 = [2,3,5]
Output: 3
Explanation: There are two possible optimal solutions:
- Replace the second element with the first: [1,7,5] => [1,1,5], or
- Replace the second element with the third: [1,7,5] => [1,5,5].
Both will yield an absolute sum difference of |1-2| + (|1-3| or |5-3|) + |5-5| = 3.

Example 2:

Input: nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
Output: 0
Explanation: nums1 is equal to nums2 so no replacement is needed. This will result in an 
absolute sum difference of 0.

Example 3:

Input: nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
Output: 20
Explanation: Replace the first element with the second: [1,10,4,4,2,7] => [10,10,4,4,2,7].
This yields an absolute sum difference of |10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20

 

Constraints:

    n == nums1.length
    n == nums2.length
    1 <= n <= 105
    1 <= nums1[i], nums2[i] <= 105



```java
package Arrays.TwoArrays.Medium.MinimumAbsoluteSumDifference;

import java.util.Arrays;

public class MinimumAbsoluteSumDiff {

    // Modulo value as per problem requirement (10^9 + 7)
    private static final int MOD = 1_000_000_007;

    /**
     * Computes the minimum possible absolute sum difference after replacing at most
     * one element in nums1 with any other element from nums1.
     *
     * @param nums1 First integer array (can be modified by replacement)
     * @param nums2 Second integer array (fixed)
     * @return Minimum absolute sum difference modulo 10^9 + 7
     */
    public static int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int arrayLength = nums1.length;

        // Step 1: Calculate the original total absolute difference
        long originalTotalSum = 0;
        for (int index = 0; index < arrayLength; index++) {
            originalTotalSum += Math.abs((long) nums1[index] - nums2[index]);
        }
        // We'll apply modulo only at the end to avoid premature truncation,
        // but keep it within long range for now.

        // Step 2: Create a sorted copy of nums1 to enable efficient binary search
        int[] sortedNums1 = nums1.clone();
        Arrays.sort(sortedNums1);

        // Step 3: Track the maximum reduction in total sum we can achieve
        // by replacing one element in nums1
        long maxPossibleReduction = 0;

        // Step 4: For each position, try to find the best replacement in nums1
        // that minimizes |replacement - nums2[i]|
        for (int index = 0; index < arrayLength; index++) {
            int currentDifference = Math.abs(nums1[index] - nums2[index]);
            int targetValue = nums2[index]; // We want a value in nums1 close to this

            // Find the smallest possible |x - targetValue| where x is in nums1
            int bestPossibleNewDifference = findClosestAbsoluteDifference(sortedNums1, targetValue);

            // How much we can reduce the total by changing nums1[index]
            long potentialReduction = (long) currentDifference - bestPossibleNewDifference;

            // Update the best reduction seen so far
            if (potentialReduction > maxPossibleReduction) {
                maxPossibleReduction = potentialReduction;
            }
        }

        // Step 5: Compute final result: original total minus best reduction
        // Ensure non-negative result before applying modulo
        long finalResult = (originalTotalSum - maxPossibleReduction) % MOD;
        if (finalResult < 0) {
            finalResult += MOD; // In case of negative (though unlikely here)
        }

        return (int) finalResult;
    }

    /**
     * Given a sorted array and a target value, finds the minimum absolute difference
     * between the target and any element in the array using binary search.
     *
     * This works by locating the insertion point of the target, then checking
     * the closest elements on the left and right of that position.
     *
     * @param sortedArray A sorted array of integers (from nums1)
     * @param target The value we want to get close to (from nums2)
     * @return The smallest |element - target| for element in sortedArray
     */
    private static int findClosestAbsoluteDifference(int[] sortedArray, int target) {
        int arrayLength = sortedArray.length;

        // Binary search to find the "insertion point" (first index where element >= target)
        int leftIndex = 0;
        int rightIndex = arrayLength; // Note: right is exclusive

        while (leftIndex < rightIndex) {
            int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
            if (sortedArray[middleIndex] < target) {
                leftIndex = middleIndex + 1;
            } else {
                rightIndex = middleIndex;
            }
        }

        // Now, 'leftIndex' is the position where 'target' would be inserted
        // Candidates for closest value are:
        //   - sortedArray[leftIndex - 1] (largest value <= target)
        //   - sortedArray[leftIndex]     (smallest value >= target, if exists)

        int minimumDifference = Integer.MAX_VALUE;

        // Check the element immediately before the insertion point (if exists)
        if (leftIndex > 0) {
            int leftCandidate = sortedArray[leftIndex - 1];
            minimumDifference = Math.min(minimumDifference, Math.abs(leftCandidate - target));
        }

        // Check the element at the insertion point (if within bounds)
        if (leftIndex < arrayLength) {
            int rightCandidate = sortedArray[leftIndex];
            minimumDifference = Math.min(minimumDifference, Math.abs(rightCandidate - target));
        }

        return minimumDifference;
    }

    // ==================== Test Driver ====================

    public static void main(String[] args) {
        // Example 1
        int[] nums1 = {1, 7, 5};
        int[] nums2 = {2, 3, 5};
        int result1 = minAbsoluteSumDiff(nums1, nums2);
        System.out.println("Example 1 Output: " + result1); // Expected: 3

        // Example 2
        int[] nums1b = {2, 4, 6, 8, 10};
        int[] nums2b = {2, 4, 6, 8, 10};
        int result2 = minAbsoluteSumDiff(nums1b, nums2b);
        System.out.println("Example 2 Output: " + result2); // Expected: 0

        // Example 3
        int[] nums1c = {1, 10, 4, 4, 2, 7};
        int[] nums2c = {9, 3, 5, 1, 7, 4};
        int result3 = minAbsoluteSumDiff(nums1c, nums2c);
        System.out.println("Example 3 Output: " + result3); // Expected: 20
    }
}
```