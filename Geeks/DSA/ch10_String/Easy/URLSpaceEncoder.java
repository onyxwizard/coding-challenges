package DSA.ch10_String.Easy;
/**
 * @author onyxwizard
 * @date 25-12-2025
 */

import java.util.*;

/**
 * URL ENCODING: REPLACE SPACES WITH "%20"
 * 
 * This class provides multiple approaches with detailed analysis of
 * time complexity, space complexity, and trade-offs.
 */
public class URLSpaceEncoder {
    
    // =========================================================================
    // 1. BUILT-IN METHODS APPROACHES
    // =========================================================================
    
    /**
     * APPROACH 1.1: Using String.replace() - Simplest
     * 
     * TRADE-OFFS:
     * ✅ PROS:
     *   - One line solution
     *   - Readable and maintainable
     *   - Built-in optimization by Java
     *   - Handles all edge cases
     * 
     * ❌ CONS:
     *   - Creates multiple intermediate strings (performance)
     *   - Not suitable for interview if they want manual implementation
     *   - May not be efficient for very large strings with many replacements
     * 
     * TIME COMPLEXITY: O(n) where n = string length
     * SPACE COMPLEXITY: O(n) for new string
     * 
     * INTERVIEW NOTE: Mention this but implement manually if asked
     */
    public static String replaceSpacesBuiltIn(String s) {
        return s.replace(" ", "%20");
    }
    
    /**
     * APPROACH 1.2: Using String.replaceAll() with regex
     * 
     * TRADE-OFFS:
     * ✅ PROS:
     *   - Can handle multiple spaces or patterns
     *   - More flexible with regex
     * 
     * ❌ CONS:
     *   - Regex overhead (slower)
     *   - Less efficient than simple replace()
     *   - Overkill for simple space replacement
     */
    public static String replaceSpacesRegex(String s) {
        return s.replaceAll(" ", "%20");
    }
    
    // =========================================================================
    // 2. MANUAL APPROACHES (Interview Preferred)
    // =========================================================================
    
    /**
     * APPROACH 2.1: StringBuilder with Character-by-Character
     * 
     * TRADE-OFFS:
     * ✅ PROS:
     *   - Efficient O(n) time
     *   - Minimizes object creation (StringBuilder internally resizes)
     *   - Good for interview demonstration
     *   - Handles any replacement pattern
     * 
     * ❌ CONS:
     *   - Slightly more code than built-in
     *   - StringBuilder overhead (but optimized)
     * 
     * TIME COMPLEXITY: O(n) - single pass through string
     * SPACE COMPLEXITY: O(n) for StringBuilder (worst case when all spaces)
     * 
     * INTERVIEW RECOMMENDATION: Use this approach
     */
    public static String replaceSpacesStringBuilder(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                result.append("%20");
            } else {
                result.append(c);
            }
        }
        
        return result.toString();
    }
    
    /**
     * APPROACH 2.2: Character Array with Pre-counting (Optimized for Memory)
     * 
     * TRADE-OFFS:
     * ✅ PROS:
     *   - Exact memory allocation (no resizing)
     *   - Good for memory-constrained environments
     *   - Demonstrates algorithmic thinking
     * 
     * ❌ CONS:
     *   - Requires two passes through string
     *   - More complex code
     *   - Over-optimization for most cases
     * 
     * TIME COMPLEXITY: O(2n) = O(n) (two passes)
     * SPACE COMPLEXITY: O(n + 2*spaceCount) for result array
     * 
     * USE CASE: When you need exact memory control
     */
    public static String replaceSpacesCharArray(String s) {
        if (s == null) {
            return null;
        }
        
        // First pass: count spaces
        int spaceCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                spaceCount++;
            }
        }
        
        // Calculate new length: original + 2 extra chars for each space
        int newLength = s.length() + (spaceCount * 2);
        char[] result = new char[newLength];
        
        // Second pass: fill the array
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                result[index++] = '%';
                result[index++] = '2';
                result[index++] = '0';
            } else {
                result[index++] = c;
            }
        }
        
        return new String(result);
    }
    
    /**
     * APPROACH 2.3: Using toCharArray() and StringBuilder (Alternative)
     * 
     * Trade-off between simplicity and performance
     */
    public static String replaceSpacesCharArrayAlt(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        
        for (char c : chars) {
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
    
    // =========================================================================
    // 3. STREAM API APPROACHES (Java 8+)
    // =========================================================================
    
    /**
     * APPROACH 3.1: Using Java Streams
     * 
     * TRADE-OFFS:
     * ✅ PROS:
     *   - Functional programming style
     *   - Clean, declarative code
     *   - Easy to parallelize for large strings
     * 
     * ❌ CONS:
     *   - Higher overhead than imperative approaches
     *   - Creates many String objects (for each character)
     *   - Less efficient for small strings
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(n) + stream overhead
     * 
     * USE CASE: When working with functional paradigms or parallel processing
     */
    public static String replaceSpacesStream(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        
        return s.chars()
                .mapToObj(c -> c == ' ' ? "%20" : String.valueOf((char) c))
                .collect(java.util.stream.Collectors.joining());
    }
    
    /**
     * APPROACH 3.2: Parallel Stream for Large Strings
     * 
     * Use only for very large strings (> 1MB)
     */
    public static String replaceSpacesParallelStream(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        
        // Split into chunks for parallel processing
        return s.chars()
                .parallel()
                .mapToObj(c -> c == ' ' ? "%20" : String.valueOf((char) c))
                .collect(java.util.stream.Collectors.joining());
    }
    
    // =========================================================================
    // 4. IN-PLACE MODIFICATION (For char[] input - Common Interview Variation)
    // =========================================================================
    
    /**
     * APPROACH 4.1: In-place replacement for char array with extra space
     * 
     * COMMON INTERVIEW VARIATION:
     * "Given a char array with enough trailing spaces, replace spaces with %20 in-place"
     * 
     * Example: ['M','r',' ','B','e','n','e','d','i','c','t',' ',' ',' ',' ']
     * Length = 15, actual string length = 11, enough spaces at end
     * 
     * TRADE-OFFS:
     * ✅ PROS:
     *   - No additional memory for result
     *   - Demonstrates array manipulation skills
     *   - Common real-world scenario (URL encoding in buffers)
     * 
     * ❌ CONS:
     *   - Requires specific input format
     *   - More complex index management
     *   - Risk of off-by-one errors
     * 
     * ASSUMPTION: Array has enough space at the end
     */
    public static void replaceSpacesInPlace(char[] str, int trueLength) {
        // Count spaces in the actual string part
        int spaceCount = 0;
        for (int i = 0; i < trueLength; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }
        
        // Calculate new length
        int newIndex = trueLength + spaceCount * 2;
        
        // Work backwards to avoid overwriting
        for (int oldIndex = trueLength - 1; oldIndex >= 0; oldIndex--) {
            if (str[oldIndex] == ' ') {
                str[newIndex - 1] = '0';
                str[newIndex - 2] = '2';
                str[newIndex - 3] = '%';
                newIndex -= 3;
            } else {
                str[newIndex - 1] = str[oldIndex];
                newIndex--;
            }
        }
    }
    
    /**
     * Helper method for the in-place variation
     */
    public static String replaceSpacesInPlaceExample(String s) {
        // Simulate the problem: create array with enough space
        int spaceCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                spaceCount++;
            }
        }
        
        // Create array with extra space
        char[] arr = new char[s.length() + spaceCount * 2];
        
        // Copy original string to beginning of array
        for (int i = 0; i < s.length(); i++) {
            arr[i] = s.charAt(i);
        }
        
        // Fill the rest with spaces (or any character)
        for (int i = s.length(); i < arr.length; i++) {
            arr[i] = ' ';
        }
        
        // Perform in-place replacement
        replaceSpacesInPlace(arr, s.length());
        
        return new String(arr);
    }
    
    // =========================================================================
    // 5. PERFORMANCE BENCHMARKING
    // =========================================================================
    
    public static void benchmarkAllMethods() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("PERFORMANCE BENCHMARK - URL ENCODING");
        System.out.println("=".repeat(70));
        
        // Create test strings
        String small = "Hello World";
        String medium = "Mr Benedict Cumberbatch is an actor";
        String large = generateLargeString(10000); // 10K chars with 20% spaces
        
        System.out.println("\n1. Small string (12 chars):");
        benchmarkString(small);
        
        System.out.println("\n2. Medium string (34 chars):");
        benchmarkString(medium);
        
        System.out.println("\n3. Large string (10,000 chars):");
        benchmarkString(large);
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("RECOMMENDATIONS:");
        System.out.println("=".repeat(70));
        System.out.println("""
            • For most cases: Use StringBuilder approach (manual)
            • For simple code: Use String.replace() 
            • For interviews: Use StringBuilder or char array with pre-counting
            • For memory constraints: Use char array with exact allocation
            • For functional style: Use Stream API
            • For very large data: Consider parallel stream
            • For in-place modification: Use backward array traversal
            """);
    }
    
    private static void benchmarkString(String input) {
        System.out.printf("Input length: %,d characters\n", input.length());
        System.out.println("-".repeat(50));
        
        Map<String, java.util.function.Function<String, String>> methods = new LinkedHashMap<>();
        methods.put("String.replace()", URLSpaceEncoder::replaceSpacesBuiltIn);
        methods.put("StringBuilder", URLSpaceEncoder::replaceSpacesStringBuilder);
        methods.put("Char Array (2-pass)", URLSpaceEncoder::replaceSpacesCharArray);
        methods.put("Stream API", URLSpaceEncoder::replaceSpacesStream);
        methods.put("Parallel Stream", URLSpaceEncoder::replaceSpacesParallelStream);
        
        // Warm up
        for (int i = 0; i < 1000; i++) {
            for (var method : methods.values()) {
                method.apply(input);
            }
        }
        
        // Benchmark
        int iterations = 10000;
        Map<String, Long> times = new LinkedHashMap<>();
        
        for (var entry : methods.entrySet()) {
            long startTime = System.nanoTime();
            for (int i = 0; i < iterations; i++) {
                entry.getValue().apply(input);
            }
            long endTime = System.nanoTime();
            times.put(entry.getKey(), (endTime - startTime) / iterations);
        }
        
        // Display results
        long fastest = Collections.min(times.values());
        for (var entry : times.entrySet()) {
            long time = entry.getValue();
            double relative = (double) time / fastest;
            System.out.printf("  %-25s: %,8d ns (%.2fx)%n", 
                entry.getKey(), time, relative);
        }
    }
    
    private static String generateLargeString(int length) {
        Random random = new Random(42); // Fixed seed for reproducibility
        StringBuilder sb = new StringBuilder(length);
        
        for (int i = 0; i < length; i++) {
            // 20% chance of space
            if (random.nextInt(100) < 20) {
                sb.append(' ');
            } else {
                sb.append((char) ('a' + random.nextInt(26)));
            }
        }
        
        return sb.toString();
    }
    
    // =========================================================================
    // 6. COMPREHENSIVE TEST SUITE
    // =========================================================================
    
    public static void runTestSuite() {
        System.out.println("=".repeat(70));
        System.out.println("URL ENCODING TEST SUITE");
        System.out.println("=".repeat(70));
        
        List<TestCase> testCases = Arrays.asList(
            new TestCase("Mr Benedict Cumberbatch", 
                        "Mr%20Benedict%20Cumberbatch", 
                        "Simple example"),
            new TestCase("i love programming", 
                        "i%20love%20programming", 
                        "Another example"),
            new TestCase("", 
                        "", 
                        "Empty string"),
            new TestCase("   ", 
                        "%20%20%20", 
                        "Multiple spaces"),
            new TestCase("Hello", 
                        "Hello", 
                        "No spaces"),
            new TestCase(" A B C ", 
                        "%20A%20B%20C%20", 
                        "Spaces at edges"),
            new TestCase("a  b   c", 
                        "a%20%20b%20%20%20c", 
                        "Multiple consecutive spaces"),
            new TestCase("Hello\tWorld", 
                        "Hello\tWorld", 
                        "Tabs not replaced"),
            new TestCase(null, 
                        null, 
                        "Null input")
        );
        
        List<EncoderMethod> methods = Arrays.asList(
            new EncoderMethod("String.replace()", URLSpaceEncoder::replaceSpacesBuiltIn),
            new EncoderMethod("StringBuilder", URLSpaceEncoder::replaceSpacesStringBuilder),
            new EncoderMethod("Char Array", URLSpaceEncoder::replaceSpacesCharArray),
            new EncoderMethod("Stream API", URLSpaceEncoder::replaceSpacesStream)
        );
        
        int totalTests = 0;
        int passed = 0;
        
        for (TestCase tc : testCases) {
            System.out.printf("\nTest: %-40s ", tc.description);
            System.out.printf("Input: '%s'\n", 
                tc.input == null ? "null" : 
                tc.input.length() > 20 ? tc.input.substring(0, 20) + "..." : tc.input);
            System.out.printf("Expected: '%s'\n", 
                tc.expected == null ? "null" : tc.expected);
            
            boolean allPassed = true;
            
            for (EncoderMethod method : methods) {
                String result = null;
                try {
                    result = method.encode(tc.input);
                } catch (Exception e) {
                    result = null;
                }
                
                boolean passedTest = (tc.expected == null && result == null) ||
                                   (tc.expected != null && tc.expected.equals(result));
                
                if (!passedTest) {
                    System.out.printf("  %-20s: ✗ Got '%s'\n", 
                        method.name, 
                        result == null ? "null" : 
                        result.length() > 30 ? result.substring(0, 30) + "..." : result);
                    allPassed = false;
                }
            }
            
            if (allPassed) {
                passed++;
                System.out.println("  All methods: ✓ PASS");
            } else {
                System.out.println("  Some methods: ✗ FAIL");
            }
            totalTests++;
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.printf("SUMMARY: %d/%d tests passed\n", passed, totalTests);
        System.out.println("=".repeat(70));
    }
    
    // =========================================================================
    // 7. INTERVIEW PREPARATION GUIDE
    // =========================================================================
    
    /**
     * Key points for interview discussion:
     */
    public static void printInterviewGuide() {
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("INTERVIEW PREPARATION GUIDE");
        System.out.println("=".repeat(70));
        
        System.out.println("""
            
            🤔 QUESTIONS TO ASK THE INTERVIEWER:
            
            1. Should we handle only spaces or other whitespace (tabs, newlines)?
            2. Is the input guaranteed to be non-null?
            3. Should we modify the string in-place or return a new string?
            4. What about Unicode spaces (non-breaking space, etc.)?
            5. Are there any memory constraints?
            6. What's the expected input size?
            
            📝 STEP-BY-STEP INTERVIEW APPROACH:
            
            Step 1: Clarify requirements
              - "So I need to replace all spaces with '%20'?"
              - "Should I handle leading/trailing spaces?"
            
            Step 2: Propose simple solution
              - "The simplest approach is using String.replace()"
              - Time: O(n), Space: O(n)
            
            Step 3: Implement manual solution (if asked)
              - Use StringBuilder for O(n) time
              - Or char array for exact memory control
            
            Step 4: Discuss edge cases
              - Empty string, null input
              - All spaces, no spaces
              - Multiple consecutive spaces
              - Very long strings
            
            Step 5: Optimize (if needed)
              - For memory: char array with pre-counting
              - For large data: parallel processing
              - For in-place: backward traversal
            
            Step 6: Test with examples
              - Provide test cases
              - Walk through algorithm
            
            💡 ALGORITHM EXPLANATIONS FOR INTERVIEW:
            
            1. StringBuilder Approach:
               - Create StringBuilder
               - Iterate through each character
               - Append character or "%20"
               - Return string
            
            2. Char Array with Pre-counting:
               - First pass: count spaces
               - Allocate exact sized array
               - Second pass: fill array
               - More memory efficient
            
            3. In-place Modification (if array given):
               - Count spaces in true length
               - Calculate new length
               - Work backwards to avoid overwriting
            
            ⚠️ COMMON PITFALLS TO AVOID:
            
            • Forgetting to handle null input
            • Not considering multiple consecutive spaces
            • Creating too many intermediate strings
            • Off-by-one errors in array indices
            • Using regex when not needed (performance)
            • Not asking about edge cases
            
            🎯 OPTIMAL SOLUTION FOR INTERVIEW:
            
            public String replaceSpaces(String s) {
                if (s == null) return null;
                
                StringBuilder sb = new StringBuilder();
                for (char c : s.toCharArray()) {
                    if (c == ' ') {
                        sb.append("%20");
                    } else {
                        sb.append(c);
                    }
                }
                return sb.toString();
            }
            
            Time: O(n), Space: O(n) [optimal for this problem]
            """);
    }
    
    // =========================================================================
    // 8. PRODUCTION-READY IMPLEMENTATION
    // =========================================================================
    
    /**
     * Production-ready URL encoder with configuration options
     */
    public static class URLEncoder {
        
        private final boolean encodeAllWhitespace;
        private final boolean preserveNull;
        
        public URLEncoder(boolean encodeAllWhitespace, boolean preserveNull) {
            this.encodeAllWhitespace = encodeAllWhitespace;
            this.preserveNull = preserveNull;
        }
        
        /**
         * Encodes spaces (or all whitespace) in a string
         */
        public String encode(String input) {
            // Handle null based on configuration
            if (input == null) {
                return preserveNull ? null : "";
            }
            
            // Use StringBuilder for efficiency
            StringBuilder result = new StringBuilder(input.length() * 2); // Initial capacity
            
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                
                if (shouldEncode(c)) {
                    result.append("%20");
                } else {
                    result.append(c);
                }
            }
            
            return result.toString();
        }
        
        private boolean shouldEncode(char c) {
            if (encodeAllWhitespace) {
                return Character.isWhitespace(c);
            } else {
                return c == ' ';
            }
        }
        
        /**
         * Batch encode multiple strings
         */
        public List<String> encodeAll(List<String> inputs) {
            List<String> results = new ArrayList<>();
            for (String input : inputs) {
                results.add(encode(input));
            }
            return results;
        }
        
        /**
         * Encode with progress callback (for large strings)
         */
        public String encodeWithProgress(String input, java.util.function.Consumer<Integer> progressCallback) {
            if (input == null) {
                return preserveNull ? null : "";
            }
            
            StringBuilder result = new StringBuilder(input.length() * 2);
            int total = input.length();
            
            for (int i = 0; i < total; i++) {
                char c = input.charAt(i);
                
                if (shouldEncode(c)) {
                    result.append("%20");
                } else {
                    result.append(c);
                }
                
                // Report progress every 10%
                if (progressCallback != null && i % (total / 10) == 0) {
                    progressCallback.accept((i * 100) / total);
                }
            }
            
            if (progressCallback != null) {
                progressCallback.accept(100);
            }
            
            return result.toString();
        }
    }
    
    // =========================================================================
    // 9. HELPER CLASSES
    // =========================================================================
    
    static class TestCase {
        String input;
        String expected;
        String description;
        
        TestCase(String input, String expected, String description) {
            this.input = input;
            this.expected = expected;
            this.description = description;
        }
    }
    
    static class EncoderMethod {
        String name;
        java.util.function.Function<String, String> encoder;
        
        EncoderMethod(String name, java.util.function.Function<String, String> encoder) {
            this.name = name;
            this.encoder = encoder;
        }
        
        String encode(String input) {
            return encoder.apply(input);
        }
    }
    
    // =========================================================================
    // 10. MAIN METHOD WITH DEMONSTRATION
    // =========================================================================
    
    public static void main(String[] args) {
        System.out.println("""
            ╔══════════════════════════════════════════════════════════════╗
            ║                URL ENCODING: SPACE TO %20                    ║
            ╚══════════════════════════════════════════════════════════════╝
            """);
        
        // Run test suite
        runTestSuite();
        
        // Benchmark performance
        benchmarkAllMethods();
        
        // Show interview guide
        printInterviewGuide();
        
        // Demonstrate production usage
        System.out.println("\n" + "=".repeat(70));
        System.out.println("PRODUCTION DEMONSTRATION");
        System.out.println("=".repeat(70));
        
        URLEncoder encoder = new URLEncoder(false, true); // Only spaces, preserve null
        
        System.out.println("\n1. Basic encoding:");
        System.out.println("   Input:  'Mr Benedict Cumberbatch'");
        System.out.println("   Output: '" + encoder.encode("Mr Benedict Cumberbatch") + "'");
        
        System.out.println("\n2. Edge case - null input:");
        System.out.println("   Input:  null");
        System.out.println("   Output: " + encoder.encode(null));
        
        System.out.println("\n3. Multiple spaces:");
        System.out.println("   Input:  '  Hello  World  '");
        System.out.println("   Output: '" + encoder.encode("  Hello  World  ") + "'");
        
        System.out.println("\n4. Batch encoding:");
        List<String> inputs = Arrays.asList(
            "Hello World",
            "Java Programming",
            "Data Structures",
            "  Leading and trailing  "
        );
        
        List<String> encoded = encoder.encodeAll(inputs);
        for (int i = 0; i < inputs.size(); i++) {
            System.out.printf("   '%s' → '%s'\n", inputs.get(i), encoded.get(i));
        }
    }
}
