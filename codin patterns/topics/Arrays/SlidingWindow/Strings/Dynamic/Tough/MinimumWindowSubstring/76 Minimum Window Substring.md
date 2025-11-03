Given two strings s and t of lengths m and n respectively, return the minimum window

of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.

Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.

 

Constraints:

    m == s.length
    n == t.length
    1 <= m, n <= 105
    s and t consist of uppercase and lowercase English letters.

 

Follow up: Could you find an algorithm that runs in O(m + n) time?

# 📘 **Minimum Window Substring (LeetCode #76) – Comparative Analysis of 3 Solutions**

## 🎯 **Problem Statement Recap**

Given two strings `s` and `t`, find the **minimum-length substring of `s`** that **contains all characters of `t`** (including duplicates).  
- If no such window exists, return `""`.
- **Case-sensitive**: `'A' != 'a'`
- `s` and `t` consist of English letters.

**Example**:  
`s = "ADOBECODEBANC"`, `t = "ABC"` → Output: `"BANC"`

## 🔑 **Core Insight Shared by All Solutions**

A valid window must satisfy:
> For every character `c` in `t`, the count of `c` in the window ≥ count of `c` in `t`.

This is a **"covering" condition**, not equality.

To efficiently check this during sliding window:
- **Don’t compare full maps repeatedly** (expensive).
- Instead, track **how many characters’ requirements are satisfied**.

We define:
- `required = number of unique characters in t` (or total characters, depending on approach)
- `formed = number of unique characters in window that meet/exceed t’s count`

➡️ Window is valid when `formed == required`.

---

# ✅ **Solution 1: Two Hash Maps + Formed/Required Counters**

### 🔧 **Logic Overview**
- Maintain two frequency maps:
  - `tMap`: frequency of characters in `t`
  - `sMap`: frequency of characters in current window of `s`
- Track:
  - `required = tMap.size()` → unique chars in `t`
  - `formed = 0` → unique chars in window that **meet or exceed** `tMap` count
- Expand `right`, update `sMap`, check if `formed` increases.
- When `formed == required`, shrink from `left` to find minimal valid window.

### 💡 **Key Steps**
1. **Expand**: Add `s[right]` to `sMap`.
2. **Check formed**: If `sMap[c] == tMap[c]`, then `formed++`.
3. **Shrink while valid**: While `formed == required`:
   - Update result
   - Remove `s[left]` from `sMap`
   - If `sMap[leftChar] < tMap[leftChar]`, then `formed--`
4. Move `right++`

### ⏱️ **Complexity**
- **Time**: `O(|s| + |t|)` — each char visited at most twice
- **Space**: `O(|s| + |t|)` — two hash maps

### ✅ **Pros**
- Very readable
- Explicitly separates `t` and `s` state
- Easy to debug

### ❌ **Cons**
- Uses **two hash maps** → extra memory
- Slightly more code

```java
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
```

---

# ✅ **Solution 2: Single Hash Map + Total Character Counter**

### 🔧 **Logic Overview**
- Use **one map** (`map`) that stores **remaining needed count** for each char in `t`.
- Use `required = t.length()` → **total characters needed** (not unique)
- When adding `s[right]`:
  - If `map[c] > 0`, it means we still need this char → `required--`
  - Always do `map[c]--`
- When removing `s[left]`:
  - Always do `map[c]++`
  - If `map[c] > 0`, it means we now need it → `required++`
- Valid window when `required == 0`

### 💡 **Key Insight**
- The map acts as a **"debt tracker"**:
  - Positive value: we still need this char
  - Zero or negative: we have enough (or excess)

### ⏱️ **Complexity**
- **Time**: `O(|s| + |t|)`
- **Space**: `O(|t|)` — only one map

### ✅ **Pros**
- **Only one map**
- Simpler condition (`required == 0`)
- Less memory than Solution 1

### ❌ **Cons**
- Slightly less intuitive (debt model)
- `required` counts **total chars**, not unique → but still correct

```java
public static String minWindow(String s, String t) {
    if (s == null || t == null || s.length() == 0 || t.length() == 0) {
        return "";
    }

    // Build frequency map for t
    Map<Character, Integer> map = new HashMap<>();
    for (char c : t.toCharArray()) {
        map.put(c, map.getOrDefault(c, 0) + 1);
    }

    int left = 0, right = 0;
    int minLen = Integer.MAX_VALUE;
    int minStart = 0;
    int required = t.length(); // total characters needed

    while (right < s.length()) {
        char c = s.charAt(right);
        // If this character is needed, reduce required
        if (map.containsKey(c)) {
            if (map.get(c) > 0) {
                required--;
            }
            map.put(c, map.get(c) - 1);
        }

        // Try to shrink window when valid (required == 0)
        while (required == 0) {
            if (right - left + 1 < minLen) {
                minLen = right - left + 1;
                minStart = left;
            }

            char leftChar = s.charAt(left);
            if (map.containsKey(leftChar)) {
                map.put(leftChar, map.get(leftChar) + 1);
                if (map.get(leftChar) > 0) {
                    required++;
                }
            }
            left++;
        }
        right++;
    }

    return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
}
```
---

# ✅ **Solution 3: Fixed-Size Array (ASCII Optimized)**

### 🔧 **Logic Overview**
- Since input is **ASCII letters**, use `int[128]` instead of `HashMap`.
- Same logic as **Solution 2**, but with array:
  - `map[c]` = remaining needed count for char `c`
  - `required = t.length()`
- All operations are **O(1) array accesses**

### 💡 **Why It Works**
- `'A'` → ASCII 65, `'z'` → 122 → fits in `int[128]`
- No hashing overhead → **fastest in practice**

### ⏱️ **Complexity**
- **Time**: `O(|s| + |t|)`
- **Space**: `O(1)` — fixed 128-size array (constant space)

### ✅ **Pros**
- **Fastest runtime** (no hash collisions, cache-friendly)
- **Least memory overhead**
- Preferred in coding interviews for this problem

### ❌ **Cons**
- Only works for **limited character sets** (ASCII)
- Not suitable for Unicode (but LeetCode uses ASCII)

```java
public static String minWindow(String s, String t) {
    if (s.length() == 0 || t.length() == 0) return "";

    int[] map = new int[128];
    for (char c : t.toCharArray()) {
        map[c]++;
    }

    int left = 0, right = 0;
    int minStart = 0, minLen = Integer.MAX_VALUE;
    int required = t.length();

    while (right < s.length()) {
        char c = s.charAt(right);
        if (map[c] > 0) required--;
        map[c]--;

        while (required == 0) {
            if (right - left + 1 < minLen) {
                minLen = right - left + 1;
                minStart = left;
            }
            char leftChar = s.charAt(left);
            map[leftChar]++;
            if (map[leftChar] > 0) required++;
            left++;
        }
        right++;
    }

    return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
}
```
---
# 🆚 **Direct Comparison**

| Feature | Solution 1 (Two Maps) | Solution 2 (Single Map) | Solution 3 (Array) |
|--------|------------------------|--------------------------|---------------------|
| **Time Complexity** | O(n + m) | O(n + m) | O(n + m) |
| **Space Complexity** | O(n + m) | O(m) | **O(1)** |
| **Readability** | ★★★★☆ | ★★★☆☆ | ★★★☆☆ |
| **Speed (Practical)** | Medium | Fast | **Fastest** |
| **Handles Unicode?** | ✅ Yes | ✅ Yes | ❌ No (ASCII only) |
| **Map Operations** | 2 maps | 1 map | Array |
| **Counter Type** | Unique chars (`formed`) | Total chars (`required`) | Total chars |
| **Best For** | Learning, clarity | General use | **Interviews, LeetCode** |

> `n = s.length()`, `m = t.length()`

# 🧪 **Example Walkthrough (All Solutions)**
Input: `s = "ADOBECODEBANC"`, `t = "ABC"`

### Common Behavior:
- All solutions will:
  1. Expand until window `"ADOBEC"` (first valid)
  2. Shrink to `"DOBEC"` → still valid? No (`A` missing)
  3. Continue expanding → find `"CODEBA"` → shrink to `"ODEBANC"` → finally `"BANC"`
  4. Return `"BANC"` (length 4)

### Difference:
- **Solution 1** checks: `sMap['A']=1 == tMap['A']=1` → `formed++`
- **Solutions 2 & 3** check: `map['A']` went from `1 → 0 → -1`, and `required` dropped from 3 → 0

# 📌 **When to Use Which?**

| Scenario | Recommended Solution |
|--------|-----------------------|
| **Learning / Teaching** | Solution 1 (Two Maps) |
| **Production (Unicode support)** | Solution 2 (Single Map) |
| **Coding Interview / LeetCode** | **Solution 3 (Array)** |
| **Memory-constrained system** | Solution 3 |
| **Need to extend to Unicode later** | Solution 2 |

# ✅ **Final Recommendation**

For **LeetCode and interviews**, **use Solution 3 (Array)** — it’s:
- **Optimal in time and space**
- **Concise and fast**
- **Expected by interviewers**

But understand **Solution 1** first to grasp the core logic, then optimize to **Solution 3**.

> 🔥 **Pro Tip**: Always ask the interviewer: _“Can I assume the input is ASCII?”_  
> If yes → go straight to array solution.

# 🧠 **Key Takeaways**

1. **Never use `map1.equals(map2)`** — it’s O(k) per check → O(nk) total.
2. **Track coverage with counters** (`formed` or `required`) for O(1) validation.
3. **Array beats HashMap** for small, known character sets.
4. **Sliding window + coverage tracking** is a powerful pattern for substring problems.

You now have **three robust tools** for this classic problem — choose wisely! 🚀