# 📘 **152. Maximum Product Subarray – Comprehensive Solution**

## 📋 **Problem Statement**

Given an integer array `nums`, find the **contiguous subarray** with the **largest product** and return the product.

Unlike the maximum sum subarray problem, this one is more complex due to the behavior of multiplication with negative numbers and zeros.

## 🧠 **Core Insight: Track Both Maximum and Minimum Products**

### 🔑 **The Fundamental Challenge**:

With multiplication, **negative numbers** can flip the sign:
- A large negative number × another negative number = large positive number
- So we need to track both the **maximum** and **minimum** products ending at each position

### 💡 **Key Insight**:
At each position `i`, the maximum product ending at `i` can come from:
1. `nums[i]` alone
2. `maxSoFar * nums[i]` (if nums[i] is positive)
3. `minSoFar * nums[i]` (if nums[i] is negative and minSoFar is negative)

So we maintain:
- `maxProduct`: maximum product ending at current position
- `minProduct`: minimum product ending at current position
- `result`: global maximum product

---

## ✅ **Optimal Solution: Dynamic Programming with Two Variables**

### 🧩 **Algorithm Steps**:
1. Initialize `maxProduct`, `minProduct`, and `result` to `nums[0]`
2. For each element from index 1 to n-1:
   - Calculate candidates: `nums[i]`, `maxProduct * nums[i]`, `minProduct * nums[i]`
   - Update `maxProduct` to maximum of candidates
   - Update `minProduct` to minimum of candidates
   - Update `result` to maximum of `result` and `maxProduct`
3. Return `result`

---

## 🚀 **Complete Implementation with Test Cases**

```java
public class MaximumProductSubarray {

    /**
     * Finds the maximum product of a contiguous subarray.
     * 
     * Approach: Dynamic Programming with Max/Min Tracking
     * 
     * Key Insights:
     * 1. Negative numbers can turn minimum into maximum when multiplied
     * 2. At each position, track both max and min products ending here
     * 3. New max/min can come from: nums[i], maxSoFar * nums[i], minSoFar * nums[i]
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * @param nums input array of integers
     * @return maximum product of contiguous subarray
     */
    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // Initialize with first element
        int maxProduct = nums[0];
        int minProduct = nums[0];
        int result = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            
            // Store current maxProduct before updating (needed for minProduct calculation)
            int tempMax = maxProduct;
            
            // Calculate new max and min products ending at current position
            maxProduct = Math.max(current, Math.max(maxProduct * current, minProduct * current));
            minProduct = Math.min(current, Math.min(tempMax * current, minProduct * current));
            
            // Update global maximum
            result = Math.max(result, maxProduct);
        }
        
        return result;
    }

    // ==================== Alternative: Without Temp Variable ====================
    /**
     * Alternative implementation using candidates array
     */
    public static int maxProductAlternative(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int maxProd = nums[0];
        int minProd = nums[0];
        int result = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            
            // Calculate all possible candidates
            int candidates[] = {
                current,
                maxProd * current,
                minProd * current
            };
            
            // Update max and min
            maxProd = Math.max(Math.max(candidates[0], candidates[1]), candidates[2]);
            minProd = Math.min(Math.min(candidates[0], candidates[1]), candidates[2]);
            
            result = Math.max(result, maxProd);
        }
        
        return result;
    }

    // ==================== Brute Force Approach (For Comparison) ====================
    /**
     * Brute force approach - O(n²) time
     * Included for educational purposes only
     */
    public static int maxProductBruteForce(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int maxProduct = nums[0];
        
        for (int i = 0; i < nums.length; i++) {
            long currentProduct = 1; // Use long to prevent overflow during calculation
            for (int j = i; j < nums.length; j++) {
                currentProduct *= nums[j];
                maxProduct = Math.max(maxProduct, (int) currentProduct);
            }
        }
        
        return maxProduct;
    }

    // ==================== Comprehensive Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Maximum Product Subarray ===\n");

        // Example 1: Standard case with negative
        int[] test1 = {2, 3, -2, 4};
        System.out.println("Test 1: nums = " + java.util.Arrays.toString(test1));
        System.out.println("DP Approach: " + maxProduct(test1)); // Expected: 6 ([2,3])
        System.out.println("Brute Force: " + maxProductBruteForce(test1)); // Expected: 6

        // Example 2: Zero in middle
        int[] test2 = {-2, 0, -1};
        System.out.println("\nTest 2: nums = " + java.util.Arrays.toString(test2) + " (contains zero)");
        System.out.println("DP Approach: " + maxProduct(test2)); // Expected: 0

        // Edge Case 1: Single element
        int[] test3 = {5};
        System.out.println("\nTest 3: nums = " + java.util.Arrays.toString(test3) + " (single element)");
        System.out.println("DP Approach: " + maxProduct(test3)); // Expected: 5

        // Edge Case 2: All negative
        int[] test4 = {-2, -3, -4};
        System.out.println("\nTest 4: nums = " + java.util.Arrays.toString(test4) + " (all negative)");
        System.out.println("DP Approach: " + maxProduct(test4)); // Expected: 12 ([-3,-4])

        // Edge Case 3: Even number of negatives
        int[] test5 = {-2, -3, 4, -5, -6};
        System.out.println("\nTest 5: nums = " + java.util.Arrays.toString(test5) + " (even negatives)");
        System.out.println("DP Approach: " + maxProduct(test5)); // Expected: 360 ([-2,-3,4,-5,-6])

        // Edge Case 4: Odd number of negatives
        int[] test6 = {-2, 3, -4, 5, -6};
        System.out.println("\nTest 6: nums = " + java.util.Arrays.toString(test6) + " (odd negatives)");
        System.out.println("DP Approach: " + maxProduct(test6)); // Expected: 720 (all except last or first)

        // Edge Case 5: Zeros and negatives
        int[] test7 = {0, -2, 0, -3, 0};
        System.out.println("\nTest 7: nums = " + java.util.Arrays.toString(test7) + " (zeros and negatives)");
        System.out.println("DP Approach: " + maxProduct(test7)); // Expected: 0

        // Edge Case 6: Alternating positive/negative
        int[] test8 = {-1, 2, -3, 4, -5};
        System.out.println("\nTest 8: nums = " + java.util.Arrays.toString(test8) + " (alternating)");
        System.out.println("DP Approach: " + maxProduct(test8)); // Expected: 120 (all elements)

        // Edge Case 7: Maximum constraint values
        int[] test9 = {10, -10, 10, -10, 10};
        System.out.println("\nTest 9: nums = " + java.util.Arrays.toString(test9) + " (max/min values)");
        System.out.println("DP Approach: " + maxProduct(test9)); // Expected: 100000 (all elements)

        // Edge Case 8: Product that fits in 32-bit (constraint guarantee)
        int[] test10 = {-2, -3, -4, -5};
        System.out.println("\nTest 10: nums = " + java.util.Arrays.toString(test10) + " (large product)");
        System.out.println("DP Approach: " + maxProduct(test10)); // Expected: 120 ([-2,-3,-4,-5])

        // Performance Comparison
        System.out.println("\n=== Performance Comparison (n=1000) ===");
        int[] performanceTest = new int[1000];
        for (int i = 0; i < 1000; i++) {
            performanceTest[i] = (int) (Math.random() * 21) - 10; // -10 to 10
        }
        
        // DP Approach
        long start1 = System.currentTimeMillis();
        int result1 = maxProduct(performanceTest);
        long end1 = System.currentTimeMillis();
        
        // Brute Force (only for small n)
        int[] smallTest = java.util.Arrays.copyOf(performanceTest, 100);
        long start2 = System.currentTimeMillis();
        int result2 = maxProductBruteForce(smallTest);
        long end2 = System.currentTimeMillis();
        
        System.out.println("DP Approach (n=1000): " + (end1 - start1) + " ms, Result: " + result1);
        System.out.println("Brute Force (n=100): " + (end2 - start2) + " ms, Result: " + result2);
    }
}
```

---

## 🔍 **How the Algorithm Works: Step-by-Step**

### **Example**: `nums = [2, 3, -2, 4]`

| i | nums[i] | maxProduct | minProduct | result | Explanation |
|---|---------|------------|------------|--------|-------------|
| 0 | 2 | 2 | 2 | 2 | Initialize |
| 1 | 3 | max(3, 2×3, 2×3)=6 | min(3, 2×3, 2×3)=3 | 6 | [2,3] |
| 2 | -2 | max(-2, 6×(-2), 3×(-2))=max(-2,-12,-6)=-2 | min(-2, 6×(-2), 3×(-2))=-12 | 6 | Start new [-2] |
| 3 | 4 | max(4, -2×4, -12×4)=max(4,-8,-48)=4 | min(4, -2×4, -12×4)=-48 | 6 | Start new [4] |

✅ Maximum product = **6**

### **Example with Negative Flipping**: `nums = [-2, 3, -4]`

| i | nums[i] | maxProduct | minProduct | result |
|---|---------|------------|------------|--------|
| 0 | -2 | -2 | -2 | -2 |
| 1 | 3 | max(3, -2×3, -2×3)=3 | min(3, -2×3, -2×3)=-6 | 3 |
| 2 | -4 | max(-4, 3×(-4), -6×(-4))=max(-4,-12,24)=**24** | min(-4, 3×(-4), -6×(-4))=-12 | **24** |

✅ Maximum product = **24** ([-2,3,-4])

---

## 📊 **Complexity Analysis**

| Approach | Time Complexity | Space Complexity | When to Use |
|----------|----------------|------------------|-------------|
| **DP with Max/Min** | O(n) | O(1) | **Optimal** - production code |
| **Brute Force** | O(n²) | O(1) | Educational only |

---

## ⚠️ **Common Mistakes to Avoid**

### ❌ **Mistake 1: Not Handling Negative Flips**
```java
// Wrong: only tracks maximum product
int maxProd = nums[0];
int currentMax = nums[0];
for (int i = 1; i < nums.length; i++) {
    currentMax = Math.max(nums[i], currentMax * nums[i]);
    maxProd = Math.max(maxProd, currentMax);
}
// Fails for [-2, 3, -4] → returns 3 instead of 24
```

### ❌ **Mistake 2: Updating minProduct with Updated maxProduct**
```java
// Wrong: uses already updated maxProduct
maxProduct = Math.max(current, Math.max(maxProduct * current, minProduct * current));
minProduct = Math.min(current, Math.min(maxProduct * current, minProduct * current)); // maxProduct changed!
```

### ✅ **Correct Implementation**:
```java
int tempMax = maxProduct; // Store before updating
maxProduct = Math.max(current, Math.max(maxProduct * current, minProduct * current));
minProduct = Math.min(current, Math.min(tempMax * current, minProduct * current));
```

---

## 💡 **Why This Problem is More Complex Than Maximum Sum**

| Factor | Maximum Sum | Maximum Product |
|--------|-------------|-----------------|
| **Sign Handling** | Positive numbers only matter | Negative numbers can be beneficial |
| **Zero Impact** | Zero resets sum to 0 | Zero resets product to 0, but may be optimal |
| **State Tracking** | Only max ending here | Must track both max and min ending here |
| **Intuition** | Extend if sum increases | Extend if product could become larger later |

---

## 🎯 **Key Takeaways**

1. **Track both maximum and minimum** products at each position
2. **Negative numbers are your friends** when paired with other negatives
3. **Zeros act as reset points** but might be the optimal answer
4. **The algorithm is elegant** but requires careful implementation
5. **This pattern extends to variants**: Maximum Product of Three Numbers, Maximum Product of Word Lengths, etc.

This problem demonstrates how a seemingly simple variation (sum → product) can require a completely different algorithmic approach due to the mathematical properties of the operation! 🚀