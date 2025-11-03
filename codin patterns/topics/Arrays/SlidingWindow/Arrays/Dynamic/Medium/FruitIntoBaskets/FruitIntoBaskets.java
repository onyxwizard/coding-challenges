package SlidingWindow.Arrays.Dynamic.Medium.FruitIntoBaskets;

import java.util.HashMap;

public class FruitIntoBaskets {

    /**
     * Finds the maximum number of fruits you can collect when you can carry at most two types of fruits.
     * This is equivalent to finding the longest contiguous subarray with at most 2 distinct integers.
     *
     * Approach: Sliding Window + Hash Map
     * - Expand the window by moving 'right'
     * - If more than 2 types are in the basket, shrink from 'left' until valid
     * - Track the maximum valid window size
     *
     * Time Complexity: O(n) — each element visited at most twice
     * Space Complexity: O(1) — map holds at most 3 entries (then shrinks to 2)
     *
     * @param fruits array representing types of fruits in a row
     * @return maximum number of fruits that can be collected
     */
    public static int totalFruit(int[] fruits) {
        if (fruits == null || fruits.length == 0) {
            return 0;
        }

        final int MAX_BASKETS = 2;
        int left = 0;
        int maxLen = 0;
        HashMap<Integer, Integer> basket = new HashMap<>(); // fruitType -> count

        for (int right = 0; right < fruits.length; right++) {
            int currentFruit = fruits[right];
            basket.put(currentFruit, basket.getOrDefault(currentFruit, 0) + 1);

            // Shrink the window from the left while we have more than 2 types
            while (basket.size() > MAX_BASKETS) {
                int leftFruit = fruits[left];
                basket.put(leftFruit, basket.get(leftFruit) - 1);
                if (basket.get(leftFruit) == 0) {
                    basket.remove(leftFruit);
                }
                left++;
            }

            // Now the window [left, right] is valid (<= 2 types)
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    // ==================== Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Fruit Into Baskets - Test Cases ===\n");

        // Test Case 1: Basic example
        int[] fruits1 = {1, 2, 1};
        System.out.println("Test 1: " + java.util.Arrays.toString(fruits1));
        System.out.println("Output: " + totalFruit(fruits1)); // Expected: 3

        // Test Case 2: More than 2 types
        int[] fruits2 = {0, 1, 2, 2};
        System.out.println("\nTest 2: " + java.util.Arrays.toString(fruits2));
        System.out.println("Output: " + totalFruit(fruits2)); // Expected: 3 ([1,2,2] or [2,2])

        // Test Case 3: All same type
        int[] fruits3 = {1, 1, 1, 1};
        System.out.println("\nTest 3: " + java.util.Arrays.toString(fruits3));
        System.out.println("Output: " + totalFruit(fruits3)); // Expected: 4

        // Test Case 4: Alternating two types (entire array valid)
        int[] fruits4 = {1, 2, 1, 2, 1, 2};
        System.out.println("\nTest 4: " + java.util.Arrays.toString(fruits4));
        System.out.println("Output: " + totalFruit(fruits4)); // Expected: 6

        // Test Case 5: Single fruit
        int[] fruits5 = {5};
        System.out.println("\nTest 5: " + java.util.Arrays.toString(fruits5));
        System.out.println("Output: " + totalFruit(fruits5)); // Expected: 1

        // Test Case 6: Empty array (edge case)
        int[] fruits6 = {};
        System.out.println("\nTest 6: [] (empty array)");
        System.out.println("Output: " + totalFruit(fruits6)); // Expected: 0

        // Test Case 7: Two fruits, different types
        int[] fruits7 = {1, 2};
        System.out.println("\nTest 7: " + java.util.Arrays.toString(fruits7));
        System.out.println("Output: " + totalFruit(fruits7)); // Expected: 2

        // Test Case 8: Long sequence with late switch
        int[] fruits8 = {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};
        System.out.println("\nTest 8: " + java.util.Arrays.toString(fruits8));
        System.out.println("Output: " + totalFruit(fruits8)); // Expected: 5 ([1,2,1,1,2])

        // Test Case 9: Null input (defensive)
        System.out.println("\nTest 9: null input");
        System.out.println("Output: " + totalFruit(null)); // Expected: 0
    }
}