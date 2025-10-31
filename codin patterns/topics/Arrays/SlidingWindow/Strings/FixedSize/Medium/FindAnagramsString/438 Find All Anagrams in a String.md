Given two strings s and p, return an array of all the start indices of p's

in s. You may return the answer in any order.

Example 1:

Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:

Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

Constraints:

    1 <= s.length, p.length <= 3 * 104
    s and p consist of lowercase English letters.


```java
package SlidingWindow.Strings.FixedSize.Medium.FindAnagramsString;

import java.util.*;

/**
 * Finds all start indices of anagrams of pattern string `p` in text string `s`.
 * 
 * Approach: Fixed-size sliding window with character frequency matching.
 * - Build frequency map of pattern `p`
 * - Maintain a sliding window of size p.length() in `s`
 * - Compare window frequency map with pattern map at each position
 * 
 * Time Complexity: O(n) where n = s.length() 
 *   (Each character visited at most twice; map comparison is O(1) due to bounded alphabet)
 * Space Complexity: O(1) 
 *   (At most 26 unique characters for lowercase English letters)
 */
public class FindAnagramsString {

    /**
     * Returns a list of starting indices where anagrams of `p` appear in `s`.
     * 
     * @param s the input text string (non-null)
     * @param p the pattern string to find anagrams of (non-null)
     * @return list of starting indices (empty if no anagrams found)
     */
    public static List<Integer> findAnagrams(String s, String p) {
        // Handle edge case: pattern longer than text
        if (s.length() < p.length()) {
            return Collections.emptyList();
        }

        // Build frequency map for pattern string
        Map<Character, Integer> patternFreq = buildFrequencyMap(p);
        Map<Character, Integer> windowFreq = new HashMap<>();
        int windowSize = p.length();

        // Initialize sliding window with first 'windowSize' characters
        for (int i = 0; i < windowSize; i++) {
            char c = s.charAt(i);
            windowFreq.put(c, windowFreq.getOrDefault(c, 0) + 1);
        }

        List<Integer> result = new ArrayList<>();
        
        // Check first window
        if (windowFreq.equals(patternFreq)) {
            result.add(0);
        }

        // Slide window one character at a time
        for (int i = windowSize; i < s.length(); i++) {
            // Add new character (right boundary)
            char newChar = s.charAt(i);
            windowFreq.put(newChar, windowFreq.getOrDefault(newChar, 0) + 1);

            // Remove old character (left boundary)
            char oldChar = s.charAt(i - windowSize);
            windowFreq.put(oldChar, windowFreq.get(oldChar) - 1);
            if (windowFreq.get(oldChar) == 0) {
                windowFreq.remove(oldChar);
            }

            // Check if current window is an anagram of pattern
            if (windowFreq.equals(patternFreq)) {
                result.add(i - windowSize + 1);
            }
        }

        return result;
    }

    /**
     * Helper method to build character frequency map from a string.
     * 
     * @param str input string
     * @return frequency map of characters
     */
    private static Map<Character, Integer> buildFrequencyMap(String str) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : str.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        return freqMap;
    }

    // ======================================================================================
    // TEST HARNESS
    // ======================================================================================

    public static void main(String[] args) {
        // Test Case 1: Example from problem statement
        String s1 = "cbaebabacd";
        String p1 = "abc";
        System.out.println("Input: s = \"" + s1 + "\", p = \"" + p1 + "\"");
        System.out.println("Output: " + findAnagrams(s1, p1)); // Expected: [0, 6]

        // Test Case 2: Overlapping anagrams
        String s2 = "abab";
        String p2 = "ab";
        System.out.println("\nInput: s = \"" + s2 + "\", p = \"" + p2 + "\"");
        System.out.println("Output: " + findAnagrams(s2, p2)); // Expected: [0, 1, 2]

        // Test Case 3: No anagrams
        String s3 = "abcdef";
        String p3 = "xyz";
        System.out.println("\nInput: s = \"" + s3 + "\", p = \"" + p3 + "\"");
        System.out.println("Output: " + findAnagrams(s3, p3)); // Expected: []

        // Test Case 4: Pattern equals text
        String s4 = "abc";
        String p4 = "bca";
        System.out.println("\nInput: s = \"" + s4 + "\", p = \"" + p4 + "\"");
        System.out.println("Output: " + findAnagrams(s4, p4)); // Expected: [0]
    }
}
```