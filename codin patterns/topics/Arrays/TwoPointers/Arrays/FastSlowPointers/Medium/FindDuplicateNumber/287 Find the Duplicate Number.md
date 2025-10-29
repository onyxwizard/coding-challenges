Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.

There is only one repeated number in nums, return this repeated number.

You must solve the problem without modifying the array nums and using only constant extra space.

Example 1:

Input: nums = [1,3,4,2,2]
Output: 2

Example 2:

Input: nums = [3,1,3,4,2]
Output: 3

Example 3:

Input: nums = [3,3,3,3,3]
Output: 3


Constraints:

    1 <= n <= 105
    nums.length == n + 1
    1 <= nums[i] <= n
    All the integers in nums appear only once except for precisely one integer which appears two or more times.



Follow up:

    How can we prove that at least one duplicate number must exist in nums?
    Can you solve the problem in linear runtime complexity?

## 🔍 Follow-Up 1:
### **How can we prove that at least one duplicate number must exist in `nums`?**

### ✅ Answer: **Pigeonhole Principle**

> If you have **`n + 1`** items (pigeons) and only **`n`** possible distinct values (pigeonholes), **at least one value must appear more than once**.

- Here:
    - Possible distinct values: `1, 2, ..., n` → **`n` options**
    - Number of elements in array: `n + 1`
- So by the **pigeonhole principle**, **a duplicate is guaranteed**.

✅ This is a **mathematical proof** — no algorithm needed.


## 🔍 Follow-Up 2:
### **Can you solve the problem in linear runtime complexity?**

### ✅ Yes — using **Floyd’s Cycle Detection (Tortoise and Hare)**

This is the **optimal solution** that satisfies:
- **O(n)** time
- **O(1)** space
- **No modification** of input



### 🧠 Why Can We Model This as a Linked List with a Cycle?

Think of the array as a **function** `f(i) = nums[i]`.

Since:
- Indices: `0` to `n` (total `n+1` indices)
- Values: `1` to `n` → so **every value is a valid index** (except `0` is never a value, but we start from index `0`)

We can treat the array as a **directed graph**:
- Node `i` → points to node `nums[i]`

Example: `nums = [1,3,4,2,2]`
```
Index:  0 → 1 → 2 → 3 → 4
Value:  1   3   4   2   2

Traversal: 
0 → nums[0] = 1  
1 → nums[1] = 3  
3 → nums[3] = 2  
2 → nums[2] = 4  
4 → nums[4] = 2 ← cycle!
```

So:
- The sequence: `0 → 1 → 3 → 2 → 4 → 2 → 4 → ...`
- **Cycle starts at index 2**, but the **duplicate value is `2`**, which is the **entry point of the cycle**.

> 💡 **Key insight**: The **duplicate number causes at least two different indices to point to the same next node** → this creates a cycle.

Thus, **finding the start of the cycle = finding the duplicate number**.



### ✅ Floyd’s Algorithm Applied

#### Step 1: Detect cycle (Phase 1)
- `slow = nums[0]`
- `fast = nums[nums[0]]`
- Move until they meet

#### Step 2: Find cycle entrance (Phase 2)
- Reset `slow = nums[0]`
- Move both `slow` and `fast` one step at a time
- They meet at the **duplicate number**



### 📊 Complexity

| Metric | Value |
|-------|------|
| **Time** | `O(n)` — each pointer traverses at most `O(n)` steps |
| **Space** | `O(1)` — only two integer variables |
| **Modifies input?** | ❌ No |
| **Assumptions used?** | Values in `[1, n]`, length = `n+1` |


### 🆚 Why Not Other Approaches?

| Approach | Why Rejected |
|--------|-------------|
| **Sorting** | Modifies input (not allowed) |
| **Hash Set** | Uses `O(n)` extra space |
| **Binary Search on Count** | Works (`O(n log n)`), but not linear |
| **Sum formula** | Fails when duplicate appears >2 times (e.g., `[3,3,3,3,3]`) |

✅ Only **Floyd’s algorithm** meets **all constraints**.



## ✅ Final Answers to Follow-Ups

1. **Proof of duplicate existence**:  
   → By the **pigeonhole principle**: `n+1` elements in range `[1, n]` ⇒ at least one duplicate.

2. **Linear runtime solution?**  
   → **Yes**, using **Floyd’s Tortoise and Hare cycle detection**, modeling the array as a sequence with a cycle caused by the duplicate.


```java
package Arrays.FastSlowPointers.Medium.FindDuplicateNumber;

public class FindDuplicateNumber {
    public static int findDuplicate(int[] nums) {
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

    public static void main(String[] args) {
        int[] nums = {1,3,4,2,2};
        findDuplicate(nums);
    }
}

```