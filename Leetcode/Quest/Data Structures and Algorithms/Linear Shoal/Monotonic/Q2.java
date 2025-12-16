import java.util.Stack;

public class Q2 {
  public int[] dailyTemperatures(int[] temperatures) {
    Stack<Integer> stackIdx = new Stack<>();
    int[] answer = new int[temperatures.length];

    for (int i = 0; i < temperatures.length; i++) {
      while (!stackIdx.isEmpty() && temperatures[i] > temperatures[stackIdx.peek()]) {
        int prevIdx = stackIdx.peek();
        answer[prevIdx] = i - prevIdx;
        stackIdx.pop();
      }
      stackIdx.push(i);
    }
    return answer;
  }
}