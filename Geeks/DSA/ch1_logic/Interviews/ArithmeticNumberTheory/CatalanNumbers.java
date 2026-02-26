package DSA.ch1_logic.Interviews.ArithmeticNumberTheory;
/**
 * @author onyxwizard
 * @date 31-12-2025
 */

import java.math.BigInteger;

public class CatalanNumbers {
    
    // ========== 1. Dynamic Programming ==========
    public static long catalanDP(int n) {
        if (n <= 1) return 1;
        
        long[] catalan = new long[n + 1];
        catalan[0] = 1;
        catalan[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            catalan[i] = 0;
            for (int j = 0; j < i; j++) {
                catalan[i] += catalan[j] * catalan[i - 1 - j];
            }
        }
        
        return catalan[n];
    }
    
    // ========== 2. Binomial Coefficient (RECOMMENDED) ==========
    public static long catalanBinomial(int n) {
        if (n <= 1) return 1;
        
        long result = 1;
        
        // Calculate binomial coefficient C(2n, n)
        for (int i = 0; i < n; i++) {
            result *= (2L * n - i);
            result /= (i + 1);
        }
        
        // Divide by (n+1) to get Catalan number
        return result / (n + 1);
    }
    
    // ========== 3. Recurrence Relation ==========
    public static long catalanRecurrence(int n) {
        if (n <= 1) return 1;
        
        long result = 1;  // C₀
        
        for (int i = 1; i <= n; i++) {
            result = (2L * (2L * i - 1) * result) / (i + 1);
        }
        
        return result;
    }
    
    // ========== BONUS: BigInteger version for large n ==========
    public static BigInteger catalanBigInteger(int n) {
        if (n <= 1) return BigInteger.ONE;
        
        BigInteger result = BigInteger.ONE;
        
        // Calculate C(2n, n)
        for (int i = 0; i < n; i++) {
            result = result.multiply(BigInteger.valueOf(2L * n - i));
            result = result.divide(BigInteger.valueOf(i + 1));
        }
        
        return result.divide(BigInteger.valueOf(n + 1));
    }
    
    // ========== TESTING ==========
    public static void main(String[] args) {
        System.out.println("Catalan Numbers (n = 0 to 10):");
        System.out.println("n\tDP\tBinomial\tRecurrence\tBigInteger");
        
        for (int n = 0; n <= 10; n++) {
            long dp = catalanDP(n);
            long binomial = catalanBinomial(n);
            long recurrence = catalanRecurrence(n);
            BigInteger bigInt = catalanBigInteger(n);
            
            System.out.printf("%d\t%d\t%d\t\t%d\t\t%s\n", 
                n, dp, binomial, recurrence, bigInt);
        }
        
        // Test with larger n (where long might overflow)
        System.out.println("\nTesting larger n (n=20):");
        System.out.println("DP: " + catalanDP(20));
        System.out.println("Binomial: " + catalanBinomial(20));
        System.out.println("Recurrence: " + catalanRecurrence(20));
        System.out.println("BigInteger: " + catalanBigInteger(20));
        
        // Applications
        System.out.println("\n=== Applications ===");
        int pairs = 3;
        System.out.println("Number of valid parentheses with " + pairs + " pairs: " + catalanBinomial(pairs));
        System.out.println("Number of BSTs with " + pairs + " nodes: " + catalanBinomial(pairs));
        System.out.println("Number of ways to triangulate polygon with " + (pairs+2) + " sides: " + catalanBinomial(pairs));
    }
}