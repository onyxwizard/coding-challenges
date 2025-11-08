## Problem Description

You are given an integer array `nums` and an integer `k`. Your task is to find the maximum length of a contiguous subarray whose elements sum to exactly `k`. If no such subarray exists, return `0`.

A subarray is a contiguous sequence of elements within an array. For example, in the array `[1, 2, 3, 4]`, some subarrays include `[1, 2]`, `[2, 3, 4]`, and `[3]`, but `[1, 3]` is not a subarray because the elements are not contiguous.

The problem asks you to:

1. Find all possible contiguous subarrays of `nums`
2. Check which ones have a sum equal to `k`
3. Return the length of the longest such subarray
4. If no subarray sums to `k`, return `0`

For example:

- If `nums = [1, -1, 5, -2, 3]` and `k = 3`, the subarray `[1, -1, 5, -2]` sums to `3` and has length `4`, which would be the answer.
- If `nums = [2, 3, 4]` and `k = 10`, no subarray sums to `10`, so the answer would be `0`.

# 📘 **Maximum Length Subarray with Sum Equals K – Comprehensive Solution**
## 📋 **Problem Statement**

Given an integer array `nums` and an integer `k`, find the **maximum length** of a contiguous subarray whose elements sum to **exactly `k`**.

If no such subarray exists, return `0`.
### 🎯 **Key Requirements**:
- **Contiguous subarrays only** (consecutive elements)
- **Exact sum = k** (not ≥ or ≤)
- **Maximum length** among all valid subarrays
- Handle **negative numbers**, **zeros**, and **positive numbers**

## 🧠 **Core Insight: Prefix Sum + HashMap**

### 🔑 **The Fundamental Idea**:

For any subarray from index `i` to `j`, the sum is:
```
sum(i, j) = prefix[j] - prefix[i-1]
```

We want: `sum(i, j) = k`

Rearranging: `prefix[j] - prefix[i-1] = k` → `prefix[i-1] = prefix[j] - k`

### 💡 **Key Insight**:
> For each position `j`, if we've seen the prefix sum `(prefix[j] - k)` before at some earlier position `i-1`, then the subarray from `i` to `j` has sum exactly `k`. We want to **maximize** `(j - i + 1)`.

### 🎯 **Strategy**:
- Store the **first occurrence** of each prefix sum (to maximize length later)
- For each current prefix sum, check if `(prefixSum - k)` exists
- If yes, calculate length and update maximum

---

## ✅ **Optimal Solution: Prefix Sum + HashMap**

### 🧩 **Why This Works**:
- **Handles negative numbers** correctly (prefix sums can repeat)
- **Maximizes length** by storing first occurrence of each prefix sum
- **O(n) time** – single pass with HashMap
- **O(n) space** – for HashMap storing prefix sums

---
## 🚀 **Complete Implementation with Test Cases**

```java
import java.util.HashMap;
import java.util.Map;

public class MaxLengthSubarraySumK {

    /**
     * Finds the maximum length of a contiguous subarray with sum exactly equal to k.
     * 
     * Approach: Prefix Sum + HashMap
     * 
     * Key Insights:
     * 1. For subarray [i, j] to have sum k: prefix[j] - prefix[i-1] = k
     * 2. Store first occurrence of each prefix sum to maximize length
     * 3. Use HashMap to track earliest index for each prefix sum
     * 
     * Time Complexity: O(n) - single pass
     * Space Complexity: O(n) - HashMap may store up to n+1 entries
     * 
     * @param nums input array of integers
     * @param k target sum
     * @return maximum length of subarray with sum exactly k, or 0 if none exists
     */
    public static int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Map<Integer, Integer> prefixIndex = new HashMap<>();
        prefixIndex.put(0, -1); // prefix sum 0 at index -1 (before array starts)
        
        int prefixSum = 0;
        int maxLength = 0;
        
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            
            // Check if (prefixSum - k) has been seen before
            int target = prefixSum - k;
            if (prefixIndex.containsKey(target)) {
                int length = i - prefixIndex.get(target);
                maxLength = Math.max(maxLength, length);
            }
            
            // Store first occurrence of this prefix sum (to maximize future lengths)
            prefixIndex.putIfAbsent(prefixSum, i);
        }
        
        return maxLength;
    }

    // ==================== Comprehensive Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Maximum Length Subarray with Sum Equals K ===\n");

        // Example 1: Standard case with negatives
        int[] test1 = {1, -1, 5, -2, 3};
        int k1 = 3;
        System.out.println("Test 1: nums = " + java.util.Arrays.toString(test1) + ", k = " + k1);
        System.out.println("Output: " + maxSubArrayLen(test1, k1)); // Expected: 4 ([1,-1,5,-2])

        // Example 2: No valid subarray
        int[] test2 = {2, 3, 4};
        int k2 = 10;
        System.out.println("\nTest 2: nums = " + java.util.Arrays.toString(test2) + ", k = " + k2);
        System.out.println("Output: " + maxSubArrayLen(test2, k2)); // Expected: 0

        // Edge Case 1: Single element match
        int[] test3 = {5};
        int k3 = 5;
        System.out.println("\nTest 3: nums = " + java.util.Arrays.toString(test3) + ", k = " + k3);
        System.out.println("Output: " + maxSubArrayLen(test3, k3)); // Expected: 1

        // Edge Case 2: Single element no match
        int[] test4 = {5};
        int k4 = 3;
        System.out.println("\nTest 4: nums = " + java.util.Arrays.toString(test4) + ", k = " + k4);
        System.out.println("Output: " + maxSubArrayLen(test4, k4)); // Expected: 0

        // Edge Case 3: All zeros, k=0
        int[] test5 = {0, 0, 0};
        int k5 = 0;
        System.out.println("\nTest 5: nums = " + java.util.Arrays.toString(test5) + ", k = " + k5);
        System.out.println("Output: " + maxSubArrayLen(test5, k5)); // Expected: 3 (entire array)

        // Edge Case 4: Mixed signs, multiple valid subarrays
        int[] test6 = {1, -1, 0, 1, -1};
        int k6 = 0;
        System.out.println("\nTest 6: nums = " + java.util.Arrays.toString(test6) + ", k = " + k6);
        System.out.println("Output: " + maxSubArrayLen(test6, k6)); // Expected: 5 (entire array)

        // Edge Case 5: Negative k
        int[] test7 = {1, 2, -3, 4};
        int k7 = -3;
        System.out.println("\nTest 7: nums = " + java.util.Arrays.toString(test7) + ", k = " + k7);
        System.out.println("Output: " + maxSubArrayLen(test7, k7)); // Expected: 1 ([-3])

        // Edge Case 6: Large array with long valid subarray
        int[] test8 = new int[1000];
        for (int i = 0; i < 500; i++) test8[i] = 1;
        for (int i = 500; i < 1000; i++) test8[i] = -1;
        int k8 = 0;
        System.out.println("\nTest 8: nums = array of 500 ones + 500 -1s, k = " + k8);
        System.out.println("Output: " + maxSubArrayLen(test8, k8)); // Expected: 1000 (entire array)

        // Edge Case 7: k = 0 with positive numbers only
        int[] test9 = {1, 2, 3};
        int k9 = 0;
        System.out.println("\nTest 9: nums = " + java.util.Arrays.toString(test9) + ", k = " + k9);
        System.out.println("Output: " + maxSubArrayLen(test9, k9)); // Expected: 0

        // Edge Case 8: Multiple occurrences of same prefix sum
        int[] test10 = {1, 0, -1, 1, 0, -1};
        int k10 = 0;
        System.out.println("\nTest 10: nums = " + java.util.Arrays.toString(test10) + ", k = " + k10);
        System.out.println("Output: " + maxSubArrayLen(test10, k10)); // Expected: 6 (entire array)
    }
}
```

---

## 🔍 **How It Works: Step-by-Step Example**

### **Example**: `nums = [1, -1, 5, -2, 3]`, `k = 3`

| i | nums[i] | prefixSum | target = prefixSum - k | Found in Map? | Length | maxLength | Map State |
|---|---------|-----------|------------------------|---------------|--------|-----------|-----------|
| - | - | 0 | -3 | - | - | 0 | `{0:-1}` |
| 0 | 1 | 1 | -2 | No | - | 0 | `{0:-1, 1:0}` |
| 1 | -1 | 0 | -3 | No | - | 0 | `{0:-1, 1:0}` |
| 2 | 5 | 5 | 2 | No | - | 0 | `{0:-1, 1:0, 5:2}` |
| 3 | -2 | 3 | 0 | Yes (index -1) | 3-(-1)=4 | 4 | `{0:-1, 1:0, 5:2, 3:3}` |
| 4 | 3 | 6 | 3 | Yes (index 3) | 4-3=1 | 4 | `{0:-1, 1:0, 5:2, 3:3, 6:4}` |

✅ Maximum length = **4** (subarray `[1, -1, 5, -2]`)

---
## 📊 **Complexity Analysis**

| Aspect | Value |
|--------|-------|
| **Time Complexity** | O(n) – single pass through array |
| **Space Complexity** | O(n) – HashMap may store up to n+1 entries |
| **Optimality** | **Optimal** – must examine each element at least once |

---
## ⚠️ **Key Differences from Similar Problems**

| Problem | Key Difference |
|---------|----------------|
| **LeetCode 560**: Subarray Sum Equals K | Counts **number** of subarrays, not maximum length |
| **This Problem** | Finds **maximum length** of subarray with sum = k |
| **LeetCode 209**: Minimum Size Subarray Sum | Finds **minimum length** with sum ≥ target, positive numbers only |

### 🔑 **Critical Implementation Detail**:
- **Store first occurrence** of each prefix sum (using `putIfAbsent`) to maximize length
- In counting problems, you increment frequency; here, you only store the earliest index

---
## 💡 **Why HashMap Approach is Necessary**

### ❌ **Why Sliding Window Fails**:
- Sliding window requires **monotonic behavior** (sum only increases or decreases)
- With **negative numbers**, expanding window can **decrease** the sum
- Cannot guarantee that shrinking from left will find the longest valid window

### ✅ **Why Prefix Sum + HashMap Works**:
- Handles **all integer values** (positive, negative, zero)
- **Guarantees finding the longest subarray** by storing first occurrences
- **Efficient** with O(n) time complexity

---
## 🎯 **Key Takeaways**

1. **Store first occurrence** of prefix sums to maximize subarray length
2. **Use `prefixIndex.putIfAbsent()`** instead of `put()` to preserve earliest indices
3. **Initialize with `{0: -1}`** to handle subarrays starting from index 0
4. **This pattern is fundamental** for maximum/minimum length subarray problems
5. **Understanding the difference** between counting and maximizing is crucial

This problem demonstrates how a small modification to the classic prefix sum pattern (storing first occurrence instead of counting frequency) can solve a completely different variant of the same underlying problem! 🚀