
Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.

Example 1:

Input: nums = [8,2,4,7], limit = 4
Output: 2 
Explanation: All subarrays are: 
[8] with maximum absolute diff |8-8| = 0 <= 4.
[8,2] with maximum absolute diff |8-2| = 6 > 4. 
[8,2,4] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
[2] with maximum absolute diff |2-2| = 0 <= 4.
[2,4] with maximum absolute diff |2-4| = 2 <= 4.
[2,4,7] with maximum absolute diff |2-7| = 5 > 4.
[4] with maximum absolute diff |4-4| = 0 <= 4.
[4,7] with maximum absolute diff |4-7| = 3 <= 4.
[7] with maximum absolute diff |7-7| = 0 <= 4. 
Therefore, the size of the longest subarray is 2.

Example 2:

Input: nums = [10,1,2,4,7,2], limit = 5
Output: 4 
Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.

Example 3:

Input: nums = [4,2,2,2,4,4,2,2], limit = 0
Output: 3

 

Constraints:

    1 <= nums.length <= 105
    1 <= nums[i] <= 109
    0 <= limit <= 109

# 🔍 **LeetCode 1438: Longest Continuous Subarray With Absolute Diff ≤ Limit**  
## **Detailed Notes with Dry Run & Why Naive Approaches Fail**

---

## 📌 **1. Problem Restatement**

Given:
- An integer array `nums`
- An integer `limit`

**Goal**: Find the **length of the longest contiguous (non-empty) subarray** such that:  
> The absolute difference between **any two elements** in the subarray is **≤ `limit`**.

Return the **maximum possible length**.

---

## 🔑 **2. Core Insight: Reduce "Any Two Elements" to Max–Min**

### ❓ Why is this valid?

In any set of numbers:
- The **largest possible absolute difference** between any two elements is:
  ```
  max(subarray) - min(subarray)
  ```

✅ So instead of checking all pairs (O(n²)), we only need:
```java
max(window) - min(window) <= limit
```

> This is the **key transformation** that makes an efficient solution possible.

---

## ❌ **3. Why Naive / Adjacent-Only Approaches Fail**

### Common Mistake:
> “Just check if `|nums[i] - nums[i-1]| <= limit` and extend the window.”

### Example That Breaks It:
```java
nums = [2, 1, 5], limit = 3
```

- Adjacent diffs:
  - |2–1| = 1 ≤ 3 ✅
  - |1–5| = 4 > 3 ❌

So you might think `[2,1]` is valid (length=2), and stop.

**But what about the full window `[2,1,5]`?**
- Min = 1, Max = 5 → 5 – 1 = **4 > 3** → **invalid** ✅ (correctly rejected)

Now try:
```java
nums = [1, 5, 3], limit = 3
```

- Adjacent diffs:
  - |1–5| = 4 > 3 ❌ → you might reset window at index 1
  - Then consider [5,3]: |5–3| = 2 ≤ 3 ✅ → length=2

**But is there a longer valid window?**  
- [1,5,3]: min=1, max=5 → diff=4 > 3 → invalid  
- So answer = 2 ✅

✅ So far, adjacent check *seems* okay.

### ✅ **Now the Correct Counterexample**:
```java
nums = [3, 1, 4, 2], limit = 2
```

Consider subarray `[1, 4, 2]`:
- Adjacent diffs:
  - |1–4| = 3 > 2 ❌ → naive method discards it
  - |4–2| = 2 ≤ 2 ✅

**But global check**:
- Min = 1, Max = 4 → 4 – 1 = **3 > 2** → invalid ❌

Still not a counterexample.

### 🎯 **True Counterexample Where Naive Fails**:

Let’s construct one where **adjacent diffs are all ≤ limit**, but **global max–min > limit**.

```java
nums = [1, 3, 2, 5], limit = 3
```

Check subarray `[1, 3, 2, 5]`:
- Adjacent diffs:
  - |1–3| = 2 ≤ 3 ✅
  - |3–2| = 1 ≤ 3 ✅
  - |2–5| = 3 ≤ 3 ✅
- So a **naive adjacent-only method would accept this window as valid!**

**But global values**:
- Min = 1, Max = 5 → 5 – 1 = **4 > 3** → **INVALID!**

✅ **This is why adjacent-only checking FAILS**.

> The window passes all local checks but violates the global condition.

---

## ✅ **4. Correct Approach: Sliding Window + Monotonic Deques**

### 🧠 Idea:
- Use a **sliding window** `[left, right]`
- Maintain **current max and min** in the window **efficiently**
- If `max - min > limit`, shrink from left until valid

### 🛠️ Data Structures:
- **`maxDeque`**: stores indices in **decreasing order of values**  
  → `nums[maxDeque.peekFirst()]` = current **max**
- **`minDeque`**: stores indices in **increasing order of values**  
  → `nums[minDeque.peekFirst()]` = current **min**

Each element enters and leaves each deque **at most once** → **O(n)** total.

---

## 🔁 **5. Step-by-Step Dry Run**

### Input:
```java
nums = [8, 2, 4, 7], limit = 4
```

We expect output = **2**

### Initialize:
- `left = 0`
- `maxLen = 0`
- `maxDeque = []`, `minDeque = []`

---

### **Step 1: right = 0 (element = 8)**

- **Update deques**:
  - `maxDeque`: empty → add 0 → `[0]`
  - `minDeque`: empty → add 0 → `[0]`
- **Current window**: [8]
- **max = 8, min = 8 → diff = 0 ≤ 4** → valid
- `maxLen = max(0, 0–0+1) = 1`

---

### **Step 2: right = 1 (element = 2)**

- **Update deques**:
  - `maxDeque`: nums[1]=2 ≤ nums[0]=8 → add 1 → `[0,1]`
  - `minDeque`: nums[1]=2 < nums[0]=8 → remove 0, then add 1 → `[1]`
- **Window**: [8,2]
- **max = nums[0] = 8**, **min = nums[1] = 2** → diff = **6 > 4** → ❌ invalid

→ **Shrink window**:
- Since `maxDeque.peekFirst() = 0 == left`, remove it → `maxDeque = [1]`
- `minDeque.peekFirst() = 1 != left (0)`, so leave it
- `left++` → `left = 1`

- Now window = [2] (indices 1–1)
- max = 2, min = 2 → diff = 0 ≤ 4 → valid
- `maxLen = max(1, 1–1+1) = 1`

---

### **Step 3: right = 2 (element = 4)**

- **Update deques**:
  - `maxDeque`: nums[2]=4 > nums[1]=2 → remove 1, add 2 → `[2]`
  - `minDeque`: nums[2]=4 > nums[1]=2 → add 2 → `[1,2]`
- **Window**: [2,4] (indices 1–2)
- max = 4, min = 2 → diff = **2 ≤ 4** → ✅ valid
- `maxLen = max(1, 2–1+1) = 2`

---

### **Step 4: right = 3 (element = 7)**

- **Update deques**:
  - `maxDeque`: nums[3]=7 > nums[2]=4 → remove 2, add 3 → `[3]`
  - `minDeque`: nums[3]=7 > nums[2]=4 → add 3 → `[1,2,3]`
- **Window**: [2,4,7] (indices 1–3)
- max = 7, min = 2 → diff = **5 > 4** → ❌ invalid

→ **Shrink window**:
- `maxDeque.peekFirst() = 3 != left(1)` → keep
- `minDeque.peekFirst() = 1 == left` → remove it → `minDeque = [2,3]`
- `left++` → `left = 2`

- Now window = [4,7]
- max = 7, min = 4 → diff = **3 ≤ 4** → ✅ valid
- `maxLen = max(2, 3–2+1) = 2`

✅ Final answer = **2**

---

## 📊 **6. Algorithm Summary**

```java
for (right = 0 to n-1):
    // Add nums[right] to deques (maintain monotonic order)
    while (!maxDeque.isEmpty() && nums[last] <= nums[right]) pop last
    maxDeque.add(right)
    
    while (!minDeque.isEmpty() && nums[last] >= nums[right]) pop last
    minDeque.add(right)

    // Shrink window while invalid
    while (nums[maxDeque.first] - nums[minDeque.first] > limit):
        if (maxDeque.first == left) maxDeque.popFirst()
        if (minDeque.first == left) minDeque.popFirst()
        left++

    maxLen = max(maxLen, right - left + 1)
```

---

## ⏱️ **7. Complexity Analysis**

| Metric | Value |
|-------|--------|
| **Time** | O(n) — each index added/removed once from each deque |
| **Space** | O(n) — deques can store up to n indices |

---

## 🧪 **8. Edge Cases to Remember**

| Case | Behavior |
|------|--------|
| `limit = 0` | All elements in window must be **equal** |
| Single element | Always valid (diff = 0) → answer ≥ 1 |
| Strictly increasing array | max–min = last – first |
| All same elements | Entire array is valid if `limit ≥ 0` |

---

## ✅ **9. Why This Works**

- **Correctness**: We always know the true max and min in the current window.
- **Efficiency**: Deques allow O(1) max/min access and O(1) amortized updates.
- **Completeness**: We consider every possible valid window via sliding.

---

## 🚫 **10. Why Simpler Methods Fail**

| Method | Why It Fails |
|-------|-------------|
| Check only adjacent pairs | Misses global max/min (e.g., [1,3,2,5] with limit=3) |
| Recompute max/min in window each time | O(n²) — too slow for n=1e5 |
| Use TreeSet for window | O(n log n) — acceptable but slower than O(n) deque |

---

## 💡 **Final Takeaway**

> 🔸 **"Any two elements" → always means `max - min`**  
> 🔸 **Use monotonic deques to track max/min in sliding window**  
> 🔸 **Never rely on local (adjacent) differences for global constraints**

This pattern appears in many sliding window problems (e.g., max in sliding window, shortest subarray with sum ≥ K). Master it! 🚀

```java
package SlidingWindow.Arrays.Dynamic.Medium.LongestContinuousSubarrayAbsLessEqualLimit;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestContinuousSubarrayAbsLessEqualLimit {

    /**
     * Finds the length of the longest contiguous subarray such that the absolute difference
     * between any two elements is <= limit.
     *
     * Key Insight:
     * In any subarray, max absolute difference = max - min.
     * So we maintain a sliding window where: max(window) - min(window) <= limit.
     *
     * Approach:
     * - Use two deques:
     *     * maxDeque: stores indices in decreasing order of values (front = max)
     *     * minDeque: stores indices in increasing order of values (front = min)
     * - Expand window with 'right'
     * - If window becomes invalid (max - min > limit), shrink from 'left'
     * - Update answer with valid window size
     *
     * Time Complexity: O(n) — each element added/removed once
     * Space Complexity: O(n) — for deques
     *
     * @param nums input array of integers
     * @param limit maximum allowed absolute difference
     * @return length of longest valid subarray
     */
    public static int longestSubarray(int[] nums, int limit) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Deque<Integer> maxDeque = new ArrayDeque<>(); // decreasing: front = index of max
        Deque<Integer> minDeque = new ArrayDeque<>(); // increasing: front = index of min

        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < nums.length; right++) {
            // Maintain maxDeque in decreasing order (largest at front)
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[right]) {
                maxDeque.pollLast();
            }
            maxDeque.offerLast(right);

            // Maintain minDeque in increasing order (smallest at front)
            while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[right]) {
                minDeque.pollLast();
            }
            minDeque.offerLast(right);

            // Shrink window from the left while it's invalid
            // Condition: max - min > limit
            while (nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > limit) {
                // If the leftmost index is the current max, remove it from maxDeque
                if (maxDeque.peekFirst() == left) {
                    maxDeque.pollFirst();
                }
                // If the leftmost index is the current min, remove it from minDeque
                if (minDeque.peekFirst() == left) {
                    minDeque.pollFirst();
                }
                // Move left pointer to shrink window
                left++;
            }

            // Now the window [left, right] is valid
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    // ==================== Main Method with Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Longest Subarray with Absolute Diff <= Limit ===\n");

        // Example 1: Expected output = 2
        int[] test1 = {8, 2, 4, 7};
        int limit1 = 4;
        System.out.println("Test 1: nums = " + java.util.Arrays.toString(test1) + ", limit = " + limit1);
        System.out.println("Output: " + longestSubarray(test1, limit1)); // 2

        // Example 2: Expected output = 4
        int[] test2 = {10, 1, 2, 4, 7, 2};
        int limit2 = 5;
        System.out.println("\nTest 2: nums = " + java.util.Arrays.toString(test2) + ", limit = " + limit2);
        System.out.println("Output: " + longestSubarray(test2, limit2)); // 4

        // Example 3: Expected output = 3
        int[] test3 = {4, 2, 2, 2, 4, 4, 2, 2};
        int limit3 = 0;
        System.out.println("\nTest 3: nums = " + java.util.Arrays.toString(test3) + ", limit = " + limit3);
        System.out.println("Output: " + longestSubarray(test3, limit3)); // 3

        // Edge: single element
        int[] test4 = {5};
        int limit4 = 0;
        System.out.println("\nTest 4: nums = " + java.util.Arrays.toString(test4) + ", limit = " + limit4);
        System.out.println("Output: " + longestSubarray(test4, limit4)); // 1

        // Edge: all same elements
        int[] test5 = {3, 3, 3, 3};
        int limit5 = 0;
        System.out.println("\nTest 5: nums = " + java.util.Arrays.toString(test5) + ", limit = " + limit5);
        System.out.println("Output: " + longestSubarray(test5, limit5)); // 4

        // Edge: strictly increasing, large limit
        int[] test6 = {1, 2, 3, 4, 5};
        int limit6 = 10;
        System.out.println("\nTest 6: nums = " + java.util.Arrays.toString(test6) + ", limit = " + limit6);
        System.out.println("Output: " + longestSubarray(test6, limit6)); // 5
    }
}
```

## 🔍 **Explanation of the Shrinking Logic**
  
```java
// Shrink window from left while invalid
while (nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > limit) {
    if (maxDeque.peekFirst() == left) {
        maxDeque.pollFirst();
    }
    if (minDeque.peekFirst() == left) {
        minDeque.pollFirst();
    }
    left++;
}
```

### ✅ **What Does This Do?**

This loop **ensures the current window `[left, right]` is valid**, i.e.,  
> `max(window) - min(window) <= limit`

If it's **not valid**, we **shrink the window from the left** until it becomes valid.

### 🔧 **Step-by-Step Breakdown**

1. **Check Validity**:
   - `maxDeque.peekFirst()` → index of **maximum** in current window
   - `minDeque.peekFirst()` → index of **minimum** in current window
   - If `nums[maxIdx] - nums[minIdx] > limit` → window is **invalid**

2. **Remove Leftmost Element**:
   - The element at `left` is **exiting the window**
   - If this element was the **current max**, it’s at the **front of `maxDeque`** → remove it
   - If it was the **current min**, it’s at the **front of `minDeque`** → remove it

3. **Move Left Pointer**:
   - `left++` shrinks the window by excluding `nums[left]`

4. **Repeat Until Valid**:
   - Use `while`, not `if`, because **one shrink might not be enough**

### 🎯 **Why Compare `peekFirst() == left`?**

- The deques store **indices in order of appearance**
- The **front** of each deque is the **index of the current max/min** **within the window**
- When we move `left` forward, **if the max/min was at `left`**, it’s no longer in the window → must remove it from deque

> 💡 We **don’t remove all outdated indices upfront** — only when `left` passes them. This is efficient.

### 🚫 **Why Not Remove All Outdated Indices at Start?**

Because:
- It’s unnecessary work
- The **front** is the only place outdated indices can appear (due to how we maintain deques)
- We only need to clean **when shrinking**

## 🧠 **Why This Logic Works**

| Component | Purpose |
|--------|--------|
| **`maxDeque`** | Always gives index of **current max** in O(1) |
| **`minDeque`** | Always gives index of **current min** in O(1) |
| **`while (max - min > limit)`** | Ensures window is **fully valid** before updating answer |
| **`if (deque.peekFirst() == left)`** | Removes **only the element leaving the window**, when it was the extremum |
