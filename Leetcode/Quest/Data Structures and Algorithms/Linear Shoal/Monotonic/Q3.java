import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Map.Entry;

public class Q3 {
  public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        // Go one past the end (sentinel)
        for (int i = 0; i <= n; i++) {
            // Current height: 0 at i == n to flush stack
            int curHeight = (i == n) ? 0 : heights[i];

            // Pop all bars taller than current — their right boundary is now i
            while (!stack.isEmpty() && curHeight < heights[stack.peek()]) {
                int idx = stack.pop();               // bar being finalized
                int height = heights[idx];
                int leftBoundary = stack.isEmpty() ? -1 : stack.peek();
                int width = i - leftBoundary - 1;    // crucial!
                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }
}
