## **1️⃣ Arrays & Strings** `🔤`
### 🔁 **1.1 Two Pointers**

The **Two Pointers** technique is a foundational and highly efficient pattern used to solve a wide range of problems involving **arrays** and **strings**. It leverages the idea of using **two indices (pointers)** that traverse the data structure in a coordinated way to reduce time complexity—often from *O(n²)* to *O(n)*—while maintaining clarity and simplicity.



### 🔍 **Core Idea**
Instead of using nested loops to compare or process elements, two pointers move through the array/string based on specific conditions, enabling linear or near-linear performance.



### 🧩 **Types of Two Pointers**

#### 1. **Opposite (Converging) Pointers** ↔️
- **How it works**: One pointer starts at the **beginning** (`left = 0`), the other at the **end** (`right = n - 1`). They move **toward each other**.
- **Typical use cases**:
    - **Sorted array pair sum**: Find two numbers that add up to a target (e.g., *Two Sum II*).
    - **Palindrome detection**: Check if a string/array reads the same forwards and backwards.
    - **Container With Most Water**: Maximize area between two vertical lines.
- **Why it works**: In **sorted arrays**, moving the pointer with the smaller value (for sum problems) or mismatched characters (for palindromes) systematically explores valid pairs without missing solutions.

> ✅ **Example**:  
> Given sorted array `[2, 7, 11, 15]` and target `9`:
> - `left=0 (2)`, `right=3 (15)` → sum = 17 > 9 → move `right` left
> - `left=0 (2)`, `right=1 (7)` → sum = 9 → found!



#### 2. **Fast-Slow (Runner) Pointers** 🐢🐇
- **How it works**: Both pointers start at the beginning, but the **fast pointer moves faster** (e.g., 2 steps vs. 1 step).
- **Typical use cases**:
    - **Detecting cycles** in linked lists (Floyd’s Cycle Detection).
    - **Finding the middle** of a linked list or array.
    - **Removing duplicates** in-place (e.g., *Remove Duplicates from Sorted Array*).
- **Why it works**: The relative speed difference allows detection of patterns (like cycles) or partitioning of data (like unique vs. duplicate elements).

> ✅ **Example**:  
> Remove duplicates from `[0,0,1,1,2,3]`:
> - `slow` tracks the position of the last unique element.
> - `fast` scans ahead. When `arr[fast] != arr[slow]`, increment `slow` and copy `arr[fast]`.  
    > Result: `[0,1,2,3,...]` with `slow + 1 = 4` unique elements.



#### 3. **Sliding Window** 🪟 *(Often grouped under Two Pointers)*
- **How it works**: Two pointers (`left` and `right`) define a **dynamic window** that expands or contracts based on conditions.
- **Typical use cases**:
    - **Longest/Shortest substring** with certain properties (e.g., *Longest Substring Without Repeating Characters*).
    - **Maximum sum of subarray** of fixed/variable size.
    - **Minimum window substring** containing all characters of a target.
- **Why it works**: Avoids recomputation by **reusing results** from previous windows—classic **space-time tradeoff optimization**.

> ⚠️ **Note**: While technically using two pointers, sliding window is often treated as its own sub-pattern due to its distinct logic (expansion vs. contraction).



## 🔍 **How to Detect Two Pointer Problems**

Ask yourself these **diagnostic questions** when reading a problem:

### ✅ 1. **Is the input sorted (or can it be sorted without breaking the problem)?**
- Two pointers **thrive on sorted data** (especially opposite pointers).
- Example: *"Find two numbers in an array that sum to target."* → If unsorted, you might sort first (if indices don’t matter).

> 🚩 Red flag: If the problem **requires original indices** (like classic *Two Sum*), sorting breaks the requirement → use hash map instead.



### ✅ 2. **Are you looking for pairs, triplets, or subarrays with a specific property?**
- Common targets: sum = X, product ≤ K, difference = D, etc.
- Two pointers efficiently **explore combinations** without O(n²) loops.

> Examples:
> - *3Sum*, *4Sum* → sort + outer loop + two pointers
> - *Subarray Product Less Than K* → sliding window (a two-pointer variant)



### ✅ 3. **Does the problem involve in-place modification or partitioning?**
- Fast-slow pointers excel at **filtering or compressing** data in one pass.
- Keywords: *"remove duplicates"*, *"move zeros"*, *"partition array"*.

> Example: *Remove Duplicates from Sorted Array* → `slow` writes unique values, `fast` scans.



### ✅ 4. **Is there symmetry or comparison between ends?**
- Palindromes, mirror checks, or container problems (like *Trapping Rain Water*) often use **left/right pointers**.

> Example: *Valid Palindrome* → compare `s[left]` and `s[right]`, skip non-alphanumeric chars.



### ✅ 5. **Is it about a contiguous segment (substring/subarray) with constraints?**
- This hints at **sliding window** (a two-pointer subtype).
- Look for: *"longest/shortest substring with..."*, *"at most K distinct..."*, *"minimum window..."*

> Example: *Longest Substring Without Repeating Characters* → expand `right`, contract `left` on duplicate.

---

## 🧩 **Where Two Pointers Can Be Combined with Other Patterns**

Two pointers rarely work in isolation. Here’s how to **combine** them strategically:


### 1. **Two Pointers + Sorting**
- **When**: Input is unsorted, but order doesn’t matter for the answer.
- **How**: Sort first → apply two pointers.
- **Problems**:
    - *Two Sum II* (sorted version)
    - *3Sum*, *4Sum*
    - *Closest Pair Sum*

> ⚠️ Trade-off: You lose original indices, but gain O(n) pair search.



### 2. **Two Pointers + Hash Map / Set**
- **When**: You need **O(1) lookups** *and* pointer movement logic.
- **How**: Use hash set to track seen elements while pointers traverse.
- **Problems**:
    - *Longest Substring Without Repeating Characters*  
      → `right` expands, add to set; if duplicate, move `left` and remove from set.
    - *Minimum Window Substring*  
      → Use hashmap to count required chars; two pointers adjust window.



### 3. **Two Pointers + Binary Search**
- **When**: You’re searching for an **optimal value** (e.g., minimal max sum) and can **verify feasibility** with two pointers.
- **How**: Binary search on answer → use two pointers to check if solution exists.
- **Problems**:
    - *Split Array Largest Sum*  
      → Binary search on possible max sum; use two pointers/greedy to check if `k` splits suffice.
    - *Capacity To Ship Packages* (similar idea)



### 4. **Two Pointers + Greedy**
- **When**: Local decisions (based on pointer values) lead to global optimum.
- **How**: Move pointer that gives best immediate improvement.
- **Problems**:
    - *Container With Most Water*  
      → Always move the **shorter** line inward (greedy choice).
    - *Trapping Rain Water*  
      → Track max left/right; move pointer from side with smaller max.



### 5. **Two Pointers + Recursion / Backtracking (Rare but powerful)**
- **When**: Generating combinations (e.g., *k-Sum*) beyond triplets.
- **How**: Use recursion for outer loops, two pointers for inner pair search.
- **Example**: *4Sum* → two nested loops + two pointers.


## 🧠 **Decision Flowchart: Is This a Two Pointer Problem?**

```plaintext
Is the input an array or string? ──No──→ Probably not.
            │
           Yes
            │
Is it about pairs, subarrays, or in-place modification? ──No──→ Likely not.
            │
           Yes
            │
Can you sort it (if needed)? ──Yes──→ Try opposite pointers.
            │
           No
            │
Is it about a contiguous window? ──Yes──→ Sliding window.
            │
           No
            │
Is it about filtering or traversal (e.g., duplicates, middle)? ──Yes──→ Fast-slow.
            │
           No
            │
Consider other patterns (DP, BFS, etc.)
```

## 🛠️ **Pro Tips for Mastery**

1. **Start with brute force**, then ask: *"Can I avoid checking all pairs?"*
2. **Visualize pointers moving**—draw small examples.
3. **Practice hybrid problems**:
    - *Minimum Window Substring* → Two pointers + hashmap
    - *3Sum Closest* → Sort + two pointers + tracking best
4. **Watch for hidden sortedness**:  
   Even if input isn’t sorted, sometimes **frequency arrays** or **character counts** create implicit order.



## 📌 Summary Table: Two Pointer + X

| Combination | Use Case | Example Problem |
|------------|--------|------------------|
| **+ Sorting** | Pair/triplet search | *3Sum*, *Two Sum II* |
| **+ Hash Map** | Dynamic window with constraints | *Longest Substring Without Repeats* |
| **+ Binary Search** | Optimize a value with feasibility check | *Split Array Largest Sum* |
| **+ Greedy** | Local optimal choice | *Container With Most Water* |
| **+ Recursion** | k-Sum generalization | *4Sum*, *k-Sum* |



### 🧠 **When to Use Two Pointers**

| Scenario | Pointer Type | Reason |
|--------|--------------|--------|
| **Sorted array** and need **pair/triplet** with sum/condition | ↔️ Opposite | Sorted order allows intelligent pointer movement |
| **Check symmetry** (palindromes) | ↔️ Opposite | Compare mirrored positions |
| **In-place modification** (remove duplicates, partition) | 🐢🐇 Fast-Slow | One pointer writes, the other reads |
| **Cycle detection** or **middle element** in linked list | 🐢🐇 Fast-Slow | Speed difference reveals structure |
| **Subarray/substring optimization** | 🪟 Sliding Window | Dynamic range with reuse of prior computation |



### ⚙️ **Implementation Tips**

1. **Initialize pointers correctly**:
    - Opposite: `left = 0`, `right = len(arr) - 1`
    - Fast-Slow: `slow = fast = 0` (or head of list)
2. **Define clear movement logic**:
    - When to move `left` vs. `right`?
    - What condition triggers pointer advancement?
3. **Handle edge cases**:
    - Empty arrays/strings
    - Single-element inputs
    - All elements identical
4. **Avoid infinite loops**: Ensure at least one pointer moves in each iteration.



### 📈 **Time & Space Complexity**
- **Time**: Typically **O(n)** — each element visited at most once.
- **Space**: **O(1)** — only uses a few extra variables (in-place).



### 🎯 **Two Pointer Mastery Tracker**

| #   | Problem Title                          | Pattern 🏷️                   | Difficulty | Status ✅ | Time ⏱️        | Space 💾  | Note 📝                                                                   |
| --- | -------------------------------------- | ----------------------------- | ---------- | :--------: | -------------- | --------- | ------------------------------------------------------------------------- |
| 1   | [Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)                       | ↔️ Opposite Pointers          | Easy       | ✅ | **O(n)**       | **O(1)**  | Skip non-alphanumeric chars with two pointers from both ends.             |
| 2   | [Reverse String](https://leetcode.com/problems/reverse-string/)                         | ↔️ Opposite Pointers          | Easy       | ✅ | **O(n)**       | **O(1)**  | Swap `s[left]` and `s[right]` in-place.                                   |
| 3   | [Two Sum II – Sorted Array](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)              | ↔️ Opposite Pointers          | Easy       | ✅ | **O(n)**       | **O(1)**  | Classic converging pointers on sorted array.                              |
| 4   | [Container With Most Water](https://leetcode.com/problems/container-with-most-water/)              | ↔️ Opposite Pointers          | Medium     | ✅ | **O(n)**       | **O(1)**  | Greedy: move pointer with smaller height.                                 |
| 5   | [3Sum](https://leetcode.com/problems/3sum/)                                   | ↔️ Opposite Pointers          | Medium     | ✅ | **O(n²)**      | **O(1)*** | Sort + fix one element, then two-sum with pointers. (*output not counted) |
| 6   | [Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/)              | ↔️ Opposite Pointers          | Medium     | ✅ | **O(n)**       | **O(n)**  | Compare abs values from both ends; fill result from back.                 |
| 7   | [4Sum](https://leetcode.com/problems/4sum/)                                   | ↔️ Opposite Pointers          | Medium     | ✅ | **O(n³)**      | **O(1)*** | Two nested loops + two pointers.                                          |
| 8   | [Boats to Save People](https://leetcode.com/problems/boats-to-save-people/)                   | ↔️ Opposite Pointers          | Medium     | ✅ | **O(n log n)** | **O(1)**  | Sort, then pair lightest + heaviest if within limit.                      |
| 9   | [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)                    | ↔️ Opposite Pointers          | Hard       | ✅ | **O(n)**       | **O(1)**  | Track `left_max` and `right_max`; move pointer with smaller max.          |
| 10  | [Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/)    | ➡️ Same Direction             | Easy       | ✅ | **O(n)**       | **O(1)**  | `slow` tracks unique pos; `fast` scans.                                   |
| 11  | [Remove Element](https://leetcode.com/problems/remove-element/)                         | ➡️ Same Direction             | Easy       | ✅ | **O(n)**       | **O(1)**  | Overwrite target values using fast-slow.                                  |
| 12  | [Move Zeroes](https://leetcode.com/problems/move-zeroes/)                            | ➡️ Same Direction             | Easy       | ✅ | **O(n)**       | **O(1)**  | Keep non-zeros at front using slow pointer.                               |
| 13  | [Subarray Product Less Than K](https://leetcode.com/problems/subarray-product-less-than-k/)           | 🪟 Sliding Window             | Medium     |   | **O(n)**       | **O(1)**  | Expand right, shrink left while product ≥ K.                              |
| 14  | [Longest Subarray with Abs Diff ≤ Limit](https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/) | 🪟 Sliding Window (Advanced)  | Medium     |   | **O(n)**       | **O(n)**  | Use deques or two pointers + min/max tracking (two-pointer core).         |
| 15  | [Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii/)               | 🪟 Sliding Window             | Medium     |   | **O(n)**       | **O(1)**  | Maintain window with ≤ K zeros using two pointers.                        |
| 16  | [Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/)                     | 🔀 Two Arrays                 | Easy       | ✅ | **O(m+n)**     | **O(1)**  | Merge from end to avoid overwriting.                                      |
| 17  | [Intersection of Two Arrays II](https://leetcode.com/problems/intersection-of-two-arrays-ii/)          | 🔀 Two Arrays                 | Easy       | ✅ | **O(m+n)**     | **O(1)*** | Sort both, then two pointers to find common elements.                     |
| 18  | [Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/)              | 🌀 Cycle Detection (Floyd)    | Medium     | ✅ | **O(n)**       | **O(1)**  | Treat array as LL; slow/fast pointers detect cycle.                       |
| 19  | [Minimum Absolute Difference](https://leetcode.com/problems/minimum-absolute-difference/)            | 🔀 Two Arrays / Adjacent Scan | Medium     | ✅ | **O(n log n)** | **O(1)**  | Sort, then scan adjacent pairs with two pointers.                         |
| 20  | [Sort Colors (Dutch Flag)](https://leetcode.com/problems/sort-colors/)               | 🎨 Three-Way Partition        | Medium     | ✅ | **O(n)**       | **O(1)**  | `low`, `mid`, `high` pointers partition 0s, 1s, 2s.                       |
| 21  | [Partition Array by Parity II](https://leetcode.com/problems/sort-array-by-parity-ii/description/)           | 🎨 Three-Way / Two Pointers   | Medium     | ✅ | **O(n)**       | **O(1)**  | Place evens at even indices, odds at odd—use two pointers.                |
| 22  | [Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/)              | 🧵 LL Fast-Slow               | Easy       | ✅ | **O(n)**       | **O(1)**  | Fast moves 2x, slow moves 1x → slow at mid.                               |
| 23  | [Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/)                      | 🧵 LL Fast-Slow               | Medium     | ✅ | **O(n)**       | **O(1)**  | If fast meets slow → cycle exists.                                        |
| 24  | [Palindrome Linked List](https://leetcode.com/problems/palindrome-linked-list/)                 | 🧵 LL Fast-Slow + Reverse     | Medium     | ✅ | **O(n)**       | **O(1)**  | Find mid, reverse second half, compare with two pointers.                 |
| 25  | [Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)       | 🧵 LL Fast-Slow               | Medium     | ✅ | **O(n)**       | **O(1)**  | Fast advances `n` steps, then both move → slow at target-1.               |


### 📌 Reference:
- **↔️** Opposite Pointers
- **➡️** Same Direction (Fast-Slow)
- **🪟** Sliding Window
- **🔀** Two Arrays / Merging
- **🌀** Cycle Detection
- **🎨** Three-Way Partition
- **🧵** Linked List Fast-Slow

> 💡 **All space complexities assume in-place modification and exclude output array** (standard in LeetCode analysis).


You can now:
- Track progress with ✅
- Estimate performance with ⏱️/💾
- Recall the core idea instantly with 📝


# ✅ Total Problems: **24 Core Problems**

| Pattern | Easy | Medium | Hard | Total |Status |
|--------|------|--------|------|-------|:-----------:|
| Opposite Pointers | 3 | 5 | 1 | 9 | ✅
| Same Direction | 3 | 3 | 0 | 6 | ✅
| Two Arrays | 2 | 2 | 0 | 4 | ✅
| Three-Way Partition | 0 | 2 | 0 | 2 | ✅
| Linked List | 1 | 3 | 0 | 4 | ✅
| **Total** | **9** | **15** | **1** | **25** | ✅

> ✅ Solving **all 25** will give you **complete command** over every Two Pointer variant asked in coding interviews.



## 📌 Recommended Practice Order

1. Start with **Pattern 1 (Opposite Pointers)** → foundational.
2. Then **Pattern 2 (Same Direction)** → most common in subarray problems.
3. Do **Pattern 5 (Linked List)** early if you’re prepping for FAANG (LL questions are frequent).
4. Finish with **Pattern 3 & 4** (merging & partitioning).

> ⏱️ Estimated time: **10–15 hours** of focused practice.




### 🎯 **Key Insight**
> **Two Pointers exploits structure** (sortedness, symmetry, sequential access) to **avoid brute-force**. Always ask: *"Can I eliminate a portion of the search space based on current pointer values?"*

Mastering this pattern unlocks efficient solutions to ~20% of common array/string interview problems! 🚀