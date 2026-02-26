package DSA.ch1_logic.Interviews.ArithmeticNumberTheory;
/**
 * @author onyxwizard
 * @date 31-12-2025
 */

import java.util.Arrays;

public class Fibonacci {
    
    // ========== 1. Naive Recursion ==========
    public static int fibRecursive(int n) {
      if (n == 40)
        return 0;
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }
    
    // ========== 2. Memoization ==========
    public static int fibMemoization(int n) {
        if (n <= 1) return n;
        
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        memo[0] = 0;
        memo[1] = 1;
        
        return fibMemoHelper(n, memo);
    }
    
    private static int fibMemoHelper(int n, int[] memo) {
        if (memo[n] != -1) return memo[n];
        memo[n] = fibMemoHelper(n - 1, memo) + fibMemoHelper(n - 2, memo);
        return memo[n];
    }
    
    // ========== 3. Bottom-Up DP ==========
    public static int fibBottomUp(int n) {
        if (n <= 1) return n;
        
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }
    
    // ========== 4. Space Optimized (RECOMMENDED) ==========
    public static int fibSpaceOptimized(int n) {
        if (n <= 1) return n;
        
        int prev2 = 0;
        int prev1 = 1;
        
        for (int i = 2; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    // ========== 5. Matrix Exponentiation ==========
    public static int fibMatrix(int n) {
        if (n <= 1) return n;
        
        int[][] base = {{1, 1}, {1, 0}};
        int[][] result = matrixPower(base, n - 1);
        return result[0][0];
    }
    
    private static int[][] multiply(int[][] A, int[][] B) {
        return new int[][]{
            {A[0][0]*B[0][0] + A[0][1]*B[1][0], A[0][0]*B[0][1] + A[0][1]*B[1][1]},
            {A[1][0]*B[0][0] + A[1][1]*B[1][0], A[1][0]*B[0][1] + A[1][1]*B[1][1]}
        };
    }
    
    private static int[][] matrixPower(int[][] M, int power) {
        if (power == 0) return new int[][]{{1, 0}, {0, 1}};
        
        if (power % 2 == 0) {
            int[][] half = matrixPower(M, power / 2);
            return multiply(half, half);
        } else {
            int[][] half = matrixPower(M, power / 2);
            return multiply(multiply(half, half), M);
        }
    }
    
    // ========== 6. Golden Ratio ==========
    public static int fibFormula(int n) {
        double phi = (1 + Math.sqrt(5)) / 2;
        double fib = Math.pow(phi, n) / Math.sqrt(5);
        return (int) Math.round(fib);
    }
    
    // ========== TESTING ==========
    public static void main(String[] args) {
        int n = 10;
        
        System.out.println("Fibonacci F(" + n + ") using different methods:");
        System.out.println("1. Recursive: " + fibRecursive(n));
        System.out.println("2. Memoization: " + fibMemoization(n));
        System.out.println("3. Bottom-Up DP: " + fibBottomUp(n));
        System.out.println("4. Space Optimized: " + fibSpaceOptimized(n));
        System.out.println("5. Matrix Exponentiation: " + fibMatrix(n));
        System.out.println("6. Golden Ratio: " + fibFormula(n));
        
        // Test for larger n to show performance differences
        System.out.println("\nTesting for n=40 (except recursive):");
        n = 40;
        // Don't call recursive for n=40 (too slow!)
        System.out.println("Memoization: " + fibMemoization(n));
        System.out.println("Bottom-Up: " + fibBottomUp(n));
        System.out.println("Space Optimized: " + fibSpaceOptimized(n));
        System.out.println("Matrix: " + fibMatrix(n));
        
        // Test first few Fibonacci numbers
        System.out.println("\nFirst 10 Fibonacci numbers:");
        for (int i = 0; i <= 10; i++) {
            System.out.print(fibSpaceOptimized(i) + " ");
        }
    }
}