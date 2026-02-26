package DSA.ch1_logic.Interviews.ArithmeticNumberTheory;
/**
 * @author onyxwizard
 * @date 30-12-2025
 */

public class ReverseDigit {
  int reverseDigits(int n) {
    int rev = 0;
    while (n > 0) {
      int rem = n % 10;
      rev = (rev * 10) + rem;
      n /= 10;
    }
    return rev;
  }
}