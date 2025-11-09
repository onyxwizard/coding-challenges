package PrefixSum.Basic_Prefix_Sum.Medium.RangeSumQuery2D;

import java.util.Arrays;

public class RangeSumQuery2D {

    /**
     * NumMatrix class for efficient 2D range sum queries.
     *
     * Approach: 2D Prefix Sum (Summed Area Table)
     *
     * Key Insights:
     * 1. prefix[i][j] = sum of rectangle from (0,0) to (i-1,j-1)
     * 2. Range sum formula:
     *    sum(row1,col1,row2,col2) = prefix[row2+1][col2+1]
     *                              - prefix[row1][col2+1]
     *                              - prefix[row2+1][col1]
     *                              + prefix[row1][col1]
     *
     * Time Complexity:
     * - Constructor: O(m×n)
     * - sumRegion: O(1)
     *
     * Space Complexity: O(m×n)
     */
    static class NumMatrix {
        private final int[][] prefix;

        public NumMatrix(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                this.prefix = new int[1][1];
                return;
            }

            int m = matrix.length;
            int n = matrix[0].length;
            this.prefix = new int[m + 1][n + 1];

            // Build 2D prefix sum
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    prefix[i + 1][j + 1] = matrix[i][j]
                            + prefix[i][j + 1]    // top
                            + prefix[i + 1][j]    // left
                            - prefix[i][j];       // top-left (added twice)
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            // Apply 2D range sum formula
            return prefix[row2 + 1][col2 + 1]
                    - prefix[row1][col2 + 1]
                    - prefix[row2 + 1][col1]
                    + prefix[row1][col1];
        }
    }

    // ==================== Comprehensive Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Range Sum Query 2D - Immutable ===\n");

        // Example from problem statement
        int[][] matrix1 = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };

        NumMatrix numMatrix = new NumMatrix(matrix1);

        System.out.println("Matrix:");
        for (int[] row : matrix1) {
            System.out.println(java.util.Arrays.toString(row));
        }

        // Test 1: [2,1] to [4,3] → expected 8
        int result1 = numMatrix.sumRegion(2, 1, 4, 3);
        System.out.println("\nTest 1: sumRegion(2, 1, 4, 3)");
        System.out.println("Output: " + result1 + " (Expected: 8)");
        // Elements: [2,0,1,5], [1,0,1,7], [0,3,0,5] → sub-rectangle [2,0,1], [1,0,1], [0,3,0] = 2+0+1+1+0+1+0+3+0 = 8

        // Test 2: [1,1] to [2,2] → expected 11
        int result2 = numMatrix.sumRegion(1, 1, 2, 2);
        System.out.println("\nTest 2: sumRegion(1, 1, 2, 2)");
        System.out.println("Output: " + result2 + " (Expected: 11)");
        // Elements: [6,3], [2,0] = 6+3+2+0 = 11

        // Test 3: [1,2] to [2,4] → expected 12
        int result3 = numMatrix.sumRegion(1, 2, 2, 4);
        System.out.println("\nTest 3: sumRegion(1, 2, 2, 4)");
        System.out.println("Output: " + result3 + " (Expected: 12)");
        // Elements: [3,2,1], [0,1,5] = 3+2+1+0+1+5 = 12

        // Edge Case 1: Single cell
        int result4 = numMatrix.sumRegion(0, 0, 0, 0);
        System.out.println("\nTest 4: sumRegion(0, 0, 0, 0) (single cell)");
        System.out.println("Output: " + result4 + " (Expected: 3)");

        // Edge Case 2: Full matrix
        int result5 = numMatrix.sumRegion(0, 0, 4, 4);
        System.out.println("\nTest 5: sumRegion(0, 0, 4, 4) (full matrix)");
        System.out.println("Output: " + result5 + " (Expected: 58)");

        // Edge Case 3: First row
        int result6 = numMatrix.sumRegion(0, 0, 0, 4);
        System.out.println("\nTest 6: sumRegion(0, 0, 0, 4) (first row)");
        System.out.println("Output: " + result6 + " (Expected: 10)"); // 3+0+1+4+2=10

        // Edge Case 4: First column
        int result7 = numMatrix.sumRegion(0, 0, 4, 0);
        System.out.println("\nTest 7: sumRegion(0, 0, 4, 0) (first column)");
        System.out.println("Output: " + result7 + " (Expected: 14)"); // 3+5+1+4+1=14

        // Edge Case 5: Last row, last column
        int result8 = numMatrix.sumRegion(4, 4, 4, 4);
        System.out.println("\nTest 8: sumRegion(4, 4, 4, 4) (last cell)");
        System.out.println("Output: " + result8 + " (Expected: 5)");

        // Edge Case 6: Empty matrix (defensive)
        try {
            NumMatrix emptyMatrix = new NumMatrix(new int[][]{});
            System.out.println("\nTest 9: Empty matrix - handled gracefully");
        } catch (Exception e) {
            System.out.println("\nTest 9: Empty matrix exception: " + e.getMessage());
        }
    }
}
