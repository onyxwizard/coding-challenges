Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).

Return the running sum of nums.

Example 1:

Input: nums = [1,2,3,4]
Output: [1,3,6,10]
Explanation: Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4].

Example 2:

Input: nums = [1,1,1,1,1]
Output: [1,2,3,4,5]
Explanation: Running sum is obtained as follows: [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1].

Example 3:

Input: nums = [3,1,2,10,1]
Output: [3,4,6,16,17]


Constraints:

    1 <= nums.length <= 1000
    -10^6 <= nums[i] <= 10^6

# 📘 **LeetCode 1480: Running Sum of 1D Array – Comprehensive Analysis**

---

## 📋 **1. Problem Statement**

Given an integer array `nums`, compute and return a new array `runningSum` such that:
```
runningSum[i] = nums[0] + nums[1] + ... + nums[i]
```
for every index `i` in the array.

In other words, each element in the result array should contain the **cumulative sum** of all elements from the beginning of the array up to and including the current position.

### 📌 **Formal Definition**
- **Input**: Array `nums` of length `n` where `1 ≤ n ≤ 1000`
- **Output**: Array `runningSum` of the same length where `runningSum[i] = Σ(nums[0..i])`
- **Constraint**: Must work for negative numbers, zeros, and positive numbers

---

## 🎯 **2. What the Problem Requires**

### ✅ **Core Requirements**
1. **Compute cumulative sums** efficiently
2. **Preserve original array order** (no sorting or reordering)
3. **Handle all integer values** within the given range
4. **Return result in the same format** (array of integers)

### 🧠 **Key Insight**
This problem tests understanding of:
- **Iterative computation**
- **State maintenance** (carrying forward previous results)
- **In-place vs. extra space trade-offs**

---

## ⚠️ **3. Edge Cases Analysis**

| Edge Case | Description | Expected Behavior |
|-----------|-------------|-------------------|
| **Single Element** | `nums = [5]` | `runningSum = [5]` |
| **All Zeros** | `nums = [0, 0, 0]` | `runningSum = [0, 0, 0]` |
| **All Negative** | `nums = [-1, -2, -3]` | `runningSum = [-1, -3, -6]` |
| **Mixed Signs** | `nums = [1, -1, 2, -2]` | `runningSum = [1, 0, 2, 0]` |
| **Large Positive** | `nums = [1000000, 1000000]` | `runningSum = [1000000, 2000000]` |
| **Large Negative** | `nums = [-1000000, -1000000]` | `runningSum = [-1000000, -2000000]` |
| **Alternating Large** | `nums = [1000000, -1000000, 1000000]` | `runningSum = [1000000, 0, 1000000]` |

> 💡 **Note**: Integer overflow is **not a concern** here because:
> - Maximum absolute sum = `1000 × 10^6 = 10^9`
> - Java `int` range = `-2,147,483,648` to `2,147,483,647`
> - `10^9 < 2.15 × 10^9` → safe from overflow

---

## 📏 **4. Constraint Analysis**

### Given Constraints:
- **Array length**: `1 ≤ nums.length ≤ 1000`
- **Element values**: `-10^6 ≤ nums[i] ≤ 10^6`

### 📊 **Implications**:
| Constraint | Impact on Solution |
|------------|-------------------|
| **Small array size (≤ 1000)** | Even O(n²) solutions would work, but O(n) is expected |
| **Large element values (±10⁶)** | Sum could be up to ±10⁹, but still fits in 32-bit integer |
| **Negative numbers allowed** | Solution must handle subtraction correctly |
| **No empty arrays** | No need to handle null/empty input edge case |

### 🎯 **Optimal Complexity Target**:
- **Time**: O(n) — must visit each element once
- **Space**: O(1) extra space (in-place) or O(n) (new array)

---

## 🧩 **5. Solution Approaches**

### 🔹 **Approach 1: In-Place Modification (Optimal Space)**

#### **Logic**:
- Modify the input array directly
- For each index `i` from 1 to n-1: `nums[i] += nums[i-1]`

#### **Code**:
```java
public int[] runningSum(int[] nums) {
    for (int i = 1; i < nums.length; i++) {
        nums[i] += nums[i - 1];
    }
    return nums;
}
```

#### **Complexity**:
- **Time**: O(n) — single pass through array
- **Space**: O(1) extra space — modifies input in-place

#### **Pros**:
- Most space-efficient
- Minimal memory allocation
- Cache-friendly (sequential access)

#### **Cons**:
- **Destroys original input** — not suitable if input must be preserved

---

### 🔹 **Approach 2: New Array Creation (Input Preservation)**

#### **Logic**:
- Create a new result array
- Copy first element, then compute cumulative sums

#### **Code**:
```java
public int[] runningSum(int[] nums) {
    int[] result = new int[nums.length];
    result[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
        result[i] = result[i - 1] + nums[i];
    }
    return result;
}
```

#### **Complexity**:
- **Time**: O(n) — single pass
- **Space**: O(n) — for the result array

#### **Pros**:
- **Preserves original input** — functional programming style
- Clear separation of input and output
- Easier to debug and test

#### **Cons**:
- Uses extra memory
- Slightly slower due to memory allocation

---

### 🔹 **Approach 3: Functional/Stream Approach (Java 8+)**

#### **Logic**:
- Use Java streams with accumulator

#### **Code**:
```java
public int[] runningSum(int[] nums) {
    int[] sum = {0};
    return Arrays.stream(nums)
                 .map(x -> sum[0] += x)
                 .toArray();
}
```

#### **Complexity**:
- **Time**: O(n)
- **Space**: O(n) for result + O(1) for accumulator

#### **Pros**:
- Concise and functional style
- No explicit loops

#### **Cons**:
- Less readable for beginners
- Slightly higher overhead
- Mutable array in lambda (not truly functional)

---

### 🔹 **Approach 4: Recursive Approach (Learning Only)**

#### **Logic**:
- Recursive function that builds result from end to start

#### **Code**:
```java
public int[] runningSum(int[] nums) {
    int[] result = new int[nums.length];
    runningSumRecursive(nums, result, nums.length - 1);
    return result;
}

private void runningSumRecursive(int[] nums, int[] result, int index) {
    if (index == 0) {
        result[0] = nums[0];
        return;
    }
    runningSumRecursive(nums, result, index - 1);
    result[index] = result[index - 1] + nums[index];
}
```

#### **Complexity**:
- **Time**: O(n)
- **Space**: O(n) for result + O(n) for recursion stack

#### **Pros**:
- Demonstrates recursive thinking
- Good for learning

#### **Cons**:
- **Stack overflow risk** for large arrays (though n ≤ 1000 is safe)
- Inefficient space usage
- Overkill for this problem

---

## 📊 **6. Approach Comparison**

| Approach | Time | Space | Input Preserved? | Readability | Use Case |
|----------|------|-------|------------------|-------------|----------|
| **In-Place** | O(n) | O(1) | ❌ No | ★★★★☆ | Interviews, memory-constrained |
| **New Array** | O(n) | O(n) | ✅ Yes | ★★★★★ | Production code, clarity |
| **Functional** | O(n) | O(n) | ✅ Yes | ★★★☆☆ | Functional programming contexts |
| **Recursive** | O(n) | O(n) | ✅ Yes | ★★☆☆☆ | Educational purposes only |

---

## 🎯 **7. Which Approach is Expected?**

### 📌 **In Technical Interviews**:
- **In-place solution** is preferred because:
  - Demonstrates space optimization awareness
  - Shows understanding of trade-offs
  - Most efficient for the given constraints

### 📌 **In Production Code**:
- **New array approach** is preferred because:
  - **Immutability** is a best practice
  - Prevents unintended side effects
  - Easier to reason about and test

### 📌 **For Learning**:
- Start with **new array approach** for clarity
- Then understand **in-place optimization**

---

## 🔍 **8. Common Mistakes & Pitfalls**

### ❌ **Mistake 1: Off-by-One Error**
```java
// Wrong: starts from index 0
for (int i = 0; i < nums.length; i++) {
    nums[i] += nums[i - 1]; // ArrayIndexOutOfBoundsException at i=0
}
```

### ❌ **Mistake 2: Not Handling Single Element**
```java
// Wrong: assumes array has at least 2 elements
int[] result = new int[nums.length];
result[0] = nums[0];
result[1] = nums[0] + nums[1]; // Fails for single element
```

### ❌ **Mistake 3: Integer Overflow Concern (Unnecessary)**
```java
// Over-engineering: long is not needed per constraints
long sum = 0;
for (int i = 0; i < nums.length; i++) {
    sum += nums[i];
    result[i] = (int) sum; // Unnecessary cast
}
```

### ✅ **Correct Pattern**:
```java
// Always start loop from index 1
for (int i = 1; i < nums.length; i++) {
    // Safe access to nums[i-1]
}
```

---

## 🧪 **9. Test Cases for Validation**

```java
// Basic functionality
assertArrayEquals(new int[]{1,3,6,10}, runningSum(new int[]{1,2,3,4}));

// Single element
assertArrayEquals(new int[]{5}, runningSum(new int[]{5}));

// Negative numbers
assertArrayEquals(new int[]{-1,-3,-6}, runningSum(new int[]{-1,-2,-3}));

// Mixed signs
assertArrayEquals(new int[]{1,0,2,0}, runningSum(new int[]{1,-1,2,-2}));

// Large numbers
assertArrayEquals(new int[]{1000000, 2000000}, runningSum(new int[]{1000000, 1000000}));

// All zeros
assertArrayEquals(new int[]{0,0,0}, runningSum(new int[]{0,0,0}));
```

---

## 🚀 **10. Connection to Advanced Concepts**

### 🔗 **Prefix Sum Foundation**
This problem is the **foundation** for:
- **Range sum queries** (LeetCode 303)
- **Subarray sum problems** (LeetCode 560)
- **Sliding window optimizations**
- **Difference array technique**

### 📈 **Real-World Applications**
- **Financial analysis**: cumulative returns, running totals
- **Data processing**: rolling averages, cumulative distributions
- **Game development**: score tracking, resource accumulation
- **Scientific computing**: integral approximations, cumulative measurements

---

## 💎 **Final Recommendation**

For **LeetCode 1480**, use the **in-place approach**:

```java
public int[] runningSum(int[] nums) {
    for (int i = 1; i < nums.length; i++) {
        nums[i] += nums[i - 1];
    }
    return nums;
}
```

**Why?**
- ✅ **Optimal time and space complexity**
- ✅ **Accepted by LeetCode** (input modification allowed)
- ✅ **Demonstrates algorithmic efficiency**
- ✅ **Foundation for more complex prefix sum problems**

This simple problem teaches a **fundamental pattern** that appears in **dozens of more advanced algorithms**. Master it, and you'll have a powerful tool for your coding arsenal! 🚀