Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:

Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]

Example 2:

Input: head = [1], n = 1
Output: []

Example 3:

Input: head = [1,2], n = 1
Output: [1]

Constraints:

    The number of nodes in the list is sz.
    1 <= sz <= 30
    0 <= Node.val <= 100
    1 <= n <= sz 

Follow up: Could you do this in one pass?

## 🔁 Approach 1: The Reverse Trick (Creative but Costly)

### 🔄 Logic:
1. **Reverse** the entire list.
2. Now, the **nth from end** becomes the **nth from start**.
3. Remove the nth node from the front.
4. **Reverse again** to restore order.

```java
ListNode reversed = reverse(head);
ListNode newReversed = removeNthFromStart(reversed, n);
return reverse(newReversed);
```

### ✅ Pros:
- Intuitive if you’re comfortable with reversal.
- Uses **O(1) space** (no extra data structures).

### ❌ Cons:
- **3 full passes**: reverse → remove → reverse.
- **Not one-pass** — violates the follow-up.
- **Fragile**: Easy to lose head reference or mishandle edge cases.

### 🕵️ Hidden Detail:
> Reversing twice **mutates the original list structure**. In real systems where nodes might be shared or observed externally, this could cause **side effects**.

### 📊 Time/Space:
- **Time**: O(n) — but with **3× constant factor**
- **Space**: O(1)

> 🎯 **When to use?** Only if you’re stuck and need a working solution fast. Not ideal for interviews.


```java
public class RemoveNEndOfList{
	// ======================================================================================  
// APPROACH 2: TWO-PASS (COUNT THEN REMOVE)  
// ======================================================================================  
  
/**  
 * Removes the nth node from the end using a classic two-pass method: * 1. First pass: count total number of nodes. * 2. Second pass: traverse to the (length - n)th node and remove the next node. * * ❌ Why is this "two passes"?  
 *   - The algorithm **fully traverses the list once to count**. *   - Then **starts again from the head** for the second traversal. *   - This reset to head makes it a true two-pass solution. * * Time Complexity: O(n) — two full traversals (still linear, but 2n steps). * Space Complexity: O(1) — constant extra space. * * While correct, this is **less efficient in practice** and doesn't satisfy the "one-pass" follow-up. * * @param head The head of the linked list * @param n The 1-indexed position from the end to remove * @return The head of the modified list */public static ListNode removeNthFromEndApproach2(ListNode head, int n) {  
    // First pass: count nodes  
    int length = 0;  
    ListNode curr = head;  
    while (curr != null) {  
        length++;  
        curr = curr.next;  
    }  
  
    // Special case: remove head  
    if (n == length) {  
        return head.next;  
    }  
  
    // Second pass: go to (length - n - 1)th node  
    curr = head;  
    for (int i = 0; i < length - n - 1; i++) {  
        curr = curr.next;  
    }  
  
    // Remove next node  
    curr.next = curr.next.next;  
    return head;  
}
}
```
## 🧪 Approach 2: Fast-Slow + Dummy Node (The Interview Favorite)

### 🚀 Logic:
1. Create a **dummy node** pointing to head.
2. Use **two pointers**:  
   - Move `fast` **n+1 steps** ahead.  
   - Then move **both** until `fast` hits `null`.
3. Now `slow` is **just before** the target node → skip it.

```java
ListNode dummy = new ListNode(0);
dummy.next = head;
// ... move fast n+1 steps ...
while (fast != null) {
    slow = slow.next;
    fast = fast.next;
}
slow.next = slow.next.next;
return dummy.next;
```

### ✅ Why It’s Brilliant:
- **Handles head removal seamlessly** — no special case!
- **True one-pass**: continuous forward traversal.
- **Elegant and robust**.

### 🕵️ Hidden Detail:
> The **dummy node isn’t just convenience** — it **unifies all edge cases**:
> - Removing head (`n = length`)
> - Single-node list
> - Two-node list  
> Without it, you’d need `if (n == length) return head.next;` — which requires **knowing the length** (defeating one-pass!).

### 📊 Time/Space:
- **Time**: O(n) — **one logical pass**
- **Space**: O(1) — dummy is constant overhead

> 🏆 **This is the gold standard**. Used in production code and expected in top-tier interviews.

```java
 // ======================================================================================
    // APPROACH 1: FAST-SLOW POINTERS (ONE-PASS, OPTIMAL)
    // ======================================================================================

    /**
     * Removes the nth node from the end of a linked list in **one logical pass** using fast-slow pointers.
     *
     * Strategy:
     * - Use a dummy node to simplify edge cases (e.g., removing the head).
     * - Move `fast` pointer `n+1` steps ahead → creates a fixed gap of `n+1` between `slow` and `fast`.
     * - Then move both until `fast` reaches null → `slow` lands just before the target node.
     * - Remove the target by skipping it: `slow.next = slow.next.next`.
     *
     * ✅ Why is this "one pass"?
     *   - The list is traversed **once continuously** from start to end.
     *   - No pointer ever resets to the head or restarts traversal.
     *   - Both pointers move forward only — total steps ≈ 2n, but in a single sweep.
     *
     * Time Complexity: O(n) — each node visited at most twice (still linear).
     * Space Complexity: O(1) — only a few extra pointers used.
     *
     * @param head The head of the linked list
     * @param n The 1-indexed position from the end to remove
     * @return The head of the modified list
     */
     public static ListNode removeNthFromEndApproach1(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = dummy;

        // Advance fast by n+1 steps to create gap
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move both until fast reaches end
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Remove target node
        slow.next = slow.next.next;

        return dummy.next;
    }
```

## ⚡ Approach 3: Fast-Slow *Without* Dummy (Minimalist Hacker Style)

### 🔍 Logic:
1. Move `fast` **n steps** (not n+1).
2. **If `fast == null`**, you’re removing the **head** → return `head.next`.
3. Otherwise, move both until `fast.next == null`.
4. `slow` is now before target → remove it.

```java
for (int i = 0; i < n; i++) fast = fast.next;
if (fast == null) return head.next; // 🚨 Critical check!
while (fast.next != null) {
    slow = slow.next;
    fast = fast.next;
}
slow.next = slow.next.next;
```

### ✅ Pros:
- **No extra node** — purist’s dream.
- Still **O(1) space** and **one-pass**.

### ❌ Cons:
- **Easy to forget the `fast == null` check** → crashes on head removal.
- Slightly **less readable** under pressure.

### 🕵️ Hidden Detail:
> The condition `fast == null` after `n` steps **implicitly tells you the list length = n**.  
> This is how you **detect head removal without counting** — a subtle but powerful insight!

### 📊 Time/Space:
- **Time**: O(n)
- **Space**: O(1)

> 🛠️ **Use this** if you want to show deep understanding — but **only if you remember the edge case**.

```java
public static ListNode removeNthFromEndWithoutDummy(ListNode head, int n) {
    ListNode slow = head;
    ListNode fast = head;

    // Move fast n steps ahead
    for (int i = 0; i < n; i++) {
        fast = fast.next;
    }

    // If fast is null, we're removing the head
    if (fast == null) {
        return head.next;
    }

    // Move both until fast reaches last node (not null)
    while (fast.next != null) {
        slow = slow.next;
        fast = fast.next;
    }

    // Now slow is just before the node to remove
    slow.next = slow.next.next;
    return head;
}
```
