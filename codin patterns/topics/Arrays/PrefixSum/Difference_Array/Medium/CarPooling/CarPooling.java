package PrefixSum.Difference_Array.Medium.CarPooling;

import java.util.*;
public class CarPooling {
    /**
     * Determines if all passengers can be picked up and dropped off without exceeding capacity.
     *
     * Approach: Difference Array / Line Sweep Algorithm
     *
     * Key Insights:
     * 1. Each trip [num, from, to] means +num at 'from', -num at 'to'
     * 2. Since car only moves east, process locations in order
     * 3. Running sum gives current occupancy at each location
     * 4. Check if running sum ever exceeds capacity
     *
     * Time Complexity: O(trips + maxLocation) = O(trips + 1001)
     * Space Complexity: O(maxLocation) = O(1001)
     *
     * @param trips array of [numPassengers, from, to]
     * @param capacity maximum passengers car can hold
     * @return true if all trips can be accommodated, false otherwise
     */
    public static boolean carPooling(int[][] trips, int capacity) {
        // Maximum location is 1000 per constraints
        final int MAX_LOCATION = 1001;
        int[] diff = new int[MAX_LOCATION];

        // Process each trip
        for (int[] trip : trips) {
            int numPassengers = trip[0];
            int from = trip[1];
            int to = trip[2];

            // Add passengers at pickup location
            diff[from] += numPassengers;
            // Remove passengers at drop-off location
            diff[to] -= numPassengers;
        }

        // Check if capacity is ever exceeded
        int currentPassengers = 0;
        for (int i = 0; i < MAX_LOCATION; i++) {
            currentPassengers += diff[i];
            if (currentPassengers > capacity) {
                return false;
            }
        }

        return true;
    }

    // ==================== Comprehensive Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Car Pooling ===\n");

        // Example 1: Capacity too small
        int[][] trips1 = {{2,1,5}, {3,3,7}};
        int capacity1 = 4;
        System.out.println("Test 1: trips = " + java.util.Arrays.deepToString(trips1) + ", capacity = " + capacity1);
        System.out.println("Output: " + carPooling(trips1, capacity1)); // Expected: false

        // Example 2: Sufficient capacity
        int[][] trips2 = {{2,1,5}, {3,3,7}};
        int capacity2 = 5;
        System.out.println("\nTest 2: trips = " + java.util.Arrays.deepToString(trips2) + ", capacity = " + capacity2);
        System.out.println("Output: " + carPooling(trips2, capacity2)); // Expected: true

        // Edge Case 1: Single trip
        int[][] trips3 = {{3,2,7}};
        int capacity3 = 3;
        System.out.println("\nTest 3: trips = " + java.util.Arrays.deepToString(trips3) + ", capacity = " + capacity3);
        System.out.println("Output: " + carPooling(trips3, capacity3)); // Expected: true

        // Edge Case 2: Drop-off and pickup at same location
        int[][] trips4 = {{2,1,3}, {3,3,6}};
        int capacity4 = 3;
        System.out.println("\nTest 4: trips = " + java.util.Arrays.deepToString(trips4) + ", capacity = " + capacity4);
        System.out.println("Output: " + carPooling(trips4, capacity4)); // Expected: true
        // At location 3: drop off 2, then pick up 3 → max = 3

        // Edge Case 3: Multiple overlapping trips
        int[][] trips5 = {{2,1,5}, {3,2,4}, {4,3,6}, {1,4,7}};
        int capacity5 = 8;
        System.out.println("\nTest 5: trips = " + java.util.Arrays.deepToString(trips5) + ", capacity = " + capacity5);
        System.out.println("Output: " + carPooling(trips5, capacity5)); // Expected: true
        // Max occupancy: 2+3+4=9 at location 3-4, but capacity=8 → should be false!
        // Let's recalculate: at location 3: 2+3+4=9 → 9>8 → false

        // Edge Case 4: Zero passengers (though constraints say >=1)
        int[][] trips6 = {{0,1,5}};
        int capacity6 = 0;
        System.out.println("\nTest 6: trips = " + java.util.Arrays.deepToString(trips6) + ", capacity = " + capacity6);
        System.out.println("Output: " + carPooling(trips6, capacity6)); // Expected: true

        // Edge Case 5: Maximum constraints
        int[][] trips7 = new int[1000][3];
        for (int i = 0; i < 1000; i++) {
            trips7[i] = new int[]{1, i, i + 1}; // Non-overlapping
        }
        int capacity7 = 1;
        System.out.println("\nTest 7: 1000 non-overlapping trips, capacity = " + capacity7);
        System.out.println("Output: " + carPooling(trips7, capacity7)); // Expected: true

        // Edge Case 6: All trips at same location
        int[][] trips8 = {{2,5,10}, {3,5,10}, {4,5,10}};
        int capacity8 = 9;
        System.out.println("\nTest 8: trips = " + java.util.Arrays.deepToString(trips8) + ", capacity = " + capacity8);
        System.out.println("Output: " + carPooling(trips8, capacity8)); // Expected: true (2+3+4=9)

        // Performance Comparison
        System.out.println("\n=== Performance Comparison ===");

        // Generate large test case
        int[][] largeTrips = new int[1000][3];
        for (int i = 0; i < 1000; i++) {
            largeTrips[i] = new int[]{1, i % 500, i % 500 + 10};
        }
        int largeCapacity = 10;

        // Difference Array approach
        long start1 = System.currentTimeMillis();
        boolean result1 = carPooling(largeTrips, largeCapacity);
        long end1 = System.currentTimeMillis();

//        // Heap approach
//        long start2 = System.currentTimeMillis();
//        boolean result2 = carPoolingHeap(largeTrips, largeCapacity);
//        long end2 = System.currentTimeMillis();

        System.out.println("Difference Array (1000 trips): " + (end1 - start1) + " ms, Result: " + result1);
//        System.out.println("Heap Approach (1000 trips): " + (end2 - start2) + " ms, Result: " + result2);
    }
}
