# 📘 **53. Maximum Subarray – Comprehensive Solution**

## 📋 **Problem Statement**

Given an integer array `nums`, find the **contiguous subarray** (containing at least one number) which has the **largest sum** and return its sum.

This is the classic **Maximum Subarray Problem**, also known as **Kadane's Algorithm**.

## 🧠 **Core Insight: Kadane's Algorithm (Dynamic Programming)**

### 🔑 **The Fundamental Idea**:

At each position `i`, we have two choices:
1. **Extend the previous subarray** by including `nums[i]`
2. **Start a new subarray** from `nums[i]`

The optimal choice is: `max(nums[i], current_sum + nums[i])`

### 💡 **Dynamic Programming Formulation**:
```
dp[i] = maximum sum of subarray ending at index i
dp[i] = max(nums[i], dp[i-1] + nums[i])
result = max(dp[i]) for all i
```

We can optimize space by keeping only the previous value.

---

## ✅ **Optimal Solution: Kadane's Algorithm (O(n))**

### 🧩 **Algorithm Steps**:
1. Initialize `maxSum = nums[0]` and `currentSum = nums[0]`
2. For each element from index 1 to n-1:
   - `currentSum = max(nums[i], currentSum + nums[i])`
   - `maxSum = max(maxSum, currentSum)`
3. Return `maxSum`

---

## 🚀 **Complete Implementation with Test Cases**

```java
public class MaximumSubarray {

    /**
     * Finds the maximum sum of a contiguous subarray using Kadane's algorithm.
     * 
     * Approach: Dynamic Programming (Kadane's Algorithm)
     * 
     * Key Insights:
     * 1. At each position, decide whether to extend previous subarray or start new
     * 2. currentSum = max(nums[i], currentSum + nums[i])
     * 3. Track maximum currentSum seen so far
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @param nums input array of integers
     * @return maximum sum of contiguous subarray
     */
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int maxSum = nums[0];
        int currentSum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            // Either extend previous subarray or start new one
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            // Update global maximum
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }

    // ==================== Divide and Conquer Approach (Follow-up) ====================
    /**
     * Divide and Conquer approach for maximum subarray.
     * 
     * Key Insights:
     * 1. Maximum subarray is either in left half, right half, or crosses the middle
     * 2. For cross-middle case, find maximum sum ending at mid and starting at mid+1
     * 
     * Time Complexity: O(n log n)
     * Space Complexity: O(log n) - recursion stack
     */
    public static int maxSubArrayDivideConquer(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return divideAndConquer(nums, 0, nums.length - 1);
    }
    
    private static int divideAndConquer(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        
        int mid = left + (right - left) / 2;
        
        // Maximum subarray in left half
        int leftMax = divideAndConquer(nums, left, mid);
        // Maximum subarray in right half  
        int rightMax = divideAndConquer(nums, mid + 1, right);
        // Maximum subarray crossing the middle
        int crossMax = maxCrossingSum(nums, left, mid, right);
        
        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }
    
    private static int maxCrossingSum(int[] nums, int left, int mid, int right) {
        // Maximum sum ending at mid (left to mid)
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftSum = Math.max(leftSum, sum);
        }
        
        // Maximum sum starting at mid+1 (mid+1 to right)
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightSum = Math.max(rightSum, sum);
        }
        
        return leftSum + rightSum;
    }

    // ==================== Brute Force Approach (For Comparison) ====================
    /**
     * Brute force approach - O(n²) time
     * Included for educational purposes only
     */
    public static int maxSubArrayBruteForce(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int maxSum = nums[0];
        
        for (int i = 0; i < nums.length; i++) {
            int currentSum = 0;
            for (int j = i; j < nums.length; j++) {
                currentSum += nums[j];
                maxSum = Math.max(maxSum, currentSum);
            }
        }
        
        return maxSum;
    }

    // ==================== Comprehensive Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Maximum Subarray ===\n");

        // Example 1: Classic case with negatives
        int[] test1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Test 1: nums = " + java.util.Arrays.toString(test1));
        System.out.println("Kadane's: " + maxSubArray(test1)); // Expected: 6 ([4,-1,2,1])
        System.out.println("Divide & Conquer: " + maxSubArrayDivideConquer(test1)); // Expected: 6
        System.out.println("Brute Force: " + maxSubArrayBruteForce(test1)); // Expected: 6

        // Example 2: Single element
        int[] test2 = {1};
        System.out.println("\nTest 2: nums = " + java.util.Arrays.toString(test2) + " (single element)");
        System.out.println("Kadane's: " + maxSubArray(test2)); // Expected: 1

        // Example 3: All positive
        int[] test3 = {5, 4, -1, 7, 8};
        System.out.println("\nTest 3: nums = " + java.util.Arrays.toString(test3) + " (mostly positive)");
        System.out.println("Kadane's: " + maxSubArray(test3)); // Expected: 23 (entire array)

        // Edge Case 1: All negative
        int[] test4 = {-5, -2, -8, -1};
        System.out.println("\nTest 4: nums = " + java.util.Arrays.toString(test4) + " (all negative)");
        System.out.println("Kadane's: " + maxSubArray(test4)); // Expected: -1 (single element)

        // Edge Case 2: Alternating positive/negative
        int[] test5 = {-1, 2, -3, 4, -5, 6};
        System.out.println("\nTest 5: nums = " + java.util.Arrays.toString(test5) + " (alternating)");
        System.out.println("Kadane's: " + maxSubArray(test5)); // Expected: 6 ([6])

        // Edge Case 3: Large array
        int[] test6 = new int[100000];
        for (int i = 0; i < 100000; i++) {
            test6[i] = (i % 2 == 0) ? 1 : -1;
        }
        System.out.println("\nTest 6: Large alternating array (100,000 elements)");
        long startTime = System.currentTimeMillis();
        int result6 = maxSubArray(test6);
        long endTime = System.currentTimeMillis();
        System.out.println("Kadane's Result: " + result6 + " (Time: " + (endTime - startTime) + " ms)");

        // Edge Case 4: Maximum constraint values
        int[] test7 = {10000, -10000, 10000, -10000, 10000};
        System.out.println("\nTest 7: nums = " + java.util.Arrays.toString(test7) + " (max/min values)");
        System.out.println("Kadane's: " + maxSubArray(test7)); // Expected: 10000

        // Performance Comparison
        System.out.println("\n=== Performance Comparison (n=10000) ===");
        int[] performanceTest = new int[10000];
        for (int i = 0; i < 10000; i++) {
            performanceTest[i] = (int) (Math.random() * 20000) - 10000;
        }
        
        // Kadane's Algorithm
        long start1 = System.currentTimeMillis();
        int result1 = maxSubArray(performanceTest);
        long end1 = System.currentTimeMillis();
        
        // Divide and Conquer
        long start2 = System.currentTimeMillis();
        int result2 = maxSubArrayDivideConquer(performanceTest);
        long end2 = System.currentTimeMillis();
        
        // Brute Force (only for small n)
        int[] smallTest = java.util.Arrays.copyOf(performanceTest, 100);
        long start3 = System.currentTimeMillis();
        int result3 = maxSubArrayBruteForce(smallTest);
        long end3 = System.currentTimeMillis();
        
        System.out.println("Kadane's (n=10000): " + (end1 - start1) + " ms, Result: " + result1);
        System.out.println("Divide & Conquer (n=10000): " + (end2 - start2) + " ms, Result: " + result2);
        System.out.println("Brute Force (n=100): " + (end3 - start3) + " ms, Result: " + result3);
    }
}
```

---

## 🔍 **How Kadane's Algorithm Works: Step-by-Step**

### **Example**: `nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]`

| i | nums[i] | currentSum | maxSum | Explanation |
|---|---------|------------|--------|-------------|
| 0 | -2 | -2 | -2 | Start with first element |
| 1 | 1 | max(1, -2+1)=1 | max(-2,1)=1 | Start new subarray [1] |
| 2 | -3 | max(-3, 1-3)=-2 | 1 | Extend [1,-3] |
| 3 | 4 | max(4, -2+4)=4 | max(1,4)=4 | Start new subarray [4] |
| 4 | -1 | max(-1, 4-1)=3 | 4 | Extend [4,-1] |
| 5 | 2 | max(2, 3+2)=5 | 5 | Extend [4,-1,2] |
| 6 | 1 | max(1, 5+1)=6 | 6 | Extend [4,-1,2,1] |
| 7 | -5 | max(-5, 6-5)=1 | 6 | Extend [4,-1,2,1,-5] |
| 8 | 4 | max(4, 1+4)=5 | 6 | Extend [4,-1,2,1,-5,4] |

✅ Maximum sum = **6**

---

## 📊 **Complexity Analysis**

| Approach | Time Complexity | Space Complexity | When to Use |
|----------|----------------|------------------|-------------|
| **Kadane's Algorithm** | O(n) | O(1) | **Optimal** - production code |
| **Divide & Conquer** | O(n log n) | O(log n) | Interview follow-up, learning |
| **Brute Force** | O(n²) | O(1) | Educational only |

---

## 🔍 **Divide and Conquer Deep Dive**

### **How It Works**:
1. **Divide**: Split array into left and right halves
2. **Conquer**: Recursively find max subarray in left and right halves
3. **Combine**: Find max subarray that crosses the middle
   - Maximum sum ending at mid (from left)
   - Maximum sum starting at mid+1 (from right)
   - Cross sum = left part + right part

### **Why It's O(n log n)**:
- T(n) = 2T(n/2) + O(n) → Master Theorem → O(n log n)

### **When It's Better**:
- When you need to find the actual subarray indices (not just sum)
- In parallel computing environments
- For educational understanding of divide and conquer

---

## ⚠️ **Common Mistakes to Avoid**

### ❌ **Mistake 1: Forgetting to Handle All Negative Arrays**
```java
// Wrong: initializes maxSum to 0
int maxSum = 0;
int currentSum = 0;
// Fails for all negative arrays
```

### ✅ **Correct Initialization**:
```java
int maxSum = nums[0];
int currentSum = nums[0];
```

### ❌ **Mistake 2: Incorrect Recurrence in Kadane's**
```java
// Wrong: always extends previous subarray
currentSum = currentSum + nums[i];
```

### ✅ **Correct Recurrence**:
```java
currentSum = Math.max(nums[i], currentSum + nums[i]);
```

---

## 💡 **Real-World Applications**

- **Financial Analysis**: Maximum profit/loss periods
- **Signal Processing**: Finding peaks in time series data
- **Bioinformatics**: DNA sequence analysis
- **Computer Vision**: Edge detection in images
- **Machine Learning**: Feature extraction from sequences

---

## 🎯 **Key Takeaways**

1. **Kadane's Algorithm** is the gold standard for maximum subarray problems
2. **Dynamic programming insight**: Make local optimal choices for global optimum
3. **Divide and conquer** provides deeper algorithmic understanding
4. **Handle edge cases**: All negative numbers, single element, empty array
5. **This pattern extends to many variants**: Maximum Product Subarray, Circular Subarray, etc.

This problem is a cornerstone of algorithmic thinking – mastering it provides a foundation for solving many more complex problems! 🚀