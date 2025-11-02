You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.

 

Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation: 
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

Example 2:

Input: nums = [1], k = 1
Output: [1]

 

Constraints:

    1 <= nums.length <= 105
    -104 <= nums[i] <= 104
    1 <= k <= nums.length

# How to Solve the **Sliding Window Maximum** Problem

The **Sliding Window Maximum** problem asks:

> Given an array of integers and a window size `k`, find the maximum value in every contiguous subarray (window) of size `k` as it slides from left to right.

At first glance, you might think:  
> “Just scan each window and take the max — that’s O(n·k). Done!”

But what if `n = 10⁶` and `k = 500,000`? That’s **500 billion operations** — too slow.

So we need a **smarter approach**. Let’s walk through the **intuition**, **key insights**, and **logical steps** to solve it efficiently.

---

## 🔍 Step 1: Understand What Changes Between Windows

Consider two consecutive windows:
- Window 1: `[a₀, a₁, ..., aₖ₋₁]`
- Window 2: `[a₁, a₂, ..., aₖ]`

Only **two things change**:
1. **One element leaves**: `a₀` is removed.
2. **One element enters**: `aₖ` is added.

So the maximum of Window 2 depends on:
- The previous maximum,
- Whether the old max just left,
- And whether the new element is bigger.

This suggests: **We don’t need to re-scan the whole window** — we can **update** the max intelligently.

---

## 💡 Step 2: Key Insight — Not All Elements Matter

Imagine the current window: `[5, 3, 4]`

- The max is `5`.
- Now the window slides: `[3, 4, 2]`
- Even though `5` is gone, the new max is `4`.

But notice: **`3` will never be the maximum** in any future window that also contains `4`, because `4 > 3` and appears **after** it.

> 🧠 **Crucial Observation**:  
> If a smaller number appears **before** a larger number in the window, the smaller one is **useless** — it can never become the maximum while the larger one is present.

This leads to the idea of maintaining a **monotonic decreasing sequence** of candidate maxima.

---

## 🧱 Step 3: Maintain a "Candidate Max" List

We keep a list of elements (or their positions) that **could** be the maximum in the current or future windows.

Rules for this list:
1. **Always in decreasing order** (largest first).
2. **Remove useless elements**: when a new number arrives, discard all smaller numbers before it — they’ll never be max.
3. **Remove outdated elements**: if the front of the list is outside the current window, drop it.

This structure is called a **monotonic deque** (double-ended queue).

---

## 🔄 Step 4: Simulate the Process Logically

As we move the window one step at a time:

1. **Before adding the new element**:
   - Remove indices from the **front** that are now outside the window.
   
2. **Before inserting the new element**:
   - Remove indices from the **back** whose values are **≤ current element** (they’re obsolete).
   
3. **Add the current index** to the back.

4. **If the window is full** (i.e., we’ve seen at least `k` elements):
   - The front of the deque holds the index of the **current maximum**.

Why does this work?
- The deque always stores **indices of potential maxima** in decreasing order of value.
- The front is always the **largest valid element** in the current window.
- Each element is added and removed **at most once** → total work is **O(n)**.

---

## 🎯 Step 5: Edge Cases to Consider

- **Empty array** → return empty result.
- **k = 1** → every element is its own max.
- **k = n** → only one window; return the global max.
- **Duplicates** → e.g., `[3, 1, 3]` with `k=3`: both `3`s matter, but only the **rightmost** one stays relevant longer.

Storing **indices** (not values) handles duplicates correctly, because we can check **position**, not just value.

---

## 🧩 Summary: The Logical Blueprint

To solve Sliding Window Maximum efficiently:

1. **Realize** that brute force is too slow.
2. **Observe** that only two elements change between windows.
3. **Notice** that smaller elements before larger ones are irrelevant.
4. **Maintain** a deque of candidate maxima in decreasing order.
5. **Update** the deque on every step:
   - Remove expired indices (out of window).
   - Remove obsolete indices (smaller than current).
   - Add current index.
6. **Record** the front of the deque as the max once the window is full.

This yields an **O(n) time, O(k) space** solution — optimal.

---

## 💬 Final Thought

The beauty of this problem isn’t in the code — it’s in the **insight**:  
> **Not all data matters equally. Discard the useless early, and you’ll see the answer clearly.**

This pattern — **monotonic queues/deques** — appears in many advanced sliding window problems (e.g., minimum in window, longest subarray with bounded max/min, etc.).


# Why Use `ArrayDeque` Instead of `LinkedList` for Queues and Stacks in Java?

When implementing algorithms that require a **stack** or **queue**—like BFS, DFS, or the famous **Sliding Window Maximum**—many Java developers instinctively reach for `LinkedList`. But there’s a better, faster, and more memory-efficient alternative: **`ArrayDeque`**.

In this post, we’ll explore:
- Why `ArrayDeque` outperforms `LinkedList`
- When (and when not) to use each
- What other data structures exist—and why they’re rarely used

---

## 🚫 The Common Mistake: Using `LinkedList` as a Queue/Stack

It’s tempting to write:

```java
Deque<Integer> dq = new LinkedList<>();
```

After all, `LinkedList` implements `Deque`, supports `addFirst()`, `pollLast()`, etc., and “feels” like a natural fit for dynamic ends.

But **this is almost always the wrong choice** for pure queue/stack use cases.

---

## ✅ Why `ArrayDeque` Is Superior

### 1. **Speed: 2–3x Faster in Practice**
- `ArrayDeque` uses a **circular resizable array** internally.
- Accessing elements is **cache-friendly** (contiguous memory).
- No object allocation per node (unlike `LinkedList`, which creates a `Node` object for every element).

> 🔬 **Benchmark Insight**: In typical workloads (e.g., sliding window, BFS), `ArrayDeque` is **20–50% faster** than `LinkedList`.

### 2. **Memory Efficiency**
- `LinkedList` stores **three references per element**: `item`, `next`, `prev`.
- `ArrayDeque` stores only the **value** in a plain array (plus a few integers for head/tail).

For 1 million integers:
- `LinkedList`: ~32 MB (due to object overhead)
- `ArrayDeque`: ~4 MB (just the `int[]`)

### 3. **No Garbage Collection Pressure**
Fewer objects → less GC churn → smoother performance in latency-sensitive apps.

### 4. **Same API, Better Performance**
`ArrayDeque` implements `Deque`, so you can **drop it in as a replacement**:

```java
// Before (slower)
Deque<Integer> dq = new LinkedList<>();

// After (faster, same code)
Deque<Integer> dq = new ArrayDeque<>();
```

---

## 📊 When to Use What?

| Use Case | Recommended |
|--------|-------------|
| Stack or Queue (LIFO/FIFO) | ✅ `ArrayDeque` |
| Need `List` operations (`get(i)`, `indexOf`, etc.) | ❌ Not `ArrayDeque` → use `ArrayList` or `LinkedList` |
| Frequent insertions/deletions in the **middle** | ✅ `LinkedList` (rare!) |
| Memory-constrained environment | ✅ `ArrayDeque` |
| Algorithm requiring only `push/pop` or `offer/poll` | ✅ `ArrayDeque` |

> 💡 **Rule of Thumb**:  
> **Use `ArrayDeque` for stacks and queues.**  
> Only use `LinkedList` if you **need its `List` behavior** (e.g., iterating and removing mid-list).

---

## 🚫 Why Not Other Options?

### 1. **`Stack` Class**
```java
Stack<Integer> stack = new Stack<>(); // ❌ Avoid!
```
- **Legacy class** (extends `Vector` → synchronized → slow).
- Not part of the modern Collections Framework.
- **Use `ArrayDeque` instead**:  
  ```java
  Deque<Integer> stack = new ArrayDeque<>();
  stack.push(x);   // same as addFirst
  stack.pop();     // same as removeFirst
  ```

### 2. **`PriorityQueue`**
- Not a FIFO/LIFO structure—it’s a **heap**.
- Use only when you need **min/max extraction**, not sliding windows or BFS.

### 3. **`ConcurrentLinkedQueue` / `LinkedBlockingDeque`**
- For **multithreaded** scenarios.
- Overkill (and slower) for single-threaded algorithms.

---

## 🔧 Real-World Example: Sliding Window Maximum

**Bad (common but slow):**
```java
Deque<Integer> dq = new LinkedList<>(); // ❌
```

**Good (fast and clean):**
```java
Deque<Integer> dq = new ArrayDeque<>(k); // ✅ Pre-sized for efficiency
```

This small change can reduce runtime by **hundreds of milliseconds** on large inputs.

---

## 📌 Final Advice

> **Never use `LinkedList` just because you need a queue or stack.**  
> **Always prefer `ArrayDeque`.**

It’s:
- Faster
- Lighter
- Part of modern Java best practices
- Recommended by [Oracle’s official docs](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayDeque.html):
  > *"This class is likely to be faster than Stack when used as a stack, and faster than LinkedList when used as a queue."*

---

## ✅ TL;DR

| Data Structure | Best For | Avoid When |
|----------------|--------|-----------|
| `ArrayDeque` | Stacks, queues, sliding windows, BFS/DFS | You need random access or mid-list edits |
| `LinkedList` | Rare cases with frequent mid-list mutations | Using as a simple queue/stack |
| `Stack` | Never | Always |
| `PriorityQueue` | Heaps, priority-based processing | FIFO/LIFO behavior needed |

Upgrade your code today—your CPU (and future self) will thank you! 💻✨

---

```java
package SlidingWindow.Arrays.Fixed.Hard.SlidingWindowMaximum;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {

    /**
     * Returns the maximum value in each sliding window of size {@code k} as it moves
     * from left to right across the input array {@code nums}.
     *
     * @param nums the input array of integers
     * @param k    the size of the sliding window (must be ≥ 1)
     * @return an array containing the maximum of each window
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        // Handle edge cases
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] result = new int[n - k + 1]; // Number of windows = n - k + 1
        Deque<Integer> deque = new LinkedList<>(); // Stores indices of elements in decreasing order of value
        int index = 0;

        for (int i = 0; i < n; i++) {
            // Remove indices that are out of the current window [i - k + 1, i]
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // Maintain monotonic decreasing order: remove indices from the end
            // whose corresponding values are less than or equal to nums[i]
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }

            // Add current index to the deque
            deque.offerLast(i);

            // Once the first window is complete (i >= k - 1), record the maximum
            if (i >= k - 1) {
                result[index++] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

    // Driver method with test cases
    public static void main(String[] args) {
        // Test Case 1: Example from problem statement
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(nums1, k1))); // Expected: [3, 3, 5, 5, 6, 7]

        // Test Case 2: Array with duplicates
        int[] nums2 = {3, 1, 2, 3};
        int k2 = 2;
        System.out.println(Arrays.toString(maxSlidingWindow(nums2, k2))); // Expected: [3, 2, 3]
    }
}
```
