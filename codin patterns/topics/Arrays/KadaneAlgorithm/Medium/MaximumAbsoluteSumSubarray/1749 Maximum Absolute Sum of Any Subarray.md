# 📘 **1749. Maximum Absolute Sum of Any Subarray – Comprehensive Solution**
## 📋 **Problem Statement**

Given an integer array `nums`, find the **maximum absolute sum** of any (possibly empty) subarray.

The absolute sum of a subarray is `abs(sum of elements in the subarray)`.

### 🎯 **Key Requirements**:
- **Contiguous subarray** (consecutive elements)
- **Absolute value** of the sum (so both large positive and large negative sums matter)
- **Possibly empty subarray** (sum = 0, abs = 0)
- Handle large arrays efficiently (up to 10⁵ elements)

## 🧠 **Core Insight: Maximum Absolute Sum = max(|max_subarray|, |min_subarray|)**

### 🔑 **The Fundamental Insight**:

The maximum absolute sum will be either:
1. **The absolute value of the maximum subarray sum** (largest positive sum)
2. **The absolute value of the minimum subarray sum** (largest negative sum, which becomes large positive after abs)

So: `maxAbsoluteSum = max(abs(maxSubarraySum), abs(minSubarraySum))`

### 💡 **Why This Works**:
- For any subarray, `abs(sum) = max(sum, -sum)`
- The maximum of `abs(sum)` over all subarrays equals `max(maxSum, -minSum)`
- Since `abs(x) ≥ 0`, and we want the maximum absolute value

### 📊 **Example Analysis**:
```
nums = [1, -3, 2, 3, -4]
maxSubarray = [2,3] = 5 → abs = 5
minSubarray = [1,-3,2,3,-4] = -1 → abs = 1
maxAbsoluteSum = max(5, 1) = 5 ✅

nums = [2, -5, 1, -4, 3, -2]
maxSubarray = [3] = 3 → abs = 3
minSubarray = [-5,1,-4] = -8 → abs = 8
maxAbsoluteSum = max(3, 8) = 8 ✅
```

---

## ✅ **Optimal Solution: Modified Kadane's Algorithm**

### 🧩 **Algorithm Steps**:
1. Use Kadane's algorithm to find `maxSum` (maximum subarray sum)
2. Use modified Kadane's algorithm to find `minSum` (minimum subarray sum)
3. Return `max(abs(maxSum), abs(minSum))`

Since `abs(x) = max(x, -x)`, we can simplify to:
- `maxAbsoluteSum = max(maxSum, -minSum)`

---

## 🚀 **Complete Implementation with Test Cases**

```java
public class MaximumAbsoluteSumOfAnySubarray {

    /**
     * Finds the maximum absolute sum of any subarray.
     * 
     * Approach: Modified Kadane's Algorithm
     * 
     * Key Insights:
     * 1. Maximum absolute sum = max(|maxSubarraySum|, |minSubarraySum|)
     * 2. Since abs(x) = max(x, -x), result = max(maxSum, -minSum)
     * 3. Use Kadane's algorithm for both max and min subarray sums
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @param nums input array of integers
     * @return maximum absolute sum of any subarray
     */
    public static int maxAbsoluteSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // Find maximum subarray sum (Kadane's algorithm)
        int maxSum = nums[0];
        int currentMax = nums[0];
        
        // Find minimum subarray sum (modified Kadane's)
        int minSum = nums[0];
        int currentMin = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            
            // Kadane's for maximum
            currentMax = Math.max(num, currentMax + num);
            maxSum = Math.max(maxSum, currentMax);
            
            // Kadane's for minimum
            currentMin = Math.min(num, currentMin + num);
            minSum = Math.min(minSum, currentMin);
        }
        
        // Maximum absolute sum = max(|maxSum|, |minSum|)
        // Since abs(x) = max(x, -x), we can use max(maxSum, -minSum)
        return Math.max(maxSum, -minSum);
    }

    // ==================== Alternative: Single Pass with Absolute Tracking ====================
    /**
     * Alternative approach: track maxPositive and maxNegative sums
     * More explicit about absolute values
     */
    public static int maxAbsoluteSumAlternative(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int maxPositive = nums[0];  // maximum positive sum ending here
        int maxNegative = nums[0];  // maximum negative sum (most negative) ending here
        int result = Math.abs(nums[0]);
        
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            
            // Update max positive sum ending at i
            maxPositive = Math.max(num, maxPositive + num);
            // Update max negative sum ending at i
            maxNegative = Math.min(num, maxNegative + num);
            
            // Update result with absolute values
            result = Math.max(result, Math.max(Math.abs(maxPositive), Math.abs(maxNegative)));
        }
        
        return result;
    }

    // ==================== Brute Force Approach (For Comparison) ====================
    /**
     * Brute force approach - O(n²) time
     * Included for educational purposes only
     */
    public static int maxAbsoluteSumBruteForce(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int maxAbsSum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            long currentSum = 0; // Use long to prevent overflow during calculation
            for (int j = i; j < nums.length; j++) {
                currentSum += nums[j];
                maxAbsSum = Math.max(maxAbsSum, Math.abs((int) currentSum));
            }
        }
        
        return maxAbsSum;
    }

    // ==================== Comprehensive Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Maximum Absolute Sum of Any Subarray ===\n");

        // Example 1: Standard case
        int[] test1 = {1, -3, 2, 3, -4};
        System.out.println("Test 1: nums = " + java.util.Arrays.toString(test1));
        System.out.println("Optimal: " + maxAbsoluteSum(test1)); // Expected: 5 ([2,3])
        System.out.println("Alternative: " + maxAbsoluteSumAlternative(test1)); // Expected: 5
        System.out.println("Brute Force: " + maxAbsoluteSumBruteForce(test1)); // Expected: 5

        // Example 2: Negative sum is better
        int[] test2 = {2, -5, 1, -4, 3, -2};
        System.out.println("\nTest 2: nums = " + java.util.Arrays.toString(test2) + " (negative better)");
        System.out.println("Optimal: " + maxAbsoluteSum(test2)); // Expected: 8 ([-5,1,-4])
        System.out.println("Alternative: " + maxAbsoluteSumAlternative(test2)); // Expected: 8

        // Edge Case 1: All positive
        int[] test3 = {1, 2, 3, 4};
        System.out.println("\nTest 3: nums = " + java.util.Arrays.toString(test3) + " (all positive)");
        System.out.println("Optimal: " + maxAbsoluteSum(test3)); // Expected: 10 (entire array)

        // Edge Case 2: All negative
        int[] test4 = {-1, -2, -3, -4};
        System.out.println("\nTest 4: nums = " + java.util.Arrays.toString(test4) + " (all negative)");
        System.out.println("Optimal: " + maxAbsoluteSum(test4)); // Expected: 10 (entire array, abs(-10)=10)

        // Edge Case 3: Single element
        int[] test5 = {5};
        System.out.println("\nTest 5: nums = " + java.util.Arrays.toString(test5) + " (single positive)");
        System.out.println("Optimal: " + maxAbsoluteSum(test5)); // Expected: 5

        // Edge Case 4: Single negative element
        int[] test6 = {-5};
        System.out.println("\nTest 6: nums = " + java.util.Arrays.toString(test6) + " (single negative)");
        System.out.println("Optimal: " + maxAbsoluteSum(test6)); // Expected: 5

        // Edge Case 5: Zero element
        int[] test7 = {0, -1, 2};
        System.out.println("\nTest 7: nums = " + java.util.Arrays.toString(test7) + " (contains zero)");
        System.out.println("Optimal: " + maxAbsoluteSum(test7)); // Expected: 2

        // Edge Case 6: Alternating positive/negative
        int[] test8 = {-1, 2, -3, 4, -5};
        System.out.println("\nTest 8: nums = " + java.util.Arrays.toString(test8) + " (alternating)");
        System.out.println("Optimal: " + maxAbsoluteSum(test8)); // Expected: 5 ([4] or abs([-5]))

        // Edge Case 7: Maximum constraint values
        int[] test9 = {10000, -10000, 10000};
        System.out.println("\nTest 9: nums = " + java.util.Arrays.toString(test9) + " (max values)");
        System.out.println("Optimal: " + maxAbsoluteSum(test9)); // Expected: 10000

        // Edge Case 8: Mixed with large absolute sum
        int[] test10 = {-2, -3, 1, 4, -5, -6};
        System.out.println("\nTest 10: nums = " + java.util.Arrays.toString(test10));
        System.out.println("Optimal: " + maxAbsoluteSum(test10)); // Expected: 11 (abs([-5,-6]) = 11)

        // Performance Comparison
        System.out.println("\n=== Performance Comparison (n=10000) ===");
        int[] performanceTest = new int[10000];
        for (int i = 0; i < 10000; i++) {
            performanceTest[i] = (i % 2 == 0) ? 100 : -100;
        }
        
        // Optimal Approach
        long start1 = System.currentTimeMillis();
        int result1 = maxAbsoluteSum(performanceTest);
        long end1 = System.currentTimeMillis();
        
        // Brute Force (only for small n)
        int[] smallTest = java.util.Arrays.copyOf(performanceTest, 500);
        long start2 = System.currentTimeMillis();
        int result2 = maxAbsoluteSumBruteForce(smallTest);
        long end2 = System.currentTimeMillis();
        
        System.out.println("Optimal (n=10000): " + (end1 - start1) + " ms, Result: " + result1);
        System.out.println("Brute Force (n=500): " + (end2 - start2) + " ms, Result: " + result2);
        
        // Step-by-step visualization for test2
        System.out.println("\n=== Step-by-Step for Test 2: [2,-5,1,-4,3,-2] ===");
        System.out.println("Finding maxSubarray and minSubarray:");
        int[] nums = {2, -5, 1, -4, 3, -2};
        int maxSum = nums[0], minSum = nums[0];
        int currMax = nums[0], currMin = nums[0];
        
        System.out.printf("i=0: num=%2d, currMax=%2d, maxSum=%2d, currMin=%2d, minSum=%2d%n", 
                         nums[0], currMax, maxSum, currMin, minSum);
        
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            currMax = Math.max(num, currMax + num);
            maxSum = Math.max(maxSum, currMax);
            currMin = Math.min(num, currMin + num);
            minSum = Math.min(minSum, currMin);
            System.out.printf("i=%d: num=%2d, currMax=%2d, maxSum=%2d, currMin=%2d, minSum=%2d%n", 
                             i, num, currMax, maxSum, currMin, minSum);
        }
        
        System.out.println("Final: maxSum = " + maxSum + ", minSum = " + minSum);
        System.out.println("Result: max(" + maxSum + ", -(" + minSum + ")) = " + 
                          Math.max(maxSum, -minSum));
    }
}
```

---

## 🔍 **How It Works: Step-by-Step Examples**

### **Example 1**: `nums = [1, -3, 2, 3, -4]`

| i | num | currMax | maxSum | currMin | minSum |
|---|-----|---------|--------|---------|--------|
| 0 | 1 | 1 | 1 | 1 | 1 |
| 1 | -3 | -2 | 1 | -3 | -3 |
| 2 | 2 | 0 | 1 | -1 | -3 |
| 3 | 3 | 3 | **3** | 2 | -3 |
| 4 | -4 | -1 | 3 | -2 | -3 |

- `maxSum = 3`, `minSum = -3` → wait, but expected answer is 5...

Let me recalculate properly:

Actually, the maximum subarray should be `[2,3] = 5`, so let me trace again:

| i | num | currMax | maxSum | currMin | minSum |
|---|-----|---------|--------|---------|--------|
| 0 | 1 | 1 | 1 | 1 | 1 |
| 1 | -3 | max(-3, 1-3=-2) = -2 | 1 | min(-3, 1-3=-2) = -3 | -3 |
| 2 | 2 | max(2, -2+2=0) = 2 | max(1,2)=2 | min(2, -3+2=-1) = -1 | -3 |
| 3 | 3 | max(3, 2+3=5) = **5** | **5** | min(3, -1+3=2) = 2 | -3 |
| 4 | -4 | max(-4, 5-4=1) = 1 | 5 | min(-4, 2-4=-2) = -4 | **-4** |

- `maxSum = 5`, `minSum = -4`
- `max(5, -(-4)) = max(5, 4) = 5` ✅

### **Example 2**: `nums = [2, -5, 1, -4, 3, -2]`

| i | num | currMax | maxSum | currMin | minSum |
|---|-----|---------|--------|---------|--------|
| 0 | 2 | 2 | 2 | 2 | 2 |
| 1 | -5 | -3 | 2 | **-5** | **-5** |
| 2 | 1 | 1 | 2 | -4 | -5 |
| 3 | -4 | -3 | 2 | **-8** | **-8** |
| 4 | 3 | 0 | 2 | -5 | -8 |
| 5 | -2 | -2 | 2 | -7 | -8 |

- `maxSum = 2`, `minSum = -8`
- `max(2, -(-8)) = max(2, 8) = 8` ✅

---

## 📊 **Complexity Analysis**

| Approach | Time Complexity | Space Complexity | When to Use |
|----------|----------------|------------------|-------------|
| **Modified Kadane's** | O(n) | O(1) | **Optimal** - production code |
| **Brute Force** | O(n²) | O(1) | Educational only |

---

## ⚠️ **Common Mistakes to Avoid**

### ❌ **Mistake 1: Only Finding Maximum Subarray**
```java
// Wrong: only considers positive sums
int maxSum = kadaneMax(nums);
return maxSum; // Fails for test2 where negative sum is better
```

### ❌ **Mistake 2: Not Taking Absolute Values**
```java
// Wrong: returns maxSum or minSum directly
return Math.max(maxSum, minSum); // For test2: max(2, -8) = 2, should be 8
```

### ✅ **Correct Formula**:
```java
return Math.max(maxSum, -minSum);
// or
return Math.max(Math.abs(maxSum), Math.abs(minSum));
```

---

## 💡 **Why This Problem is Interesting**

1. **Combines two classic problems**: Maximum Subarray and Minimum Subarray
2. **Absolute value twist** makes it non-trivial
3. **Shows the power of Kadane's algorithm** in variations
4. **Real-world relevance**: Finding maximum deviation in time series data

This problem demonstrates how a simple modification (adding absolute value) can require understanding both extremes of a distribution! 🚀