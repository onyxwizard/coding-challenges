You are given a **0-indexed** integer array `nums` of size `n`.

Define two arrays `leftSum` and `rightSum` where:

- `leftSum[i]` is the sum of elements to the left of the index `i` in the array `nums`. If there is no such element, `leftSum[i] = 0`.
- `rightSum[i]` is the sum of elements to the right of the index `i` in the array `nums`. If there is no such element, `rightSum[i] = 0`.

Return an integer array `answer` of size `n` where `answer[i] = |leftSum[i] - rightSum[i]|`.

**Example 1:**

**Input:** nums = [10,4,8,3]
**Output:** [15,1,11,22]
**Explanation:** The array leftSum is [0,10,14,22] and the array rightSum is [15,11,3,0].
The array answer is [|0 - 15|,|10 - 11|,|14 - 3|,|22 - 0|] = [15,1,11,22].

**Example 2:**

**Input:** nums = [1]
**Output:** [0]
**Explanation:** The array leftSum is [0] and the array rightSum is [0].
The array answer is [|0 - 0|] = [0].

**Constraints:**

- `1 <= nums.length <= 1000`
- `1 <= nums[i] <= 105`

# 📘 **Left and Right Sum Differences – Comprehensive Solution**

---

## 📋 **Problem Statement**

Given a 0-indexed integer array `nums` of size `n`, create an answer array where each element `answer[i]` equals the **absolute difference** between:
- `leftSum[i]` = sum of elements **strictly to the left** of index `i`
- `rightSum[i]` = sum of elements **strictly to the right** of index `i`

If no elements exist on either side, the sum is 0.

---

## 🧠 **Core Insight: Efficient Calculation Using Total Sum**

### 🔑 **Key Observation**:

For any index `i`:
- `leftSum[i]` = sum of elements from index `0` to `i-1`
- `rightSum[i]` = `totalSum - leftSum[i] - nums[i]`

This allows us to compute both sums efficiently without nested loops.

### 💡 **Algorithm Strategy**:
1. Calculate `totalSum` of the entire array
2. Initialize `leftSum = 0`
3. For each index `i`:
   - Calculate `rightSum = totalSum - leftSum - nums[i]`
   - Compute `answer[i] = |leftSum - rightSum|`
   - Update `leftSum += nums[i]` for next iteration

---

## ✅ **Optimal Solution: Single Pass with Total Sum**

### 🧩 **Why This Works**:
- **O(1) extra space** (excluding output array)
- **O(n) time** – single pass through array
- **Handles all edge cases naturally**:
  - First element: `leftSum = 0`
  - Last element: `rightSum = 0`
  - Single element: both sums = 0

---

## 🚀 **Complete Implementation with Test Cases**

```java
public class LeftRightSumDifference {

    /**
     * Calculates the absolute difference between left and right sums for each index.
     * 
     * For each index i:
     * - leftSum[i] = sum of elements from 0 to i-1
     * - rightSum[i] = sum of elements from i+1 to n-1
     * - answer[i] = |leftSum[i] - rightSum[i]|
     * 
     * Approach:
     * 1. Calculate total sum of array
     * 2. Iterate through array, maintaining running left sum
     * 3. For each index i: rightSum = totalSum - leftSum - nums[i]
     * 4. Compute absolute difference and store in result
     * 
     * Time Complexity: O(n) - single pass
     * Space Complexity: O(1) extra space (excluding output array)
     * 
     * @param nums input array of integers
     * @return array containing absolute differences of left and right sums
     */
    public static int[] leftRightDifference(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        int n = nums.length;
        int[] answer = new int[n];
        
        // Calculate total sum of array
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        int leftSum = 0;
        
        for (int i = 0; i < n; i++) {
            // Right sum = total sum - left sum - current element
            int rightSum = totalSum - leftSum - nums[i];
            
            // Calculate absolute difference
            answer[i] = Math.abs(leftSum - rightSum);
            
            // Update left sum for next iteration
            leftSum += nums[i];
        }
        
        return answer;
    }

    // ==================== Comprehensive Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Left and Right Sum Differences ===\n");

        // Example 1: Standard case
        int[] test1 = {10, 4, 8, 3};
        System.out.println("Test 1: nums = " + java.util.Arrays.toString(test1));
        int[] result1 = leftRightDifference(test1);
        System.out.println("Output: " + java.util.Arrays.toString(result1)); 
        // Expected: [15, 1, 11, 22]
        // leftSum = [0, 10, 14, 22]
        // rightSum = [15, 11, 3, 0]
        // diff = [|0-15|, |10-11|, |14-3|, |22-0|] = [15, 1, 11, 22]

        // Example 2: Single element
        int[] test2 = {1};
        System.out.println("\nTest 2: nums = " + java.util.Arrays.toString(test2) + " (single element)");
        int[] result2 = leftRightDifference(test2);
        System.out.println("Output: " + java.util.Arrays.toString(result2)); 
        // Expected: [0]
        // leftSum = [0], rightSum = [0], diff = [0]

        // Edge Case 1: Two elements
        int[] test3 = {5, 3};
        System.out.println("\nTest 3: nums = " + java.util.Arrays.toString(test3) + " (two elements)");
        int[] result3 = leftRightDifference(test3);
        System.out.println("Output: " + java.util.Arrays.toString(result3)); 
        // Expected: [3, 5]
        // leftSum = [0, 5], rightSum = [3, 0], diff = [3, 5]

        // Edge Case 2: All same elements
        int[] test4 = {2, 2, 2, 2};
        System.out.println("\nTest 4: nums = " + java.util.Arrays.toString(test4) + " (all same)");
        int[] result4 = leftRightDifference(test4);
        System.out.println("Output: " + java.util.Arrays.toString(result4)); 
        // Expected: [6, 2, 2, 6]
        // leftSum = [0, 2, 4, 6]
        // rightSum = [6, 4, 2, 0]
        // diff = [6, 2, 2, 6]

        // Edge Case 3: Increasing sequence
        int[] test5 = {1, 2, 3, 4, 5};
        System.out.println("\nTest 5: nums = " + java.util.Arrays.toString(test5) + " (increasing)");
        int[] result5 = leftRightDifference(test5);
        System.out.println("Output: " + java.util.Arrays.toString(result5)); 
        // Expected: [14, 11, 6, 1, 10]
        // leftSum = [0, 1, 3, 6, 10]
        // rightSum = [14, 12, 9, 5, 0]
        // diff = [14, 11, 6, 1, 10]

        // Edge Case 4: Large numbers
        int[] test6 = {100000, 50000, 25000};
        System.out.println("\nTest 6: nums = " + java.util.Arrays.toString(test6) + " (large numbers)");
        int[] result6 = leftRightDifference(test6);
        System.out.println("Output: " + java.util.Arrays.toString(result6)); 
        // Expected: [75000, 75000, 150000]
        // leftSum = [0, 100000, 150000]
        // rightSum = [75000, 25000, 0]
        // diff = [75000, 75000, 150000]

        // Edge Case 5: Maximum constraint size
        int[] test7 = new int[1000];
        for (int i = 0; i < 1000; i++) {
            test7[i] = 1;
        }
        System.out.println("\nTest 7: nums = array of 1000 ones (max constraint)");
        int[] result7 = leftRightDifference(test7);
        System.out.println("Output first 5: " + java.util.Arrays.toString(java.util.Arrays.copyOf(result7, 5)));
        System.out.println("Output last 5: " + java.util.Arrays.toString(java.util.Arrays.copyOfRange(result7, 995, 1000)));
        // Expected: [999, 997, 995, ..., 995, 997, 999]
    }
}
```

---

## 🔍 **How It Works: Step-by-Step Example**

### **Example**: `nums = [10, 4, 8, 3]`

1. **Total Sum** = 10 + 4 + 8 + 3 = **25**

2. **Iteration**:
   - `i=0`: leftSum=0, rightSum=25-0-10=15 → |0-15|=**15**
   - `i=1`: leftSum=10, rightSum=25-10-4=11 → |10-11|=**1**
   - `i=2`: leftSum=14, rightSum=25-14-8=3 → |14-3|=**11**
   - `i=3`: leftSum=22, rightSum=25-22-3=0 → |22-0|=**22**

✅ Output = **[15, 1, 11, 22]**

---

## 📊 **Complexity Analysis**

| Aspect | Value |
|--------|-------|
| **Time Complexity** | O(n) – single pass through array |
| **Space Complexity** | O(1) extra space (output array doesn't count) |
| **Optimality** | **Optimal** – must examine each element at least once |

---

## ⚠️ **Common Mistakes to Avoid**

### ❌ **Mistake 1: Nested Loops (O(n²) Solution)**
```java
// Inefficient: recalculates left and right sums for each index
for (int i = 0; i < n; i++) {
    int leftSum = 0;
    for (int j = 0; j < i; j++) leftSum += nums[j];
    int rightSum = 0;
    for (int j = i + 1; j < n; j++) rightSum += nums[j];
    answer[i] = Math.abs(leftSum - rightSum);
}
```

### ❌ **Mistake 2: Incorrect Right Sum Calculation**
```java
// Wrong: doesn't subtract current element
int rightSum = totalSum - leftSum; // includes nums[i]
```

### ✅ **Correct Formula**:
- `rightSum = totalSum - leftSum - nums[i]`

---

## 💡 **Alternative Approach: Prefix Sum Array**

While not necessary, this approach is also valid and more explicit:

```java
public int[] leftRightDifference(int[] nums) {
    int n = nums.length;
    int[] prefix = new int[n + 1];
    
    // Build prefix sum array
    for (int i = 0; i < n; i++) {
        prefix[i + 1] = prefix[i] + nums[i];
    }
    
    int[] answer = new int[n];
    int totalSum = prefix[n];
    
    for (int i = 0; i < n; i++) {
        int leftSum = prefix[i];
        int rightSum = totalSum - prefix[i + 1];
        answer[i] = Math.abs(leftSum - rightSum);
    }
    
    return answer;
}
```

**Trade-off**: More explicit but uses O(n) extra space for prefix array.

---

## 🎯 **Key Takeaways**

1. **Total sum optimization** eliminates the need for nested loops
2. **Single pass algorithm** is both time and space efficient
3. **Edge cases are handled naturally** by the mathematical formula
4. **This pattern is fundamental** for many array problems involving left/right sums
5. **Understanding the relationship** between total sum, left sum, and right sum is crucial

This problem demonstrates how a **simple mathematical insight** can transform an O(n²) brute force solution into an elegant O(n) algorithm! 🚀