package DSA.ch1_logic.Interviews.ArithmeticNumberTheory;

import java.math.BigInteger;

/**
 * @author onyxwizard
 * @date 30-12-2025
 */

public class ModularExponentiation {
  /**
   * Your formula:
   * Even: x^n = (x^2)^(n/2) → Square the base, halve the exponent
   * Odd: x^n = x × x^(n-1) → Multiply result by base, reduce exponent by 1
   * 
   * @param x base
   * @param n power (x**n)
   * @param M Modulo ((x**(n)) % M)
   * @return Integer value
   */
  int powMod(int x, int n, int M) {
    if (M == 1)
      return 0;

    long res = 1L;
    long base = x % M;

    while (n > 0) {
      if ((n & 1) == 1) {
        res = (res * base) % M;
      }
      base = (base * base) % M;
      n >>= 1;
    }

    return (int) res;
  }

  int powModBig(int x, int n, int M) {
    BigInteger bigX = BigInteger.valueOf(x);
    BigInteger bigM = BigInteger.valueOf(M);
    return bigX.modPow(BigInteger.valueOf(n), bigM).intValue();
  }

  public static void main(String[] args) {
    ModularExponentiation md = new ModularExponentiation();
    int res = md.powMod(2, 1000, 10);
    System.out.println(res);

    res = (int) md.powMod(2, 1000, 10);
    System.out.println(res);
  }
}
