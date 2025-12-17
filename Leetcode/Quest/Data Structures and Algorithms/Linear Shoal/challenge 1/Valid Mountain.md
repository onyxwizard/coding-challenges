# 🏔️ Q2. Valid Mountain Array

## 📋 Problem Statement
Given an array of integers `arr`, return `true` if and only if it is a **valid mountain array**.

### 🏞️ What is a Mountain Array?
An array is considered a **mountain array** if and only if:

1. **Length ≥ 3** 📏
2. There exists some index `i` with `0 < i < arr.length - 1` such that:
   - **Strictly increasing** ↗️: `arr[0] < arr[1] < ... < arr[i-1] < arr[i]`
   - **Strictly decreasing** ↘️: `arr[i] > arr[i + 1] > ... > arr[arr.length - 1]`

In other words:
- The array must have a single peak 🌄
- No plateaus allowed (strictly increasing/decreasing)
- The peak cannot be at the first or last position

## 📊 Examples

**Example 1:**
```
Input: arr = [2, 1]
Output: false ❌
Reason: Length less than 3
```

**Example 2:**
```
Input: arr = [3, 5, 5]
Output: false ❌
Reason: Plateau at the top (5, 5) - not strictly increasing or decreasing
```

**Example 3:**
```
Input: arr = [0, 3, 2, 1]
Output: true ✅
Reason: Strictly increasing to 3, then strictly decreasing
Visual: 0 ↗️ 3 ↘️ 2 ↘️ 1
```

**More Examples:**
- `[1, 2, 3, 4, 3, 2, 1]` → `true` ✅ (perfect mountain)
- `[1, 2, 3, 4, 5]` → `false` ❌ (only increasing)
- `[5, 4, 3, 2, 1]` → `false` ❌ (only decreasing)
- `[1, 3, 2, 4, 1]` → `false` ❌ (multiple peaks)

## ⚙️ Constraints
- `1 <= arr.length <= 10⁴`
- `0 <= arr[i] <= 10⁴`

## 🎯 Approaches

### 🔍 Approach 1: Two-Pointer / Two-Pass
Traverse from both ends to find where the increasing and decreasing sequences stop.

**Algorithm:**
1. Start from left, find where increasing stops (peak candidate)
2. Start from right, find where decreasing stops (peak candidate)
3. Check if both pointers meet at the same index (valid peak)

```java
class Solution {
    public boolean validMountainArray(int[] arr) {
        int n = arr.length;
        if (n < 3) return false;
        
        int left = 0;
        // Walk up from left
        while (left + 1 < n && arr[left] < arr[left + 1]) {
            left++;
        }
        
        int right = n - 1;
        // Walk down from right
        while (right > 0 && arr[right] < arr[right - 1]) {
            right--;
        }
        
        // Check if they meet at the same point (peak)
        // And ensure peak is not at edges
        return left > 0 && right < n - 1 && left == right;
    }
}
```

### 🚶 Approach 2: Single Pass (Climbing the Mountain)
Simulate climbing up and then down the mountain in one pass.

**Algorithm:**
1. Climb up while strictly increasing
2. Check if we reached a valid peak (not at start or end)
3. Climb down while strictly decreasing
4. Check if we reached the end

```java
class Solution {
    public boolean validMountainArray(int[] arr) {
        int n = arr.length;
        if (n < 3) return false;
        
        int i = 0;
        
        // Walk up ↗️
        while (i + 1 < n && arr[i] < arr[i + 1]) {
            i++;
        }
        
        // Peak can't be first or last
        if (i == 0 || i == n - 1) {
            return false;
        }
        
        // Walk down ↘️
        while (i + 1 < n && arr[i] > arr[i + 1]) {
            i++;
        }
        
        // Should reach the end
        return i == n - 1;
    }
}
```

## ⏱️ Complexity Analysis

| Approach | Time Complexity | Space Complexity |
|----------|-----------------|------------------|
| Two-Pointer | O(n) | O(1) |
| Single Pass | O(n) | O(1) |

Both approaches are optimal with **O(n)** time and **O(1)** space.

## 🧪 Test Cases to Consider

```java
// Edge Cases:
[1, 2, 3, 2, 1]       // true ✅ - Perfect mountain
[1, 2, 3]             // false ❌ - No decreasing part
[3, 2, 1]             // false ❌ - No increasing part
[1, 2, 2, 1]          // false ❌ - Plateau in increasing part
[1, 2, 1, 2]          // false ❌ - Multiple peaks
[0, 1, 2, 1, 0]       // true ✅ - Symmetric mountain
[1, 2]                // false ❌ - Too short
[1]                   // false ❌ - Too short
[1, 2, 3, 4, 5, 4]    // true ✅ - Valid mountain
```

## 🎨 Visualization

### Valid Mountain: `[0, 2, 3, 2, 1]`
```
    3 ↗️ ↘️
  2 ↗️     ↘️ 2
0 ↗️         ↘️ 1
```

### Invalid (Plateau): `[1, 2, 2, 1]`
```
  2 ↗️ ➡️ ↘️
1 ↗️  2    ↘️ 1
```

### Invalid (Multiple Peaks): `[1, 2, 1, 2]`
```
  2 ↗️ ↘️ ↗️
1 ↗️   ↘️ 1 ↗️ 2
```

## 💡 Key Insights

1. **Strictly means no equals** ⚠️: The problem says "less than" and "greater than", not "less than or equal". So `[1, 2, 2, 1]` is invalid.

2. **One peak only** ⛰️: There should be exactly one point where the array transitions from increasing to decreasing.

3. **No flat terrain** 🏜️: The array cannot have any plateaus (consecutive equal elements).

4. **Both sides needed** ↔️: A valid mountain must have both increasing and decreasing parts.

## 🔗 Related Problems
- Find Peak Element (LeetCode #162)
- Peak Index in a Mountain Array (LeetCode #852)
- Longest Mountain in Array (LeetCode #845)

## 🚀 Quick Tips
- Always check the length first (must be ≥ 3)
- The peak cannot be at index 0 or last index
- Watch out for equal consecutive elements
- Consider edge cases with minimum and maximum values (0 and 10⁴)

## 📝 Solution (Most Efficient)

```java
class Solution {
    public boolean validMountainArray(int[] arr) {
        int n = arr.length;
        if (n < 3) return false;
        
        int i = 0;
        
        // Climb up
        while (i < n - 1 && arr[i] < arr[i + 1]) {
            i++;
        }
        
        // Check if peak is at start or end
        if (i == 0 || i == n - 1) {
            return false;
        }
        
        // Climb down
        while (i < n - 1 && arr[i] > arr[i + 1]) {
            i++;
        }
        
        return i == n - 1;
    }
}
```

This solution is **clean, efficient, and easy to understand**! It simply simulates walking up and down the mountain and checks if we reach the end properly. 🥾🏔️