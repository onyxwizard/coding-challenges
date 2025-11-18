## 📈 1.4 Kadane’s Algorithm

**Kadane’s Algorithm** is a remarkably elegant and efficient dynamic programming technique used to solve the **Maximum Subarray Sum** problem in **O(n) time** and **O(1) space**. At its core, it leverages the idea of **local optimality**: *“If continuing the current subarray yields a better sum than starting fresh, keep going—otherwise, reset.”*

Despite its simplicity, Kadane’s Algorithm is a powerhouse pattern that extends beyond basic maximum sum problems to variants involving **circular arrays**, **product maximization**, **constraints on length**, and even **2D matrices**.

### 🔍 Core Idea

At each position `i`, decide:
> _“Should I extend the existing subarray, or start a new one from here?”_

This decision is captured by the recurrence:

```
current_max = max(arr[i], current_max + arr[i])
global_max = max(global_max, current_max)
```

- **`current_max`**: Best sum of subarray ending **at** index `i`.
- **`global_max`**: Best sum seen **anywhere** so far.

💡 **Key Insight**:  
> _Negative prefixes are never worth keeping._  
> If the running sum drops below the current element, discard the past—it only drags you down.

---

### 🧩 Types of Kadane’s Algorithm Variants

#### 1. **Classic Maximum Subarray** 📈

- **Problem**: Find the contiguous subarray with the largest sum.
- **Solution**: Standard Kadane’s.
- **Edge Case**: All numbers negative → return the least negative (or 0 if empty subarray allowed).
- ✅ **Example**:
  ```python
  arr = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
  → Max subarray: [4, -1, 2, 1] → sum = 6
  ```

---

#### 2. **Maximum Subarray with at Least One Element** (No Empty Subarray)

- **Default assumption** in most coding problems (e.g., LeetCode 53).
- Initialize `global_max = arr[0]`, not 0.
- Ensures we **always pick at least one element**.

---

#### 3. **Circular Subarray Maximum Sum** 🔁

- **Twist**: Subarray can wrap around from end to start.
- **Key Insight**:  
  The maximum circular sum is either:
  1. The standard Kadane’s result (non-circular), **or**
  2. `Total sum - Minimum Subarray Sum` (the wrap-around case)

- **Special Case**: If **all elements are negative**, return standard Kadane’s (avoid returning 0 from empty middle).

✅ **Example**:  
`arr = [5, -3, 5]`  
- Standard max = 5  
- Total = 7, min subarray = -3 → circular = 7 - (-3) = 10 → answer = **10**

> 💡 Use **Kadane’s for max** and **reverse-Kadane’s for min** in one pass.

---

#### 4. **Maximum Product Subarray** ✖️

- **Not Kadane’s directly**, but **same spirit**: track both **max and min** at each step.
- Why? A negative number can turn a large negative into a large positive.
- **Recurrence**:
  ```python
  temp_max = max(arr[i], arr[i] * max_prod, arr[i] * min_prod)
  min_prod = min(arr[i], arr[i] * max_prod, arr[i] * min_prod)
  max_prod = temp_max
  ```
- ✅ **Example**: `[-2, 3, -4]` → max product = 24

> 🔸 Though not “Kadane’s” per se, it’s often taught alongside as a **Kadane-inspired DP**.

---

#### 5. **Kadane’s with Length Constraints** 📏

- **Problem**: Max sum subarray of **length ≥ K** or **exactly K**.
- **Approach**:
  - For **fixed length K**: Sliding window + running sum.
  - For **at least K**: Combine **prefix sum** + **Kadane-like DP** with deque to track best prefix.

✅ Example: *Maximum Sum of Subarray ≥ K* → use prefix sums and maintain min prefix seen so far.

---

#### 6. **2D Kadane’s (Maximum Sum Rectangle)** 🧱

- **Idea**: Fix top and bottom rows, compress columns into 1D array, then apply Kadane’s.
- **Time**: O(n³) for n×n matrix.
- **Steps**:
  1. For each pair of rows `(top, bottom)`, compute column-wise sum → 1D array.
  2. Run Kadane’s on that array.
  3. Track global maximum.

✅ Used in: *Max Sum of Rectangle No Larger Than K* (advanced variant with BST).

---
### 🔍 How to Detect Kadane’s Algorithm Problems

Ask yourself:

✅ **1. Does the problem ask for the maximum (or minimum) sum/product of a contiguous subarray?**  
→ Strong signal. Keywords: “contiguous”, “subarray”, “maximum sum”, “best segment”.

✅ **2. Is there a constraint that the subarray must be non-empty and contiguous?**  
→ Classic Kadane’s domain.

✅ **3. Does the input allow negative numbers?**  
→ If yes, brute force is O(n²); Kadane’s gives O(n).

✅ **4. Is it a circular array?**  
→ Think: *max(normal, total - min_subarray)*.

✅ **5. Are you tracking both best and worst (e.g., for products)?**  
→ Extend Kadane’s logic to maintain two states.

> 🚩 **Red Flag**: If **discontiguous** elements are allowed → it’s **not Kadane’s** (e.g., “maximum subset sum” → just sum all positives).

### 🧩 Where Kadane’s Combines with Other Patterns

| Combination              | Use Case                                      | Example Problem                     |
|--------------------------|-----------------------------------------------|-------------------------------------|
| **+ Prefix Sum**         | Enforce minimum length or find indices        | Max Sum Subarray ≥ K                |
| **+ Sliding Window**     | Fixed-size maximum sum                        | Maximum Average Subarray I          |
| **+ Modulo / Counting**  | Rare, but possible in constrained variants     | —                                   |
| **+ 2D Arrays**          | Maximum sum rectangle                         | Max Sum Rectangle                   |
| **+ Greedy**             | Local decision = global optimum               | Classic Kadane’s                    |
| **+ DP State Tracking**  | Product variants, sign-aware logic            | Maximum Product Subarray            |

> 💡 Kadane’s is **dynamic programming in disguise**—but so optimized it feels like greedy.

### 🧠 Decision Flowchart: Is This a Kadane’s Problem?

```
Is the input an array? ──No──→ Not Kadane’s.
        │
       Yes
        │
Are you looking for a contiguous subarray with optimal sum/product? ──No──→ No.
        │
       Yes
        │
Is empty subarray allowed? ──Yes──→ Handle carefully (often not in interviews).
        │
       No
        │
Are negatives present? ──Yes──→ Kadane’s shines.
        │
       No
        │
→ Just sum all (trivial), but Kadane’s still works.

Is it circular? ──Yes──→ Use total - min_subarray trick.
        │
       No
        │
→ Run standard Kadane’s.
```

### 🛠️ Pro Tips for Mastery

- **Always handle all-negative arrays** explicitly.
- For **circular**: compute **min_subarray** using *inverted Kadane’s* (flip signs or modify condition).
- In **product version**, **never forget the min**—it might become the max after a negative.
- To **recover indices** of the best subarray, track `start` and `end` when `current_max` resets.
- **Initialize wisely**:
  ```python
  max_current = max_global = arr[0]
  ```
  Avoid `= 0` unless empty subarray is allowed.

- **One-pass only**: Kadane’s is inherently sequential—no need for extra arrays.

### ⚙️ Implementation Template (Classic)

#### 1. **Classic Maximum Subarray** 📈

```java
public int maxSubArray(int[] nums) {
    if (nums == null || nums.length == 0) {
        throw new IllegalArgumentException("Array must be non-empty");
    }
    
    int currentMax = nums[0];
    int globalMax = nums[0];
    
    for (int i = 1; i < nums.length; i++) {
        currentMax = Math.max(nums[i], currentMax + nums[i]);
        globalMax = Math.max(globalMax, currentMax);
    }
    
    return globalMax;
}
```

#### 2. **Maximum Sum Circular Subarray** 🔁

```java
public int maxSubarraySumCircular(int[] nums) {
    int maxNormal = kadane(nums);
    
    // Compute total sum and min subarray sum
    int total = 0;
    for (int n : nums) total += n;
    
    int minSubarray = minKadane(nums); // or invert signs and use kadane
    int maxCircular = total - minSubarray;
    
    // If maxCircular is 0, all elements are negative → return maxNormal
    return (maxCircular == 0) ? maxNormal : Math.max(maxNormal, maxCircular);
}

private int kadane(int[] nums) {
    int cur = nums[0], best = nums[0];
    for (int i = 1; i < nums.length; i++) {
        cur = Math.max(nums[i], cur + nums[i]);
        best = Math.max(best, cur);
    }
    return best;
}

private int minKadane(int[] nums) {
    int cur = nums[0], best = nums[0];
    for (int i = 1; i < nums.length; i++) {
        cur = Math.min(nums[i], cur + nums[i]);
        best = Math.min(best, cur);
    }
    return best;
}
```

> ⚠️ Better: write a `min_kadane` to avoid double negation.

#### 3. **Maximum Product Subarray** ✖️

```java
public int maxProduct(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    
    int maxProd = nums[0];
    int minProd = nums[0];
    int result = nums[0];
    
    for (int i = 1; i < nums.length; i++) {
        int tempMax = Math.max(nums[i], Math.max(maxProd * nums[i], minProd * nums[i]));
        minProd = Math.min(nums[i], Math.min(maxProd * nums[i], minProd * nums[i]));
        maxProd = tempMax;
        
        result = Math.max(result, maxProd);
    }
    
    return result;
}
```

#### 4. **Kadane’s with Start/End Indices** 📍
```java
public int[] maxSubArrayWithIndices(int[] nums) {
    int currentMax = nums[0];
    int globalMax = nums[0];
    int start = 0, end = 0, tempStart = 0;
    
    for (int i = 1; i < nums.length; i++) {
        if (currentMax < 0) {
            currentMax = nums[i];
            tempStart = i;
        } else {
            currentMax += nums[i];
        }
        
        if (currentMax > globalMax) {
            globalMax = currentMax;
            start = tempStart;
            end = i;
        }
    }
    
    return new int[]{globalMax, start, end}; // {maxSum, startIndex, endIndex}
}
```
### 📊 Time & Space Cmplexity

| Variant              | Time  | Space |
| -------------------- | ----- | ----- |
| Classic Kadane’s     | O(n)  | O(1)  |
| Circular             | O(n)  | O(1)  |
| Maximum Product      | O(n)  | O(1)  |
| 2D Maximum Rectangle | O(n³) | O(n)  |
| With Index Tracking  | O(n)  | O(1)  |

---

### 🎯 Kadane’s Algorithm Mastery Tracker

| #   | Problem Title                         | Pattern 🏷️                | Difficulty | Status  | Time ⏱️     | Space 💾 | Note 📝                            |
| --- | ------------------------------------- | -------------------------- | ---------- | :-----: | ----------- | -------- | ---------------------------------- |
| 1   | Maximum Subarray                      | Classic Kadane’s           | Easy       |    ✅    | O(n)        | O(1)     | LeetCode #53                       |
| 2   | Maximum Product Subarray              | Kadane’s (Product)         | Medium     |    ✅    | O(n)        | O(1)     | Track min & max                    |
| 3   | Maximum Sum Circular Subarray         | Circular Kadane’s          | Medium     |    ✅    | O(n)        | O(1)     | total - min_subarray               |
| 4   | Best Time to Buy and Sell Stock       | Kadane’s (Diff Array)      | Easy       |    ✅   | O(n)        | O(1)     | Max profit = max subarray of diffs |
| 5   | Maximum Absolute Subarray Sum         | Kadane’s + Min/Max         | Hard       |    ✅   | O(n)        | O(1)     | Rare variant                       |
| 6   | Max Sum of Rectangle No Larger Than K | 2D + Kadane’s + BST        | Hard       |    ✅    | O(n³ log n) | O(n)     | Advanced                           |
| 7   | Maximum Subarray Min-Product          | Kadane’s + Monotonic Stack | Medium     |    ✅    | O(n)        | O(n)     | Combine with next greater element  |
| 8   | Largest Subarray Length with Sum = K  | Prefix + Hash Map          | Medium     |    ✅    | O(n)        | O(n)     | Not Kadane’s—don’t confuse!        |
| 9   | Maximum Average Subarray I            | Sliding Window             | Easy       |    ✅    | O(n)        | O(1)     | Fixed size → not Kadane’s          |
| 10  | Flip String to Monotone Increasing    | DP (Kadane-like)           | Medium     |         | O(n)        | O(1)     | Min flips = min cost to split      |

> 🔸 **Note**: Problems like #8 and #9 **are not Kadane’s**—they test your ability to **distinguish** between patterns.

### 📌 Summary Table: When to Use Kadane’s

| Scenario                                      | Use Kadane’s? | Why? |
|----------------------------------------------|:---------------:|------|
| Max sum of **contiguous** subarray           | ✅ Yes        | Optimal O(n) solution |
| Subarray must be non-empty                   | ✅ Yes        | Handle initialization correctly |
| Array contains negatives                      | ✅ Yes        | Brute force is too slow |
| **Circular** array                           | ✅ Yes (with twist) | Use total - min_subarray |
| **Product** instead of sum                   | ✅ Modified   | Track min and max |
| **Fixed length** subarray                    | ❌ No         | Use sliding window |
| **Discontiguous** elements allowed           | ❌ No         | Just sum positives |
| Need to **count** subarrays with sum = K     | ❌ No         | Use prefix sum + hash map |
✅ **Total Core Problems**: 10 (4 solved above)  
🎯 **Master these, and you’ll solve any max subarray problem in O(n).**
