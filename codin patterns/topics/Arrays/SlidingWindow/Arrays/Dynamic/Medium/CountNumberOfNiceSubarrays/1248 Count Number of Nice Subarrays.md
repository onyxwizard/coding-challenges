Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.

Return the number of nice sub-arrays.
Example 1:

Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].

Example 2:

Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There are no odd numbers in the array.

Example 3:

Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16

 

Constraints:

    1 <= nums.length <= 50000
    1 <= nums[i] <= 10^5
    1 <= k <= nums.length
# 📘 **1248. Count Number of Nice Subarrays – Complete Guide**



## 🎯 **Problem Summary**

Given an array `nums` and an integer `k`, count the number of **contiguous subarrays** that contain **exactly `k` odd numbers**.

✅ **Key**: Only **odd numbers** matter; even numbers are "neutral".



## 🔑 **Core Insight**

This problem is **equivalent to**:  
> **"Count subarrays with sum exactly equal to `k`"**  
> where we transform the array:
> - `1` if number is odd
> - `0` if number is even

So:  
`nums = [1,1,2,1,1]` → `binary = [1,1,0,1,1]`  
Now find subarrays with **sum = k = 3**.

This is a **classic prefix sum + hashmap** problem.

---

## 🧠 **Approach 1: Prefix Sum + HashMap (Most Efficient)**

### 💡 **Logic**:
- Let `prefix[i]` = number of odd numbers from index `0` to `i-1`
- For any subarray `[j, i]`, number of odds = `prefix[i+1] - prefix[j]`
- We want: `prefix[i+1] - prefix[j] = k` → `prefix[j] = prefix[i+1] - k`
- Use a **hashmap** to count how many times each prefix sum has occurred

### 🚀 **Optimization**: Use running sum instead of full prefix array



## 🔍 **How the HashMap Solution Works**

### **Step-by-Step for `nums = [1,1,2,1,1]`, `k = 3`**:

| Index | num | prefixSum | Need (prefixSum - k) | Count from map | Total Count | Map State |
|-------|-----|-----------|----------------------|----------------|-------------|-----------|
| -     | -   | 0         | -                    | -              | 0           | {0:1}     |
| 0     | 1   | 1         | -2                   | 0              | 0           | {0:1, 1:1}|
| 1     | 1   | 2         | -1                   | 0              | 0           | {0:1, 1:1, 2:1}|
| 2     | 2   | 2         | -1                   | 0              | 0           | {0:1, 1:1, 2:2}|
| 3     | 1   | 3         | 0                    | 1              | 1           | {0:1, 1:1, 2:2, 3:1}|
| 4     | 1   | 4         | 1                    | 1              | 2           | {0:1, 1:1, 2:2, 3:1, 4:1}|

✅ Final result = **2**

---

## 🔁 **Sliding Window Alternative Explained**

### **Key Insight**:
> `exactly(k) = atMost(k) - atMost(k-1)`

### **Why It Works**:
- `atMost(k)` counts all subarrays with **≤ k** odd numbers
- `atMost(k-1)` counts all subarrays with **≤ k-1** odd numbers  
- Subtracting gives subarrays with **exactly k** odd numbers

### **How `atMostKOdds` Works**:
- Use sliding window to maintain **≤ k** odd numbers
- For each valid window `[left, right]`, there are `(right - left + 1)` subarrays ending at `right`



## ⏱️ **Complexity Analysis**

| Approach | Time | Space |
|---------|------|-------|
| **HashMap (Prefix Sum)** | O(n) | O(n) |
| **Sliding Window** | O(n) | O(1) |

> 💡 **Sliding window is more space-efficient**, but **HashMap is more intuitive** for this exact-count problem.


## 🧪 **Why Example 3 Gives 16**

`nums = [2,2,2,1,2,2,1,2,2,2]`, `k = 2`

- Only two odd numbers at indices 3 and 6
- Any subarray that includes **both** odds and **any number of evens** on left/right is valid
- Left choices: 4 options (start at 0,1,2,3)
- Right choices: 4 options (end at 6,7,8,9)
- Total = 4 × 4 = **16**

✅ Both solutions correctly compute this.


---
## ✅ **Recommendation**

- **Use HashMap approach** for clarity and direct logic
- **Use Sliding Window approach** if memory is constrained
- Both are **optimal O(n)** solutions

This problem is a **classic example** of transforming a counting problem into a prefix sum problem! 🚀

```java
public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }
    
    private int atMost(int[] nums, int k) {
        if (k < 0) return 0;
        int left = 0, oddCount = 0, result = 0;
        
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] % 2 == 1) {
                oddCount++;
            }
            
            while (oddCount > k) {
                if (nums[left] % 2 == 1) {
                    oddCount--;
                }
                left++;
            }
            
            // All subarrays ending at 'right' with start from 'left' to 'right' are valid
            result += right - left + 1;
        }
        
        return result;
    }
```
