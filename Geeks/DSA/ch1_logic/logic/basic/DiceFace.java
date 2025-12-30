package DSA.ch1_logic.logic.basic;
/**
 * @author onyxwizard
 * @date 30-12-2025
 */

public class DiceFace {
  /**
   * In a normal 6-faced dice, 1 is opposite to 6,
   *  2 is opposite to 5, and 3 is opposite to 4.
   * Hence a normal if-else-if block can be placed
   * --------------------------------------------------------------------------------------------------------
   * The idea is based on the observation that the sum of two opposite sides of a cubical dice is equal to 7.
   * So, just subtract the given n from 7 and print the answer.
   * --------------------------------------------------------------------------------------------------------
   */
  int oppositeFaceOfDice(int n) {
    return 7 - n;
  }

}
