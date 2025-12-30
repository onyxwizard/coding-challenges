package DSA.ch1_logic.logic.basic;
/**
 * @author onyxwizard
 * @date 30-12-2025
 */

public class Arithmeticprogression {
  
  int nthTermOfAP(int a1, int a2, int n) {
    int d = a2 - a1;
    return a1 + ((n - 1) * d);
  }
}
