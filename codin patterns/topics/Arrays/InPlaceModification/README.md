# 🧪 1.6 In-Place Modification

**In-Place Modification** is a clever space-optimization technique where you **encode extra information directly into the input array**—without using additional data structures like hash maps, sets, or boolean arrays. By repurposing the **sign**, **value**, or **index** of existing elements as **metadata**, you can solve problems in **O(1) extra space** while maintaining **O(n) time**.

This pattern is especially powerful when:
- The input consists of **positive integers in a known range** (e.g., `1` to `n`)
- You're asked to find **missing**, **duplicate**, or **unseen** elements
- **Modifying the input is allowed** (common in interviews unless stated otherwise)

Two dominant tricks form the backbone of this pattern:
1. **Negative (Sign) Marking** ➖  
2. **Index-as-Hash (Value-to-Index Mapping)** 🗃️

Used together or separately, they turn the array into a **self-referential hash table**.

### 🔍 Core Idea

> _“If you can’t bring a notebook, write on the test paper.”_

Instead of allocating a `seen[]` boolean array or `Set<Integer>`, **use the input array itself to track which numbers have been encountered**—by:
- Flipping the **sign** of `arr[value - 1]` to mark that `value` was seen, or
- Storing information at **index = value** (or `value - 1`)

This only works when:
- All numbers are **positive** (so sign carries meaning)
- All numbers are within **valid index bounds** (so `value - 1` is a safe index)

💡 **Key Insight**:  
> The array is not just data—it’s also **storage for state**.


## 🧩 Two Key Techniques

### 1. **Negative Marking (Sign as Flag)** ➖

**How it works**:
- Traverse the array.
- For each value `x = |arr[i]|`, treat it as a number that “exists”.
- Go to index `x - 1` and **negate** the value there → this marks that `x` has been seen.
- In a second pass, **positive values** indicate **missing numbers**.

✅ **Requirements**:
- Input contains **positive integers**
- Range is **1 to n** (so `x - 1` is a valid index)

**Example**: `arr = [4,3,2,7,8,2,3,1]`  
After marking:
- `arr[3]` (for 4) → negated
- `arr[2]` (for 3) → negated  
- …  
- Final: `[-4,-3,-2,-7,8,2,-3,-1]`  
- Positive at index 4 and 5 → missing = `[5,6]` (but in duplicate problem, positives reveal duplicates)

> 🔸 Used in: *Find All Numbers Disappeared in an Array*

### 2. **Index-as-Hash (Using Position as Key)** 🗃️

**How it works**:
- The **index** represents a number (`index + 1`), and the **value** at that index encodes whether it’s been visited.
- Instead of negation, you might **add `n`**, **set to zero**, or **swap**—depending on constraints.

**Most powerful when combined with negative marking**.

✅ **Critical**: Always use **absolute value** when reading, because the array gets mutated during traversal.

---
### 🔍 How to Detect In-Place Modification Problems

Ask:
✅ **1. Is the array of size `n` and contains integers from `1` to `n` (or `1` to `n+1`)?**  
✅ **2. Are you asked to find missing/duplicate/first missing positive?**  
✅ **3. Is O(1) space required?**  
✅ **4. Are all values positive (or can be made positive)?**

If **yes** to all → **In-Place Modification is likely the intended solution**.

🚩 **Red Flags**:
- Array contains **negatives or zeros** → may need preprocessing (e.g., in *First Missing Positive*)
- **Cannot modify input** → use Floyd’s cycle detection or extra space

---
### 🧩 Problem Breakdowns (Java Logic)

#### 🔸 **287. Find the Duplicate Number**  
> Given `n + 1` integers in range `[1, n]`, find the duplicate. **Assume only one duplicate**, but it could repeat >2 times. **Modify array allowed?** → Yes in this variant.

**In-Place Solution (Negative Marking)**:

```java
public int findDuplicate(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
        int index = Math.abs(nums[i]) - 1; // map value to index
        
        if (nums[index] < 0) {
            // Already marked → this value is duplicate
            return Math.abs(nums[i]);
        }
        
        nums[index] = -nums[index]; // mark as seen
    }
    return -1; // should not happen per problem constraints
}
```

✅ **Time**: O(n), **Space**: O(1)  
⚠️ **Modifies input**—if not allowed, use **Floyd’s Cycle Detection** (treat as linked list)

---
#### 🔸 **41. First Missing Positive**  
> Given unsorted integer array, find the smallest missing positive integer.  
> Must run in **O(n)** time and **O(1)** space.  
> Array may contain **negatives, zeros, and large numbers**.

**Strategy**:
1. **Ignore invalid numbers**: Only care about values in `[1, n]`
2. Use **in-place marking** on valid numbers
3. Scan for first index `i` where `nums[i] > 0` → answer = `i + 1`

**Step-by-step**:
- First pass: Replace all non-positive and >n values with a **placeholder** (e.g., `n + 1`)
- Second pass: For each `x` in `[1, n]`, mark `nums[x - 1]` as negative
- Third pass: First index with positive value → missing = `index + 1`

**Optimized (single-pass marking with absolute values)**:

```java
public int firstMissingPositive(int[] nums) {
    int n = nums.length;
    
    // Step 1: Replace invalid numbers with (n + 1) so they don't interfere
    for (int i = 0; i < n; i++) {
        if (nums[i] <= 0 || nums[i] > n) {
            nums[i] = n + 1;
        }
    }
    
    // Step 2: Use sign to mark presence of numbers 1..n
    for (int i = 0; i < n; i++) {
        int num = Math.abs(nums[i]);
        if (num <= n) {
            nums[num - 1] = -Math.abs(nums[num - 1]); // ensure negative
        }
    }
    
    // Step 3: Find first positive → missing number
    for (int i = 0; i < n; i++) {
        if (nums[i] > 0) {
            return i + 1;
        }
    }
    
    return n + 1; // all 1..n present
}
```

✅ **Time**: O(n), **Space**: O(1)  
✅ Handles negatives, zeros, and out-of-range gracefully

### 🧠 When to Choose In-Place vs. Cyclic Sort vs. Floyd’s

| Problem Constraint                      | Best Approach               |
|----------------------------------------|-----------------------------|
| Can modify array, 1 duplicate, 1..n    | ✅ **In-Place (Negative Marking)** |
| Cannot modify array                    | 🔄 **Floyd’s Cycle Detection** |
| Need to find **all** duplicates/missing| 🔄 **Cyclic Sort** or **In-Place Marking** |
| First missing positive (with negatives)| ✅ **In-Place Marking (with cleanup)** |

> 💡 **In-Place Marking** is **simpler and faster** than Cyclic Sort for single-scan problems.

### 🛠️ Pro Tips for Mastery

- **Always use `Math.abs()`** when reading values during marking—because the array gets mutated mid-traversal.
- **Never assume input is clean**—in *First Missing Positive*, sanitize first.
- **Placeholder choice matters**: Use `n + 1` (or `Integer.MAX_VALUE`) to avoid index errors.
- **Restore array if needed**: Interviewers may ask to revert modifications—track changes or use reversible encoding.
- **Test edge cases**:
  - `[1]` → missing = 2
  - `[1,1]` → duplicate = 1
  - `[-1, -2, -3]` → missing = 1

### 📊 Time & Space Complexity

| Problem                     | Time       | Space     | Modifies Input? |
|----------------------------|------------|-----------|------------------|
| Find Duplicate (In-Place)  | O(n)       | O(1)      | ✅ Yes           |
| First Missing Positive     | O(n)       | O(1)      | ✅ Yes           |
| Find All Missing Numbers   | O(n)       | O(1)      | ✅ Yes           |

---

### 🎯 In-Place Modification Mastery Tracker

| # | Problem Title                  | Technique 🏷️             | Difficulty | Status ✅ | Time ⏱️ | Space 💾 | Note 📝 |
|---|--------------------------------|--------------------------|------------|----------|--------|----------|--------|
| 1 | Find the Duplicate Number      | Negative Marking         | Medium     | ✅        | O(n)   | O(1)     | Assumes modifiable array |
| 2 | First Missing Positive         | Sanitize + Negative Mark | Hard       | ✅        | O(n)   | O(1)     | Handle negatives/zero |
| 3 | Find All Numbers Disappeared   | Negative Marking         | Easy       | ✅        | O(n)   | O(1)     | Return all missing 1..n |
| 4 | Find All Duplicates            | Negative Marking         | Medium     | ✅        | O(n)   | O(1)     | Add to result when seen twice |
| 5 | Set Mismatch                   | Negative Marking         | Easy       | ✅        | O(n)   | O(1)     | One dup, one missing |

> 🔸 **Note**: If the problem says **“read-only array”** or **“don’t modify input”**, **skip in-place** and use **Floyd’s** or **extra space**.
### 📌 Summary: In-Place Modification Cheat Sheet

| Trick                 | How It Works                          | When to Use |
|-----------------------|----------------------------------------|-------------|
| **Negative Marking**  | Flip sign at `index = value - 1`       | All values in `[1, n]`, positive |
| **Sanitize First**    | Replace invalid with `n+1`             | Input has negatives/zeros |
| **Absolute Value**    | Always read with `Math.abs()`          | During traversal (array is mutated) |
| **Scan for Positives**| First `nums[i] > 0` → missing = `i+1`  | Finding missing numbers |

✅ **Total Core Problems**: 5 (all solved above)  
🎯 **With this, you’ve completed the entire “Arrays & Strings” foundational toolkit**:

1️⃣ Two Pointers  
2️⃣ Prefix Sum  
3️⃣ Kadane’s Algorithm  
4️⃣ Cyclic Sort  
5️⃣ In-Place Modification  

You’re now equipped to solve **90% of array/string interview problems** with optimal time and space.
