package SlidingWindow.Arrays.Fixed.Hard.SlidingWindowMaximum;

import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0 || nums.length == 0) {
            return new int[0];
        }

        int n = nums.length;
        if (k == 1) return nums.clone(); // Optional fast path

        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>(k); // Optimized deque
        int idx = 0;

        for (int i = 0; i < n; i++) {
            // Remove indices outside current window [i - k + 1, i]
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // Maintain decreasing order: remove smaller or equal elements from back
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }

            deque.offerLast(i);

            // Record max once first full window is formed
            if (i >= k - 1) {
                result[idx++] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3))); // [3, 3, 5, 5, 6, 7]
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{3,1,2,3}, 2)));            // [3, 2, 3]
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1}, 1)));                   // [1]
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{}, 1)));                    // []
    }
}