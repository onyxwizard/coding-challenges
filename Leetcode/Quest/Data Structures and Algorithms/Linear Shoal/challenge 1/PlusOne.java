
public class PlusOne {
  public int[] plusOne(int[] digits) {
    int n = digits.length;

    // Traverse from the last digit to the first
    for (int i = n - 1; i >= 0; i--) {
      if (digits[i] < 9) {
        digits[i]++;
        return digits;
      }
      digits[i] = 0; // Set current digit to 0 and continue with carry
    }

    // If we reach here, all digits were 9
    int[] result = new int[n + 1];
    result[0] = 1;
    return result;
  }
}
