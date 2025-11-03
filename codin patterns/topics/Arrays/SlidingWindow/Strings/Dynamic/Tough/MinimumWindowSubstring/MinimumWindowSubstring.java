package SlidingWindow.Strings.Dynamic.Tough.MinimumWindowSubstring;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    /**
     * Finds the minimum window in 's' that contains all characters of 't' (including duplicates).
     *
     * Approach:
     * - Use sliding window with two pointers (left, right)
     * - Use two hash maps:
     *     * tMap: frequency of characters in 't'
     *     * sMap: frequency of characters in current window of 's'
     * - Track 'formed' = number of unique chars in window that meet t's requirement
     * - Expand right, then shrink left while window is valid
     *
     * Time Complexity: O(|s| + |t|)
     * Space Complexity: O(|s| + |t|)
     *
     * Note: Problem is CASE-SENSITIVE (e.g., 'A' != 'a')
     */
    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0 || t.length() > s.length()) {
            return "";
        }

        // Build frequency map for t
        Map<Character, Integer> tMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        // Sliding window variables
        int left = 0, right = 0;
        int formed = 0; // number of unique chars in window that meet t's requirement
        int required = tMap.size(); // number of unique chars in t
        Map<Character, Integer> sMap = new HashMap<>();

        // Result tracking
        int minLen = Integer.MAX_VALUE;
        int minLeft = 0; // start index of best window

        while (right < s.length()) {
            // Expand window by including s[right]
            char c = s.charAt(right);
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);

            // Check if this character satisfies t's requirement
            if (tMap.containsKey(c) && sMap.get(c).equals(tMap.get(c))) {
                formed++;
            }

            // Try to shrink window from left while it's valid
            while (left <= right && formed == required) {
                // Update result if this window is smaller
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                }

                // Remove s[left] from window
                char leftChar = s.charAt(left);
                sMap.put(leftChar, sMap.get(leftChar) - 1);
                if (tMap.containsKey(leftChar) && sMap.get(leftChar) < tMap.get(leftChar)) {
                    formed--; // we no longer satisfy this char's requirement
                }
                left++;
            }

            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }

    // ==================== Main Method with Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Minimum Window Substring ===\n");

        // Example 1
        String s1 = "ADOBECODEBANC", t1 = "ABC";
        System.out.println("Test 1: s = \"" + s1 + "\", t = \"" + t1 + "\"");
        System.out.println("Output: \"" + minWindow(s1, t1) + "\""); // Expected: "BANC"

        // Example 2: No valid window
        String s2 = "a", t2 = "aa";
        System.out.println("\nTest 2: s = \"" + s2 + "\", t = \"" + t2 + "\"");
        System.out.println("Output: \"" + minWindow(s2, t2) + "\""); // Expected: ""

        // Example 3: Exact match
        String s3 = "abc", t3 = "cba";
        System.out.println("\nTest 3: s = \"" + s3 + "\", t = \"" + t3 + "\"");
        System.out.println("Output: \"" + minWindow(s3, t3) + "\""); // Expected: "abc"

        // Example 4: Case sensitivity
        String s4 = "a", t4 = "A";
        System.out.println("\nTest 4: s = \"" + s4 + "\", t = \"" + t4 + "\" (case-sensitive)");
        System.out.println("Output: \"" + minWindow(s4, t4) + "\""); // Expected: ""

        // Example 5: Duplicate characters in t
        String s5 = "aa", t5 = "aa";
        System.out.println("\nTest 5: s = \"" + s5 + "\", t = \"" + t5 + "\"");
        System.out.println("Output: \"" + minWindow(s5, t5) + "\""); // Expected: "aa"
    }
}