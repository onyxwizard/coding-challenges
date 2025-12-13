import java.util.Arrays;

public class Q2 {
  public int[] smallerNumbersThanCurrent(int[] nums) {
    // Step 1: count frequencies (0 to 100)
    int[] count = new int[101]; // index = number
    for (int num : nums) {
        count[num]++;
    }

    // Step 2: compute prefix: prefix[i] = count[0] + ... + count[i-1]
    int[] prefix = new int[102]; // prefix[0] = 0, prefix[101] for safety
    for (int i = 1; i <= 101; i++) {
        prefix[i] = prefix[i - 1] + count[i - 1];
    }

    // Step 3: answer[i] = prefix[nums[i]]
    int[] ans = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
        ans[i] = prefix[nums[i]];
    }
    return ans;
}
}
