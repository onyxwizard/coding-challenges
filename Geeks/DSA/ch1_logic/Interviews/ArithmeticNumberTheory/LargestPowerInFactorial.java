package DSA.ch1_logic.Interviews.ArithmeticNumberTheory;
/**
 * @author onyxwizard
 * @date 31-12-2025
 */

import java.util.*;

public class LargestPowerInFactorial {
    
    // ========== APPROACH 1: NAIVE ==========
    public static int naiveApproach(int n, int k) {
        long factorial = 1;
        
        // Compute n!
        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }
        
        // Count divisions by k
        int power = 0;
        while (factorial % k == 0) {
            factorial /= k;
            power++;
        }
        
        return power;
    }
    
    // ========== APPROACH 2: PRIME COUNT ==========
    public static int primeCountApproach(int n, int k) {
        if (!isPrime(k)) {
            System.out.println("Warning: k is not prime!");
        }
        
        int count = 0;
        long power = k;  // Use long to prevent overflow
        
        while (n / power > 0) {
            count += n / power;
            if (power > n / k) break;  // Prevent overflow
            power *= k;
        }
        
        return count;
    }
    
    private static boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num <= 3) return true;
        if (num % 2 == 0 || num % 3 == 0) return false;
        
        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) return false;
        }
        return true;
    }
    
    // ========== APPROACH 3: LEGENDRE FOR COMPOSITE ==========
    public static int legendreMethod(int n, int k) {
        if (n == 0) return 0;  // 0! = 1, no factors
        
        int result = Integer.MAX_VALUE;
        int tempK = k;
        
        // Check all prime factors up to sqrt(k)
        for (int prime = 2; prime * prime <= tempK; prime++) {
            if (tempK % prime == 0) {
                int exponentInK = 0;
                
                // Extract all occurrences of this prime
                while (tempK % prime == 0) {
                    tempK /= prime;
                    exponentInK++;
                }
                
                // Count this prime in n!
                int countInFactorial = countPrimeInFactorial(n, prime);
                
                // How many complete sets can we make?
                int sets = countInFactorial / exponentInK;
                
                // Update minimum
                result = Math.min(result, sets);
            }
        }
        
        // Handle remaining prime factor (if any)
        if (tempK > 1) {
            int countInFactorial = countPrimeInFactorial(n, tempK);
            // exponentInK is 1 for the remaining prime
            result = Math.min(result, countInFactorial);
        }
        
        return result == Integer.MAX_VALUE ? 0 : result;
    }
    
    // Helper: Count prime p in n!
    private static int countPrimeInFactorial(int n, int p) {
        int count = 0;
        long power = p;
        
        while (n / power > 0) {
            count += n / power;
            if (power > n / p) break;  // Prevent overflow
            power *= p;
        }
        
        return count;
    }
    
    // ========== TEST CASES ==========
    public static void main(String[] args) {
        // Test cases from problem
        System.out.println("=== Test Cases ===");
        
        // Test 1: n=7, k=2
        System.out.println("\nTest 1: n=7, k=2");
        System.out.println("Naive: " + naiveApproach(7, 2));
        System.out.println("Prime Count: " + primeCountApproach(7, 2));
        System.out.println("Legendre: " + legendreMethod(7, 2));
        System.out.println("Expected: 4");
        
        // Test 2: n=10, k=9
        System.out.println("\nTest 2: n=10, k=9");
        System.out.println("Naive: " + naiveApproach(10, 9));
        System.out.println("Prime Count: " + primeCountApproach(10, 9));
        System.out.println("Legendre: " + legendreMethod(10, 9));
        System.out.println("Expected: 2");
        
        // Test 3: n=10, k=12 (composite)
        System.out.println("\nTest 3: n=10, k=12");
        System.out.println("Legendre: " + legendreMethod(10, 12));
        System.out.println("Expected: 4");
        
        // Test 4: n=100, k=10 (would overflow with naive)
        System.out.println("\nTest 4: n=100, k=10");
        System.out.println("Legendre: " + legendreMethod(100, 10));
        
        // Compare approaches for small n
        System.out.println("\n=== Comparison for small n ===");
        System.out.println("n=6, k=4");
        System.out.println("Naive: " + naiveApproach(6, 4));
        System.out.println("Legendre: " + legendreMethod(6, 4));
    }
}