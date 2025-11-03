You are given a string **s** consisting only lowercase alphabets and an integer **k**. Your task is to find the **length** of the **longest substring** that contains exactly **k** distinct characters.

**Note :** If no such substring exists, return **-1**. 

**Examples:**

**Input:** s = "aabacbebebe", k = 3
**Output:** 7
**Explanation**: The longest substring with exactly 3 distinct characters is "cbebebe", which includes 'c', 'b', and 'e'.

**Input**: s = "aaaa", k = 2
**Output:** -1
**Explanation**: There's no substring with 2 distinct characters.  

**Input:** s = "aabaaab", k = 2
**Output:** 7
**Explanation**: The entire string "aabaaab" has exactly 2 unique characters 'a' and 'b', making it the longest valid substring.

**Constraints:**  
1 ≤ s.size() ≤ 105  
1 ≤ k ≤ 26  

**Expected Complexities**

Time Complexity: O(n)

Auxiliary Space: O(1)

```java
package SlidingWindow.Arrays.Dynamic.Medium.LongestSubstringKUniques;

import java.util.HashMap;

public class LongestSubstringKUniques {
    public static int longestKSubstr(String s, int k) {
        // code here
        int n = s.length();
        int left = 0, right = 0;
        int maxLen = -1;
        HashMap<Character, Integer> freq = new HashMap<>();

        while (right < n) {
            char cRight = s.charAt(right);
            if (!Character.isAlphabetic(cRight)) {
                right++;
                continue;
            }

            freq.put(cRight, freq.getOrDefault(cRight, 0) + 1);

            while (freq.size() > k) {
                char cLeft = s.charAt(left);
                freq.put(cLeft, freq.get(cLeft) - 1);
                if (freq.get(cLeft) == 0) {
                    freq.remove(cLeft);
                }
                left++;
            }

            if (freq.size() == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }

            right++;
        }

        return maxLen;
    }

    public static void main(String[] args) {
        // Original test case
        String s1 = "aabacbebebe";
        int k1 = 3;
        System.out.println("Test 1: s = \"" + s1 + "\", k = " + k1);
        System.out.println("Output: " + longestKSubstr(s1, k1)); // Expected: 7

        // Requested test cases
        String s2 = "1";
        int k2 = 2;
        System.out.println("\nTest 2: s = \"" + s2 + "\", k = " + k2);
        System.out.println("Output: " + longestKSubstr(s2, k2)); // Expected: -1

        String s3 = "1";
        int k3 = 1;
        System.out.println("\nTest 3: s = \"" + s3 + "\", k = " + k3);
        System.out.println("Output: " + longestKSubstr(s3, k3)); // Expected: -1

        // Additional edge: empty string
        String s4 = "";
        int k4 = 1;
        System.out.println("\nTest 4: s = \"" + s4 + "\", k = " + k4);
        System.out.println("Output: " + longestKSubstr(s4, k4)); // Expected: -1
    }
}
```