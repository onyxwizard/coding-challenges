Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.

Example 1:

Input: nums = [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.

Example 2:

Input: nums = [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

Example 3:

Input: nums = [0,1,1,1,1,1,0,0,0]
Output: 6
Explanation: [1,1,1,0,0,0] is the longest contiguous subarray with equal number of 0 and 1.

Constraints:

    1 <= nums.length <= 105
    nums[i] is either 0 or 1.

# 📘 **525. Contiguous Array – Comprehensive Solution**

---

## 📋 **Problem Statement**

Given a **binary array** `nums` (containing only 0s and 1s), find the **maximum length of a contiguous subarray** that contains an **equal number of 0s and 1s**.

### 🎯 **Key Requirements**:
- **Contiguous subarray only** (consecutive elements)
- **Equal count** of 0s and 1s (not necessarily alternating)
- Return the **maximum length** possible
- If no such subarray exists, return 0

---

## 🧠 **Core Insight: Transform to Prefix Sum Problem**

### 🔑 **The Brilliant Transformation**:

The key insight is to **convert this counting problem into a sum problem**:

- Treat **0 as -1** and **1 as +1**
- Now, a subarray with equal 0s and 1s will have a **sum of 0**

### 💡 **Why This Works**:
- If a subarray has `x` zeros and `x` ones:
  - Sum = `x × (-1) + x × (+1) = 0`
- So we need to find the **longest subarray with sum = 0**

### 📊 **Example Transformation**:
```java
nums = [0, 1, 0] 
transformed = [-1, 1, -1]
prefix = [0, -1, 0, -1]

// Subarray [0,1] → indices 0-1: prefix[2] - prefix[0] = 0 - 0 = 0 ✅
// Subarray [1,0] → indices 1-2: prefix[3] - prefix[1] = -1 - (-1) = 0 ✅
```

### 🔍 **Key Observation**:
> If `prefix[i] == prefix[j]`, then the subarray from `i` to `j-1` has sum 0.

So we need to find the **maximum distance between two indices with the same prefix sum**.

---

## ✅ **Optimal Solution: Prefix Sum + HashMap**

### 🧩 **Algorithm Steps**:
1. Initialize HashMap with `{0: -1}` (prefix sum 0 at index -1 for edge cases)
2. Track running prefix sum (treating 0 as -1, 1 as +1)
3. For each position:
   - If current prefix sum has been seen before, calculate distance
   - If not seen, store the first occurrence (to maximize length later)
4. Return maximum length found

---

## 🚀 **Complete Implementation with Test Cases**

```java
import java.util.HashMap;
import java.util.Map;

public class ContiguousArray {

    /**
     * Finds the maximum length of a contiguous subarray with equal number of 0s and 1s.
     * 
     * Approach: Transform 0→-1, 1→+1, then find longest subarray with sum=0.
     * Use prefix sum + HashMap to track first occurrence of each prefix sum.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * @param nums binary array containing only 0s and 1s
     * @return maximum length of contiguous subarray with equal 0s and 1s
     */
    public static int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Map<Integer, Integer> prefixIndex = new HashMap<>();
        prefixIndex.put(0, -1); // prefix sum 0 at index -1 (before array starts)
        
        int prefixSum = 0;
        int maxLength = 0;
        
        for (int i = 0; i < nums.length; i++) {
            // Transform: 0 → -1, 1 → +1
            prefixSum += (nums[i] == 0) ? -1 : 1;
            
            if (prefixIndex.containsKey(prefixSum)) {
                // Same prefix sum seen before → subarray between has sum 0
                int length = i - prefixIndex.get(prefixSum);
                maxLength = Math.max(maxLength, length);
            } else {
                // Store first occurrence of this prefix sum
                prefixIndex.put(prefixSum, i);
            }
        }
        
        return maxLength;
    }

    // ==================== Comprehensive Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Contiguous Array - Maximum Length with Equal 0s and 1s ===\n");

        // Example 1: Basic case
        int[] test1 = {0, 1};
        System.out.println("Test 1: nums = " + java.util.Arrays.toString(test1));
        System.out.println("Output: " + findMaxLength(test1)); // Expected: 2

        // Example 2: Multiple valid subarrays
        int[] test2 = {0, 1, 0};
        System.out.println("\nTest 2: nums = " + java.util.Arrays.toString(test2));
        System.out.println("Output: " + findMaxLength(test2)); // Expected: 2

        // Example 3: Longer valid subarray
        int[] test3 = {0, 1, 1, 1, 1, 1, 0, 0, 0};
        System.out.println("\nTest 3: nums = " + java.util.Arrays.toString(test3));
        System.out.println("Output: " + findMaxLength(test3)); // Expected: 6
        // Explanation: [1,1,1,0,0,0] → indices 2-7

        // Edge Case 1: All 1s
        int[] test4 = {1, 1, 1, 1};
        System.out.println("\nTest 4: nums = " + java.util.Arrays.toString(test4) + " (all 1s)");
        System.out.println("Output: " + findMaxLength(test4)); // Expected: 0

        // Edge Case 2: All 0s
        int[] test5 = {0, 0, 0, 0};
        System.out.println("\nTest 5: nums = " + java.util.Arrays.toString(test5) + " (all 0s)");
        System.out.println("Output: " + findMaxLength(test5)); // Expected: 0

        // Edge Case 3: Single element
        int[] test6 = {1};
        System.out.println("\nTest 6: nums = " + java.util.Arrays.toString(test6) + " (single element)");
        System.out.println("Output: " + findMaxLength(test6)); // Expected: 0

        // Edge Case 4: Alternating pattern
        int[] test7 = {0, 1, 0, 1, 0, 1};
        System.out.println("\nTest 7: nums = " + java.util.Arrays.toString(test7) + " (alternating)");
        System.out.println("Output: " + findMaxLength(test7)); // Expected: 6 (entire array)

        // Edge Case 5: Empty array (though constraints say n>=1)
        int[] test8 = {};
        System.out.println("\nTest 8: nums = [] (empty array)");
        System.out.println("Output: " + findMaxLength(test8)); // Expected: 0

        // Edge Case 6: Long valid subarray in middle
        int[] test9 = {1, 1, 0, 0, 1, 1, 0, 0, 1, 1};
        System.out.println("\nTest 9: nums = " + java.util.Arrays.toString(test9));
        System.out.println("Output: " + findMaxLength(test9)); // Expected: 8 (indices 1-8)

        // Edge Case 7: Valid subarray at beginning
        int[] test10 = {0, 1, 1, 1, 1};
        System.out.println("\nTest 10: nums = " + java.util.Arrays.toString(test10));
        System.out.println("Output: " + findMaxLength(test10)); // Expected: 2 ([0,1])
    }
}
```

---

## 🔍 **How It Works: Step-by-Step Example**

### **Example**: `nums = [0, 1, 0]`

| i | nums[i] | prefixSum | HashMap State | Action | maxLength |
|---|---------|-----------|---------------|--------|-----------|
| - | - | 0 | `{0: -1}` | Initialize | 0 |
| 0 | 0 | -1 | `{0: -1, -1: 0}` | Store first occurrence | 0 |
| 1 | 1 | 0 | `{0: -1, -1: 0}` | Seen 0 before! Length = 1 - (-1) = 2 | 2 |
| 2 | 0 | -1 | `{0: -1, -1: 0}` | Seen -1 before! Length = 2 - 0 = 2 | 2 |

✅ Final result = **2**

---

## 📊 **Complexity Analysis**

| Aspect | Value |
|--------|-------|
| **Time Complexity** | O(n) – single pass through array |
| **Space Complexity** | O(n) – HashMap may store up to n+1 entries |
| **Optimality** | **Optimal** – must examine each element at least once |

---

## ⚠️ **Why Other Approaches Fail**

### ❌ **Brute Force O(n²)**:
- Check all subarrays → too slow for n=10⁵ (10¹⁰ operations)

### ❌ **Sliding Window**:
- Doesn't work because adding elements doesn't guarantee monotonic behavior
- Can't shrink window predictably with binary arrays

### ❌ **Two Pointers**:
- No clear condition to move pointers since sum can fluctuate

---

## 💡 **Key Takeaways**

1. **Transform the problem**: Convert counting problem to sum problem
2. **Use prefix sums**: Track cumulative sum with 0→-1, 1→+1
3. **HashMap for first occurrence**: Store the earliest index for each prefix sum
4. **Initialize with {0: -1}**: Handles subarrays starting from index 0
5. **This pattern appears everywhere**: Similar to "Subarray Sum Equals K"

---
