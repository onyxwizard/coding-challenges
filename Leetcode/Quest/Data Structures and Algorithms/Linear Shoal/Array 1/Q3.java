public class Q3 {
  public int findMaxConsecutiveOnes(int[] nums) {
    int count = 0, res = 0;
    for (int n : nums) {
        if (n == 1) {
            count++;
            if (count > res) res = count;
        } else {
            count = 0;
        }
    }
    return res;
  } 
}
