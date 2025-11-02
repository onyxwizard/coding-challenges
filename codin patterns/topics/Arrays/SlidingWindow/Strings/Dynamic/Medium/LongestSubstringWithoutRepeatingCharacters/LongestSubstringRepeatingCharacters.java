package SlidingWindow.Strings.Dynamic.Medium.LongestSubstringWithoutRepeatingCharacters;

import java.util.HashSet;

/**
 * Solution for "Longest Substring Without Repeating Characters" (LeetCode #3)
 *
 * Uses a dynamic sliding window approach with a HashSet to track characters
 * in the current window. The window expands by moving the right pointer and
 * contracts from the left whenever a duplicate character is encountered.
 *
 * Time Complexity: O(n) - each character is visited at most twice
 * Space Complexity: O(min(m, n)) - where m is the size of the character set
 */
public class LongestSubstringRepeatingCharacters  {

    /**
     * Finds the length of the longest substring without repeating characters.
     *
     * @param s input string (can be empty or null)
     * @return length of the longest substring without repeating characters
     */
    public static int lengthOfLongestSubstring(String s) {
        // Handle edge case: null or empty string
        if (s == null || s.isEmpty()) {
            return 0;
        }

        HashSet<Character> charSet = new HashSet<>();
        int left = 0;           // Left boundary of the sliding window
        int maxLength = 0;      // Track the maximum length found

        // Expand window by moving right pointer (i)
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // Contract window from the left until no duplicate exists
            while (charSet.contains(currentChar)) {
                char leftChar = s.charAt(left);
                charSet.remove(leftChar);
                left++;
            }

            // Add current character to the set (window is now valid)
            charSet.add(currentChar);

            // Update maximum length with current valid window size
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    /**
     * Main method with test cases to demonstrate the solution.
     */
    public static void main(String[] args) {
        // Test cases
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // Expected: 3 ("abc")
        System.out.println(lengthOfLongestSubstring("bbbbb"));    // Expected: 1 ("b")
        System.out.println(lengthOfLongestSubstring("pwwkew"));   // Expected: 3 ("wke")
        System.out.println(lengthOfLongestSubstring(""));         // Expected: 0
        System.out.println(lengthOfLongestSubstring("dvdf"));     // Expected: 3 ("vdf")
        System.out.println(lengthOfLongestSubstring("1234a"));     // Expected: 5 ("1234a")
    }
}