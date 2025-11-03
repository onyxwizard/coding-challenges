# **Sliding Window** 🪟
### **The Dynamic Subarray/Substring Optimizer**

The **Sliding Window** technique is a highly efficient strategy for solving problems that involve **contiguous subarrays or substrings** with specific constraints—such as maximum/minimum length, sum, distinct characters, or coverage of certain elements. By maintaining a "window" (a range between two pointers) that **expands and contracts dynamically**, we avoid redundant recomputation and achieve **linear or near-linear time complexity**.

> 💡 **Core Philosophy**: *Reuse work from the previous window instead of starting from scratch.*

### 🔍 **Core Idea**

- Use two pointers: `left` and `right`, defining the current window `[left, right]`.
- **Expand** the window by moving `right` to include new elements.
- **Contract** the window by moving `left` when the window violates a constraint.
- Track the **best valid window** (longest, shortest, max sum, etc.) during traversal.

This creates an **O(n)** solution because each element is visited **at most twice** (once by `right`, once by `left`).

### 🧩 **Types of Sliding Window**

#### 1. **Fixed-Size Window** 📏
- **Window size is constant** (e.g., “find max sum of any 3 consecutive elements”).
- **How it works**: Move the window one step at a time; subtract the outgoing element and add the incoming one.
- **Time**: O(n), **Space**: O(1)

> ✅ **Example**:  
> Array: `[2, 1, 5, 1, 3, 2]`, k = 3
> - Window 1: `[2,1,5]` → sum = 8
> - Window 2: `[1,5,1]` → sum = 7
> - Window 3: `[5,1,3]` → sum = 9 ← max
> - Window 4: `[1,3,2]` → sum = 6  
    > → **Answer: 9**

#### 2. **Variable-Size (Dynamic) Window** 🌊
- **Window size changes** based on problem constraints.
- Most common in interview problems.
- Two subtypes:
    - **Maximize window** under a constraint (e.g., “longest substring with ≤ K distinct chars”).
    - **Minimize window** to satisfy a condition (e.g., “smallest substring containing all chars of T”).

> ✅ **Example (Maximize)**:  
> `"abcabcbb"`, no repeating chars → longest = `"abc"` → length = 3
>
> ✅ **Example (Minimize)**:  
> `s = "ADOBECODEBANC"`, `t = "ABC"` → min window = `"BANC"`


### 🛠️ **When to Use Sliding Window? (Diagnostic Questions)**

Ask yourself:

1. **Does the problem ask for a contiguous subarray or substring?**  
   → If **no**, sliding window likely doesn’t apply.

2. **Is there a constraint that can be checked incrementally?**  
   (e.g., “at most K distinct”, “sum ≤ X”, “contains all chars of T”)

3. **Can the constraint be *violated* and then *repaired* by shrinking the window?**  
   → This is the hallmark of dynamic sliding window.

4. **Are you optimizing for length (longest/shortest) or value (max sum)?**  
   → Sliding window excels at **optimization over contiguous segments**.

> 🚩 **Red Flags** (NOT sliding window):
> - Non-contiguous subsets (use subsets, DP, or backtracking)
> - Global properties not local to a segment (e.g., “total number of inversions”)


### 🧠 **Key Implementation Patterns**

#### A. **Maximizing Window Size (Most Common)**
**Goal**: Find the **longest** valid window.

```python
left = 0
best = 0
for right in range(n):
    # Add arr[right] to window (e.g., update freq map, sum, etc.)
    while window_is_invalid():
        # Remove arr[left] and shrink
        left += 1
    # Now window [left, right] is valid
    best = max(best, right - left + 1)
```

> ✅ **Used in**:
> - Longest Substring Without Repeating Characters
> - Longest Subarray with At Most K Zeros
> - Max Consecutive Ones III

#### B. **Minimizing Window Size**
**Goal**: Find the **smallest** window that satisfies a condition.

```python
left = 0
best = float('inf')
for right in range(n):
    # Expand: add arr[right]
    while window_is_valid():
        best = min(best, right - left + 1)
        # Try to shrink from left
        left += 1
        # (may break validity, but we already recorded best)
```

> ⚠️ **Tricky**: You must **check validity *before* shrinking**.  
> Often requires a **counter or hashmap** to track coverage.

> ✅ **Used in**:
> - Minimum Window Substring
> - Smallest Subarray with Sum ≥ Target

#### C. **Fixed-Size Window (Simplest)**
```python
window_sum = sum(arr[:k])
max_sum = window_sum
for i in range(k, n):
    window_sum = window_sum - arr[i - k] + arr[i]
    max_sum = max(max_sum, window_sum)
```

> ✅ **Used in**:
> - Maximum Average Subarray I
> - Find All Anagrams in a String (with char count comparison)



### 🔗 **Common Data Structures Used with Sliding Window**

| Problem Need | Data Structure | Why |
|-------------|----------------|-----|
| Track character frequencies | `dict` / `Counter` / array of size 26/128 | O(1) updates and checks |
| Check if window contains all required chars | Hashmap + `formed` counter | Avoid scanning full map each time |
| Track min/max in window | **Deque** (monotonic queue) | For advanced problems like “sliding window maximum” |
| Count distinct elements | Set or freq map with `len(freq)` | Efficient distinct count |

> 💡 **Pro Tip**: For **Minimum Window Substring**, maintain:
> - `need = Counter(t)`
> - `have = defaultdict(int)`
> - `required = len(need)`
> - `formed = 0` (number of chars in `have` that meet `need` count)

---

## 🎯 **Sliding Window Mastery Tracker (Ordered by Type & Difficulty)**

### 🔹 **I. Fixed-Size Window**
*(Window length is constant — e.g., "subarray of size k")*

| #   | Problem Title                          | Pattern 🏷️               | Difficulty | Status  | Time ⏱️   | Space 💾 | Note 📝 |
|-----|----------------------------------------|--------------------------|------------|:----------:|-----------|----------|--------|
| 1   | Maximum Sum Subarray of Size K         | 🪟 Fixed-Size            | Easy       |      ✅      | **O(n)**  | **O(1)** | Classic intro: maintain running sum. |
| 2   | Find All Anagrams in a String          | 🪟 Fixed-Size + Char Map | Medium     |      ✅      | **O(n)**  | **O(1)** | Compare char freq of window with `p` (use array[26]). |
| 3   | Grumpy Bookstore Owner                 | 🪟 Fixed-Size (Max Gain) | Medium     |      ✅      | **O(n)**  | **O(1)** | Base satisfied + max extra from window of size X. |
| 4   | Sliding Window Maximum                  | 🪟 Fixed-Size + Deque    | Hard       |      ✅      | **O(n)**  | **O(k)** | Use **monotonic decreasing deque** to track max. |



### 🔹 **II. Dynamic (Variable-Size) Window**
*(Window expands/contracts based on constraints)*

#### ➤ **A. Maximize Window Length**
*(Find the **longest** valid window)*

| #   | Problem Title                                      | Pattern 🏷️                     | Difficulty | Status ✅ | Time ⏱️   | Space 💾     | Note 📝 |
|-----|----------------------------------------------------|----------------------------------|------------|:----------:|-----------|--------------|--------|
| 5   | Longest Substring Without Repeating Characters     | 🪟 Dynamic – Max (No Repeats)    | Medium     |      ✅      | **O(n)**  | **O(min(m,n))** | Shrink on duplicate; use set or map. |
| 6   | Max Consecutive Ones III                           | 🪟 Dynamic – Max (Flip Budget)   | Medium     |      ✅      | **O(n)**  | **O(1)**     | Treat zeros as cost; allow ≤ K flips. |
| 7   | Longest Substring with At Most K Distinct Chars    | 🪟 Dynamic – Max (Distinct ≤ K)  | Medium     |      ✅      | **O(n)**  | **O(K)**     | Shrink when `len(freq) > K`. |
| 8   | Fruit Into Baskets                                 | 🪟 Dynamic – Max (K=2 Types)     | Medium     |      ✅      | **O(n)**  | **O(1)**     | Special case of #7 with K=2. |
| 9   | Subarray Product Less Than K                       | 🪟 Dynamic – Max (Product < K)   | Medium     |      ✅      | **O(n)**  | **O(1)**     | **Watch**: if K ≤ 1, return 0. |
| 10  | Longest Continuous Subarray with Abs Diff ≤ Limit  | 🪟 Dynamic – Max + Deque (Min/Max)| Medium     |     ✅       | **O(n)**  | **O(n)**     | Use **two deques** (min & max); shrink when `max - min > limit`. |

#### ➤ **B. Minimize Window Length**
*(Find the **smallest** window that satisfies a condition)*

| #   | Problem Title                | Pattern 🏷️                   | Difficulty | Status  | Time ⏱️   | Space 💾 | Note 📝 |
|-----|------------------------------|------------------------------|------------|:----------:|-----------|----------|--------|
| 11  | Minimum Size Subarray Sum    | 🪟 Dynamic – Min (Sum ≥ Target)| Medium     |     ✅       | **O(n)**  | **O(1)** | Shrink while `sum ≥ target`; update min length. |
| 12  | Minimum Window Substring     | 🪟 Dynamic – Min (Coverage)   | Hard       |     ✅       | **O(n)**  | **O(m)** | Track `formed == required`; shrink when valid. |

#### ➤ **C. Count Valid Windows**
*(Count how many windows satisfy a condition)*

| #   | Problem Title                          | Pattern 🏷️                     | Difficulty | Status ✅ | Time ⏱️   | Space 💾 | Note 📝 |
|-----|----------------------------------------|----------------------------------|------------|:----------:|-----------|----------|--------|
| 13  | Number of Substrings Containing All 3 Chars | 🪟 Dynamic – Counting         | Medium     |     ✅       | **O(n)**  | **O(1)** | For each `right`, count valid `left` positions. |
| 14  | Count Number of Nice Subarrays         | 🪟 Dynamic – Exact K → AtMost(K) - AtMost(K-1) | Medium |   ✅   | **O(n)**  | **O(1)** | Transform exact-K into two "at most" passes. |



### 📊 **Summary by Category & Difficulty**

| Category | Easy | Medium | Hard | Total |
|--------|------|--------|------|-------|
| **Fixed-Size Window** | 1 | 2 | 1 | **4** |
| **Dynamic – Maximize** | 0 | 5 | 0 | **5** |
| **Dynamic – Minimize** | 0 | 1 | 1 | **2** |
| **Dynamic – Counting** | 0 | 2 | 0 | **2** |
| **✅ Total** | **1** | **10** | **2** | **13** |

> 💡 **Note**: There are very few **Easy** sliding window problems—most start at **Medium** because the pattern inherently involves non-trivial state management.

### 🧠 **Study Flow Recommendation**

1. **Fixed-Size (Easy → Hard)**  
   → #1 → #2 → #3 → #4
2. **Dynamic – Maximize**  
   → #5 (most fundamental) → #6, #7, #8 → #9 → #10
3. **Dynamic – Minimize**  
   → #11 → #12
4. **Dynamic – Counting**  
   → #13 → #14

This order ensures you:
- Master **window movement** before **complex state logic**
- Learn **maximization** (more intuitive) before **minimization**
- Tackle **counting tricks** only after solid dynamic window foundation

---

<!-- ### 📌 **Classic Problems & Patterns**

| Problem | Type | Key Insight |
|--------|------|------------|
| **Longest Substring Without Repeating Characters** | Max dynamic | Use set or freq map; shrink on duplicate |
| **Minimum Window Substring** | Min dynamic | Track `formed == required` to know when valid |
| **Find All Anagrams in a String** | Fixed-size | Compare char counts of window and `p` |
| **Subarray Product Less Than K** | Max dynamic | Shrink while `product >= K`; **watch for K ≤ 1!** |
| **Max Consecutive Ones III** | Max dynamic | Treat zeros as "cost"; allow up to K |
| **Sliding Window Maximum** | Fixed-size | Requires **monotonic deque** (not basic two pointers!) |
| **Longest Substring with At Most K Distinct Characters** | Max dynamic | Use freq map; shrink when `len(freq) > K` | -->



### ⚙️ **Implementation Checklist**

1. **Initialize** `left = 0`, and data structure (e.g., `freq = {}`).
2. **Iterate** `right` from `0` to `n-1`.
3. **Add** `arr[right]` to window (update data structure).
4. **While** window is **invalid** (for max problems) or **valid** (for min problems):
    - Update answer if needed.
    - **Remove** `arr[left]` and increment `left`.
5. **Return** best result.

> 🚨 **Edge Cases**:
> - Empty string/array
> - `k = 0` or `target = 0`
> - All elements invalid (e.g., no window satisfies condition → return 0 or "")


### 📈 **Time & Space Complexity**

| Type | Time | Space |
|------|------|-------|
| Fixed-Size | **O(n)** | **O(1)** or **O(1)** (if using array for chars) |
| Variable-Size (with hashmap) | **O(n)** | **O(min(m, n))** where `m` = charset size |
| With Deque (e.g., max in window) | **O(n)** | **O(k)** |

> ✅ **Why O(n)?** Each element added once, removed once → **amortized O(1)** per operation.

---
### 🧠 **Advanced Variants**

#### 1. **Sliding Window + Binary Search**
- When window condition is **monotonic** (e.g., “is there a subarray of length L with sum ≥ X?”).
- Binary search on answer (length), use sliding window to verify.

> Example: *Maximum Average Subarray II* (hard)

#### 2. **Sliding Window + Two Heaps / Deque**
- For problems needing **min/max in current window**.
- Standard sliding window isn’t enough → need **monotonic queue**.

> Example: *Sliding Window Maximum*

#### 3. **Multi-Constraint Windows**
- Window must satisfy **multiple conditions** (e.g., “at most K distinct AND length ≥ L”).
- Combine logic in `is_valid()` check.

---
### 🎯 **How to Practice Sliding Window Effectively**

1. **Start with fixed-size** → build intuition.
2. **Move to “longest without repeats”** → classic dynamic window.
3. **Then tackle “minimum window substring”** → hardest common variant.
4. **Finally, try advanced** (with deques, binary search).

> 🔁 **Drill this mental loop**:  
> *"Expand right → check validity → shrink left until valid → update answer."*

<!-- 

### 📊 **Sliding Window Mastery Tracker (Recommended)**

| # | Problem | Type | Difficulty | Core Skill |
|---|--------|------|-----------|-----------|
| 1 | Maximum Sum Subarray of Size K | Fixed | Easy | Basic window sum |
| 2 | Longest Substring Without Repeating Characters | Max Dynamic | Medium | Set/hashmap + shrink on duplicate |
| 3 | Minimum Window Substring | Min Dynamic | Hard | Coverage tracking (`formed == required`) |
| 4 | Find All Anagrams in a String | Fixed | Medium | Char count comparison |
| 5 | Subarray Product Less Than K | Max Dynamic | Medium | Watch for edge cases (K ≤ 1) |
| 6 | Max Consecutive Ones III | Max Dynamic | Medium | Treat flips as budget |
| 7 | Longest Substring with At Most K Distinct | Max Dynamic | Medium | Distinct count via hashmap |
| 8 | Sliding Window Maximum | Fixed + Deque | Hard | Monotonic decreasing deque |

> ✅ Master these 8, and you’ll handle **95%** of sliding window interview questions. -->



### 💡 **Key Insight to Remember**

> **Sliding Window is greedy + incremental**:  
> It assumes that **once a window becomes invalid, all larger windows starting at the same `left` are also invalid** (for minimization), or that **we can safely extend until invalid** (for maximization). This **monotonicity** is what makes it work.



### 🚀 Final Thought

While **Two Pointers** is about **smart traversal**, **Sliding Window** is about **smart reuse**. It turns what could be an O(n²) nested loop into a sleek, single-pass O(n) solution by **leveraging the contiguity and incremental nature** of the problem.

Given your focus on interview prep, **sliding window is non-negotiable**—it appears in **~15% of top company array/string questions**. Combine it with hashmaps, and you’ve got a powerhouse pattern.
