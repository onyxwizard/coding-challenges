## ➕ Prefix Sum / Cumulative Sum

The **Prefix Sum** (also called **Cumulative Sum**) technique is a powerful and elegant pre processing strategy that transforms repeated range queries or subarray computations from **O(n)** or **O(n²)** down to **O(1)** or **O(n)** total time. It’s especially prevalent in problems involving **subarray sums**, **range queries**, **average calculations**, and **difference constraints**.

By precomputing cumulative totals from the start of an array, you gain instant access to the sum of any contiguous subarray—making it a go-to tool for **optimizing brute-force approaches** involving repeated summation.

### 🔍 Core Idea

Instead of recalculating the sum of `arr[i..j]` every time (which takes **O(j - i + 1)**), **precompute a prefix array** `prefix` where:

```
prefix[0] = 0  
prefix[i] = arr[0] + arr[1] + ... + arr[i - 1]
```

Then, the sum of any subarray `arr[i..j]` (inclusive) is simply:

```
sum(i, j) = prefix[j + 1] - prefix[i]
```

This turns **range sum queries** into **constant-time operations**.

✅ **Key Insight**:
> _“Don’t recompute what you can remember.”_  
> Prefix sum trades **O(n)** extra space for massive time savings across repeated or nested subarray operations.

---

### 🧩 Types of Prefix Sum Patterns

#### 1. **Basic Range Sum Queries** 📊

- **How it works**: Build `prefix` once; answer any `sum(i, j)` in O(1).
- **Use cases**:
    - Range sum queries (LeetCode: *Range Sum Query - Immutable*)
    - Subarray sum equals K (optimized with hash map—see below)
- **Example**:
  ```python
  arr = [1, 2, 3, 4, 5]
  prefix = [0, 1, 3, 6, 10, 15]  # prefix[0]=0, prefix[1]=arr[0], etc.
  sum(1, 3) = prefix[4] - prefix[1] = 10 - 1 = 9  # arr[1]+arr[2]+arr[3] = 2+3+4
  ```

#### 2. **Prefix Sum + Hash Map (Subarray Sum = K)** 🔍

- **How it works**: While building the prefix sum on the fly, store how many times each prefix sum has occurred in a hash map.
- **Why it works**:  
  If `prefix[j] - prefix[i] = K`, then `prefix[i] = prefix[j] - K`.  
  So at each step `j`, check if `prefix[j] - K` exists in the map.
- **Use cases**:
    - *Subarray Sum Equals K*
    - *Contiguous Array* (treat 0 as -1, find subarray with sum 0)
    - *Find Pivot Index* (left sum == right sum → use total sum and prefix)
- **Time**: O(n), **Space**: O(n)

✅ **Example**:  
`arr = [1, 1, 1], K = 2`  
Prefix sums: `[0, 1, 2, 3]`  
At index 2 (prefix=2), we look for `2 - 2 = 0` → seen once → count += 1  
At index 3 (prefix=3), look for `3 - 2 = 1` → seen once → count += 1  
→ Total subarrays = **2**


#### 3. **2D Prefix Sum (Matrix Range Sum)** 🧱

- **How it works**: Extend 1D idea to 2D grid.
  ```
  prefix[i][j] = sum of all elements in rectangle from (0,0) to (i-1, j-1)
  ```
  Then:
  ```
  sum(r1, c1, r2, c2) = prefix[r2+1][c2+1] 
                        - prefix[r1][c2+1] 
                        - prefix[r2+1][c1] 
                        + prefix[r1][c1]
  ```
- **Use cases**:
    - *Range Sum Query 2D - Immutable*
    - *Max Sum of Rectangle No Larger Than K* (combined with sorted sets)


#### 4. **Difference Array (Range Update + Point Query)** ➕➖

- **How it works**: For **range increment/decrement** operations (e.g., “add 5 to all elements from i to j”), use a **difference array**:
    - `diff[i] += val`, `diff[j+1] -= val`
    - Final array = prefix sum of `diff`
- **Why it works**: Lazy propagation of updates; reconstruct actual array in O(n).
- **Use cases**:
    - *Corporate Flight Bookings*
    - *Car Pooling*
    - *Range Addition*

✅ **Example**:  
Apply `+10` to indices [1, 3] in array of size 5:  
`diff = [0, 10, 0, 0, -10, 0]`  
Prefix sum → `[0, 10, 10, 10, 0, 0]` → actual updates applied in O(1) per operation.

---
### 🔍 How to Detect Prefix Sum Problems

Ask yourself:

✅ **1. Does the problem ask for subarray sums, averages, or totals over ranges?**  
→ Classic signal. Examples: “sum of subarray between i and j”, “find subarray with sum K”.

✅ **2. Are there repeated or multiple range queries?**  
→ If you’re looping and summing the same regions repeatedly, prefix sum will help.

✅ **3. Is there a constraint like “subarray sum divisible by K” or “sum equals zero”?**  
→ Often solved via **prefix sum + modulo + hash map** (e.g., *Subarray Sums Divisible by K*).

✅ **4. Are you updating ranges and then querying final state?**  
→ Think **difference array**, then prefix sum to reconstruct.

✅ **5. Does the problem involve balance or cancellation?**  
→ Convert to sum problem: e.g., *Contiguous Array* → treat 0 as -1, find subarray with sum 0.

### 🧩 Where Prefix Sum Combines with Other Patterns

| Combination              | Use Case                                      | Example Problem                     |
|--------------------------|-----------------------------------------------|-------------------------------------|
| **+ Hash Map**           | Count subarrays with target sum               | Subarray Sum Equals K               |
| **+ Modulo Arithmetic**  | Subarrays divisible by K                      | Subarray Sums Divisible by K        |
| **+ Sliding Window**     | When window size is fixed; use prefix for sum | Maximum Average Subarray I          |
| **+ Binary Search**      | Find minimal subarray length with sum ≥ target| Minimum Size Subarray Sum           |
| **+ 2D Arrays**          | Matrix region sums                            | Range Sum Query 2D                  |
| **+ Difference Array**   | Batch range updates                           | Corporate Flight Bookings           |

> 💡 **Pro Tip**: If you see “subarray” + “sum” + “count” or “exists”, **first think prefix sum + hash map**.

### 🧠 Decision Flowchart: Is This a Prefix Sum Problem?

```
Does the problem involve subarrays or ranges? ──No──→ Probably not.
                │
               Yes
                │
Is it about sum, average, or total over a segment? ──No──→ Consider other patterns (e.g., DP).
                │
               Yes
                │
Are you doing this once or many times? ──Many──→ Prefix sum likely needed.
                │
               Once
                │
Is K involved (e.g., sum = K, divisible by K)? ──Yes──→ Prefix + hash map / modulo.
                │
               No
                │
Is it a 2D grid? ──Yes──→ 2D prefix sum.
                │
               No
                │
Are you updating ranges? ──Yes──→ Difference array → prefix sum.
```

### 🛠️ Pro Tips for Mastery

- **Always include `prefix[0] = 0`**—it handles subarrays starting at index 0 cleanly.
- In hash map version, **initialize map with `{0: 1}`** to account for subarrays starting at index 0.
- For **modulo problems**, use `(prefix % K + K) % K` to handle negative remainders.
- **Difference array** is underused—remember it for “apply N range updates, then output final array”.
- When stuck on a subarray sum problem, **write out prefix sums by hand**—patterns emerge quickly.

### 📊 Time & Space Complexity

| Variant                     | Time       | Space     |
|----------------------------|------------|-----------|
| Basic Prefix Sum           | O(n) build, O(1) query | O(n) |
| Prefix + Hash Map          | O(n)       | O(n)      |
| 2D Prefix Sum              | O(mn) build, O(1) query | O(mn) |
| Difference Array           | O(1) per update, O(n) final | O(n) |

---

### 🎯 Prefix Sum Mastery Tracker

| # | Problem Title                              | Pattern 🏷️                     | Difficulty | Status  | Time ⏱️   | Space 💾 | Note 📝 |
|---|--------------------------------------------|-------------------------------|------------|:----------:|----------|----------|--------|
| 1 | Running Sum of 1d Array                    | Basic Prefix                  | Easy       |   ✅    | O(n)     | O(1)*    | In-place or new array |
| 2 | Range Sum Query - Immutable                | Basic Prefix                  | Easy       |   ✅    | O(n)/O(1)| O(n)     | Classic range query |
| 3 | Subarray Sum Equals K                      | Prefix + Hash Map             | Medium     |  ✅   | O(n)     | O(n)     | Initialize map with {0:1} |
| 4 | Contiguous Array                           | Prefix + Hash Map (0→-1)      | Medium     |  ✅   | O(n)     | O(n)     | Max length subarray with equal 0s/1s |
| 5 | Find Pivot Index                           | Prefix + Total Sum            | Easy       |  ✅    | O(n)     | O(1)     | leftSum == total - leftSum - arr[i] |
| 6 | Left and Right Sum Differences             | Prefix / Suffix               | Easy       |  ✅    | O(n)     | O(1)     | Compute left/right sums |
| 7 | Subarray Sums Divisible by K               | Prefix + Modulo + Hash Map    | Medium     |  ✅    | O(n)     | O(K)     | Handle negative mod! |
| 8 | Minimum Size Subarray Sum                  | Prefix + Binary Search        | Medium     |  ✅     | O(n log n)| O(n)     | Or use sliding window (better) |
| 9 | Maximum Size Subarray Sum Equals K         | Prefix + Hash Map             | Medium     |  ✅     | O(n)     | O(n)     | Track first occurrence of prefix |
|10 | Range Sum Query 2D - Immutable             | 2D Prefix Sum                 | Medium     |  ✅     | O(mn)/O(1)| O(mn)    | 2D extension |
|11 | Corporate Flight Bookings                  | Difference Array              | Medium     |  ✅    | O(n)     | O(n)     | Apply range updates, then prefix |
|12 | Car Pooling                                | Difference Array (timeline)   | Medium     |  ✅     | O(n)     | O(1001)  | Use fixed-size diff array |

> *O(1) space if modifying input; O(n) if returning new array.


### 📌 Summary Table: When to Use Prefix Sum

| Scenario                                      | Pattern                        | Why? |
|----------------------------------------------|-------------------------------|------|
| Answer many subarray sum queries             | Basic Prefix                  | O(1) per query after O(n) build |
| Count subarrays with sum = K                 | Prefix + Hash Map             | Avoid O(n²) brute force |
| Subarray with sum divisible by K             | Prefix + Modulo + Hash Map    | Use remainder properties |
| Apply multiple range increments              | Difference Array              | Batch updates in O(1) each |
| 2D matrix region sum                         | 2D Prefix Sum                 | Extend 1D logic to grid |
| Find balance point (e.g., pivot index)       | Prefix vs Total Sum           | Left sum = right sum |

✅ **Total Core Problems**: 12 (12 solved above)  