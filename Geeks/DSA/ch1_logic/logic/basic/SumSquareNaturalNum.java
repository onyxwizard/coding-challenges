package DSA.ch1_logic.logic.basic;

/**
 * @author onyxwizard
 * @date 30-12-2025
 */

public class SumSquareNaturalNum {

  public int summation(int n) {
    return ((n * (n + 1)) * (2 * n + 1)) / 6;
  }
  
  //Avoiding the overflow:
  public int summation1(int n) {
    return (n * (n + 1) / 2) * (2 * n + 1) / 3;
  }

}
