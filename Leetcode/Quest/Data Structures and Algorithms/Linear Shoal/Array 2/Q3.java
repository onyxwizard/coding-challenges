import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q3 {
  public List<Integer> findDisappearedNumbers(int[] nums) {
    // First pass: mark visited indices
    for (int i = 0; i < nums.length; i++) {
        int idx = Math.abs(nums[i]) - 1;   // map value → index
        if (nums[idx] > 0) {               // avoid double-negation
            nums[idx] = -nums[idx];
        }
    }

    // Second pass: collect unmarked (positive) indices
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] > 0) {
            res.add(i + 1);  // missing number is i+1
        }
    }

    return res;
}
}
