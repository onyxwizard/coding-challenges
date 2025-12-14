import java.util.*;

public class Q1 {
  public List<String> buildArray(int[] target, int n) {
    List<String> result = new ArrayList<>();
    Stack<Integer> stack = new Stack<>();

    int end = target[target.length-1];
    int pt = 0;
    int inc = 1;
    while (inc <= end) {
      if (!stack.isEmpty()) {
        while (!stack.isEmpty() && stack.peek() != target[pt]) {
          stack.pop();
          result.add("Pop");
          pt--;
        }
        pt++;
      }

      stack.add(inc);
      result.add("Push");
      inc++;
    }
    return result;
    }
}