# 📘 **918. Maximum Sum Circular Subarray – Comprehensive Solution**

## 📋 **Problem Statement**

Given a **circular integer array** `nums`, find the **maximum possible sum of a non-empty subarray**.

A circular array means the end connects to the beginning, but **subarrays cannot wrap around more than once** (each element can be used at most once).

### 🎯 **Key Requirements**:
- **Circular structure**: subarray can wrap from end to beginning
- **No element repetition**: subarray is contiguous in the circular sense
- **Non-empty subarray**: at least one element
- Handle negative numbers and circular wrapping

## 🧠 **Core Insight: Two Cases Analysis**

### 🔑 **The Fundamental Insight**:

In a circular array, the maximum subarray sum can be in **one of two cases**:

1. **Non-circular case**: Maximum subarray is entirely within the array (no wrapping)
   - This is the same as the standard **Maximum Subarray** problem (Kadane's algorithm)

2. **Circular case**: Maximum subarray wraps around from end to beginning
   - This is equivalent to: **total sum - minimum subarray sum**
   - Why? The maximum circular subarray = total array - minimum middle subarray

### 💡 **Mathematical Formulation**:
```
maxCircular = max(
    maxSubarray(nums),                           // non-circular case
    totalSum - minSubarray(nums)                 // circular case
)
```

### ⚠️ **Special Case: All Negative Numbers**
If all numbers are negative, `totalSum - minSubarray` = 0, but we need a non-empty subarray, so we must return `maxSubarray`.

---

## ✅ **Optimal Solution: Modified Kadane's Algorithm**

### 🧩 **Algorithm Steps**:
1. Calculate `maxSum` using standard Kadane's algorithm
2. Calculate `minSum` using modified Kadane's algorithm (for minimum subarray)
3. Calculate `totalSum` of the array
4. If `maxSum < 0`, return `maxSum` (all negative case)
5. Otherwise, return `max(maxSum, totalSum - minSum)`

---

## 🚀 **Complete Implementation with Test Cases**

```java
public class MaximumSumCircularSubarray {

    /**
     * Finds the maximum sum of a non-empty subarray in a circular array.
     * 
     * Approach: Two Cases Analysis
     * 
     * Key Insights:
     * 1. Non-circular case: standard maximum subarray (Kadane's algorithm)
     * 2. Circular case: total sum - minimum subarray sum
     * 3. Special case: if all numbers are negative, circular case gives 0, but we need non-empty
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @param nums circular integer array
     * @return maximum sum of non-empty subarray
     */
    public static int maxSubarraySumCircular(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // Calculate maximum subarray sum (non-circular case)
        int maxSum = nums[0];
        int currentMax = nums[0];
        
        // Calculate minimum subarray sum (for circular case)
        int minSum = nums[0];
        int currentMin = nums[0];
        
        // Calculate total sum
        int totalSum = nums[0];
        
        // Single pass to compute all three values
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            totalSum += num;
            
            // Kadane's algorithm for maximum subarray
            currentMax = Math.max(num, currentMax + num);
            maxSum = Math.max(maxSum, currentMax);
            
            // Modified Kadane's for minimum subarray
            currentMin = Math.min(num, currentMin + num);
            minSum = Math.min(minSum, currentMin);
        }
        
        // Handle all negative case: circular case would give 0, but we need non-empty subarray
        if (maxSum < 0) {
            return maxSum;
        }
        
        // Return maximum of non-circular and circular cases
        return Math.max(maxSum, totalSum - minSum);
    }

    // ==================== Alternative: Separate Helper Methods ====================
    /**
     * Alternative implementation with separate helper methods for clarity
     */
    public static int maxSubarraySumCircularAlternative(int[] nums) {
        int maxSum = kadaneMax(nums);
        int minSum = kadaneMin(nums);
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        // All negative case
        if (maxSum < 0) {
            return maxSum;
        }
        
        return Math.max(maxSum, totalSum - minSum);
    }
    
    private static int kadaneMax(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
    
    private static int kadaneMin(int[] nums) {
        int minSum = nums[0];
        int currentSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.min(nums[i], currentSum + nums[i]);
            minSum = Math.min(minSum, currentSum);
        }
        return minSum;
    }

    // ==================== Comprehensive Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Maximum Sum Circular Subarray ===\n");

        // Example 1: Standard case
        int[] test1 = {1, -2, 3, -2};
        System.out.println("Test 1: nums = " + java.util.Arrays.toString(test1));
        System.out.println("Output: " + maxSubarraySumCircular(test1)); // Expected: 3 ([3])

        // Example 2: Circular case is better
        int[] test2 = {5, -3, 5};
        System.out.println("\nTest 2: nums = " + java.util.Arrays.toString(test2) + " (circular better)");
        System.out.println("Output: " + maxSubarraySumCircular(test2)); // Expected: 10 ([5,5])

        // Example 3: All negative
        int[] test3 = {-3, -2, -3};
        System.out.println("\nTest 3: nums = " + java.util.Arrays.toString(test3) + " (all negative)");
        System.out.println("Output: " + maxSubarraySumCircular(test3)); // Expected: -2 ([-2])

        // Edge Case 1: All positive
        int[] test4 = {1, 2, 3, 4};
        System.out.println("\nTest 4: nums = " + java.util.Arrays.toString(test4) + " (all positive)");
        System.out.println("Output: " + maxSubarraySumCircular(test4)); // Expected: 10 (entire array)

        // Edge Case 2: Single element
        int[] test5 = {5};
        System.out.println("\nTest 5: nums = " + java.util.Arrays.toString(test5) + " (single element)");
        System.out.println("Output: " + maxSubarraySumCircular(test5)); // Expected: 5

        // Edge Case 3: Mixed with circular advantage
        int[] test6 = {-2, 4, -1, 5, -3};
        System.out.println("\nTest 6: nums = " + java.util.Arrays.toString(test6) + " (mixed)");
        System.out.println("Output: " + maxSubarraySumCircular(test6)); // Expected: 8 ([5,-3,-2,4] or [4,-1,5])

        // Edge Case 4: Zero in middle
        int[] test7 = {3, -1, 2, -1};
        System.out.println("\nTest 7: nums = " + java.util.Arrays.toString(test7) + " (zero effect)");
        System.out.println("Output: " + maxSubarraySumCircular(test7)); // Expected: 4 ([3,-1,2] or circular [2,-1,3])

        // Edge Case 5: Large circular advantage
        int[] test8 = {6, 9, -12, 5, 3};
        System.out.println("\nTest 8: nums = " + java.util.Arrays.toString(test8));
        System.out.println("Output: " + maxSubarraySumCircular(test8)); // Expected: 17 ([5,3,6,9] - (-12))

        // Edge Case 6: Minimum subarray is entire array
        int[] test9 = {-1, -2, -3};
        System.out.println("\nTest 9: nums = " + java.util.Arrays.toString(test9) + " (min = entire)");
        System.out.println("Output: " + maxSubarraySumCircular(test9)); // Expected: -1

        // Edge Case 7: Minimum subarray is middle portion
        int[] test10 = {5, -3, -2, 5};
        System.out.println("\nTest 10: nums = " + java.util.Arrays.toString(test10));
        System.out.println("Output: " + maxSubarraySumCircular(test10)); // Expected: 10 ([5,5])

        // Performance Test
        System.out.println("\n=== Performance Test (n=30000) ===");
        int[] largeTest = new int[30000];
        for (int i = 0; i < 30000; i++) {
            largeTest[i] = (i % 3 == 0) ? 10 : -1;
        }
        
        long startTime = System.currentTimeMillis();
        int result = maxSubarraySumCircular(largeTest);
        long endTime = System.currentTimeMillis();
        
        System.out.println("Result: " + result + " (Time: " + (endTime - startTime) + " ms)");
    }
}
```

---

## 🔍 **How It Works: Step-by-Step Examples**

### **Example 1**: `nums = [1, -2, 3, -2]`
- **Non-circular max**: `maxSubarray = 3` ([3])
- **Total sum**: `1 - 2 + 3 - 2 = 0`
- **Min subarray**: `-2` ([-2] or [1,-2])
- **Circular case**: `0 - (-2) = 2`
- **Result**: `max(3, 2) = 3` ✅

### **Example 2**: `nums = [5, -3, 5]`
- **Non-circular max**: `maxSubarray = 5` ([5] or [5])
- **Total sum**: `5 - 3 + 5 = 7`
- **Min subarray**: `-3` ([-3])
- **Circular case**: `7 - (-3) = 10` ([5,5] wrapping around)
- **Result**: `max(5, 10) = 10` ✅

### **Example 3**: `nums = [-3, -2, -3]`
- **Non-circular max**: `maxSubarray = -2` ([-2])
- **Total sum**: `-8`
- **Min subarray**: `-8` (entire array)
- **Circular case**: `-8 - (-8) = 0`
- **All negative case**: since `maxSum < 0`, return `-2` ✅

---

## 📊 **Complexity Analysis**

| Aspect | Value |
|--------|-------|
| **Time Complexity** | O(n) – single pass through array |
| **Space Complexity** | O(1) – constant extra space |
| **Optimality** | **Optimal** – must examine each element at least once |

---

## ⚠️ **Common Mistakes to Avoid**

### ❌ **Mistake 1: Forgetting All Negative Case**
```java
// Wrong: doesn't handle all negative numbers
return Math.max(maxSum, totalSum - minSum);
// For [-1,-2,-3]: returns max(-1, 0) = 0, but should be -1
```

### ✅ **Correct Handling**:
```java
if (maxSum < 0) {
    return maxSum; // all numbers are negative
}
return Math.max(maxSum, totalSum - minSum);
```

### ❌ **Mistake 2: Confusing Circular Subarray Definition**
- **Correct**: subarray can wrap once (end to beginning)
- **Wrong**: subarray can wrap multiple times or use elements multiple times

The problem states: "there does not exist i <= k1, k2 <= j with k1 % n == k2 % n" → each element used at most once.

---

## 💡 **Why Two Cases Work**

### **Non-circular Case**:
```
[1, 2, 3, 4, 5]
     ↑--------↑
```
Standard Kadane's algorithm handles this.

### **Circular Case**:
```
[1, 2, 3, 4, 5]
↑-----     ----↑
```
The circular subarray = total array - middle subarray
- Middle subarray is the **minimum** subarray
- So circular max = total - min

### **Geometric Interpretation**:
- Think of the array as a circle
- The maximum subarray is either an arc within the circle or the complement of the minimum arc

---

## 🎯 **Key Takeaways**

1. **Circular problems often reduce to two cases**: with and without wrapping
2. **The complement trick** (total - minimum) is powerful for circular arrays
3. **Always handle edge cases**: all negative numbers, single element, all positive
4. **Single pass efficiency** is possible by computing max, min, and total simultaneously
5. **This pattern extends to other circular problems**: Maximum Product Circular Subarray, etc.

This problem demonstrates how **geometric insight** (thinking in circles) combined with **algorithmic technique** (Kadane's algorithm) can solve complex problems elegantly! 🚀