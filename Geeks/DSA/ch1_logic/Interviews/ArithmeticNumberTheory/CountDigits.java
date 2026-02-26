package DSA.ch1_logic.Interviews.ArithmeticNumberTheory;
/**
 * @author onyxwizard
 * @date 31-12-2025
 */

public class CountDigits {
  /**
   * [Approach 1] Iterative Solution 
   * @param n
   * @return Integer
   * Time : O(digit)
   * Space : O(1)
   */
  int countDigit(int n) {
    int digit = 0;
    while (n > 0) {
      n /= 10;
      digit++;
    }
    return digit;
  }

  /**
   * [Approach 2] Using log base 10 function
   * We can use log10(logarithm of base 10) to count the number of digits of positive numbers (logarithm is not defined for negative numbers).
   * Digit count of n = floor(log10(n) + 1) 
   * @param n
   * @return Integer
   * Time : O(1)
   * Space : O(1)
   */
  int countDigitLog(int n) {

    return (int) Math.floor(Math.log10(n) + 1);
  }
}
