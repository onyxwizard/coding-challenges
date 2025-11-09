package PrefixSum.Difference_Array.Medium.CorporateFlightBooking;

import java.util.Arrays;

public class CorporateFlightBooking {
    /**
     * Calculates total seats reserved for each flight using difference array technique.
     *
     * Approach: Difference Array (Range Update Optimization)
     *
     * Key Insights:
     * 1. For range update [l, r] with value x:
     *    - diff[l] += x
     *    - diff[r+1] -= x (if r+1 < n)
     * 2. Prefix sum of diff array gives actual values
     * 3. O(1) per booking update, O(n) final computation
     *
     * Time Complexity: O(bookings + n)
     * Space Complexity: O(n)
     *
     * @param bookings array of [first, last, seats] bookings
     * @param n number of flights
     * @return array where answer[i] = total seats for flight i+1
     */
    public static int[] corporateFlightBooking(int[][] bookings, int n) {
        int[] diff = new int[n];

        // Process each booking
        for (int[] booking : bookings) {
            int first = booking[0];   // 1-indexed
            int last = booking[1];    // 1-indexed
            int seats = booking[2];

            // Add seats to start of range
            diff[first - 1] += seats;

            // Subtract seats after end of range (if within bounds)
            if (last < n) {
                diff[last] -= seats;
            }
        }

        // Compute prefix sum to get actual seat counts
        for (int i = 1; i < n; i++) {
            diff[i] += diff[i - 1];
        }

        return diff;
    }


    // ==================== Alternative: Direct Array Approach (Less Efficient) ====================
    /**
     * Naive approach for comparison - O(n × bookings) time
     * Included for educational purposes only
     */
    public static int[] corpFlightBookingsNaive(int[][] bookings, int n) {
        int[] answer = new int[n];

        for (int[] booking : bookings) {
            int first = booking[0];
            int last = booking[1];
            int seats = booking[2];

            // Update each flight in the range
            for (int i = first - 1; i <= last - 1; i++) {
                answer[i] += seats;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println("=== Corporate Flight Bookings ===\n");

        // Example 1: Standard case
        int[][] bookings1 = {{1,2,10}, {2,3,20}, {2,5,25}};
        int n1 = 5;
        System.out.println("Test 1: bookings = " + Arrays.deepToString(bookings1) + ", n = " + n1);
        int[] result1 = corporateFlightBooking(bookings1, n1);
        System.out.println("Output: " + Arrays.toString(result1));
        // Expected: [10, 55, 45, 25, 25]

        // Example 2: Overlapping ranges
        int[][] bookings2 = {{1,2,10}, {2,2,15}};
        int n2 = 2;
        System.out.println("\nTest 2: bookings = " + Arrays.deepToString(bookings2) + ", n = " + n2);
        int[] result2 = corporateFlightBooking(bookings2, n2);
        System.out.println("Output: " + Arrays.toString(result2));
        // Expected: [10, 25]

        // Edge Case 1: Single flight, single booking
        int[][] bookings3 = {{1,1,100}};
        int n3 = 1;
        System.out.println("\nTest 3: bookings = " + Arrays.deepToString(bookings3) + ", n = " + n3);
        int[] result3 = corporateFlightBooking(bookings3, n3);
        System.out.println("Output: " + Arrays.toString(result3));
        // Expected: [100]

        // Edge Case 2: Booking covers all flights
        int[][] bookings4 = {{1,5,10}};
        int n4 = 5;
        System.out.println("\nTest 4: bookings = " + Arrays.deepToString(bookings4) + ", n = " + n4);
        int[] result4 = corporateFlightBooking(bookings4, n4);
        System.out.println("Output: " + Arrays.toString(result4));
        // Expected: [10, 10, 10, 10, 10]

        // Edge Case 3: Multiple bookings for same range
        int[][] bookings5 = {{1,3,5}, {1,3,3}, {1,3,2}};
        int n5 = 3;
        System.out.println("\nTest 5: bookings = " + Arrays.deepToString(bookings5) + ", n = " + n5);
        int[] result5 = corporateFlightBooking(bookings5, n5);
        System.out.println("Output: " + Arrays.toString(result5));
        // Expected: [10, 10, 10]

        // Edge Case 4: Non-overlapping ranges
        int[][] bookings6 = {{1,1,10}, {2,2,20}, {3,3,30}};
        int n6 = 3;
        System.out.println("\nTest 6: bookings = " + Arrays.deepToString(bookings6) + ", n = " + n6);
        int[] result6 = corporateFlightBooking(bookings6, n6);
        System.out.println("Output: " + Arrays.toString(result6));
        // Expected: [10, 20, 30]

        // Edge Case 5: Large numbers
        int[][] bookings7 = {{1,10000,10000}};
        int n7 = 10000;
        System.out.println("\nTest 7: Large booking (10000 flights, 10000 seats)");
        long startTime = System.currentTimeMillis();
        int[] result7 = corporateFlightBooking(bookings7, n7);
        long endTime = System.currentTimeMillis();
        System.out.println("First 5: " + Arrays.toString(Arrays.copyOf(result7, 5)));
        System.out.println("Time: " + (endTime - startTime) + " ms");
        // Expected: all 10000

        // Performance Comparison: Difference Array vs Naive
        System.out.println("\n=== Performance Comparison ===");
        int[][] largeBookings = new int[20000][3];
        for (int i = 0; i < 20000; i++) {
            largeBookings[i] = new int[]{1, 100, 1}; // Small ranges
        }
        int largeN = 100;

        // Difference Array
        long start1 = System.currentTimeMillis();
        corporateFlightBooking(largeBookings, largeN);
        long end1 = System.currentTimeMillis();

        // Naive approach (only for small n to avoid timeout)
        long start2 = System.currentTimeMillis();
        corpFlightBookingsNaive(largeBookings, largeN);
        long end2 = System.currentTimeMillis();

        System.out.println("Difference Array (20000 bookings, n=100): " + (end1 - start1) + " ms");
        System.out.println("Naive Approach (20000 bookings, n=100): " + (end2 - start2) + " ms");
    }
}
