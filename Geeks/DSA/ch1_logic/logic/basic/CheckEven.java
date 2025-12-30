package DSA.ch1_logic.logic.basic;
/**
 * @author onyxwizard
 * @date 30-12-2025
 */

public class CheckEven {
  boolean isEven(int n) {
    return n % 2 == 0;
  }
  /**
   * Examples:
   * 15  ->               1 1 1 1
   *                   &  0 0 0 1
   *                      -------
   *                      0 0 0 1 , so this we can say it is an odd number.
   */

  boolean isEvenBitwise(int n) {
    return (n & 1) == 0;
  }
}
