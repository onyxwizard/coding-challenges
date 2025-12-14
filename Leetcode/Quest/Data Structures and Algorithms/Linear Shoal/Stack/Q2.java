import java.util.*;

public class Q2 {
    public int evalRPN(String[] tokens) {
        Stack<Integer> ops = new Stack<>();  // remove early return

        for (String token : tokens) {
            // Better: use Set or direct switch
            if (token.equals("+") || token.equals("-") || 
                token.equals("*") || token.equals("/")) {
                
                int b = ops.pop();  // right operand
                int a = ops.pop();  // left operand
                int res = operation(a, b, token);
                ops.push(res);
                
            } else {
                ops.push(Integer.parseInt(token));
            }
        }
        return ops.pop();  // ✅ always return top of stack
    }

    private int operation(int a, int b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b;  // truncates toward 0
            default: throw new IllegalArgumentException("Unknown op: " + op);
        }
    }
}