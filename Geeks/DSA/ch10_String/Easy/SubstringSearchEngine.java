package DSA.ch10_String.Easy;

/**
 * @author onyxwizard
 * @date 24-12-2025
 */

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * COMPREHENSIVE SUBSTRING SEARCH IMPLEMENTATION
 * 
 * This class provides multiple approaches to substring search with detailed
 * trade-off analysis for each implementation. Designed for both educational
 * understanding and production-ready scenarios.
 * 
 * Author: AI Assistant
 * Date: 2024
 * Version: 1.0.0
 */
public class SubstringSearchEngine {

    // =========================================================================
    // 1. BRUTE FORCE APPROACHES
    // =========================================================================

    /**
     * APPROACH 1.1: Basic Brute Force (Character-by-Character)
     * 
     * TRADE-OFFS:
     * ✅ PROS:
     * - Simple, easy to understand and implement
     * - O(1) space complexity (no extra data structures)
     * - Good for short strings or one-time searches
     * - Early exit on mismatch improves average case
     * 
     * ❌ CONS:
     * - O(m*n) worst-case time complexity
     * - Poor performance on repetitive patterns (e.g., "aaaaa" in "aaaaaaaaab")
     * - Not optimal for production with large texts
     * 
     * USE CASES:
     * - Interview coding questions
     * - Small text processing (< 1KB)
     * - Educational purposes
     * 
     * PERFORMANCE:
     * - Best case: O(n) when first character never matches
     * - Average case: O(n + m) for random strings
     * - Worst case: O(m*n) for worst-case patterns
     */
    public static int bruteForceCharacter(String txt, String pat) {
        // Guard clauses for edge cases
        if (pat == null || txt == null)
            return -1;
        if (pat.isEmpty())
            return 0; // Empty pattern matches at index 0
        if (pat.length() > txt.length())
            return -1;

        int n = txt.length();
        int m = pat.length();

        // Outer loop: all possible starting positions in text
        for (int i = 0; i <= n - m; i++) {
            int j = 0;

            // Inner loop: compare characters one by one
            while (j < m && txt.charAt(i + j) == pat.charAt(j)) {
                j++;
            }

            // If we matched all characters of pattern
            if (j == m) {
                return i;
            }
        }

        return -1;
    }

    /**
     * APPROACH 1.2: Optimized Brute Force with First Char Check
     * 
     * OPTIMIZATION: Skip unnecessary comparisons by checking first character
     * separately
     * 
     * TRADE-OFFS:
     * ✅ PROS:
     * - Reduces comparisons when first character mismatches frequently
     * - Maintains O(1) space complexity
     * - Better cache locality than substring approach
     * 
     * ❌ CONS:
     * - Still O(m*n) worst-case
     * - Extra branching may hurt performance on modern CPUs with branch prediction
     * - Not significantly better than basic brute force for random text
     * 
     * USE CASES:
     * - When pattern has distinctive first character
     * - When most searches will fail early
     */
    public static int bruteForceOptimized(String txt, String pat) {
        if (pat == null || txt == null)
            return -1;
        if (pat.isEmpty())
            return 0;
        if (pat.length() > txt.length())
            return -1;

        int n = txt.length();
        int m = pat.length();
        char first = pat.charAt(0);
        int max = n - m;

        for (int i = 0; i <= max; i++) {
            // Quick skip if first character doesn't match
            if (txt.charAt(i) != first) {
                // Skip to next occurrence of first character
                while (++i <= max && txt.charAt(i) != first)
                    ;
            }

            // Found possible match, check remaining characters
            if (i <= max) {
                int j = 1;
                while (j < m && txt.charAt(i + j) == pat.charAt(j)) {
                    j++;
                }
                if (j == m) {
                    return i;
                }
            }
        }

        return -1;
    }

    // =========================================================================
    // 2. JAVA BUILT-IN APPROACHES
    // =========================================================================

    /**
     * APPROACH 2.1: Standard Java Built-in Method
     * 
     * TRADE-OFFS:
     * ✅ PROS:
     * - Highly optimized by Java team (uses platform-specific optimizations)
     * - Handles all edge cases (null, empty strings, Unicode)
     * - Maintainable and readable code
     * - Performance comparable to or better than custom implementations
     * - Security: No risk of off-by-one errors or buffer overflows
     * 
     * ❌ CONS:
     * - Black box - can't customize for specific use cases
     * - Implementation may change between Java versions
     * - Not suitable for interview coding questions
     * 
     * IMPLEMENTATION NOTES (Java 17):
     * - Uses Two-Way String Search algorithm (Crochemore-Perrin)
     * - Optimized for both small and large strings
     * - Handles locale-sensitive comparison if needed
     * 
     * PRODUCTION RECOMMENDATION:
     * - Use this in 95% of production scenarios
     * - Only implement custom solution if profiling shows it's a bottleneck
     */
    public static int builtInIndexOf(String txt, String pat) {
        // Null safety is handled by Java, but we add explicit check for clarity
        if (txt == null || pat == null)
            return -1;
        return txt.indexOf(pat);
    }

    /**
     * APPROACH 2.2: Using contains() with indexOf()
     * 
     * TRADE-OFFS:
     * ✅ PROS:
     * - More readable intent when you only need boolean result
     * - Can be optimized by JIT compiler
     * 
     * ❌ CONS:
     * - Double computation if you need the index (contains calls indexOf
     * internally)
     * - Slightly more overhead for boolean-only checks
     * 
     * USE CASES:
     * - When you only need to know if substring exists (not position)
     */
    public static boolean builtInContains(String txt, String pat) {
        return txt != null && pat != null && txt.contains(pat);
    }

    // =========================================================================
    // 3. ADVANCED ALGORITHMS
    // =========================================================================

    /**
     * APPROACH 3.1: Knuth-Morris-Pratt (KMP) Algorithm
     * 
     * TRADE-OFFS:
     * ✅ PROS:
     * - O(m+n) guaranteed worst-case time complexity
     * - Never re-scans characters in text
     * - Excellent for searching in streams or with backtracking limitations
     * - Good for searching same pattern multiple times (preprocess once)
     * 
     * ❌ CONS:
     * - O(m) space for LPS (Longest Prefix Suffix) array
     * - Higher constant factors than brute force for short patterns
     * - Complex implementation with risk of bugs
     * - Overkill for one-time searches with short strings
     * 
     * USE CASES:
     * - DNA sequence analysis (repetitive patterns)
     * - Text editors with "Find" functionality
     * - Network packet inspection
     * - When worst-case performance must be guaranteed
     * 
     * IMPLEMENTATION DETAILS:
     * - Preprocessing time: O(m)
     * - Search time: O(n)
     * - Space: O(m)
     */
    public static int kmpSearch(String txt, String pat) {
        // Edge cases
        if (pat == null || txt == null)
            return -1;
        if (pat.isEmpty())
            return 0;
        if (pat.length() > txt.length())
            return -1;

        int n = txt.length();
        int m = pat.length();

        // Preprocess pattern: compute LPS array
        int[] lps = computeLPSArray(pat);

        int i = 0; // index for txt
        int j = 0; // index for pat

        while (i < n) {
            if (pat.charAt(j) == txt.charAt(i)) {
                i++;
                j++;

                if (j == m) {
                    // Pattern found at index i - j
                    return i - j;
                }
            } else {
                // Mismatch after j matches
                if (j != 0) {
                    // Use LPS to skip unnecessary comparisons
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return -1;
    }

    /**
     * Helper method for KMP: Compute LPS array
     * 
     * LPS[i] = the longest proper prefix of pat[0..i]
     * which is also a suffix of pat[0..i]
     */
    private static int[] computeLPSArray(String pat) {
        int m = pat.length();
        int[] lps = new int[m];

        int len = 0; // length of previous longest prefix suffix
        int i = 1;

        while (i < m) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    // Fall back to previous longest prefix suffix
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    /**
     * APPROACH 3.2: Boyer-Moore Algorithm
     * 
     * TRADE-OFFS:
     * ✅ PROS:
     * - Often sublinear in practice (skips many characters)
     * - Excellent for natural language text
     * - Two heuristics: Bad Character Rule and Good Suffix Rule
     * - Performs well with large alphabets
     * 
     * ❌ CONS:
     * - O(m + σ) space (σ = alphabet size)
     * - Complex implementation
     * - Worst-case O(m*n) (but rare in practice)
     * - Not optimal for small alphabets (e.g., binary data)
     * 
     * USE CASES:
     * - Text editors and IDEs
     * - Natural language processing
     * - When searching English text
     * - When pattern is relatively long
     */
    public static int boyerMooreSearch(String txt, String pat) {
        if (pat == null || txt == null)
            return -1;
        if (pat.isEmpty())
            return 0;
        if (pat.length() > txt.length())
            return -1;

        int n = txt.length();
        int m = pat.length();

        // Preprocessing: Bad Character Heuristic
        int[] badChar = new int[256]; // Assuming extended ASCII

        // Initialize all occurrences as -1
        Arrays.fill(badChar, -1);

        // Fill actual value of last occurrence of each character
        for (int i = 0; i < m; i++) {
            badChar[pat.charAt(i)] = i;
        }

        int s = 0; // Shift of pattern with respect to text

        while (s <= n - m) {
            int j = m - 1;

            // Compare from right to left
            while (j >= 0 && pat.charAt(j) == txt.charAt(s + j)) {
                j--;
            }

            if (j < 0) {
                // Pattern found
                return s;
            } else {
                // Shift pattern based on bad character heuristic
                int shift = Math.max(1, j - badChar[txt.charAt(s + j)]);
                s += shift;
            }
        }

        return -1;
    }

    /**
     * APPROACH 3.3: Rabin-Karp Algorithm (Rolling Hash)
     * 
     * TRADE-OFFS:
     * ✅ PROS:
     * - O(m+n) average case
     * - Can find multiple patterns efficiently
     * - Easy to extend to 2D pattern matching
     * - Good for plagiarism detection
     * 
     * ❌ CONS:
     * - O(m*n) worst case due to hash collisions
     * - Requires careful choice of prime/modulus
     * - Additional verification step needed
     * - Slower than KMP for single pattern search
     * 
     * USE CASES:
     * - Plagiarism detection
     * - DNA sequence matching
     * - Finding multiple patterns simultaneously
     * - When pattern has wildcards
     */
    public static int rabinKarpSearch(String txt, String pat) {
        if (pat == null || txt == null)
            return -1;
        if (pat.isEmpty())
            return 0;
        if (pat.length() > txt.length())
            return -1;

        int n = txt.length();
        int m = pat.length();

        // Prime number for hash calculation
        int prime = 101;
        int base = 256; // Number of characters in alphabet

        // Calculate hash value for pattern and first window of text
        int patHash = 0;
        int txtHash = 0;
        int h = 1; // pow(base, m-1) % prime

        // Calculate h = base^(m-1) % prime
        for (int i = 0; i < m - 1; i++) {
            h = (h * base) % prime;
        }

        // Calculate initial hash values
        for (int i = 0; i < m; i++) {
            patHash = (base * patHash + pat.charAt(i)) % prime;
            txtHash = (base * txtHash + txt.charAt(i)) % prime;
        }

        // Slide pattern over text
        for (int i = 0; i <= n - m; i++) {
            // Check hash values first
            if (patHash == txtHash) {
                // If hash matches, verify character by character
                int j;
                for (j = 0; j < m; j++) {
                    if (txt.charAt(i + j) != pat.charAt(j)) {
                        break;
                    }
                }

                if (j == m) {
                    return i; // Pattern found
                }
            }

            // Calculate hash for next window
            if (i < n - m) {
                txtHash = (base * (txtHash - txt.charAt(i) * h) +
                        txt.charAt(i + m)) % prime;

                // Ensure hash is positive
                if (txtHash < 0) {
                    txtHash += prime;
                }
            }
        }

        return -1;
    }

    /**
     * APPROACH 3.4: Z-Algorithm (Linear Time Pattern Search)
     * 
     * TRADE-OFFS:
     * ✅ PROS:
     * - O(m+n) time complexity
     * - Simpler than KMP for some implementations
     * - Can be used for multiple pattern search
     * 
     * ❌ CONS:
     * - O(m+n) space complexity
     * - Less known than KMP
     * - Requires concatenation of strings
     * 
     * USE CASES:
     * - Competitive programming
     * - When you need both prefix and substring matching
     */
    public static int zAlgorithmSearch(String txt, String pat) {
        if (pat == null || txt == null)
            return -1;
        if (pat.isEmpty())
            return 0;
        if (pat.length() > txt.length())
            return -1;

        // Create concatenated string: pattern + '$' + text
        String concat = pat + "$" + txt;
        int n = concat.length();
        int m = pat.length();

        int[] z = new int[n];

        // Compute Z-array
        int l = 0, r = 0;
        for (int i = 1; i < n; i++) {
            if (i <= r) {
                z[i] = Math.min(r - i + 1, z[i - l]);
            }

            while (i + z[i] < n && concat.charAt(z[i]) == concat.charAt(i + z[i])) {
                z[i]++;
            }

            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }

        // Search for pattern
        for (int i = m + 1; i < n; i++) {
            if (z[i] == m) {
                return i - m - 1;
            }
        }

        return -1;
    }

    // =========================================================================
    // 4. SPECIALIZED APPROACHES
    // =========================================================================

    /**
     * APPROACH 4.1: Stream-Oriented Search (Memory Efficient)
     * 
     * TRADE-OFFS:
     * ✅ PROS:
     * - O(1) memory for text (processes as stream)
     * - Can handle extremely large files
     * - No need to load entire text into memory
     * 
     * ❌ CONS:
     * - O(m) memory for pattern
     * - Cannot jump backward in text
     * - More complex state management
     * 
     * USE CASES:
     * - Log file analysis
     * - Network traffic monitoring
     * - Large database text fields
     */
    public static int streamSearch(java.io.Reader textReader, String pat)
            throws java.io.IOException {
        if (pat == null || pat.isEmpty())
            return 0;

        int m = pat.length();
        char[] buffer = new char[m];
        int readChars;
        int position = 0;

        // Initialize buffer with first m characters
        readChars = textReader.read(buffer, 0, m);
        if (readChars < m)
            return -1;

        while (true) {
            // Check if current buffer matches pattern
            boolean match = true;
            for (int i = 0; i < m; i++) {
                if (buffer[i] != pat.charAt(i)) {
                    match = false;
                    break;
                }
            }

            if (match) {
                return position;
            }

            // Shift buffer and read next character
            for (int i = 0; i < m - 1; i++) {
                buffer[i] = buffer[i + 1];
            }

            int nextChar = textReader.read();
            if (nextChar == -1)
                break;

            buffer[m - 1] = (char) nextChar;
            position++;
        }

        return -1;
    }

    /**
     * APPROACH 4.2: Case-Insensitive Search
     * 
     * TRADE-OFFS:
     * ✅ PROS:
     * - Handles case variations
     * - Useful for user-facing search
     * - More robust for real-world text
     * 
     * ❌ CONS:
     * - Slower due to case conversion
     * - Locale-sensitive (may have unexpected behavior)
     * - May not preserve original case in result
     * 
     * USE CASES:
     * - Search engines
     * - Database queries
     * - User interface search boxes
     */
    public static int caseInsensitiveSearch(String txt, String pat) {
        if (pat == null || txt == null)
            return -1;
        return txt.toLowerCase().indexOf(pat.toLowerCase());
    }

    // =========================================================================
    // 5. YOUR ORIGINAL APPROACH (with analysis)
    // =========================================================================

    /**
     * ORIGINAL APPROACH: substring() + equals()
     * 
     * CRITICAL ANALYSIS:
     * 
     * 🚨 MAJOR ISSUES:
     * 1. OBJECT CREATION OVERHEAD: Creates O(n) String objects in loop
     * 2. MEMORY CHURN: High garbage collection pressure
     * 3. HIDDEN COSTS: substring() may copy characters in Java 7+
     * 4. POOR CACHE LOCALITY: New objects disrupt CPU cache
     * 
     * DETAILED BREAKDOWN:
     * - Time: O(m*n) worst-case (same as brute force but with higher constants)
     * - Space: O(m) per iteration for substring creation
     * - Objects: Creates (n-m+1) String objects, all but one become garbage
     * 
     * WHY AVOID IN PRODUCTION:
     * - For 1MB text and 100-char pattern: Creates ~1M String objects!
     * - Each String: 24-40 bytes overhead + char array
     * - GC overhead can be 100x slower than character comparison
     * 
     * LEARNING VALUE:
     * - Good example of how simple-looking code can have hidden costs
     * - Illustrates importance of understanding library method implementations
     * - Shows why algorithmic complexity isn't the whole story
     */
    public static int originalApproach(String txt, String pat) {
        int left = 0, right = pat.length();
        int n = txt.length();
        while (right <= n) {
            // 🚨 PROBLEM: Creates new String object each iteration
            // In Java 7+: copies characters (O(m) time and space)
            // In Java 6: shares char array (memory leak risk)
            String s = txt.substring(left, right);

            // 🚨 PROBLEM: Full comparison even if first char differs
            // Uses O(m) time, no early exit for first char mismatch
            if (s.equals(pat)) {
                return left;
            }
            left++;
            right++;
        }
        return -1;
    }

    // =========================================================================
    // 6. PERFORMANCE BENCHMARKING UTILITY
    // =========================================================================

    /**
     * Performance benchmarking for all algorithms
     */
    public static void benchmarkAlgorithms(String txt, String pat, String scenario) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("BENCHMARK: " + scenario);
        System.out.println("Text length: " + txt.length() +
                ", Pattern length: " + pat.length());
        System.out.println("=".repeat(70));

        Map<String, Runnable> algorithms = new LinkedHashMap<>();
        algorithms.put("1. Brute Force (Char-by-Char)", () -> bruteForceCharacter(txt, pat));
        algorithms.put("2. Optimized Brute Force", () -> bruteForceOptimized(txt, pat));
        algorithms.put("3. Java Built-in indexOf()", () -> builtInIndexOf(txt, pat));
        algorithms.put("4. KMP Algorithm", () -> kmpSearch(txt, pat));
        algorithms.put("5. Boyer-Moore", () -> boyerMooreSearch(txt, pat));
        algorithms.put("6. Rabin-Karp", () -> rabinKarpSearch(txt, pat));
        algorithms.put("7. Z-Algorithm", () -> zAlgorithmSearch(txt, pat));
        algorithms.put("8. Original (substring+equals)", () -> originalApproach(txt, pat));

        Map<String, Long> times = new LinkedHashMap<>();

        // Warm up JVM
        for (int i = 0; i < 1000; i++) {
            for (Runnable algo : algorithms.values()) {
                algo.run();
            }
        }

        // Benchmark each algorithm
        for (Map.Entry<String, Runnable> entry : algorithms.entrySet()) {
            long startTime = System.nanoTime();
            entry.getValue().run();
            long endTime = System.nanoTime();
            times.put(entry.getKey(), endTime - startTime);
        }

        // Display results
        System.out.println("\nPERFORMANCE RESULTS (nanoseconds):");
        System.out.println("-".repeat(70));

        // Find fastest time for comparison
        long fastest = Collections.min(times.values());

        for (Map.Entry<String, Long> entry : times.entrySet()) {
            long time = entry.getValue();
            double relative = (double) time / fastest;
            System.out.printf("%-35s: %,12d ns (%.2fx)%n",
                    entry.getKey(), time, relative);
        }

        // Memory analysis
        System.out.println("\nMEMORY ANALYSIS:");
        System.out.println("-".repeat(70));
        System.out.println("• Brute Force (Char-by-Char): O(1) space");
        System.out.println("• Java indexOf(): O(1) space (optimized)");
        System.out.println("• KMP Algorithm: O(m) space for LPS array");
        System.out.println("• Boyer-Moore: O(σ) space (σ = alphabet size)");
        System.out.println("• Original Approach: O(m) space per iteration (creates objects)");

        System.out.println("\nRECOMMENDATION FOR THIS SCENARIO:");
        System.out.println("-".repeat(70));
        recommendAlgorithm(txt, pat);
    }

    /**
     * Intelligent algorithm recommendation based on input characteristics
     */
    private static void recommendAlgorithm(String txt, String pat) {
        int n = txt.length();
        int m = pat.length();

        System.out.print("   → ");

        if (m == 0) {
            System.out.println("Use direct return 0 (empty pattern)");
            return;
        }

        if (m > n) {
            System.out.println("Use direct return -1 (pattern longer than text)");
            return;
        }

        // Analyze pattern characteristics
        boolean hasRepeats = hasRepeatingPattern(pat);
        int alphabetSize = calculateAlphabetSize(txt + pat);

        if (n < 100 && m < 10) {
            System.out.println("Brute Force (simple, low overhead for small strings)");
        } else if (m < 5) {
            System.out.println("Optimized Brute Force (quick first char check)");
        } else if (hasRepeats) {
            System.out.println("KMP Algorithm (handles repeating patterns efficiently)");
        } else if (alphabetSize > 50) {
            System.out.println("Boyer-Moore (good for large alphabets like natural language)");
        } else if (n > 1000000) {
            System.out.println("Java Built-in indexOf() (highly optimized for large texts)");
        } else {
            System.out.println("Java Built-in indexOf() (default choice - optimized by JVM)");
        }

        // Additional considerations
        System.out.println("\n   Additional Considerations:");
        if (m > 100) {
            System.out.println("   • Pattern is long (" + m + " chars) - consider KMP or Boyer-Moore");
        }
        if (n / m > 1000) {
            System.out.println("   • Text is much longer than pattern - Boyer-Moore may perform well");
        }
    }

    private static boolean hasRepeatingPattern(String s) {
        if (s.length() < 2)
            return false;

        // Check for obvious repeats
        for (int len = 1; len <= s.length() / 2; len++) {
            if (s.length() % len == 0) {
                String sub = s.substring(0, len);
                boolean repeats = true;
                for (int i = len; i < s.length(); i += len) {
                    if (!s.substring(i, i + len).equals(sub)) {
                        repeats = false;
                        break;
                    }
                }
                if (repeats)
                    return true;
            }
        }
        return false;
    }

    private static int calculateAlphabetSize(String s) {
        Set<Character> chars = new HashSet<>();
        for (char c : s.toCharArray()) {
            chars.add(c);
        }
        return chars.size();
    }

    // =========================================================================
    // 7. TEST SUITE
    // =========================================================================

    public static void runTestSuite() {
        System.out.println("=".repeat(70));
        System.out.println("SUBSTRING SEARCH TEST SUITE");
        System.out.println("=".repeat(70));

        // Test cases
        List<TestCase> testCases = Arrays.asList(
                new TestCase("geeksforgeeks", "eks", 2, "Basic test"),
                new TestCase("geeksforgeeks", "xyz", -1, "Not found"),
                new TestCase("hello world", "world", 6, "Simple word"),
                new TestCase("aaaaa", "aaa", 0, "Multiple matches"),
                new TestCase("", "", 0, "Both empty"),
                new TestCase("abc", "", 0, "Empty pattern"),
                new TestCase("", "abc", -1, "Empty text"),
                new TestCase("mississippi", "issip", 4, "Overlapping"),
                new TestCase("ababcabcabababd", "ababd", 10, "Partial matches"),
                new TestCase(repeat('a', 1000) + "b", repeat('a', 500) + "b", 500, "Worst-case brute force"));

        List<Algorithm> algorithms = Arrays.asList(
                new Algorithm("Brute Force", SubstringSearchEngine::bruteForceCharacter),
                new Algorithm("Java indexOf", SubstringSearchEngine::builtInIndexOf),
                new Algorithm("KMP", SubstringSearchEngine::kmpSearch),
                new Algorithm("Boyer-Moore", SubstringSearchEngine::boyerMooreSearch),
                new Algorithm("Original", SubstringSearchEngine::originalApproach));

        int passed = 0;
        int failed = 0;

        for (TestCase tc : testCases) {
            System.out.println("\nTest: " + tc.description);
            System.out.println("Text: \"" + tc.txt + "\"");
            System.out.println("Pattern: \"" + tc.pattern + "\"");
            System.out.println("Expected: " + tc.expected);

            boolean allPassed = true;
            for (Algorithm algo : algorithms) {
                int result = algo.function.apply(tc.txt, tc.pattern);
                boolean correct = (result == tc.expected);
                System.out.printf("  %-15s: %3d %s%n",
                        algo.name, result, correct ? "✓" : "✗ (Expected: " + tc.expected + ")");
                if (!correct)
                    allPassed = false;
            }

            if (allPassed) {
                passed++;
                System.out.println("  Result: PASS");
            } else {
                failed++;
                System.out.println("  Result: FAIL");
            }
        }

        System.out.println("\n" + "=".repeat(70));
        System.out.println("TEST SUMMARY:");
        System.out.println("  Passed: " + passed + "/" + testCases.size());
        System.out.println("  Failed: " + failed + "/" + testCases.size());
        System.out.println("=".repeat(70));
    }

    private static String repeat(char c, int n) {
        char[] chars = new char[n];
        Arrays.fill(chars, c);
        return new String(chars);
    }

    // =========================================================================
    // 8. MAIN METHOD WITH DEMONSTRATION
    // =========================================================================

    public static void main(String[] args) throws Exception {
        System.out.println("""
                ╔══════════════════════════════════════════════════════════════╗
                ║           SUBSTRING SEARCH ENGINE - COMPREHENSIVE            ║
                ║                    IMPLEMENTATION GUIDE                      ║
                ╚══════════════════════════════════════════════════════════════╝
                """);

        // Run test suite
        runTestSuite();

        // Benchmark different scenarios
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("PERFORMANCE BENCHMARKING");
        System.out.println("=".repeat(70));

        // Scenario 1: Small strings
        benchmarkAlgorithms("geeksforgeeks", "eks", "Small strings");

        // Scenario 2: Worst-case for brute force
        String worstCaseText = repeat('a', 10000) + "b";
        String worstCasePattern = repeat('a', 100) + "b";
        benchmarkAlgorithms(worstCaseText, worstCasePattern, "Worst-case pattern");

        // Scenario 3: Natural language text
        String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        benchmarkAlgorithms(loremIpsum, "consectetur", "Natural language");

        // Scenario 4: Binary-like data (small alphabet)
        String binaryLike = repeat('0', 5000) + "1" + repeat('0', 5000);
        benchmarkAlgorithms(binaryLike, "01", "Binary-like data");

        // Final recommendations
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("PRODUCTION RECOMMENDATIONS SUMMARY");
        System.out.println("=".repeat(70));

        System.out.println("""

                🏆 GOLDEN RULE: Use String.indexOf() unless you have specific needs

                📊 DECISION TREE:

                  1. Is pattern empty? → Return 0

                  2. Is pattern longer than text? → Return -1

                  3. Do you need case-insensitive search? → Use toLowerCase().indexOf()

                  4. Are you searching the same pattern many times? → Consider KMP

                  5. Is the text extremely large (>10MB)? → Consider stream-based approach

                  6. Is the pattern long (>100 chars) with repeats? → Use KMP

                  7. Is the alphabet large (e.g., natural language)? → Consider Boyer-Moore

                  8. Otherwise → Use String.indexOf()

                🚫 WHAT TO AVOID:
                  • Creating new String objects in loops (substring() in loop)
                  • Implementing custom algorithms without profiling first
                  • Ignoring memory usage for large datasets
                  • Forgetting edge cases (null, empty strings)

                🔧 WHEN TO IMPLEMENT CUSTOM ALGORITHM:
                  1. Proven bottleneck in profiling
                  2. Specialized requirements (streaming, multiple patterns)
                  3. Educational/learning purposes
                  4. Interview coding questions
                """);
    }

    // Helper classes for testing
    static class TestCase {
        String txt;
        String pattern;
        int expected;
        String description;

        TestCase(String txt, String pattern, int expected, String description) {
            this.txt = txt;
            this.pattern = pattern;
            this.expected = expected;
            this.description = description;
        }
    }

    static class Algorithm {
        String name;
        java.util.function.BiFunction<String, String, Integer> function;

        Algorithm(String name, java.util.function.BiFunction<String, String, Integer> function) {
            this.name = name;
            this.function = function;
        }
    }
}

// =========================================================================
// 9. ADDITIONAL UTILITY CLASS FOR PRODUCTION USE
// =========================================================================

/**
 * Production-ready substring search utility with adaptive algorithm selection
 * 
 * This class automatically chooses the best algorithm based on input
 * characteristics and provides monitoring capabilities.
 */
class ProductionSubstringSearch {

    private enum Algorithm {
        BRUTE_FORCE,
        KMP,
        BOYER_MOORE,
        RABIN_KARP,
        BUILT_IN
    }

    private static class SearchMetrics {
        long totalSearches = 0;
        long totalCharactersProcessed = 0;
        Map<Algorithm, Long> algorithmUsage = new HashMap<>();
        Map<Algorithm, Long> algorithmTime = new HashMap<>();

        void recordSearch(Algorithm algo, long timeNs, int textLength) {
            totalSearches++;
            totalCharactersProcessed += textLength;
            algorithmUsage.merge(algo, 1L, Long::sum);
            algorithmTime.merge(algo, timeNs, Long::sum);
        }

        void printMetrics() {
            System.out.println("\nSearch Metrics:");
            System.out.printf("Total searches: %,d%n", totalSearches);
            System.out.printf("Total characters processed: %,d%n", totalCharactersProcessed);

            System.out.println("\nAlgorithm usage:");
            for (Algorithm algo : Algorithm.values()) {
                long count = algorithmUsage.getOrDefault(algo, 0L);
                if (count > 0) {
                    long time = algorithmTime.getOrDefault(algo, 0L);
                    System.out.printf("  %-15s: %,d times (avg: %,d ns)%n",
                            algo, count, time / count);
                }
            }
        }
    }

    private final SearchMetrics metrics = new SearchMetrics();

    /**
     * Adaptive substring search that chooses algorithm based on input
     */
    public int search(String text, String pattern) {
        if (pattern == null || text == null)
            return -1;
        if (pattern.isEmpty())
            return 0;
        if (pattern.length() > text.length())
            return -1;

        Algorithm chosenAlgorithm = chooseAlgorithm(text, pattern);
        long startTime = System.nanoTime();
        int result = -1;

        try {
            switch (chosenAlgorithm) {
                case BRUTE_FORCE:
                    result = SubstringSearchEngine.bruteForceOptimized(text, pattern);
                    break;
                case KMP:
                    result = SubstringSearchEngine.kmpSearch(text, pattern);
                    break;
                case BOYER_MOORE:
                    result = SubstringSearchEngine.boyerMooreSearch(text, pattern);
                    break;
                case RABIN_KARP:
                    result = SubstringSearchEngine.rabinKarpSearch(text, pattern);
                    break;
                case BUILT_IN:
                default:
                    result = text.indexOf(pattern);
                    break;
            }
        } finally {
            long endTime = System.nanoTime();
            metrics.recordSearch(chosenAlgorithm, endTime - startTime, text.length());
        }

        return result;
    }

    private Algorithm chooseAlgorithm(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        // Simple heuristic-based selection
        if (m < 5) {
            return Algorithm.BRUTE_FORCE;
        } else if (m > 100 && hasRepeatingPattern(pattern)) {
            return Algorithm.KMP;
        } else if (calculateAlphabetSize(text + pattern) > 100) {
            return Algorithm.BOYER_MOORE;
        } else if (n > 1000000) {
            return Algorithm.BUILT_IN; // Let Java optimize
        } else {
            return Algorithm.BUILT_IN; // Default choice
        }
    }

    private boolean hasRepeatingPattern(String s) {
        // Simplified check for production
        if (s.length() < 4)
            return false;

        // Check if first 4 chars repeat
        String firstFour = s.substring(0, Math.min(4, s.length()));
        int count = 0;
        for (int i = 0; i <= s.length() - 4; i += 4) {
            if (s.substring(i, Math.min(i + 4, s.length())).startsWith(firstFour)) {
                count++;
            }
        }
        return count > 1;
    }

    private int calculateAlphabetSize(String s) {
        boolean[] seen = new boolean[256]; // Extended ASCII
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c < 256 && !seen[c]) {
                seen[c] = true;
                count++;
            }
        }
        return count;
    }

    public SearchMetrics getMetrics() {
        return metrics;
    }
}