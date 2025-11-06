Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.

A subarray is a contiguous part of an array.

Example 1:

Input: nums = [4,5,0,-2,-3,1], k = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by k = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]

Example 2:

Input: nums = [5], k = 9
Output: 0

Constraints:

    1 <= nums.length <= 3 * 104
    -104 <= nums[i] <= 104
    2 <= k <= 104


# 📘 **974. Subarray Sums Divisible by K – Comprehensive Solution**

---

## 📋 **Problem Statement**

Given an integer array `nums` and an integer `k`, find the **number of non-empty contiguous subarrays** whose sum is **divisible by `k`**.

### 🎯 **Key Requirements**:
- **Contiguous subarrays only** (not subsequences)
- **Non-empty subarrays**
- **Sum % k == 0** (handles negative numbers correctly)
- Return the **total count** of such subarrays

---

## 🧠 **Core Insight: Prefix Sum + Modular Arithmetic**

### 🔑 **The Fundamental Idea**:

For any subarray from index `i` to `j`, the sum is:
```
sum(i, j) = prefix[j] - prefix[i-1]
```

We want: `sum(i, j) % k == 0`

This means: `(prefix[j] - prefix[i-1]) % k == 0`

Which implies: `prefix[j] % k == prefix[i-1] % k`

### 💡 **Key Insight**:
> If two prefix sums have the **same remainder when divided by k**, then the subarray between them has a sum divisible by k.

### ⚠️ **Critical Detail: Negative Modulo Handling**

In Java, negative numbers return negative remainders:
- `-1 % 5 = -1`, but we want `4` (positive equivalent)

So we need to **normalize modulo** to always be positive:
```java
int mod = prefixSum % k;
if (mod < 0) mod += k;
```

---

## 📊 **Example Walkthrough**

### **Example**: `nums = [4,5,0,-2,-3,1], k = 5`

| Step | num | prefixSum | mod (normalized) | Count from Map | Total Count | Map State |
|------|-----|-----------|------------------|----------------|-------------|-----------|
| Init | - | 0 | 0 | - | 0 | `{0:1}` |
| 1 | 4 | 4 | 4 | 0 | 0 | `{0:1, 4:1}` |
| 2 | 5 | 9 | 4 | 1 | 1 | `{0:1, 4:2}` |
| 3 | 0 | 9 | 4 | 2 | 3 | `{0:1, 4:3}` |
| 4 | -2 | 7 | 2 | 0 | 3 | `{0:1, 4:3, 2:1}` |
| 5 | -3 | 4 | 4 | 3 | 6 | `{0:1, 4:4, 2:1}` |
| 6 | 1 | 5 | 0 | 1 | 7 | `{0:2, 4:4, 2:1}` |

✅ Final result = **7**

**Why this works**:
- At step 2: prefix=9, mod=4 → seen once before → subarray [5] sums to 5 ✅
- At step 3: prefix=9, mod=4 → seen twice before → subarrays [5,0] and [0] ✅
- At step 5: prefix=4, mod=4 → seen 3 times before → 3 more valid subarrays ✅
- At step 6: prefix=5, mod=0 → seen once (initial 0) → entire array sums to 5 ✅

---

## ✅ **Optimal Solution: Prefix Sum + HashMap with Modulo**

### 🧩 **Algorithm Steps**:
1. Initialize HashMap with `{0: 1}` (empty prefix sum has remainder 0)
2. Maintain running prefix sum
3. For each element:
   - Update prefix sum
   - Calculate normalized modulo: `(prefixSum % k + k) % k`
   - If this modulo has been seen before, add its frequency to result
   - Update HashMap with current modulo frequency

---

## 🚀 **Complete Implementation with Test Cases**

```java
import java.util.HashMap;
import java.util.Map;

public class SubarraySumsDivisibleByK {

    /**
     * Counts the number of contiguous subarrays with sum divisible by k.
     * 
     * Approach: Prefix Sum + Modular Arithmetic + HashMap
     * 
     * Key Insights:
     * 1. If prefix[i] % k == prefix[j] % k, then subarray (i+1 to j) is divisible by k
     * 2. Handle negative modulo by normalizing to positive range [0, k-1]
     * 3. Use HashMap to count frequency of each modulo remainder
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(min(n, k)) - at most k different remainders
     * 
     * @param nums input array of integers
     * @param k divisor (k >= 2)
     * @return number of subarrays with sum divisible by k
     */
    public static int subarraysDivByK(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Map<Integer, Integer> modCount = new HashMap<>();
        modCount.put(0, 1); // empty prefix sum has remainder 0
        
        int prefixSum = 0;
        int count = 0;
        
        for (int num : nums) {
            prefixSum += num;
            
            // Normalize modulo to handle negative numbers
            int mod = prefixSum % k;
            if (mod < 0) {
                mod += k;
            }
            // Alternative one-liner: int mod = (prefixSum % k + k) % k;
            
            // If this modulo has been seen before, add its frequency
            if (modCount.containsKey(mod)) {
                count += modCount.get(mod);
            }
            
            // Update frequency of current modulo
            modCount.put(mod, modCount.getOrDefault(mod, 0) + 1);
        }
        
        return count;
    }

    // ==================== Comprehensive Test Cases ====================
    public static void main(String[] args) {
        System.out.println("=== Subarray Sums Divisible by K ===\n");

        // Example 1: Standard case
        int[] test1 = {4, 5, 0, -2, -3, 1};
        int k1 = 5;
        System.out.println("Test 1: nums = " + java.util.Arrays.toString(test1) + ", k = " + k1);
        System.out.println("Output: " + subarraysDivByK(test1, k1)); // Expected: 7

        // Example 2: No valid subarrays
        int[] test2 = {5};
        int k2 = 9;
        System.out.println("\nTest 2: nums = " + java.util.Arrays.toString(test2) + ", k = " + k2);
        System.out.println("Output: " + subarraysDivByK(test2, k2)); // Expected: 0

        // Edge Case 1: All zeros
        int[] test3 = {0, 0, 0};
        int k3 = 1;
        System.out.println("\nTest 3: nums = " + java.util.Arrays.toString(test3) + ", k = " + k3);
        System.out.println("Output: " + subarraysDivByK(test3, k3)); // Expected: 6 (all subarrays)

        // Edge Case 2: Negative numbers with positive k
        int[] test4 = {-1, 2, 9};
        int k4 = 2;
        System.out.println("\nTest 4: nums = " + java.util.Arrays.toString(test4) + ", k = " + k4);
        System.out.println("Output: " + subarraysDivByK(test4, k4)); // Expected: 2

        // Edge Case 3: Single element divisible by k
        int[] test5 = {10};
        int k5 = 5;
        System.out.println("\nTest 5: nums = " + java.util.Arrays.toString(test5) + ", k = " + k5);
        System.out.println("Output: " + subarraysDivByK(test5, k5)); // Expected: 1

        // Edge Case 4: k = 2 (even/odd sums)
        int[] test6 = {1, 2, 3, 4};
        int k6 = 2;
        System.out.println("\nTest 6: nums = " + java.util.Arrays.toString(test6) + ", k = " + k6);
        System.out.println("Output: " + subarraysDivByK(test6, k6)); // Expected: 3

        // Edge Case 5: Large array with many valid subarrays
        int[] test7 = {1, 1, 1, 1, 1};
        int k7 = 1;
        System.out.println("\nTest 7: nums = " + java.util.Arrays.toString(test7) + ", k = " + k7);
        System.out.println("Output: " + subarraysDivByK(test7, k7)); // Expected: 15 (all subarrays)

        // Edge Case 6: Alternating positive/negative
        int[] test8 = {2, -2, 2, -2, 2};
        int k8 = 4;
        System.out.println("\nTest 8: nums = " + java.util.Arrays.toString(test8) + ", k = " + k8);
        System.out.println("Output: " + subarraysDivByK(test8, k8)); // Expected: 3
    }
}
```

---

## 🔍 **Why Negative Modulo Handling is Critical**

### 🧪 **Without Normalization**:
```java
// Java's default modulo for negative numbers
-1 % 5 = -1
-2 % 5 = -2
// But mathematically, we want:
-1 ≡ 4 (mod 5)
-2 ≡ 3 (mod 5)
```

### ✅ **Correct Normalization**:
```java
int mod = prefixSum % k;
if (mod < 0) mod += k;
// or
int mod = (prefixSum % k + k) % k;
```

This ensures all remainders are in the range `[0, k-1]`, which is essential for correct HashMap lookups.

---

## 📊 **Complexity Analysis**

| Aspect | Value |
|--------|-------|
| **Time Complexity** | O(n) – single pass through array |
| **Space Complexity** | O(min(n, k)) – at most k different remainders possible |
| **Optimality** | **Optimal** – must examine each element at least once |

---

## ⚠️ **Common Mistakes to Avoid**

### ❌ **Mistake 1: Forgetting Negative Modulo Handling**
```java
// Wrong: doesn't handle negative remainders
int mod = prefixSum % k;
```

### ❌ **Mistake 2: Not Initializing HashMap with {0: 1}**
```java
// Wrong: misses subarrays starting from index 0
Map<Integer, Integer> map = new HashMap<>();
```

### ❌ **Mistake 3: Updating HashMap Before Checking**
```java
// Wrong order
map.put(mod, map.getOrDefault(mod, 0) + 1);
if (map.containsKey(mod)) count += map.get(mod) - 1; // complicated
```

### ✅ **Correct Order**:
1. Calculate current modulo
2. **Check** for existing count in HashMap
3. **Update** HashMap with current modulo

---

## 💡 **Connection to Related Problems**

| Problem | Connection |
|---------|------------|
| **LeetCode 560**: Subarray Sum Equals K | Same prefix sum + HashMap pattern |
| **LeetCode 523**: Continuous Subarray Sum | Uses similar modulo arithmetic |
| **LeetCode 1524**: Number of Sub-arrays With Odd Sum | Special case of k=2 |
| **LeetCode 1590**: Make Sum Divisible by P | Inverse problem |

---

## 🎯 **Key Takeaways**

1. **Modular arithmetic** transforms divisibility problems into equality problems
2. **Normalize negative modulo** to positive range for correct HashMap operations
3. **Initialize with {0: 1}** to handle subarrays starting from index 0
4. **This pattern is fundamental** for many subarray sum problems with modular constraints
5. **Space is bounded by k**, not n, because there are only k possible remainders

This problem demonstrates the **power of mathematical transformation** – by using modular arithmetic, we reduce a complex divisibility problem to a simple counting problem with prefix sums! 🚀