Given an array of integers nums, calculate the pivot index of this array.

The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.

If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.

Return the leftmost pivot index. If no such index exists, return -1.
Example 1:

Input: nums = [1,7,3,6,5,6]
Output: 3
Explanation:
The pivot index is 3.
Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
Right sum = nums[4] + nums[5] = 5 + 6 = 11

Example 2:

Input: nums = [1,2,3]
Output: -1
Explanation:
There is no index that satisfies the conditions in the problem statement.

Example 3:

Input: nums = [2,1,-1]
Output: 0
Explanation:
The pivot index is 0.
Left sum = 0 (no elements to the left of index 0)
Right sum = nums[1] + nums[2] = 1 + -1 = 0

Constraints:

    1 <= nums.length <= 104
    -1000 <= nums[i] <= 1000


# 📘 **724. Find Pivot Index – Comprehensive Solution**

---

## 📋 **Problem Statement**

Given an array of integers `nums`, find the **pivot index** — the index where the sum of all elements **strictly to the left** equals the sum of all elements **strictly to the right**.

### 🎯 **Key Requirements**:
- **Left sum** = sum of elements from index `0` to `i-1`
- **Right sum** = sum of elements from index `i+1` to `n-1`
- **Edge cases**: 
  - Leftmost index: left sum = 0
  - Rightmost index: right sum = 0
- Return the **leftmost pivot index** if multiple exist
- Return **-1** if no pivot index exists

---

## 🧠 **Core Insight: Total Sum Optimization**

### 🔑 **The Key Observation**:

For any index `i`:
- `leftSum = sum(0 to i-1)`
- `rightSum = totalSum - leftSum - nums[i]`

We want: `leftSum == rightSum`

Substituting:  
`leftSum = totalSum - leftSum - nums[i]`  
`2 * leftSum = totalSum - nums[i]`  
`leftSum = (totalSum - nums[i]) / 2`

But even simpler:  
**At pivot index `i`: `leftSum == totalSum - leftSum - nums[i]`**

### 💡 **Algorithm Strategy**:
1. Calculate `totalSum` of the array
2. Iterate through array, maintaining `leftSum`
3. For each index `i`, calculate `rightSum = totalSum - leftSum - nums[i]`
4. If `leftSum == rightSum`, return `i`
5. Update `leftSum += nums[i]` for next iteration

---

## ✅ **Optimal Solution: Single Pass with Total Sum**

### 🧩 **Why This Works**:
- **O(1) space** – no extra arrays needed
- **O(n) time** – two passes (one for total sum, one for pivot check)
- **Handles all edge cases naturally**:
  - Left edge: `leftSum = 0`
  - Right edge: `rightSum = 0`
  - Negative numbers: works correctly

---

## 🚀 **Complete Implementation with Test Cases**

```java
public class FindPivotIndex {

    /**
     * Finds the leftmost pivot index in the array.
     * 
     * Pivot index definition: sum of elements strictly to the left 
     * equals sum of elements strictly to the right.
     * 
     * Approach: 
     * 1. Calculate total sum of array
     * 2. Iterate through array, maintaining left sum
     * 3. For each index i: rightSum = totalSum - leftSum - nums[i]
     * 4. If leftSum == rightSum, return i
     * 
     * Time Complexity: O(n) - two passes through array
     * Space Complexity: O(1) - constant extra space
     * 
     * @param nums input array of integers
     * @return leftmost pivot index, or -1 if none exists
     */
    public static int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        // Calculate total sum of array
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        int leftSum = 0;
        
        // Check each index as potential pivot
        for (int i = 0; i < nums.length; i++) {
            // Right sum = total sum - left sum - current element
            int rightSum = totalSum - leftSum - nums[i];
            
            if (leftSum == rightSum) {
                return i;
            }
            
            // Update left sum for next iteration
            leftSum += nums[i];
        }
        
        return -1;
    }

    // ==================== Comprehensive Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Find Pivot Index ===\n");

        // Example 1: Standard case
        int[] test1 = {1, 7, 3, 6, 5, 6};
        System.out.println("Test 1: nums = " + java.util.Arrays.toString(test1));
        System.out.println("Output: " + pivotIndex(test1)); // Expected: 3

        // Example 2: No pivot index
        int[] test2 = {1, 2, 3};
        System.out.println("\nTest 2: nums = " + java.util.Arrays.toString(test2));
        System.out.println("Output: " + pivotIndex(test2)); // Expected: -1

        // Example 3: Pivot at left edge
        int[] test3 = {2, 1, -1};
        System.out.println("\nTest 3: nums = " + java.util.Arrays.toString(test3));
        System.out.println("Output: " + pivotIndex(test3)); // Expected: 0

        // Edge Case 1: Single element
        int[] test4 = {1};
        System.out.println("\nTest 4: nums = " + java.util.Arrays.toString(test4) + " (single element)");
        System.out.println("Output: " + pivotIndex(test4)); // Expected: 0 (left=0, right=0)

        // Edge Case 2: All zeros
        int[] test5 = {0, 0, 0, 0};
        System.out.println("\nTest 5: nums = " + java.util.Arrays.toString(test5) + " (all zeros)");
        System.out.println("Output: " + pivotIndex(test5)); // Expected: 0 (leftmost)

        // Edge Case 3: Pivot at right edge
        int[] test6 = {-1, 1, 0};
        System.out.println("\nTest 6: nums = " + java.util.Arrays.toString(test6) + " (pivot at right edge)");
        System.out.println("Output: " + pivotIndex(test6)); // Expected: 2 (left=-1+1=0, right=0)

        // Edge Case 4: Negative numbers
        int[] test7 = {-1, -1, 0, 1, 1};
        System.out.println("\nTest 7: nums = " + java.util.Arrays.toString(test7) + " (negative numbers)");
        System.out.println("Output: " + pivotIndex(test7)); // Expected: 2

        // Edge Case 5: Large array with pivot in middle
        int[] test8 = {1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1};
        System.out.println("\nTest 8: nums = " + java.util.Arrays.toString(test8));
        System.out.println("Output: " + pivotIndex(test8)); // Expected: 5

        // Edge Case 6: No pivot in large array
        int[] test9 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("\nTest 9: nums = " + java.util.Arrays.toString(test9) + " (no pivot)");
        System.out.println("Output: " + pivotIndex(test9)); // Expected: -1

        // Edge Case 7: Two potential pivots (return leftmost)
        int[] test10 = {0, 1, -1, 0, 0};
        System.out.println("\nTest 10: nums = " + java.util.Arrays.toString(test10) + " (multiple pivots)");
        System.out.println("Output: " + pivotIndex(test10)); // Expected: 0 (not 3)
    }
}
```

---

## 🔍 **How It Works: Step-by-Step Example**

### **Example**: `nums = [1, 7, 3, 6, 5, 6]`

1. **Total Sum** = 1 + 7 + 3 + 6 + 5 + 6 = **28**

2. **Iteration**:
   - `i=0`: leftSum=0, rightSum=28-0-1=27 → 0≠27 ❌
   - `i=1`: leftSum=1, rightSum=28-1-7=20 → 1≠20 ❌  
   - `i=2`: leftSum=8, rightSum=28-8-3=17 → 8≠17 ❌
   - `i=3`: leftSum=11, rightSum=28-11-6=11 → 11=11 ✅
   
✅ Return **3**

---

## 📊 **Complexity Analysis**

| Aspect | Value |
|--------|-------|
| **Time Complexity** | O(n) – two passes (total sum + pivot check) |
| **Space Complexity** | O(1) – only a few variables used |
| **Optimality** | **Optimal** – must examine each element at least once |

---

## ⚠️ **Common Mistakes to Avoid**

### ❌ **Mistake 1: Including Current Element in Left/Right Sum**
```java
// Wrong: includes nums[i] in leftSum
leftSum += nums[i];
if (leftSum == rightSum) return i;
```

### ❌ **Mistake 2: Not Handling Edge Cases Properly**
```java
// Wrong: assumes array has at least 2 elements
for (int i = 1; i < nums.length - 1; i++) // misses edges
```

### ❌ **Mistake 3: Using Extra Space Unnecessarily**
```java
// Overkill: creates prefix sum array
int[] prefix = new int[n+1]; // O(n) space when O(1) is possible
```

### ✅ **Correct Approach**:
- **Calculate total sum first**
- **Use running leftSum**
- **Compute rightSum on the fly**
- **Check all indices including edges**

---

## 💡 **Alternative Approach: Prefix Sum Array**

While not optimal in space, this approach is also valid:

```java
public int pivotIndex(int[] nums) {
    int n = nums.length;
    int[] prefix = new int[n + 1];
    
    for (int i = 0; i < n; i++) {
        prefix[i + 1] = prefix[i] + nums[i];
    }
    
    for (int i = 0; i < n; i++) {
        int leftSum = prefix[i];
        int rightSum = prefix[n] - prefix[i + 1];
        if (leftSum == rightSum) {
            return i;
        }
    }
    
    return -1;
}
```

**Trade-off**: O(n) space vs O(1) space, same time complexity.

---

## 🎯 **Key Takeaways**

1. **Total sum optimization** is the key insight – avoids extra space
2. **Handle edge cases naturally** by checking all indices (0 to n-1)
3. **Leftmost requirement** is satisfied by returning on first match
4. **Works with negative numbers** because we're using actual sums
5. **This pattern appears in many array problems** – understanding total sum vs partial sum is fundamental

This problem demonstrates how a **simple mathematical insight** can transform what might seem like a complex problem into an elegant O(n) solution! 🚀