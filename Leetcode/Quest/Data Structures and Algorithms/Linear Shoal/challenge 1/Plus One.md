# Problem: Plus One

## Problem Statement
You are given a large integer represented as an integer array `digits`, where each `digits[i]` is the `i-th` digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.

Your task is to increment this large integer by one and return the resulting array of digits.

## Examples

**Example 1:**
```
Input: digits = [1, 2, 3]
Output: [1, 2, 4]
Explanation: The array represents the integer 123.
Incrementing by one gives 123 + 1 = 124.
Thus, the result should be [1, 2, 4].
```

**Example 2:**
```
Input: digits = [4, 3, 2, 1]
Output: [4, 3, 2, 2]
Explanation: The array represents the integer 4321.
Incrementing by one gives 4321 + 1 = 4322.
Thus, the result should be [4, 3, 2, 2].
```

**Example 3:**
```
Input: digits = [9]
Output: [1, 0]
Explanation: The array represents the integer 9.
Incrementing by one gives 9 + 1 = 10.
Thus, the result should be [1, 0].
```

## Constraints
- `1 <= digits.length <= 100`
- `0 <= digits[i] <= 9`
- `digits` does not contain any leading 0's

## Approaches

### Approach 1: Simple Increment with Carry
The most straightforward approach is to:
1. Start from the last digit (least significant)
2. Add 1 to it
3. Handle any carry that results
4. If there's still a carry after processing all digits, create a new array with an extra digit at the beginning

**Time Complexity:** O(n)  
**Space Complexity:** O(n) in the worst case (when we need to add a new digit)

### Approach 2: Early Return Optimization
This approach is similar but includes an optimization:
- If the current digit is less than 9 after adding 1, we can return immediately since there's no carry to propagate
- This improves average-case performance

**Time Complexity:** O(n)  
**Space Complexity:** O(n) in the worst case

## Common Edge Cases
1. **Single digit 9:** Returns `[1, 0]`
2. **Multiple 9's at the end:** `[1, 9, 9]` becomes `[2, 0, 0]`
3. **All 9's:** `[9, 9, 9]` becomes `[1, 0, 0, 0]`
4. **No carry needed:** `[1, 2, 3]` becomes `[1, 2, 4]`

## Solution Implementation

```java
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        
        // Traverse from the last digit to the first
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0; // Set current digit to 0 and continue with carry
        }
        
        // If we reach here, all digits were 9
        int[] result = new int[n + 1];
        result[0] = 1;
        return result;
    }
}
```

## Alternative Implementation (Without Early Return)

```java
class Solution {
    public int[] plusOne(int[] digits) {
        int carry = 1;
        
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = digits[i] + carry;
            digits[i] = sum % 10;
            carry = sum / 10;
            
            if (carry == 0) {
                return digits;
            }
        }
        
        if (carry == 1) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            return result;
        }
        
        return digits;
    }
}
```

## Key Points to Remember
1. The array represents a number without leading zeros, but the result might have more digits than the input.
2. The operation should handle cases where adding 1 causes a chain of carries (like 999 + 1 = 1000).
3. The solution should work for arrays of up to 100 digits.
4. This problem is a simplified version of adding arbitrary large numbers, focusing only on adding 1.

## Related Problems
- Add Two Numbers (LeetCode #2)
- Add Strings (LeetCode #415)
- Add to Array-Form of Integer (LeetCode #989)