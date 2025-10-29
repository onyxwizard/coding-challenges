## 🌀 Floyd’s Cycle Detection Algorithm (a.k.a. "Tortoise and Hare")

### 🔍 What Problem Does It Solve?

> **Given a sequence (like a linked list or a function iteration), detect if it contains a cycle — and do it using O(1) extra space.**

Common contexts:
- Detecting a loop in a **singly linked list**
- Finding cycles in **iterative function sequences** (e.g., in Pollard's rho algorithm)
- Solving problems like **"Find the duplicate number"** (LeetCode #287)


## 🧠 Core Idea: Two Pointers Moving at Different Speeds

Imagine a race track with a loop:
- A **slow runner** (tortoise) moves **1 step** at a time.
- A **fast runner** (hare) moves **2 steps** at a time.

> 💡 **If there’s a cycle, the fast runner will eventually "lap" the slow runner — they’ll meet inside the cycle.**  
> If there’s **no cycle**, the fast runner will reach the end (e.g., `null` in a linked list).

This is **Floyd’s insight**: you don’t need to store visited nodes — just use two pointers!



## 📐 How It Works (Step by Step)

### Phase 1: Detect if a cycle exists
- Start both pointers at the beginning.
- Move:
  - `slow = slow.next`
  - `fast = fast.next.next`
- If `fast == slow` → **cycle detected!**
- If `fast` or `fast.next` becomes `null` → **no cycle**

### Phase 2 (Optional): Find the **start** of the cycle
Once they meet, reset one pointer to the start, and move **both at same speed (1 step)**.  
They will meet again — **at the entrance of the cycle**.

> ✅ We’ll prove why this works shortly.



## 🧮 Why Does It Work? (Intuition + Math)

Assume:
- Distance from start to cycle entrance = **L**
- Cycle length = **C**
- When `slow` enters the cycle, `fast` is already inside, possibly multiple laps ahead.

Let’s say they meet after `slow` has taken **k** steps inside the cycle.

Then:
- `slow` has traveled: `L + k`
- `fast` has traveled: `L + k + nC` (for some integer `n ≥ 1`, because it’s lapped the cycle `n` times)

But since `fast` moves twice as fast:
```
2 × (L + k) = L + k + nC
→ 2L + 2k = L + k + nC
→ L + k = nC
→ L = nC - k
```

Now, notice:
- `nC - k` is exactly the distance from the **meeting point** back to the **cycle entrance** (going forward in the cycle).

So if we:
- Put one pointer at **head** (distance `L` from entrance)
- Put another at **meeting point** (distance `nC - k = L` from entrance)

And move both **1 step at a time**, they meet **at the cycle entrance**!

✅ **Brilliant and elegant!**



## 💻 Code Examples

### 1. Detect Cycle in Linked List (LeetCode #141)

```java
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public boolean hasCycle(ListNode head) {
    if (head == null) return false;
    
    ListNode slow = head;
    ListNode fast = head;
    
    while (fast != null && fast.next != null) {
        slow = slow.next;           // 1 step
        fast = fast.next.next;      // 2 steps
        
        if (slow == fast) {
            return true;            // Cycle detected!
        }
    }
    return false; // Reached end → no cycle
}
```



### 2. Find Start of Cycle (LeetCode #142)

```java
public ListNode detectCycle(ListNode head) {
    if (head == null) return null;
    
    ListNode slow = head, fast = head;
    
    // Phase 1: Detect meeting point
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) break;
    }
    
    // If no cycle
    if (fast == null || fast.next == null) return null;
    
    // Phase 2: Find cycle entrance
    slow = head;
    while (slow != fast) {
        slow = slow.next;
        fast = fast.next;
    }
    return slow; // or fast — cycle start
}
```



### 3. Application: Find Duplicate Number (LeetCode #287)

> Given array `nums` of size `n+1` with values in `[1, n]`, find the duplicate **without modifying array** and **O(1) space**.

**Idea**: Treat array as a linked list where `i → nums[i]`. Duplicate ⇒ cycle!

```java
public int findDuplicate(int[] nums) {
    int slow = nums[0];
    int fast = nums[0];
    
    // Phase 1: Find meeting point inside cycle
    do {
        slow = nums[slow];
        fast = nums[nums[fast]];
    } while (slow != fast);
    
    // Phase 2: Find entrance (which is the duplicate)
    slow = nums[0];
    while (slow != fast) {
        slow = nums[slow];
        fast = nums[fast];
    }
    return slow;
}
```



## ✅ Summary

| Feature | Floyd’s Algorithm |
|--------|-------------------|
| **Space** | O(1) — only two pointers |
| **Time** | O(n) — linear |
| **Use Cases** | Linked list cycles, function iteration cycles, duplicate detection |
| **Key Insight** | Fast pointer laps slow pointer **iff** there’s a cycle |



## 🧠 When to Use It?

Ask yourself:
- Do I have a **sequence** where each element points to the next?
- Is there a possibility of a **loop**?
- Am I constrained to **constant extra space**?

→ If yes, think **Floyd’s Tortoise and Hare**!

