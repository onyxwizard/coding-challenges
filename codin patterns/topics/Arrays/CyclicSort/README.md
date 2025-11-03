## 🔁 1.5 Cyclic Sort

**Cyclic Sort** is a **linear-time, in-place sorting algorithm** designed specifically for arrays where elements are **positive integers in a known, limited range**—most commonly, **numbers from `1` to `n`** (where `n` is the array length). Unlike general-purpose sorts (e.g., QuickSort, MergeSort), Cyclic Sort exploits the **direct relationship between values and their correct indices** to place each number in its “home” position in **O(n) time** and **O(1) space**.

While rarely used for general sorting, Cyclic Sort shines in **coding interviews** as a *stepping stone* to solve problems like:
- Find the missing number
- Find all duplicates
- Find the first missing positive
- Identify misplaced elements

Its true power lies not in sorting—but in **revealing anomalies** (duplicates, missing values, out-of-place elements) **after** the (partial) sort.



### 🔍 Core Idea

In a sorted array of `n` elements containing **all numbers from `1` to `n` exactly once**, the number `x` should be at index `x - 1`.

> ✅ **Correct position of value `x` → index `x - 1`**

Cyclic Sort iterates through the array and, for each misplaced element, **swaps it to its correct position**. This process continues until the current index holds the right value—or until a duplicate is detected.

Because each element is moved **at most once** to its final position, the total number of operations is **O(n)**.

💡 **Key Insight**:  
> _“If you know where every number should go, just put it there—and see what’s left behind.”_



### 🧩 How Cyclic Sort Works (Step-by-Step)

Given: `arr = [3, 5, 2, 1, 4]` (n = 5, values 1–5)

- Index 0: value = 3 → should be at index 2 → swap `arr[0]` and `arr[2]` → `[2, 5, 3, 1, 4]`
- Index 0: value = 2 → should be at index 1 → swap → `[5, 2, 3, 1, 4]`
- Index 0: value = 5 → should be at index 4 → swap → `[4, 2, 3, 1, 5]`
- Index 0: value = 4 → should be at index 3 → swap → `[1, 2, 3, 4, 5]`
- Now index 0 is correct. Move to index 1… and so on.

✅ Final array is sorted—**in O(n) time, no extra space**.



### 🧩 Common Problem Patterns Solved with Cyclic Sort

| Problem Type | How Cyclic Sort Helps |
|--------------|------------------------|
| **Find Missing Number** | After sorting, the first index `i` where `arr[i] != i + 1` reveals the missing number. |
| **Find Duplicate(s)** | If `arr[i] != i + 1` and `arr[arr[i] - 1] == arr[i]`, then `arr[i]` is a duplicate. |
| **Find All Missing Numbers** | Scan after sort; all `i` where `arr[i] != i + 1` → missing = `i + 1`. |
| **Find First Missing Positive** | Ignore non-positive and out-of-range numbers; apply Cyclic Sort only to valid candidates. |
| **Find Corrupt Pair (one dup, one missing)** | After sort, index with wrong value → missing = `i + 1`, duplicate = `arr[i]`. |



### 🔍 How to Detect Cyclic Sort Problems

Ask yourself:

✅ **1. Is the input an array of positive integers?**  
✅ **2. Is the range of values known and bounded by the array size?**  
→ e.g., “numbers from 1 to n”, “n+1 elements with values 1 to n”, etc.

✅ **3. Are you asked to find missing, duplicate, or misplaced numbers?**  
→ Classic signal. Keywords: “missing”, “duplicate”, “not in place”, “first missing positive”.

✅ **4. Can we assume O(1) extra space and O(n) time?**  
→ If yes, **hash map or sorting won’t cut it**—Cyclic Sort is likely intended.

🚩 **Red Flags**:
- Array contains **negative numbers or zeros** → may need filtering (e.g., *First Missing Positive*).
- Values **exceed `n`** → ignore or skip them during sort.

---

### 🛠️ Generic Cyclic Sort Template (Java)

```java
public void cyclicSort(int[] nums) {
    int i = 0;
    while (i < nums.length) {
        int correctIndex = nums[i] - 1; // value x should be at index x - 1
        
        // Only swap if:
        // - the current value is within [1, n]
        // - and it's not already in the correct place
        if (nums[i] >= 1 && nums[i] <= nums.length && nums[i] != nums[correctIndex]) {
            swap(nums, i, correctIndex);
        } else {
            i++; // move forward only when current position is correct or invalid
        }
    }
}

private void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}
```

> ⚠️ **Critical**: Only increment `i` when you **don’t swap**—otherwise, you might skip an unprocessed element.

---

### 🧩 Problem-Specific Variants (Java Examples)

#### 1. **Find the Missing Number**  
Array has `n` distinct numbers from `0` to `n` → one missing.

> Adjust: correct index for value `x` is `x` (since range is 0 to n).

But easier: **use XOR or sum formula**. Cyclic Sort is overkill here—unless modified for 1-based.

✅ Better use case: **1 to n with one missing** → array length = n, values = 1 to n+1 → one missing.

#### 2. **Find All Duplicates in an Array**  
`arr = [4,3,2,7,8,2,3,1]` → return `[2,3]`

```java
public List<Integer> findDuplicates(int[] nums) {
    List<Integer> duplicates = new ArrayList<>();
    int i = 0;
    
    while (i < nums.length) {
        int correct = nums[i] - 1;
        if (nums[i] != nums[correct]) {
            swap(nums, i, correct);
        } else {
            i++;
        }
    }
    
    // Now, any index i where nums[i] != i + 1 → nums[i] is duplicate
    for (i = 0; i < nums.length; i++) {
        if (nums[i] != i + 1) {
            duplicates.add(nums[i]);
        }
    }
    
    return duplicates;
}
```

#### 3. **Find the First Missing Positive** (LeetCode #41)  
Array may contain negatives, zeros, and values > n.

**Strategy**:
- Ignore invalid numbers (≤ 0 or > n)
- Apply Cyclic Sort only to valid ones
- Scan for first `i` where `nums[i] != i + 1`

```java
public int firstMissingPositive(int[] nums) {
    int i = 0;
    while (i < nums.length) {
        // Only consider numbers in [1, n]
        if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
            swap(nums, i, nums[i] - 1);
        } else {
            i++;
        }
    }
    
    for (i = 0; i < nums.length; i++) {
        if (nums[i] != i + 1) {
            return i + 1;
        }
    }
    
    return nums.length + 1; // all 1..n present
}
```


### 🧠 Decision Flowchart: Is This a Cyclic Sort Problem?

```
Is the array of integers? ──No──→ No.
        │
       Yes
        │
Are values in a known range like 1 to n (or 0 to n)? ──No──→ Probably not.
        │
       Yes
        │
Are you asked to find missing/duplicate/misplaced elements? ──No──→ Maybe not.
        │
       Yes
        │
Is O(n) time and O(1) space required? ──Yes──→ Cyclic Sort likely intended.
```

### 🛠️ Pro Tips for Mastery

- **Always validate the value** before computing `correctIndex`:
  ```java
  if (nums[i] >= 1 && nums[i] <= n) { ... }
  ```
- **Don’t increment `i` after a swap**—the new element at `i` may also be misplaced.
- For **0-based ranges** (0 to n-1), correct index = `nums[i]`.
- For **1-based ranges** (1 to n), correct index = `nums[i] - 1`.
- After sorting, **scan the array**—the answer is usually in the **mismatched positions**.
- Cyclic Sort **does not work** if:
  - Range is unknown
  - Values are arbitrary (e.g., [100, 200, 300])
  - Duplicates are many and range is unclear



### 📊 Time & Space Complexity

| Scenario                     | Time       | Space     |
|------------------------------|------------|-----------|
| Cyclic Sort (valid range)    | O(n)       | O(1)      |
| With duplicates/missing scan | O(n)       | O(1)      |
| First Missing Positive       | O(n)       | O(1)      |

> ✅ Each element is swapped **at most once** → total operations ≤ 2n.

---

### 🎯 Cyclic Sort Mastery Tracker

| # | Problem Title                        | Pattern 🏷️             | Difficulty | Status ✅ | Time ⏱️ | Space 💾 | Note 📝 |
|---|--------------------------------------|------------------------|------------|:----------:|--------|----------|--------|
| 1 | Find All Numbers Disappeared in Array | Cyclic Sort + Scan     | Easy       | ✅        | O(n)   | O(1)     | After sort, missing = i+1 where arr[i] ≠ i+1 |
| 2 | Find All Duplicates in an Array       | Cyclic Sort + Scan     | Medium     |  ✅  | O(n)   | O(1)     | Duplicates sit at wrong indices |
| 3 | Find the Duplicate Number             | Cyclic Sort **or** Floyd | Medium   |      | O(n)   | O(1)     | Cyclic Sort works if you can modify array |
| 4 | First Missing Positive                | Cyclic Sort (filtered) | Hard       |      | O(n)   | O(1)     | Ignore invalid numbers |
| 5 | Set Mismatch                          | Cyclic Sort            | Easy       |      | O(n)   | O(1)     | One dup, one missing → scan after sort |
| 6 | Missing Number                        | Math/XOR preferred     | Easy       |      | O(n)   | O(1)     | Cyclic Sort possible but overkill |
| 7 | Find the Smallest Missing Integer     | Cyclic Sort variant    | Medium     |      | O(n)   | O(1)     | Custom range handling |

> 🔸 **Note**: Some problems (like #3) have **non-modifying** constraints → use **Floyd’s Cycle Detection** instead.

### 📌 Summary Table: When to Use Cyclic Sort

| Scenario                                      | Use Cyclic Sort? | Why? |
|----------------------------------------------|------------------|------|
| Array of size `n` with values `1` to `n` (or close) | ✅ Yes | Direct index-value mapping |
| Asked to find **missing/duplicate** in O(1) space | ✅ Yes | Reveals anomalies after sort |
| Array contains **negatives or out-of-range** values | ⚠️ With filtering | Skip invalid during sort |
| **Cannot modify input array**                | ❌ No            | Use hash set or math instead |
| Range is **unknown or large**                | ❌ No            | No index-value relationship |


✅ **Total Core Problems**: 7 (5 solved above)  
🎯 **Master these, and you’ll spot Cyclic Sort opportunities instantly.**


> Cyclic Sort is a **specialized but powerful** pattern—ideal for interviews where the constraints align perfectly with its assumptions. It’s not a general sorting tool, but a **diagnostic technique** that turns sorting into problem-solving.
