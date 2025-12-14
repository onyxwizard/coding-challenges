import java.util.*;

public class Q3 {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();  // ✅ Store IDs only (not full strings)
        int prevTime = 0;

        for (String s : logs) {
            int first = s.indexOf(':');
            int second = s.indexOf(':', first + 1);
            
            int id = Integer.parseInt(s.substring(0, first));
            String type = s.substring(first + 1, second);
            int time = Integer.parseInt(s.substring(second + 1));

            if (!stack.isEmpty()) {
                int runningId = stack.peek();
                if (type.equals("start")) {
                    // ⏸️ Pause current function: runs from prevTime to time-1
                    res[runningId] += time - prevTime;
                    stack.push(id);
                    prevTime = time;
                } else { // "end"
                    // ⏹️ Current function runs from prevTime to time (inclusive)
                    res[id] += time - prevTime + 1;
                    stack.pop();
                    prevTime = time + 1;  // next start at beginning of next timestamp
                }
            } else {
                stack.push(id);
                prevTime = time;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Q3 obj = new Q3();
        String[] s = {"0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"};
        List<String> logs = Arrays.asList(s);
        System.out.println(Arrays.toString(obj.exclusiveTime(1, logs))); // [8]
    }
}