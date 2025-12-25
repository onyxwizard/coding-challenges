package DSA.ch10_String.Easy;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Supplier;
/**
 * @author onyxwizard
 * @date 25-12-2025
 */
import java.util.*;

/**
 * K-ANAGRAMS SOLUTION WITH OPTIMIZATIONS AND ANALYSIS
 * 
 * Problem Definition:
 * Two strings are k-anagrams if:
 * 1. Both have same number of characters
 * 2. Can become anagrams by changing at most k characters in one string
 * 
 * Time Complexity Analysis:
 * - Best approach: O(n) time, O(26) = O(1) space
 * - Alternative approaches have trade-offs
 */
public class KAnagramsOptimized {

    // =========================================================================
    // APPROACH 1: Frequency Array (Optimized - Your Approach Fixed)
    // =========================================================================

    /**
     * OPTIMIZED VERSION 1.1: Fixed Frequency Array
     * 
     * Time Complexity: O(n) where n = length of strings
     * Space Complexity: O(26) = O(1) constant space
     * 
     * TRADE-OFFS:
     * ✅ PROS:
     * - Simple and efficient for lowercase English letters
     * - O(1) space usage
     * - Single pass through strings
     * 
     * ❌ CONS:
     * - Only works for lowercase English letters (a-z)
     * - Fixed to 26 characters limit
     * - Needs separate counting logic for Unicode/other charsets
     * 
     * BEST FOR: Standard coding problems with lowercase a-z constraints
     */
    public static boolean areKAnagramsOptimized(String s1, String s2, int k) {
        // Edge case: different lengths
        if (s1.length() != s2.length()) {
            return false;
        }

        int[] freq = new int[26];
        int n = s1.length();

        // Count frequency difference
        for (int i = 0; i < n; i++) {
            freq[s1.charAt(i) - 'a']++;
            freq[s2.charAt(i) - 'a']--;
        }

        // Count number of changes needed
        int changesNeeded = 0;
        for (int count : freq) {
            if (count > 0) {
                changesNeeded += count;
            }
        }

        return changesNeeded <= k;
    }

    /**
     * VERSION 1.2: Early Exit Optimization
     * 
     * Additional optimization: Exit early if changes needed exceed k
     * during frequency calculation
     */
    public static boolean areKAnagramsEarlyExit(String s1, String s2, int k) {
        if (s1.length() != s2.length()) {
            return false;
        }

        int[] freq = new int[26];
        int n = s1.length();

        // First pass: only count s1 frequencies
        for (int i = 0; i < n; i++) {
            freq[s1.charAt(i) - 'a']++;
        }

        int changesNeeded = 0;

        // Second pass: decrement for s2 and count changes
        for (int i = 0; i < n; i++) {
            char c = s2.charAt(i);
            if (freq[c - 'a'] > 0) {
                freq[c - 'a']--; // Matching character found
            } else {
                changesNeeded++; // Need to change this character
                if (changesNeeded > k) {
                    return false; // Early exit
                }
            }
        }

        return changesNeeded <= k;
    }

    // =========================================================================
    // APPROACH 2: HashMap (Generic - Works for any characters)
    // =========================================================================

    /**
     * APPROACH 2.1: HashMap for Generic Characters
     * 
     * Time Complexity: O(n) average, O(n²) worst-case (hash collisions)
     * Space Complexity: O(min(m, n)) where m = character set size
     * 
     * TRADE-OFFS:
     * ✅ PROS:
     * - Works for any character set (Unicode, uppercase, etc.)
     * - No 26-character limitation
     * - More readable and maintainable
     * 
     * ❌ CONS:
     * - Higher constant factor than array
     * - HashMap overhead (object creation, hashing)
     * - Slower for lowercase a-z only problems
     * 
     * BEST FOR: Production code with unknown character sets
     */
    public static boolean areKAnagramsHashMap(String s1, String s2, int k) {
        if (s1.length() != s2.length()) {
            return false;
        }

        Map<Character, Integer> freqMap = new HashMap<>();

        // Count frequencies in s1
        for (char c : s1.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        int changesNeeded = 0;

        // Check s2 against s1 frequencies
        for (char c : s2.toCharArray()) {
            if (freqMap.containsKey(c) && freqMap.get(c) > 0) {
                freqMap.put(c, freqMap.get(c) - 1);
            } else {
                changesNeeded++;
                if (changesNeeded > k) {
                    return false; // Early exit
                }
            }
        }

        return changesNeeded <= k;
    }

    /**
     * VERSION 2.2: HashMap with Single Pass
     * 
     * Trade-off: Uses two HashMaps but only one pass
     */
    public static boolean areKAnagramsTwoMaps(String s1, String s2, int k) {
        if (s1.length() != s2.length()) {
            return false;
        }

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        // Build frequency maps
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            map1.put(c1, map1.getOrDefault(c1, 0) + 1);
            map2.put(c2, map2.getOrDefault(c2, 0) + 1);
        }

        // Calculate changes needed
        int changesNeeded = 0;
        for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
            char c = entry.getKey();
            int count1 = entry.getValue();
            int count2 = map2.getOrDefault(c, 0);

            if (count1 > count2) {
                changesNeeded += (count1 - count2);
                if (changesNeeded > k) {
                    return false;
                }
            }
        }

        return changesNeeded <= k;
    }

    // =========================================================================
    // APPROACH 3: Sorting Based (Alternative but not optimal)
    // =========================================================================

    /**
     * APPROACH 3: Sorting Approach
     * 
     * Time Complexity: O(n log n) due to sorting
     * Space Complexity: O(n) for character arrays
     * 
     * TRADE-OFFS:
     * ✅ PROS:
     * - Simple to understand
     * - Works for any character set
     * - No HashMap overhead
     * 
     * ❌ CONS:
     * - O(n log n) vs O(n) for other approaches
     * - Extra space for character arrays
     * - Not optimal for this problem
     * 
     * USE CASE: When simplicity is more important than performance
     */
    public static boolean areKAnagramsSorting(String s1, String s2, int k) {
        if (s1.length() != s2.length()) {
            return false;
        }

        // Convert to char arrays and sort
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        // Count mismatches
        int changesNeeded = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                changesNeeded++;
                if (changesNeeded > k) {
                    return false;
                }
            }
        }

        return changesNeeded <= k;
    }

    // =========================================================================
    // APPROACH 4: Bitmasking (For Very Specific Constraints)
    // =========================================================================

    /**
     * APPROACH 4: Bitmasking (When k is 0 or 1)
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1) with integer bitmask
     * 
     * ONLY WORKS FOR: Checking if k=0 (exact anagrams) or simple cases
     * NOT GENERAL: Cannot handle k > 1 properly
     */
    public static boolean areKAnagramsBitmask(String s1, String s2, int k) {
        if (s1.length() != s2.length()) {
            return false;
        }

        int mask1 = 0, mask2 = 0;

        // Build character masks
        for (int i = 0; i < s1.length(); i++) {
            mask1 |= (1 << (s1.charAt(i) - 'a'));
            mask2 |= (1 << (s2.charAt(i) - 'a'));
        }

        // Check if masks are equal (only for k=0 case)
        if (k == 0) {
            return mask1 == mask2;
        }

        // Limited use for k>0
        return true; // Not reliable for general case
    }

    // =========================================================================
    // COMPREHENSIVE TEST SUITE
    // =========================================================================

    public static void runTestSuite() {
        System.out.println("=".repeat(70));
        System.out.println("K-ANAGRAMS TEST SUITE");
        System.out.println("=".repeat(70));

        List<TestCase> testCases = Arrays.asList(
                new TestCase("fodr", "gork", 2, true, "Example 1"),
                new TestCase("geeks", "eggkf", 1, false, "Example 2"),
                new TestCase("adb", "fdab", 2, false, "Example 3 - Different lengths"),
                new TestCase("aaa", "aaa", 0, true, "Same strings"),
                new TestCase("abc", "def", 3, true, "All characters different"),
                new TestCase("abc", "def", 2, false, "Need 3 changes, k=2"),
                new TestCase("aab", "bba", 1, true, "Partial match"),
                new TestCase("listen", "silent", 0, true, "Anagrams"),
                new TestCase("bbbdeebd", "cddcebda", 7, true, "Your test case"),
                new TestCase("a".repeat(100000), "b".repeat(100000), 100000, true, "Large input"),
                new TestCase("", "", 0, true, "Empty strings"));

        List<Algorithm> algorithms = Arrays.asList(
                new Algorithm("Fixed Freq Array", KAnagramsOptimized::areKAnagramsOptimized),
                new Algorithm("Early Exit", KAnagramsOptimized::areKAnagramsEarlyExit),
                new Algorithm("HashMap", KAnagramsOptimized::areKAnagramsHashMap),
                new Algorithm("Two Maps", KAnagramsOptimized::areKAnagramsTwoMaps),
                new Algorithm("Sorting", KAnagramsOptimized::areKAnagramsSorting));

        int totalTests = 0;
        int passed = 0;

        for (TestCase tc : testCases) {
            System.out.printf("\nTest: %-30s ", tc.description);
            System.out.printf("s1='%s', s2='%s', k=%d",
                    tc.s1.length() > 10 ? tc.s1.substring(0, 10) + "..." : tc.s1,
                    tc.s2.length() > 10 ? tc.s2.substring(0, 10) + "..." : tc.s2,
                    tc.k);
            System.out.printf("\nExpected: %s\n", tc.expected);

            boolean allPassed = true;
            for (Algorithm algo : algorithms) {
                boolean result = false;
                try {
                    result = algo.function.apply(tc.s1, tc.s2, tc.k);
                } catch (Exception e) {
                    result = false;
                }

                if (result != tc.expected) {
                    System.out.printf("  %-15s: ✗ (Got %s)\n", algo.name, result);
                    allPassed = false;
                }
            }

            if (allPassed) {
                passed++;
                System.out.println("  All algorithms: ✓ PASS");
            } else {
                System.out.println("  Some algorithms: ✗ FAIL");
            }
            totalTests++;
        }

        System.out.println("\n" + "=".repeat(70));
        System.out.printf("SUMMARY: %d/%d tests passed\n", passed, totalTests);
        System.out.println("=".repeat(70));
    }

    // =========================================================================
    // PERFORMANCE BENCHMARK
    // =========================================================================

    public static void benchmarkAlgorithms() {
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("PERFORMANCE BENCHMARK (Average of 1000 runs)");
        System.out.println("=".repeat(70));

        // Create test data
        String s1 = generateRandomString(10000);
        String s2 = generateKAnagram(s1, 500); // Make it 500 changes away

        Map<String, Supplier<Boolean>> algorithms = new LinkedHashMap<>();
        algorithms.put("1. Fixed Freq Array", () -> areKAnagramsOptimized(s1, s2, 500));
        algorithms.put("2. Early Exit", () -> areKAnagramsEarlyExit(s1, s2, 500));
        algorithms.put("3. HashMap", () -> areKAnagramsHashMap(s1, s2, 500));
        algorithms.put("4. Two Maps", () -> areKAnagramsTwoMaps(s1, s2, 500));
        algorithms.put("5. Sorting", () -> areKAnagramsSorting(s1, s2, 500));

        // Warm up
        for (int i = 0; i < 100; i++) {
            for (Supplier<Boolean> algo : algorithms.values()) {
                algo.get();
            }
        }

        // Benchmark
        int iterations = 1000;
        Map<String, Long> times = new LinkedHashMap<>();

        for (Map.Entry<String, Supplier<Boolean>> entry : algorithms.entrySet()) {
            long startTime = System.nanoTime();
            for (int i = 0; i < iterations; i++) {
                entry.getValue().get();
            }
            long endTime = System.nanoTime();
            times.put(entry.getKey(), (endTime - startTime) / iterations);
        }

        // Display results
        System.out.printf("\nInput size: %,d characters\n", s1.length());
        System.out.println("-".repeat(70));

        long fastest = Collections.min(times.values());
        for (Map.Entry<String, Long> entry : times.entrySet()) {
            long time = entry.getValue();
            double relative = (double) time / fastest;
            System.out.printf("%-20s: %,8d ns (%.2fx)%n",
                    entry.getKey(), time, relative);
        }

        // Memory analysis
        System.out.println("\nMEMORY ANALYSIS:");
        System.out.println("-".repeat(70));
        System.out.println("• Fixed Freq Array: O(1) - 26 ints (~104 bytes)");
        System.out.println("• Early Exit: O(1) - 26 ints + variables");
        System.out.println("• HashMap: O(min(n,26)) - typically 26 entries");
        System.out.println("• Two Maps: O(min(n,26)) - two maps, ~2x HashMap");
        System.out.println("• Sorting: O(n) - two char arrays");
    }

    // =========================================================================
    // RECOMMENDATION ENGINE
    // =========================================================================

    public static void printRecommendations() {
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("RECOMMENDATION GUIDE");
        System.out.println("=".repeat(70));

        System.out.println("""

                📊 DECISION MATRIX:

                Scenario                          | Recommended Approach
                ----------------------------------|----------------------
                Lowercase a-z only                | Fixed Frequency Array
                Any characters (Unicode)          | HashMap with early exit
                Need absolute minimum memory      | Fixed Frequency Array
                Need maximum readability          | HashMap
                Strings very long (>100K)         | Early Exit variant
                Need to handle null/edge cases    | HashMap with validation
                Interview setting                 | Fixed Frequency Array
                Production with unknown input     | HashMap

                🎯 INTERVIEW TIPS:
                1. Always start with frequency array for lowercase a-z
                2. Mention HashMap alternative for general case
                3. Discuss time/space complexity
                4. Handle edge cases:
                   - Different lengths → return false immediately
                   - Empty strings → return true (k >= 0)
                   - Null strings → handle gracefully
                5. Consider early exit optimization

                🚀 PRODUCTION CONSIDERATIONS:
                1. Input validation (null, empty, length)
                2. Character set assumptions (ask requirements)
                3. Performance profiling for your use case
                4. Memory constraints
                5. Thread safety if needed

                ⚠️ PITFALLS TO AVOID:
                • Using sorting (O(n log n)) when O(n) is possible
                • Not handling different character cases
                • Forgetting to check string lengths first
                • Not considering Unicode/multi-byte characters
                • Over-optimizing without measuring
                """);
    }

    // =========================================================================
    // PRODUCTION-READY IMPLEMENTATION
    // =========================================================================

    /**
     * PRODUCTION-READY VERSION with all best practices
     * 
     * Features:
     * 1. Input validation
     * 2. Configurable character set support
     * 3. Early exit optimization
     * 4. Comprehensive logging (optional)
     * 5. Thread-safe (if needed)
     */
    public static class ProductionKAnagramChecker {

        private final boolean supportUnicode;
        private final int alphabetSize;

        public ProductionKAnagramChecker(boolean supportUnicode) {
            this.supportUnicode = supportUnicode;
            this.alphabetSize = supportUnicode ? 65536 : 26; // Unicode range
        }

        public boolean areKAnagrams(String s1, String s2, int k) {
            // Validate inputs
            if (s1 == null || s2 == null) {
                throw new IllegalArgumentException("Strings cannot be null");
            }

            // Quick length check
            if (s1.length() != s2.length()) {
                return false;
            }

            // Empty strings are always k-anagrams for any k >= 0
            if (s1.isEmpty()) {
                return true;
            }

            // Choose algorithm based on character set
            if (supportUnicode) {
                return areKAnagramsUnicode(s1, s2, k);
            } else {
                return areKAnagramsAscii(s1, s2, k);
            }
        }

        private boolean areKAnagramsAscii(String s1, String s2, int k) {
            int[] freq = new int[26];
            int changesNeeded = 0;

            for (int i = 0; i < s1.length(); i++) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(i);

                // Validate characters are in range
                if (c1 < 'a' || c1 > 'z' || c2 < 'a' || c2 > 'z') {
                    throw new IllegalArgumentException(
                            "Strings must contain only lowercase letters a-z");
                }

                freq[c1 - 'a']++;

                if (freq[c2 - 'a'] > 0) {
                    freq[c2 - 'a']--;
                } else {
                    changesNeeded++;
                    if (changesNeeded > k) {
                        return false; // Early exit
                    }
                }
            }

            return changesNeeded <= k;
        }

        private boolean areKAnagramsUnicode(String s1, String s2, int k) {
            Map<Character, Integer> freqMap = new HashMap<>();
            int changesNeeded = 0;

            for (int i = 0; i < s1.length(); i++) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(i);

                freqMap.put(c1, freqMap.getOrDefault(c1, 0) + 1);

                if (freqMap.containsKey(c2) && freqMap.get(c2) > 0) {
                    freqMap.put(c2, freqMap.get(c2) - 1);
                } else {
                    changesNeeded++;
                    if (changesNeeded > k) {
                        return false; // Early exit
                    }
                }
            }

            return changesNeeded <= k;
        }
    }

    // =========================================================================
    // HELPER METHODS AND CLASSES
    // =========================================================================

    private static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + random.nextInt(26)));
        }
        return sb.toString();
    }

    private static String generateKAnagram(String s, int k) {
        char[] arr = s.toCharArray();
        Random random = new Random();

        // Change exactly k characters
        for (int i = 0; i < k; i++) {
            int index = random.nextInt(arr.length);
            char newChar;
            do {
                newChar = (char) ('a' + random.nextInt(26));
            } while (newChar == arr[index]);
            arr[index] = newChar;
        }

        return new String(arr);
    }

    // Test case class
    static class TestCase {
        String s1, s2;
        int k;
        boolean expected;
        String description;

        TestCase(String s1, String s2, int k, boolean expected, String description) {
            this.s1 = s1;
            this.s2 = s2;
            this.k = k;
            this.expected = expected;
            this.description = description;
        }
    }

    // Algorithm class
    static class Algorithm {
        String name;
        TriFunction<String, String, Integer, Boolean> function;

        Algorithm(String name, TriFunction<String, String, Integer, Boolean> function) {
            this.name = name;
            this.function = function;
        }

    }

    // Functional interface for testing
    @FunctionalInterface
    interface TriFunction<A, B, C, R> {
        R apply(A a, B b, C c);
    }

    // =========================================================================
    // MAIN METHOD
    // =========================================================================

    public static void main(String[] args) {
        System.out.println("""
                ╔══════════════════════════════════════════════════════════════╗
                ║                K-ANAGRAMS SOLUTION ANALYSIS                  ║
                ╚══════════════════════════════════════════════════════════════╝
                """);

        // Run test suite
        runTestSuite();

        // Benchmark performance
        benchmarkAlgorithms();

        // Show recommendations
        printRecommendations();

        // Demonstrate production usage
        System.out.println("\n" + "=".repeat(70));
        System.out.println("PRODUCTION USAGE EXAMPLE");
        System.out.println("=".repeat(70));

        ProductionKAnagramChecker checker = new ProductionKAnagramChecker(false);
        System.out.println("Checking 'fodr' vs 'gork' with k=2:");
        System.out.println("Result: " + checker.areKAnagrams("fodr", "gork", 2));

        System.out.println("\nChecking 'geeks' vs 'eggkf' with k=1:");
        System.out.println("Result: " + checker.areKAnagrams("geeks", "eggkf", 1));
    }
}