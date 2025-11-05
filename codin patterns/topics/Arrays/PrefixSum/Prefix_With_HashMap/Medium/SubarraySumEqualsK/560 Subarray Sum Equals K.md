Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [1,1,1], k = 2
Output: 2

Example 2:
Input: nums = [1,2,3], k = 3
Output: 2

Constraints:

    1 <= nums.length <= 2 * 104
    -1000 <= nums[i] <= 1000
    -107 <= k <= 107


# 📘 **LeetCode 560: Subarray Sum Equals K – Comprehensive Analysis**

---

## 📋 **1. Problem Statement**

Given an array of integers `nums` and an integer `k`, find the **total number of contiguous subarrays** whose sum equals exactly `k`.

### 🎯 **Key Requirements**:
- **Contiguous subarrays only** (not subsequences)
- **Non-empty subarrays**
- **Exact sum = k** (not ≥ or ≤)
- Handle **negative numbers**, **zeros**, and **large k values**

---

## 🧠 **2. Core Insight: Prefix Sum + HashMap**

### 🔑 **The Fundamental Idea**:

For any subarray from index `i` to `j`, the sum is:
```
sum(i, j) = prefix[j] - prefix[i-1]
```

We want: `sum(i, j) = k`

Rearranging: `prefix[j] - prefix[i-1] = k` → `prefix[i-1] = prefix[j] - k`

### 💡 **Key Insight**:
> For each position `j`, if we've seen the prefix sum `(prefix[j] - k)` before at some earlier position `i-1`, then the subarray from `i` to `j` has sum exactly `k`.

### 📊 **Example Walkthrough**:
```java
nums = [1, 1, 1], k = 2
prefix: [0, 1, 2, 3]  // prefix[0] = 0, prefix[1] = 1, etc.

j=0: prefix=1, need 1-2=-1 → not seen → count=0
j=1: prefix=2, need 2-2=0 → seen once (prefix[0]) → count=1  
j=2: prefix=3, need 3-2=1 → seen once (prefix[1]) → count=2

Total = 2 ✅
```

---

## ⚠️ **3. Edge Cases Analysis**

| Edge Case | Description | Why It Matters |
|-----------|-------------|----------------|
| **Negative Numbers** | `nums = [-1, -1, 1], k = -1` | Multiple subarrays can sum to same value |
| **Zero Values** | `nums = [0, 0, 0], k = 0` | Every subarray sums to 0 → answer = 6 |
| **k = 0** | Special case requiring careful handling | Need to count subarrays with sum 0 |
| **Single Element** | `nums = [5], k = 5` | Should return 1 |
| **Large k** | `k = 10^7` | May not be achievable, return 0 |
| **Empty Subarray** | Not allowed per problem | But we use prefix[0] = 0 for calculation |

---

## 📏 **4. Constraint Analysis**

### Given Constraints:
- **Array length**: `1 ≤ nums.length ≤ 2×10⁴`
- **Element values**: `-1000 ≤ nums[i] ≤ 1000`
- **Target k**: `-10⁷ ≤ k ≤ 10⁷`

### 📊 **Implications**:
| Constraint | Impact |
|------------|--------|
| **Negative numbers allowed** | Prefix sums can decrease → can't use sliding window |
| **Large array size** | O(n²) brute force = 4×10⁸ operations → **too slow** |
| **Large k range** | Must use HashMap, not array indexing |
| **Multiple occurrences** | Must count frequency of prefix sums |

### 🎯 **Optimal Complexity**:
- **Time**: O(n) – single pass with HashMap
- **Space**: O(n) – for HashMap storing prefix sums

---

## 🧩 **5. Solution Approaches**

### 🔹 **Approach 1: Brute Force (O(n²))**

#### **Logic**:
- For each starting index `i`, compute sum for all ending indices `j ≥ i`
- Count when sum equals `k`

#### **Code**:
```java
public int subarraySum(int[] nums, int k) {
    int count = 0;
    for (int i = 0; i < nums.length; i++) {
        int sum = 0;
        for (int j = i; j < nums.length; j++) {
            sum += nums[j];
            if (sum == k) count++;
        }
    }
    return count;
}
```

#### **Complexity**:
- **Time**: O(n²)
- **Space**: O(1)

#### **Analysis**:
- **Fails for large inputs** (n=20,000 → 200M operations)
- **Not acceptable** for interviews

---

### 🔹 **Approach 2: Prefix Sum + HashMap (Optimal)**

#### **Logic**:
1. Initialize HashMap with `{0: 1}` (empty prefix sum)
2. Maintain running prefix sum
3. For each element:
   - Check if `(prefixSum - k)` exists in HashMap
   - Add its frequency to result
   - Update HashMap with current prefix sum

#### **Code**:
```java
import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1); // empty prefix sum
        
        int prefixSum = 0;
        int count = 0;
        
        for (int num : nums) {
            prefixSum += num;
            
            // Check if (prefixSum - k) has been seen before
            if (prefixCount.containsKey(prefixSum - k)) {
                count += prefixCount.get(prefixSum - k);
            }
            
            // Update frequency of current prefix sum
            prefixCount.put(prefixSum, prefixCount.getOrDefault(prefixSum, 0) + 1);
        }
        
        return count;
    }
}
```

#### **Complexity**:
- **Time**: O(n) – single pass
- **Space**: O(n) – HashMap may store up to n+1 entries

#### **Why It Works**:
- Handles **negative numbers** correctly (prefix sums can repeat)
- Counts **all possible subarrays** ending at current position
- **HashMap lookup** is O(1) average case

---

### 🔹 **Approach 3: Sliding Window (Incorrect for This Problem)**

#### **Why It Fails**:
- Sliding window requires **monotonic behavior** (sum only increases or decreases)
- With **negative numbers**, expanding window can **decrease** the sum
- Cannot guarantee that shrinking from left will find all valid windows

#### **Example That Breaks Sliding Window**:
```java
nums = [1, -1, 0], k = 0
Valid subarrays: [1,-1], [-1,0], [0], [1,-1,0] → total = 4
```

Sliding window would miss some of these because the sum doesn't behave monotonically.

---

## 📊 **6. Approach Comparison**

| Approach | Time | Space | Handles Negatives? | Interview Ready? |
|----------|------|-------|-------------------|------------------|
| **Brute Force** | O(n²) | O(1) | ✅ Yes | ❌ No |
| **Prefix Sum + HashMap** | **O(n)** | **O(n)** | ✅ Yes | ✅ **Yes** |
| **Sliding Window** | O(n) | O(1) | ❌ **No** | ❌ No |

---

## 🔍 **7. Deep Dive: How the HashMap Solution Works**

### 🧪 **Step-by-Step for `nums = [1, 1, 1], k = 2`**:

| Step | num | prefixSum | Need (prefixSum - k) | Count from Map | Total Count | Map State |
|------|-----|-----------|---------------------|----------------|-------------|-----------|
| Init | - | 0 | - | - | 0 | `{0:1}` |
| 1 | 1 | 1 | -1 | 0 | 0 | `{0:1, 1:1}` |
| 2 | 1 | 2 | 0 | 1 | 1 | `{0:1, 1:1, 2:1}` |
| 3 | 1 | 3 | 1 | 1 | 2 | `{0:1, 1:1, 2:1, 3:1}` |

✅ Final result = **2**

### 🧪 **Step-by-Step for `nums = [1, -1, 0], k = 0`**:

| Step | num | prefixSum | Need | Count | Total | Map |
|------|-----|-----------|------|-------|-------|-----|
| Init | - | 0 | - | - | 0 | `{0:1}` |
| 1 | 1 | 1 | 1 | 0 | 0 | `{0:1, 1:1}` |
| 2 | -1 | 0 | 0 | 1 | 1 | `{0:2, 1:1}` |
| 3 | 0 | 0 | 0 | 2 | 3 | `{0:3, 1:1}` |

Wait, expected answer is **4**, but we got **3**?

Let's list all valid subarrays:
1. `[1, -1]` → sum = 0
2. `[-1, 0]` → sum = -1 ❌
3. `[0]` → sum = 0
4. `[1, -1, 0]` → sum = 0

Actually, valid subarrays are:
- `[1, -1]` (indices 0-1)
- `[0]` (index 2)  
- `[1, -1, 0]` (indices 0-2)

That's **3**, not 4! Let me recalculate:

`nums = [1, -1, 0]`
- `[0,0]`: 1 ≠ 0
- `[0,1]`: 1 + (-1) = 0 ✅
- `[0,2]`: 1 + (-1) + 0 = 0 ✅
- `[1,1]`: -1 ≠ 0
- `[1,2]`: -1 + 0 = -1 ≠ 0
- `[2,2]`: 0 ✅

Total = **3** ✅

The algorithm is correct!

---

## 🧪 **8. Test Cases for Validation**

```java
// Example 1
assertEquals(2, subarraySum(new int[]{1,1,1}, 2));

// Example 2  
assertEquals(2, subarraySum(new int[]{1,2,3}, 3));

// Negative numbers
assertEquals(2, subarraySum(new int[]{1,-1,0}, 0));

// All zeros, k=0
assertEquals(6, subarraySum(new int[]{0,0,0}, 0)); // 3+2+1=6

// Single element match
assertEquals(1, subarraySum(new int[]{5}, 5));

// Single element no match
assertEquals(0, subarraySum(new int[]{5}, 3));

// Large k
assertEquals(0, subarraySum(new int[]{1,2,3}, 100));
```

---

## 💡 **9. Common Mistakes to Avoid**

### ❌ **Mistake 1: Forgetting to Initialize HashMap with {0: 1}**
```java
// Wrong: misses subarrays starting from index 0
Map<Integer, Integer> map = new HashMap<>();
```

### ❌ **Mistake 2: Updating HashMap Before Checking**
```java
// Wrong order
map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
if (map.containsKey(prefixSum - k)) { ... }
```

### ❌ **Mistake 3: Using Array Instead of HashMap for Large k**
```java
// k can be ±10^7, so array indexing impossible
int[] count = new int[20000001]; // Memory inefficient and may not work
```

### ✅ **Correct Order**:
1. Calculate current prefix sum
2. **Check** for `(prefixSum - k)` in HashMap
3. **Update** HashMap with current prefix sum

---

## 🚀 **10. Connection to Advanced Concepts**

### 🔗 **Related Problems**:
- **LeetCode 974**: Subarray Sums Divisible by K (uses modulo arithmetic)
- **LeetCode 523**: Continuous Subarray Sum (uses modulo with k)
- **LeetCode 1524**: Number of Sub-arrays With Odd Sum
- **LeetCode 1590**: Make Sum Divisible by P

### 📊 **Real-World Applications**:
- **Financial analysis**: Finding periods with specific profit/loss
- **Signal processing**: Detecting patterns in time series data
- **Database queries**: Range sum queries with constraints
- **Algorithm design**: Foundation for more complex prefix sum problems

---

## 💎 **Final Implementation (Production Ready)**

```java
import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    /**
     * Counts the number of contiguous subarrays with sum exactly equal to k.
     * 
     * Algorithm: Prefix Sum + HashMap
     * - Time: O(n)
     * - Space: O(n)
     * 
     * Key Insight: For subarray [i, j] to have sum k,
     * prefix[j] - prefix[i-1] = k → prefix[i-1] = prefix[j] - k
     */
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1); // Empty prefix sum
        
        int prefixSum = 0;
        int count = 0;
        
        for (int num : nums) {
            prefixSum += num;
            
            // Check if we've seen (prefixSum - k) before
            count += prefixCount.getOrDefault(prefixSum - k, 0);
            
            // Update frequency of current prefix sum
            prefixCount.put(prefixSum, prefixCount.getOrDefault(prefixSum, 0) + 1);
        }
        
        return count;
    }
}
```

---

## 🎯 **Key Takeaways**

1. **Prefix sum + HashMap** is the **only efficient solution** for this problem
2. **Always initialize HashMap with `{0: 1}`** to handle subarrays starting at index 0
3. **Check before updating** the HashMap to avoid counting the current prefix
4. **This pattern is fundamental** for many subarray sum problems
5. **Sliding window doesn't work** with negative numbers

This problem teaches a crucial lesson: when you need to find **exact sums** in arrays with **negative numbers**, **prefix sums with HashMap** is your go-to technique! 🚀