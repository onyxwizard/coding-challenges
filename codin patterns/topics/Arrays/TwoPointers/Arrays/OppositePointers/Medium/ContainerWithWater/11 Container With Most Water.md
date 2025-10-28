You are given an integer array `height` of length `n`. There are `n` vertical lines drawn such that the two endpoints of the `ith` line are `(i, 0)` and `(i, height[i])`.

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return _the maximum amount of water a container can store_.

**Notice** that you may not slant the container.

**Example 1:**

![](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/07/17/question_11.jpg)

**Input:** height = [1,8,6,2,5,4,8,3,7]
**Output:** 49
**Explanation:** The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

**Example 2:**

**Input:** height = [1,1]
**Output:** 1

**Constraints:**

- `n == height.length`
- `2 <= n <= 105`
- `0 <= height[i] <= 104`


```java
package Arrays.OppositePointers.Medium.ContainerWithWater;

public class ContainerMostWater {
    public static int maxArea(int[] height) {
        int maxWater = 0, left = 0, right = height.length -1;

        while(left < right){
            int distance = right - left;
            int minHeight = Math.min(height[left],height[right]);
            maxWater = Math.max(maxWater, distance* minHeight);

            if(height[left] <= height[right]){
                left++;
            }else{
                right--;
            }

        }

        return maxWater;
    }

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        int res = maxArea(height);
        System.out.println(res);
    }
}

```