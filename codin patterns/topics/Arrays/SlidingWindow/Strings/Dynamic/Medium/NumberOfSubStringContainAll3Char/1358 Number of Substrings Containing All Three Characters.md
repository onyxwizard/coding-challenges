Given a string s consisting only of characters a, b and c.

Return the number of substrings containing at least one occurrence of all these characters a, b and c.

Example 1:

Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again). 

Example 2:

Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb". 

Example 3:

Input: s = "abc"
Output: 1

Constraints:

    3 <= s.length <= 5 x 10^4
    s only consists of a, b or c characters.

## 🔢 Simple Math Idea:

Instead of checking every substring (there could be thousands!), we use this key observation:

    Once a substring has all three letters ('a', 'b', 'c'), then adding more letters to the right will still keep all three. 


So, if a substring starting at position i first gets all three letters when it ends at position j, then:

    s[left..right] ✅  
    s[left..right+1] ✅  
    s[left..right+2] ✅  
    ...  
    s[left..n-1] ✅


That’s **`n - right`** valid substrings starting at left.

```java
public class NumberOfSubstringsContainingAllThreeChars {

    /**
     * Counts the number of substrings that contain at least one 'a', 'b', and 'c'.
     * 
     * Approach: Sliding Window
     * - Use three counters for 'a', 'b', 'c'
     * - Expand window with 'right'
     * - When all three counts >= 1, the window [left, right] is valid
     * - Now, **all substrings starting from 0 to left (inclusive) and ending at right are valid**
     *   → add (left + 1) to result
     * - But we must ensure that [left, right] is the **first valid window** from the left
     * 
     * Actually, better: while window is valid, we can count and shrink to find minimal left.
     * However, there's a more direct way:
     * 
     * Alternate Insight:
     * For each right, maintain the window [left, right] as the smallest valid window.
     * Then, number of valid substrings ending at right = left + 1.
     * 
     * But easiest: once valid, all starting positions from 0 to left work? No.
     * 
     * ✅ Correct logic:
     * When [left, right] is valid, then **any substring starting at 0..left and ending at right is valid**.
     * So add (left + 1).
     * But we must move left only when necessary.
     * 
     * Actually, standard solution:
     * - Expand right
     * - While window is valid, update result and shrink left
     * - But we want to count **all** valid substrings, not just minimal ones.
     * 
     * 🔥 Best approach:
     * At each right, maintain the smallest left such that [left, right] contains all three.
     * Then, number of valid substrings ending at right = left + 1.
     * 
     * How?
     * Use counts. When all counts >= 1, then while counts allow, shrink left.
     * But after shrinking to minimal valid left, then answer += left + 1? No.
     * 
     * Let's think:
     * s = "aaacb", right=4 (0-indexed, 'b')
     * At this point, window [2,4] = "acb" is first valid window.
     * Then substrings ending at 4 that are valid:
     *   [0,4] = "aaacb"
     *   [1,4] = "aacb"
     *   [2,4] = "acb"
     * → 3 substrings = (2 + 1) = left + 1? left=2 → 2+1=3 ✅
     * 
     * So yes: if minimal valid start is `left`, then all starts from 0 to left work → count = left + 1.
     * 
     * Algorithm:
     * - Use counts for a,b,c
     * - left = 0
     * - for right in 0..n-1:
     *     add s[right] to counts
     *     while (all counts >= 1):
     *         update result: ans += (n - right) ??? No.
     * 
     * Wait, the above example shows: at right=4, left=2, ans += 3.
     * But how do we get left=2?
     * 
     * Correct standard solution:
     * while (window [left, right] is valid):
     *     ans += (n - right)
     *     remove s[left], left++
     * 
     * But that counts from current right to end.
     * 
     * Actually, the **most common and correct solution** is:
     * 
     * int left = 0, ans = 0;
     * for (right = 0; right < n; right++) {
     *     add s[right]
     *     while (valid) {
     *         ans += (n - right);
     *         remove s[left++];
     *     }
     * }
     * 
     * But let's test with "aaacb":
     * right=0: 'a' → not valid
     * right=1: 'a' → not valid
     * right=2: 'a' → not valid
     * right=3: 'c' → not valid (no 'b')
     * right=4: 'b' → valid
     *   now while valid:
     *       ans += (5 - 4) = 1  → substring "aaacb"
     *       remove s[0]='a' → counts: a=2,b=1,c=1 → still valid
     *       ans += (5-4)=1 → "aacb"
     *       remove s[1]='a' → a=1,b=1,c=1 → valid
     *       ans += 1 → "acb"
     *       remove s[2]='a' → a=0 → invalid → break
     *   total ans = 3 ✅
     * 
     * So this works.
     * 
     * But the problem: we are at right=4, and we add (n - right) = 1 for each valid left.
     * Why (n - right)? Because for window [left, right], all extensions to the end are valid?
     * No! In this problem, we only care about substrings **ending at or after right**?
     * 
     * Actually, no: the substring must end at **right**, not beyond.
     * 
     * Wait, in the above, when right=4, we are only considering substrings that **end at 4**.
     * But (n - right) = 1, and we added 3 times → total 3.
     * 
     * That doesn't make sense.
     * 
     * Let me reexamine:
     * The standard solution for "number of substrings containing all three" is:
     * 
     * long res = 0;
     * int l = 0;
     * int[] cnt = new int[3];
     * for (int r = 0; r < s.length(); r++) {
     *     cnt[s.charAt(r) - 'a']++;
     *     while (cnt[0] > 0 && cnt[1] > 0 && cnt[2] > 0) {
     *         res += s.length() - r;
     *         cnt[s.charAt(l++) - 'a']--;
     *     }
     * }
     * return res;
     * 
     * But why s.length() - r?
     * 
     * Explanation:
     * When [l, r] is valid, then **all substrings starting at l and ending at r, r+1, ..., n-1 are valid**.
     * Because adding more characters won't remove a, b, or c.
     * 
     * So for fixed start = l, number of valid substrings = n - r.
     * 
     * Then we move l forward.
     * 
     * In "aaacb", n=5
     * At r=4, l=0: [0,4] valid → add 5-4=1 → "aaacb"
     * Then l=1: [1,4] valid → add 1 → "aacb"
     * Then l=2: [2,4] valid → add 1 → "acb"
     * Then l=3: [3,4] = "cb" → invalid
     * Total = 3 ✅
     * 
     * But the substrings are exactly those ending at 4 — and there are 3 of them.
     * And n - r = 1, but we added it 3 times → total 3.
     * 
     * So the logic is: for each valid starting position `l`, there is **exactly one substring ending at r** that starts at l.
     * But why n - r?
     * 
     * Actually, no: the substring must end **at r or later**, but since we're at r, and we haven't processed r+1 yet, 
     * the only substring ending at r that starts at l is [l, r].
     * 
     * So why n - r?
     * 
     * I think I confused it with another problem.
     * 
     * Let's look at the example "abcabc", expected=10.
     * 
     * Using the method:
     * r=0: 'a' → not valid
     * r=1: 'b' → not valid
     * r=2: 'c' → valid
     *   l=0: [0,2] valid → add n - r = 6 - 2 = 4 → substrings: "abc", "abca", "abcab", "abcabc"
     *   then remove 'a', l=1: [1,2]="bc" invalid → break
     *   total=4
     * r=3: 'a' → [1,3]="bca" valid
     *   l=1: add 6-3=3 → "bca", "bcab", "bcabc"
     *   remove 'b', l=2: [2,3]="ca" invalid
     *   total=4+3=7
     * r=4: 'b' → [2,4]="cab" valid
     *   l=2: add 6-4=2 → "cab", "cabc"
     *   remove 'c', l=3: [3,4]="ab" invalid
     *   total=9
     * r=5: 'c' → [3,5]="abc" valid
     *   l=3: add 6-5=1 → "abc"
     *   remove 'a', l=4: [4,5]="bc" invalid
     *   total=10 ✅
     * 
     * So it works!
     * 
     * Explanation:
     * When we have a valid window [l, r], then **any extension of this window to the right is also valid**.
     * So for start=l, the valid substrings are:
     *   [l, r], [l, r+1], ..., [l, n-1] → total = n - r
     * 
     * And since we're iterating r from 0 to n-1, we haven't counted these before.
     * 
     * This is the correct logic.
     */

    public static int numberOfSubstrings(String s) {
        int n = s.length();
        int left = 0;
        int countA = 0, countB = 0, countC = 0;
        int result = 0;

        for (int right = 0; right < n; right++) {
            // Expand window
            char c = s.charAt(right);
            if (c == 'a') countA++;
            else if (c == 'b') countB++;
            else countC++;

            // Shrink window while it's valid (contains all three)
            while (countA > 0 && countB > 0 && countC > 0) {
                // For current left, all substrings starting at 'left' and ending at 'right' to 'n-1' are valid
                result += n - right;

                // Remove leftmost character and shrink
                char leftChar = s.charAt(left);
                if (leftChar == 'a') countA--;
                else if (leftChar == 'b') countB--;
                else countC--;
                left++;
            }
        }

        return result;
    }

    // ==================== Main Method ====================
    public static void main(String[] args) {
        System.out.println("=== Number of Substrings Containing All Three Characters ===\n");

        // Example 1
        String s1 = "abcabc";
        System.out.println("Test 1: s = \"" + s1 + "\"");
        System.out.println("Output: " + numberOfSubstrings(s1)); // Expected: 10

        // Example 2
        String s2 = "aaacb";
        System.out.println("\nTest 2: s = \"" + s2 + "\"");
        System.out.println("Output: " + numberOfSubstrings(s2)); // Expected: 3

        // Example 3
        String s3 = "abc";
        System.out.println("\nTest 3: s = \"" + s3 + "\"");
        System.out.println("Output: " + numberOfSubstrings(s3)); // Expected: 1

        // Edge: all same
        String s4 = "aaaa";
        System.out.println("\nTest 4: s = \"" + s4 + "\"");
        System.out.println("Output: " + numberOfSubstrings(s4)); // Expected: 0

        // Edge: just enough
        String s5 = "abccba";
        System.out.println("\nTest 5: s = \"" + s5 + "\"");
        System.out.println("Output: " + numberOfSubstrings(s5)); // Expected: ?
    }
}
```
