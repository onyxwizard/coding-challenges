import java.util.Stack;

public class Q1 {

  public int[] finalPrices(int[] prices) {
    Stack<Integer> stack = new Stack<>();

    for (int i = prices.length - 1; i > -1; i--) {
      stack.add(i);
    }

    for (int i = 0; i < prices.length; i++) {
      int n = prices[i];
      if (!stack.isEmpty()) {
        while (!stack.isEmpty() && prices[stack.peek()] > n && stack.peek() <= i) {
          stack.pop();
        }
        int sub = !stack.isEmpty() ? prices[stack.peek()] : 0;
        prices[i] = prices[i] - sub;
      }
    }

    return prices;
  }

}