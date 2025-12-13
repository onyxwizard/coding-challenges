import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Q1 {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int[] count = new int[n + 1]; // 1-indexed
        for (int num : nums)
            count[num]++;

        int dup = 0, miss = 0;
        for (int i = 1; i <= n; i++) {
            if (count[i] == 2)
                dup = i;
            if (count[i] == 0)
                miss = i;
        }
        return new int[] { dup, miss };
    }
}
