Given an integer array nums, handle multiple queries of the following type:

    Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.

Implement the NumArray class:

    NumArray(int[] nums) Initializes the object with the integer array nums.
    int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).


Example 1:

Input
["NumArray", "sumRange", "sumRange", "sumRange"]
[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
Output
[null, 1, -1, -3]

Explanation
NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3


Constraints:

    1 <= nums.length <= 104
    -105 <= nums[i] <= 105
    0 <= left <= right < nums.length
    At most 104 calls will be made to sumRange.


## My Approach  - without prefix array : dummy [0]

> Note : Still read the bottom docs!!!

```java
class NumArray {
    private static int[] nums;
    
        public NumArray(int[] nums) {
            for(int i=1;i<nums.length;i++){
                nums[i] += nums[i-1];
            }
            this.nums = nums;
        }

        public int sumRange(int left, int right) {
            if(left==0) return this.nums[right];
            return this.nums[right] - this.nums[left-1];
        }
}
```
# 📘 **LeetCode 303: Range Sum Query - Immutable – Comprehensive Analysis**

---

## 📋 **1. Problem Statement**

Design a data structure that efficiently answers **multiple range sum queries** on a **static (immutable) array**.

### 🎯 **Requirements**:
- **Constructor**: `NumArray(int[] nums)` – preprocess the array
- **Query method**: `sumRange(int left, int right)` – return sum of elements from `left` to `right` (inclusive)

### 📌 **Key Constraint**:
- The array **does not change** after initialization (immutable)
- **Multiple queries** will be made (up to 10⁴)
- Need **efficient query responses**

---

## 🎯 **2. What the Problem Requires**

### ✅ **Core Requirements**:
1. **Preprocessing**: Do work once during construction to enable fast queries
2. **Query Efficiency**: Each `sumRange` call should be **faster than O(n)**
3. **Correctness**: Handle all edge cases (single element, full array, negative numbers)
4. **Memory Efficiency**: Reasonable space usage for preprocessing

### 🧠 **Key Insight**:
> Since the array is **immutable**, we can **precompute** information to answer queries in **O(1) time**.

---

## ⚠️ **3. Edge Cases Analysis**

| Edge Case | Description | Expected Behavior |
|-----------|-------------|-------------------|
| **Single Element Array** | `nums = [5]`, query `[0,0]` | Return `5` |
| **Full Array Query** | `left = 0, right = n-1` | Return total sum |
| **Single Element Query** | `left = right = i` | Return `nums[i]` |
| **Negative Numbers** | Mixed positive/negative values | Handle correctly |
| **Zero Values** | Array contains zeros | Handle correctly |
| **Large Range** | Query covers most of array | Efficient response |
| **Adjacent Indices** | `right = left + 1` | Return sum of two elements |

---

## 📏 **4. Constraint Analysis**

### Given Constraints:
- **Array length**: `1 ≤ nums.length ≤ 10⁴`
- **Element values**: `-10⁵ ≤ nums[i] ≤ 10⁵`
- **Query count**: Up to `10⁴` calls to `sumRange`
- **Query bounds**: `0 ≤ left ≤ right < nums.length`

### 📊 **Implications**:
| Constraint | Impact on Solution |
|------------|-------------------|
| **Multiple queries (10⁴)** | O(n) per query = 10⁸ operations → **too slow** |
| **Static array** | Can preprocess safely |
| **Large element values** | Maximum sum = `10⁴ × 10⁵ = 10⁹` → fits in `int` |
| **Reasonable array size** | O(n) preprocessing space is acceptable |

### 🎯 **Optimal Complexity Target**:
- **Preprocessing Time**: O(n)
- **Query Time**: O(1)
- **Space**: O(n) for preprocessing

---

## 🧩 **5. Solution Approaches**

### 🔹 **Approach 1: Naive Approach (Inefficient)**

#### **Logic**:
- Store original array
- For each query, iterate from `left` to `right` and sum elements

#### **Code**:
```java
class NumArray {
    private int[] nums;

    public NumArray(int[] nums) {
        this.nums = nums;
    }

    public int sumRange(int left, int right) {
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += nums[i];
        }
        return sum;
    }
}
```

#### **Complexity**:
- **Preprocessing Time**: O(1)
- **Query Time**: O(n) per query
- **Space**: O(1) extra

#### **Analysis**:
- **Total time for 10⁴ queries**: `10⁴ × 10⁴ = 10⁸` operations
- **In Java**: ~1 second (borderline acceptable, but not optimal)
- **Fails interview expectations** for efficiency

---

### 🔹 **Approach 2: Prefix Sum (Optimal)**

#### **Logic**:
- Precompute **prefix sum array** during construction
- Use the **range sum formula**: `sum[left...right] = prefix[right+1] - prefix[left]`

#### **Why `prefix[right+1]`?**
- Define `prefix[0] = 0`
- `prefix[i] = nums[0] + nums[1] + ... + nums[i-1]`
- Then: `sum[left...right] = prefix[right+1] - prefix[left]`

#### **Example**:
```java
nums = [-2, 0, 3, -5, 2, -1]
prefix = [0, -2, -2, 1, -4, -2, -3]

Query [0,2]: prefix[3] - prefix[0] = 1 - 0 = 1 ✅
Query [2,5]: prefix[6] - prefix[2] = -3 - (-2) = -1 ✅
Query [0,5]: prefix[6] - prefix[0] = -3 - 0 = -3 ✅
```

#### **Code**:
```java
class NumArray {
    private int[] prefix;

    public NumArray(int[] nums) {
        prefix = new int[nums.length + 1];
        prefix[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return prefix[right + 1] - prefix[left];
    }
}
```

#### **Complexity**:
- **Preprocessing Time**: O(n)
- **Query Time**: O(1)
- **Space**: O(n) for prefix array

#### **Pros**:
- ✅ **Optimal query time**
- ✅ **Simple and clean**
- ✅ **Handles all edge cases naturally**
- ✅ **Standard solution for immutable range queries**

#### **Cons**:
- Uses O(n) extra space (but this is acceptable)

---

### 🔹 **Approach 3: Segment Tree (Overkill)**

#### **Logic**:
- Build a segment tree for range queries
- Each query takes O(log n) time

#### **Code** (Conceptual):
```java
// Segment tree implementation (not shown for brevity)
```

#### **Complexity**:
- **Preprocessing Time**: O(n)
- **Query Time**: O(log n)
- **Space**: O(n)

#### **Analysis**:
- **Unnecessary complexity** for this problem
- Segment trees are useful when array is **mutable**
- For **immutable arrays**, prefix sum is simpler and faster

---

## 📊 **6. Approach Comparison**

| Approach | Preprocessing Time | Query Time | Space | Best Use Case |
|----------|-------------------|------------|-------|---------------|
| **Naive** | O(1) | O(n) | O(1) | Single query, small arrays |
| **Prefix Sum** | O(n) | **O(1)** | O(n) | **Multiple queries, immutable array** |
| **Segment Tree** | O(n) | O(log n) | O(n) | Mutable arrays, complex queries |

> 💡 **For LeetCode 303: Prefix Sum is the clear winner**

---

## 🔍 **7. Why Prefix Sum Works Perfectly Here**

### 🎯 **The Immutable Advantage**:
- Since the array never changes, **preprocessing is safe**
- One-time O(n) cost pays off with O(1) queries

### 📈 **Query Performance**:
- **10⁴ queries × O(1) = 10⁴ operations** (vs 10⁸ for naive)
- **10,000x faster** for worst case

### 🧠 **Mathematical Foundation**:
The prefix sum formula is derived from:
```
prefix[right+1] = nums[0] + ... + nums[right]
prefix[left] = nums[0] + ... + nums[left-1]
Subtracting: prefix[right+1] - prefix[left] = nums[left] + ... + nums[right]
```

This works even for edge cases:
- **Single element** (`left = right`): `prefix[left+1] - prefix[left] = nums[left]`
- **Full array** (`left = 0`): `prefix[right+1] - prefix[0] = prefix[right+1]`

---

## 🧪 **8. Test Cases Validation**

```java
// Example from problem
NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
assertEquals(1, numArray.sumRange(0, 2));   // -2 + 0 + 3 = 1
assertEquals(-1, numArray.sumRange(2, 5));  // 3 + (-5) + 2 + (-1) = -1
assertEquals(-3, numArray.sumRange(0, 5));  // Total sum = -3

// Edge cases
NumArray single = new NumArray(new int[]{5});
assertEquals(5, single.sumRange(0, 0));

NumArray negatives = new NumArray(new int[]{-1, -2, -3});
assertEquals(-6, negatives.sumRange(0, 2));
assertEquals(-2, negatives.sumRange(1, 1));
```

---

## 💡 **9. Connection to Broader Concepts**

### 🔗 **Prefix Sum Applications**:
- **LeetCode 1480**: Running Sum (simpler version)
- **LeetCode 560**: Subarray Sum Equals K (prefix sum + hashmap)
- **LeetCode 974**: Subarray Sums Divisible by K
- **Competitive Programming**: Standard technique for range queries

### 📊 **Real-World Use Cases**:
- **Database indexing**: Precomputed aggregates for fast queries
- **Financial systems**: Running totals, cumulative returns
- **Image processing**: Integral images for fast region sums
- **Statistics**: Cumulative distribution functions

---

## 🚀 **10. Final Implementation (Production Ready)**

```java
/**
 * Efficient range sum queries for immutable arrays using prefix sums.
 *
 * Time Complexity:
 * - Constructor: O(n)
 * - sumRange: O(1)
 *
 * Space Complexity: O(n)
 */
class NumArray {
    private final int[] prefix;

    public NumArray(int[] nums) {
        // Handle null input (defensive programming)
        if (nums == null) {
            this.prefix = new int[1];
            return;
        }

        this.prefix = new int[nums.length + 1];
        this.prefix[0] = 0;

        for (int i = 0; i < nums.length; i++) {
            this.prefix[i + 1] = this.prefix[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        // Input validation (optional, per constraints left <= right is guaranteed)
        if (left < 0 || right >= prefix.length - 1 || left > right) {
            throw new IllegalArgumentException("Invalid range");
        }
        return prefix[right + 1] - prefix[left];
    }
}
```

---

## 💎 **Key Takeaways**

1. **Immutable arrays** are perfect for **prefix sum preprocessing**
2. **O(1) queries** are achievable with **O(n) preprocessing**
3. **Prefix sum formula**: `sum[left...right] = prefix[right+1] - prefix[left]`
4. **Always consider query frequency** when choosing data structures
5. **This pattern is foundational** for many advanced algorithms

This problem teaches a crucial lesson: **preprocessing can transform inefficient repeated operations into lightning-fast queries**. Master this, and you'll solve range query problems with confidence! 🚀