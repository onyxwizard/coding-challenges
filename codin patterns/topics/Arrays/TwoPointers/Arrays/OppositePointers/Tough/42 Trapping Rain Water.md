Given `n` non-negative integers representing an elevation map where the width of each bar is `1`, compute how much water it can trap after raining.

**Example 1:**

![](https://assets.leetcode.com/uploads/2018/10/22/rainwatertrap.png)

**Input:** height = [0,1,0,2,1,0,1,3,2,1,2,1]
**Output:** 6
**Explanation:** The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

**Example 2:**

**Input:** height = [4,2,0,3,2,5]
**Output:** 9

**Constraints:**

- `n == height.length`
- `1 <= n <= 2 * 104`
- `0 <= height[i] <= 105`

### 🌧️ **Problem Goal (Restated Simply)**
You’re given a skyline made of vertical bars (like histogram columns). After it rains, water gets **trapped in the valleys** between taller bars.  
**Question**: How much total water is trapped?

> 💡 Key insight: Water can only be trapped **above a position** if there are **taller bars on both its left and right**.


### 🔍 Step 1: Understand What Determines Water at a Single Position

At any index `i`, the water height is limited by the **shorter** of:
- The tallest bar to the **left** of `i` (including `i`)
- The tallest bar to the **right** of `i` (including `i`)

So:  
> **Water above `i` = max(0, min(leftMax, rightMax) − height[i])**

If either side has no tall bar, water flows away → **no trapping**.

### 🧠 Step 2: Brute Force Idea (to build intuition)
For every position `i`:
- Scan left to find `leftMax`
- Scan right to find `rightMax`
- Compute water

✅ Correct, but **O(n²)** — too slow for large inputs.

We need to **avoid rescanning** for every `i`.

### 🚀 Step 3: Optimize with Precomputation (O(n) time, O(n) space)
- Make two passes:
  - Left-to-right → store `leftMax[i]` for every `i`
  - Right-to-left → store `rightMax[i]` for every `i`
- Then one final pass to compute total water.

✅ Efficient, but uses **extra arrays**.

Can we do **better in space**?


### 💡 Step 4: The Two-Pointer Insight (O(1) extra space)

Ask: **Do we really need to know both `leftMax` and `rightMax` for every `i` at the same time?**

**Observation**:  
If we know that the **global leftMax < global rightMax**, then for the **current left position**, the water level is **definitely capped by leftMax** — because even if there’s a huge wall on the far right, the left side is the bottleneck.

So:
- Use two pointers: `left` (start) and `right` (end)
- Track `leftMax` and `rightMax` as you go
- Always process the **side with the smaller max**:
  - If `leftMax < rightMax` → water at `left` depends **only on leftMax**
  - So compute water at `left`, then move `left` forward
  - Else, do the same for `right`

This way, you **never need to look ahead** — you always have enough info to decide.

### ✅ Step 5: Why This Works
- You **never overcount** or **miss water**
- You **process every index exactly once**
- You use **only 4–5 variables**: `left`, `right`, `leftMax`, `rightMax`, `water`
- **No extra arrays** → **O(1) auxiliary space** (output is just an integer)

This satisfies the [in-place algorithm](https://en.wikipedia.org/wiki/In-place_algorithm) practical definition:  
> *"Does not require extra space proportional to input size."*

### 🧪 Step 6: Validate with an Example
Input: `[0,1,0,2,1,0,1,3,2,1,2,1]`

- Start: `left=0 (0)`, `right=11 (1)` → `leftMax=0`, `rightMax=1`
- Since `leftMax < rightMax`, process `left`
- At `i=0`: `0 − 0 = 0` water
- Move `left` → now `height[1]=1` → update `leftMax=1`
- Continue... eventually, you’ll capture the 6 units correctly.

### 🎯 Final Logical Conclusion

> **To compute trapped rainwater optimally**:
> 1. Use **two pointers** from both ends.
> 2. Keep running **max heights** from left and right.
> 3. Always advance the pointer on the **side with the smaller max** — because that side **determines the water level** for its current position.
> 4. Accumulate water as you go.

This gives **O(n) time, O(1) extra space**, and **handles all edge cases**.

```java
package OppositePointers.Tough;
public class TappingRainWater {
    public static int trap(int[] height) {
        if (height == null || height.length <= 2) return 0;

        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }
        return water;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height)); // Output: 6

        height = new int[]{4,2,0,3,2,5};
        System.out.println(trap(height)); // Output: 9

        height = new int[]{2,0,2};
        System.out.println(trap(height)); // Output: 2

    }
}
```